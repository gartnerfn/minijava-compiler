package syntaxAnalyzer;

import semanticAnalyzerI.SymbolTable;
import semanticAnalyzerI.entities.*;
import semanticAnalyzerI.entities.Class;
import semanticAnalyzerI.types.*;
import semanticAnalyzerII.nodes.exp.*;
import semanticAnalyzerII.nodes.lit.*;
import semanticAnalyzerII.nodes.ref.*;
import semanticAnalyzerII.nodes.sent.*;
import src.Token;
import syntaxAnalyzer.exceptions.SyntaxException;
import lexicalAnalyzer.LexicalAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class SyntaxAnalyzer {
    private final LexicalAnalyzer lexicalAnalyzer;
    private Token currentToken;
    private final SymbolTable symbolTable = SymbolTable.getInstance();

    public SyntaxAnalyzer(LexicalAnalyzer lexicalAnalyzer){
        this.lexicalAnalyzer = lexicalAnalyzer;
        currentToken = lexicalAnalyzer.nextToken();
        inicial();
    }

    private void match(String token) {
        if(token.equals(currentToken.token()))
            currentToken = lexicalAnalyzer.nextToken();
        else
            throw new SyntaxException(token, currentToken.lexeme(), currentToken.lineNumber());
    }

    private boolean is(String expected) {
        return currentToken.token().equals(expected);
    }

    private boolean isOneOf(String... options) {
        for (String opt : options)
            if (currentToken.token().equals(opt))
                return true;

        return false;
    }

    private boolean isModificador() {
        return isOneOf("rw_abstract","rw_static","rw_final");
    }

    private boolean isMiembroVisibilidadStart() {
        return isOneOf("rw_public", "rw_private", "rw_void","rw_abstract","rw_static","rw_final","rw_boolean","rw_char","rw_int","classId");
    }

    private boolean isTipoStart() {
        return isOneOf("rw_boolean","rw_char","rw_int","classId");
    }

    private boolean isSentenciaStart() {
        return isOneOf(";", "rw_var", "rw_return", "rw_if", "rw_while", "rw_for", "{") || isExpresionStart();
    }

    private boolean isExpresionStart() {
        return isPrimitivo() || isOperadorUnario() || isPrimario();
    }

    private boolean isOperadorBinario() {
        return isOneOf("||","&&","==","!=","<",">","<=",">=","+","-","*","/","%");
    }

    private boolean isOperadorUnario() {
        return isOneOf("+","++","-","--","!");
    }

    private boolean isPrimitivo() {
        return isOneOf("rw_true","rw_false","intLiteral","charLiteral","rw_null");
    }

    private boolean isPrimario(){
        return isOneOf("rw_this","stringLiteral","methodVarId","classId","rw_new","(");
    }

    private void inicial() {
        listaClasesOInterfaces();
        match("eof");
    }

    private void listaClasesOInterfaces() {
        if(isOneOf("rw_class", "rw_abstract", "rw_final", "rw_static")){
            String optionalModifer = modificadorOpcional();
            clase(optionalModifer);
            listaClasesOInterfaces();
        }  else if(is("rw_interface")){
            interfaz();
            listaClasesOInterfaces();
        }
    }

    private void clase(String optionalModifier) {
        match("rw_class");
        symbolTable.currentEntity = new Class(currentToken, optionalModifier);
        match("classId");
        genericidadOpcional();
        herenciaOImplementacionOpcional();
        match("{");
        listaMiembros();
        match("}");
        symbolTable.addClass((Class) symbolTable.currentEntity);
    }

    private void interfaz() {
        match("rw_interface");
        symbolTable.currentEntity = new Interface(currentToken);
        match("classId");
        genericidadOpcional();
        herenciaOpcional();
        match("{");
        listaMiembrosInterfaz();
        match("}");
        symbolTable.addInterface((Interface) symbolTable.currentEntity);
    }

    private void genericidadOpcional(){
        if(is("<")){
            match("<");
            match("classId");
            match(">");
        }
    }

    private void diamanteOGenericidadOpcional(){
        if(is("<")){
            match("<");
            diamanteOGenericidadOpcionalPrima();
            match(">");
        }
    }

    private void diamanteOGenericidadOpcionalPrima(){
        if(is("classId"))
            match("classId");
    }

    private String modificadorOpcional() {
        if (isModificador())
            return modificador();
        else return "";
    }

    private Token herencia(){
        match("rw_extends");
        Token name = currentToken;
        match("classId");
        genericidadOpcional();
        return name;
    }

    private Token implementacion(){
        match("rw_implements");
        Token name = currentToken;
        match("classId");
        genericidadOpcional();
        return name;
    }

    private void herenciaOpcional() {
        if (is("rw_extends"))
            symbolTable.currentEntity.inheritsFrom(herencia());
    }

    private void herenciaOImplementacionOpcional(){
        if (is("rw_extends")) {
            symbolTable.currentEntity.inheritsFrom(herencia());
        }else if (is("rw_implements")){
            symbolTable.currentEntity.implementsFrom(implementacion());
        }
    }

    private void listaMiembros() {
        if (isMiembroVisibilidadStart()) {
            miembroVisibilidad();
            listaMiembros();
        }
    }

    private void listaMiembrosInterfaz(){
        if(isMiembroVisibilidadStart()){
            miembroVisibilidadInterfaz();
            listaMiembrosInterfaz();
        }
    }

    private void miembroVisibilidad(){
        String visibilityModifier = visibilidadOpcional();
        miembro(visibilityModifier);
    }

    private String visibilidadOpcional(){
        if(isOneOf("rw_public", "rw_private")){
            Token visibilityModifier = currentToken;
            match(currentToken.token());
            return visibilityModifier.lexeme();
        } else return "";
    }

    private void miembro(String visibilityModifier) {
        if (is("classId")) {
            Token classId = currentToken;
            match("classId");
            metodoOAtributoOConstructor(classId, visibilityModifier);
        } else if(isOneOf("rw_boolean", "rw_char", "rw_int")) {
            Type primitiveType = tipoPrimitivo();
            Token methodVarId = currentToken;
            match("methodVarId");
            metodoOAtributo(primitiveType, methodVarId, visibilityModifier);
        } else if (isOneOf("rw_abstract", "rw_static", "rw_final")) {
            String typeModifier = modificador();
            Type returnType = tipoMetodo();
            declaracionMetodo(returnType, typeModifier, visibilityModifier);
        } else if(is("rw_void")) {
            Type returnType = voidType();
            declaracionMetodo(returnType, "", visibilityModifier);
        }
        else throw new SyntaxException("Class identifier, boolean, char, int, abstract, static, final or void", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void miembroVisibilidadInterfaz(){
        String visibilityModifier = visibilidadOpcional();
        miembroInterfaz(visibilityModifier);
    }

    private void miembroInterfaz(String visibilityModifier) {
        if (is("classId")) {
            Token classId = currentToken;
            match("classId");
            Type referenceType = new ReferenceType(classId);
            genericidadOpcional();
            Token methodVarId = currentToken;
            match("methodVarId");
            metodoOAtributo(referenceType, methodVarId, visibilityModifier);
        } else if(isOneOf("rw_boolean", "rw_char", "rw_int")) {
            Type primitiveType = tipoPrimitivo();
            Token methodVarId = currentToken;
            match("methodVarId");
            metodoOAtributo(primitiveType, methodVarId, visibilityModifier);
        } else if (isOneOf("rw_abstract", "rw_static", "rw_final")) {
            String typeModifier = modificador();
            Type returnType = tipoMetodo();
            declaracionMetodo(returnType, typeModifier, visibilityModifier);
        } else if(is("rw_void")) {
            Type returnType = voidType();
            declaracionMetodo(returnType, "", visibilityModifier);
        }
        else throw new SyntaxException("Class identifier, boolean, char, int, abstract, static, final or void", currentToken.lexeme(), currentToken.lineNumber());
    }

    private String modificador(){
        Token modifier = currentToken;
        match(currentToken.token());
        return modifier.lexeme();
    }

    private void declaracionMetodo(Type returnType, String typeModifier, String visibilityModifier) {
        Token methodVarId = currentToken;
        match("methodVarId");
        Method method = new Method(methodVarId, returnType, typeModifier, visibilityModifier, symbolTable.currentEntity);
        symbolTable.currentRoutine = method;
        argsFormales();
        NodoBloque block = bloqueOpcional();
        method.addBlock(block);
        symbolTable.currentEntity.addMethod((Method) symbolTable.currentRoutine);
    }

    private void metodoOAtributo(Type type, Token methodVarId, String visibilityModifier) {
        if(is("(")){
            Method method = new Method(methodVarId, type, "", visibilityModifier, symbolTable.currentEntity);
            symbolTable.currentRoutine = method;
            argsFormales();
            NodoBloque block = bloqueOpcional();
            method.addBlock(block);
            symbolTable.currentEntity.addMethod((Method) symbolTable.currentRoutine);
        } else if(is("=")){
            NodoExpComp init =  inicializacionAtributo();
            symbolTable.currentEntity.addAttribute(new Attribute(methodVarId, type, visibilityModifier, init, symbolTable.currentEntity));
        } else if(is(";")){
            match(";");
            symbolTable.currentEntity.addAttribute(new Attribute(methodVarId, type, visibilityModifier, new NodoExpVacia(), symbolTable.currentEntity));
        } else throw new SyntaxException("(, = or ;", currentToken.lexeme(), currentToken.lineNumber());
    }

    private NodoExpComp inicializacionAtributo(){
        operadorAsignacion();
        NodoExpComp init = expresionCompuesta();
        match(";");
        return init;
    }

    private void constructor(Token classId, String visibilityModifier){
        Constructor constructor = new Constructor(classId, visibilityModifier);
        symbolTable.currentRoutine = constructor;
        argsFormales();
        NodoBloque block = bloque();
        constructor.addBlock(block);
        symbolTable.currentEntity.addConstructor((Constructor) symbolTable.currentRoutine);
    }

    private void metodoOAtributoOConstructor(Token classId, String visibilityModifier){
        if(isOneOf("<","methodVarId")){
            genericidadOpcional();
            Token methodVarId = currentToken;
            match("methodVarId");
            Type referenceType = new ReferenceType(classId);
            metodoOAtributo(referenceType, methodVarId, visibilityModifier);
        } else if(is("(")){
            constructor(classId, visibilityModifier);
        } else throw new SyntaxException("Method identifier, attribute identifier or (", currentToken.lexeme(), currentToken.lineNumber());
    }

    private Type tipoMetodo() {
        if (isOneOf("rw_boolean", "rw_char", "rw_int", "classId"))
            return tipo();
        else if (is("rw_void")){
            return voidType();
        }
        else throw new SyntaxException("boolean, char, int, class identifier or void", currentToken.lexeme(), currentToken.lineNumber());
    }

    private Type voidType(){
        Type voidType = new VoidType(currentToken.lineNumber());
        match("rw_void");
        return voidType;
    }

    private Type tipo() {
        if (isOneOf("rw_boolean", "rw_char", "rw_int"))
            return tipoPrimitivo();
        else if(is("classId")){
            Type referenceType = new ReferenceType(currentToken);
            match("classId");
            genericidadOpcional();
            return referenceType;
        } else
            throw new SyntaxException("boolean, char, int or class identifier", currentToken.lexeme(), currentToken.lineNumber());
    }

    private Type tipoPrimitivo() {
        if(is("rw_boolean")){
            match("rw_boolean");
            return new BooleanType(currentToken.lineNumber());
        } else if(is("rw_char")){
            match("rw_char");
            return new CharType(currentToken.lineNumber());
        } else if(is("rw_int")){
            match("rw_int");
            return new IntType(currentToken.lineNumber());
        } else {
            throw new SyntaxException("boolean, char or int expected", currentToken.lexeme(), currentToken.lineNumber());
        }
    }

    private void argsFormales() {
        match("(");
        listaArgsFormalesOpcional();
        match(")");
    }

    private void listaArgsFormalesOpcional() {
        if (isTipoStart())
            listaArgsFormales();
    }

    private void listaArgsFormales() {
        argFormal();
        listaArgsFormalesPrima();
    }

    private void listaArgsFormalesPrima() {
        if (is(",")) {
            match(",");
            argFormal();
            listaArgsFormalesPrima();
        }
    }

    private void argFormal() {
        Type type = tipo();
        Token methodVarId = currentToken;
        match("methodVarId");
        symbolTable.currentRoutine.addParameter(new Parameter(methodVarId, type));
    }

    private NodoBloque bloqueOpcional() {
        if (is("{")){
            return bloque();
        } else if(is(";")){
            match(";");
            return new NodoBloqueNulo();
        } else throw new SyntaxException("{ or ;", currentToken.lexeme(), currentToken.lineNumber());
    }

    private NodoBloque bloque() {
        match("{");
        NodoBloque block = new NodoBloque();
        block.sentences = listaSentencias(block);
        match("}");
        return block;
    }

    private List<NodoSentencia> listaSentencias(NodoBloque block) {
        List<NodoSentencia> sentenceList = new ArrayList<>();
        sentenceList.add(new NodoSentenciaVacia());

        if (isSentenciaStart()) {
            NodoSentencia sentence = sentencia(block);
            sentenceList = listaSentencias(block);
            sentenceList.addFirst(sentence);
        }

        return sentenceList;
    }

    private NodoSentencia sentencia(NodoBloque block) {
        if (is(";")) {
            match(";");
            return new NodoSentenciaVacia();
        } else if (is("rw_var")) {
            NodoSentencia varLocal = varLocal();
            match(";");
            return varLocal;
        } else if (is("rw_return")) {
            NodoSentencia ret = returnSentence();
            match(";");
            return ret;
        } else if (is("rw_if")) {
            return ifSentence(block);
        } else if (is("rw_while")) {
            return whileSentence(block);
        } else if(is("rw_for")){
            forSentence(block);
            return new NodoSentenciaVacia();
        } else if (is("{")) {
            return bloque();
        } else if(isExpresionStart()){
            Token tkn = currentToken;
            NodoExp exp = expresion();
            match(";");
            return new NodoSentenciaConExp(tkn, exp);
        } else {
            throw new SyntaxException("Valid sentence", currentToken.lexeme(), currentToken.lineNumber());
        }
    }

    private NodoVarLocal varLocal() {
        match("rw_var");
        Token tkn = currentToken;
        match("methodVarId");
        match("=");
        NodoExp exp = expresionCompuesta();
        return new NodoVarLocal(tkn, exp);
    }

    private NodoSentencia returnSentence() {
        Token tkn = currentToken;
        match("rw_return");
        NodoExp returnExp = expresionOpcional();
        return new NodoReturn(tkn, returnExp);
    }

    private NodoExp expresionOpcional() {
        if (isExpresionStart())
            return expresion();
        else
            return new NodoExpVacia();
    }

    private NodoIf ifSentence(NodoBloque block) {
        Token tkn = currentToken;
        match("rw_if");
        match("(");
        NodoExp cond = expresion();
        match(")");
        NodoSentencia thenBody = sentencia(block);
        NodoSentencia elseBody = ifPrima(block);
        return new NodoIf(tkn, cond, thenBody, elseBody);
    }

    private NodoSentencia ifPrima(NodoBloque block) {
        if (is("rw_else")) {
            match("rw_else");
            return sentencia(block);
        } else {
            return new NodoSentenciaVacia();
        }
    }

    private NodoWhile whileSentence(NodoBloque block) {
        Token tkn = currentToken;
        match("rw_while");
        match("(");
        NodoExp cond = expresion();
        match(")");
        NodoSentencia body = sentencia(block);
        return new NodoWhile(tkn, cond, body);
    }

    private void forSentence(NodoBloque block){
        match("rw_for");
        match("(");
        forPrima();
        match(")");
        sentencia(block);
    }

    private void forPrima(){
        if(is("rw_var")){
            match("rw_var");
            match("methodVarId");
            forSec();
        } else if(isExpresionStart()){
            expresion();
            forClasico();
        } else
            throw new SyntaxException("var or expression", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void forSec(){
        if(is("=")){
            match("=");
            expresionCompuesta();
            forClasico();
        } else if(is(":")){
            match(":");
            expresion();
        } else
            throw new SyntaxException("= or :", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void forClasico(){
        match(";");
        expresion();
        match(";");
        expresion();
    }

    private NodoExp expresion() {
        NodoExpComp leftSide = expresionCompuesta();
        return expresionPrima(leftSide);
    }

    private NodoExp expresionPrima(NodoExpComp leftSide) {
        if (is("=")) {
            Token tkn = currentToken;
            operadorAsignacion();
            NodoExpComp rightSide = expresionCompuesta();
            return new NodoAsignacion(tkn, leftSide, rightSide);
        } else {
            return leftSide;
        }
    }

    private void operadorAsignacion() {
        match(currentToken.token());
    }

    private NodoExpComp expresionCompuesta() {
        NodoExpBasica leftSide = expresionBasica();
        ternariaOpcional();
        return expresionCompuestaPrima(leftSide);
    }

    private NodoExpComp expresionCompuestaPrima(NodoExpComp leftSide) {
        if (isOperadorBinario()) {
            Token operator = currentToken;
            operadorBinario();
            NodoExpBasica rightSide = expresionBasica();
            ternariaOpcional();
            NodoExpComp nodoExpComp = new NodoExpComp(leftSide, operator, rightSide);
            return expresionCompuestaPrima(nodoExpComp);
        } else {
            return leftSide;
        }
    }

    private void ternariaOpcional(){
        if(is("?")){
            match("?");
            expresionCompuesta();
            match(":");
            expresionCompuesta();
        }
    }

    private void operadorBinario() {
        match(currentToken.token());
    }

    private NodoExpBasica expresionBasica() {
        if (isOperadorUnario()) {
            Token unaryOperator = operadorUnario();
            NodoOperando operand = operando();
            return new NodoExpBasica(unaryOperator, operand);
        } else if(isPrimitivo() || isPrimario()){
            return operando();
        } else
            throw new SyntaxException("Valid expression", currentToken.lexeme(), currentToken.lineNumber());
    }

    private Token operadorUnario() {
        Token unaryOperator = currentToken;
        match(currentToken.token());
        return unaryOperator;
    }

    private NodoOperando operando() {
        if (isPrimitivo()){
            return primitivo();
        } else if(isPrimario()){
            return referencia();
        } else
            throw new SyntaxException("Valid operand", currentToken.lexeme(), currentToken.lineNumber());
    }

    private NodoLit primitivo() {
        if(isOneOf("rw_true", "rw_false")){
            NodoLit lit = new NodoBooleanLit(currentToken);
            match(currentToken.token());
            return lit;
        } else if(is("intLiteral")){
            NodoLit lit = new NodoIntLit(currentToken);
            match("intLiteral");
            return lit;
        } else if(is("charLiteral")){
            NodoLit lit = new NodoCharLit(currentToken);
            match("charLiteral");
            return lit;
        } else if(is("rw_null")){
            NodoLit lit = new NodoNullLit(currentToken);
            match("rw_null");
            return lit;
        } else throw new SyntaxException("Valid primitive literal", currentToken.lexeme(), currentToken.lineNumber());
    }

    private NodoOperando referencia() {
        NodoOperando prim = primario();
        referenciaPrima();
        return prim;
    }

    private void referenciaPrima() {
        if (is(".")) {
            match(".");
            match("methodVarId");
            referenciaSec();
            referenciaPrima();
        }
    }

    private void referenciaSec() {
        if (is("(")) {
            argsActuales();
        }
    }

    private NodoOperando primario() {
        if (is("rw_this")){
            match("rw_this");
            return new NodoReferenciaThis();
        } else if (is("stringLiteral")){
            NodoLit lit = new NodoStringLit(currentToken);
            match("stringLiteral");
            return lit;
        } else if (is("rw_new")){
            llamadaConstructor();
            return new NodoLlamadaConstructor();
        } else if (is("(")){
            expresionParentizada();
            return new NodoExpresionParentizada();
        } else if (is("methodVarId")) {
            Token methodVarId = currentToken;
            match("methodVarId");
            return primarioPrima(methodVarId);
        } else if (is("classId")){
            llamadaMetodoEstatico();
            return new NodoLlamadaMetodoEstatico();
        } else
            throw new SyntaxException("this, new, string literal, method identifier, variable identifier or class identifier", currentToken.lexeme(), currentToken.lineNumber());
    }

    private NodoReferencia primarioPrima(Token methodVarId){
        if (is("(")) {
            List<NodoExp> args = argsActuales();
            return new NodoLlamadaMetodo(methodVarId, args);
        } else {
            return new NodoLlamadaVar(methodVarId);
        }
    }

    private void llamadaConstructor() {
        match("rw_new");
        match("classId");
        diamanteOGenericidadOpcional();
        argsActuales();
    }

    private void expresionParentizada() {
        match("(");
        expresion();
        match(")");
    }

    private void llamadaMetodoEstatico() {
        match("classId");
        match(".");
        match("methodVarId");
        argsActuales();
    }

    private List<NodoExp> argsActuales() {
        match("(");
        List<NodoExp> args =  listaExpsOpcional();
        match(")");
        return args;
    }

    private List<NodoExp> listaExpsOpcional() {
        if (isExpresionStart())
            return listaExps();
        else {
            return new ArrayList<>();
        }
    }

    private List<NodoExp> listaExps() {
        NodoExp arg = expresion();
        List<NodoExp> args = listaExpsPrima();
        args.addFirst(arg);
        return args;
    }

    private List<NodoExp> listaExpsPrima() {
        if (is(",")) {
            match(",");
            return listaExps();
        } else {
            return new ArrayList<>();
        }
    }
}
