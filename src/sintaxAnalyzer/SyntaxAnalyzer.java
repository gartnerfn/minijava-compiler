package sintaxAnalyzer;

import src.Token;
import sintaxAnalyzer.exceptions.SyntaxException;
import lexicalAnalyzer.LexicalAnalyzer;

public class SyntaxAnalyzer {
    private final LexicalAnalyzer lexicalAnalyzer;
    private Token currentToken;

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
        modificadorOpcional();
        claseOInterfaz();
    }

    private void claseOInterfaz(){
        if(is("rw_class")){
            clase();
            listaClasesOInterfaces();
        } else if(is("rw_interface")){
            interfaz();
            listaClasesOInterfaces();
        }
    }

    private void clase() {
        match("rw_class");
        match("classId");
        genericidadOpcional();
        herenciaOImplementacionOpcional();
        match("{");
        listaMiembros();
        match("}");
    }

    private void interfaz(){
        match("rw_interface");
        match("classId");
        genericidadOpcional();
        herenciaOpcional();
        match("{");
        listaMiembrosInterfaz();
        match("}");
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

    private void modificadorOpcional() {
        if (isModificador())
            modificador();
    }

    private void herencia(){
        match("rw_extends");
        match("classId");
        genericidadOpcional();
    }

    private void herenciaOpcional() {
        if (is("rw_extends")) {
            herencia();
        }
    }

    private void herenciaOImplementacionOpcional(){
        if (is("rw_extends")) {
            herencia();
        }else if (is("rw_implements")){
            match("rw_implements");
            match("classId");
            genericidadOpcional();
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
        visibilidadOpcional();
        miembro();
    }

    private void visibilidadOpcional(){
        if(isOneOf("rw_public", "rw_private"))
            match(currentToken.token());
    }

    private void miembro() {
        if (is("classId")) {
            match("classId");
            metodoOAtributoOConstructor();
        } else if(isOneOf("rw_boolean", "rw_char", "rw_int")) {
            tipoPrimitivo();
            match("methodVarId");
            metodoOAtributo();
        } else if (isOneOf("rw_abstract", "rw_static", "rw_final")) {
            modificador();
            tipoMetodo();
            declaracionMetodo();
        } else if(is("rw_void")) {
            match("rw_void");
            declaracionMetodo();
        }
        else throw new SyntaxException("Class identifier, boolean, char, int, abstract, static, final or void", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void miembroVisibilidadInterfaz(){
        visibilidadOpcional();
        miembroInterfaz();
    }

    private void miembroInterfaz() {
        if (is("classId")) {
            match("classId");
            genericidadOpcional();
            match("methodVarId");
            metodoOAtributo();
        } else if(isOneOf("rw_boolean", "rw_char", "rw_int")) {
            tipoPrimitivo();
            match("methodVarId");
            metodoOAtributo();
        } else if (isOneOf("rw_abstract", "rw_static", "rw_final")) {
            modificador();
            tipoMetodo();
            declaracionMetodo();
        } else if(is("rw_void")) {
            match("rw_void");
            declaracionMetodo();
        }
        else throw new SyntaxException("Class identifier, boolean, char, int, abstract, static, final or void", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void modificador(){
        match(currentToken.token());
    }

    private void declaracionMetodo(){
        match("methodVarId");
        argsFormales();
        bloqueOpcional();
    }

    private void metodoOAtributo() {
        if(is("(")){
            argsFormales();
            bloqueOpcional();
        } else if(is("=")){
            inicializacionAtributo();
        } else if(is(";")){
            match(";");
        } else throw new SyntaxException("( or =", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void inicializacionAtributo(){
        operadorAsignacion();
        expresionCompuesta();
        match(";");
    }

    private void constructor() {
        argsFormales();
        bloque();
    }

    private void metodoOAtributoOConstructor(){
        if(isOneOf("<","methodVarId")){
            genericidadOpcional();
            match("methodVarId");
            metodoOAtributo();
        } else if(is("(")){
            constructor();
        } else throw new SyntaxException("Method identifier, attribute identifier or (", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void tipoMetodo() {
        if (isOneOf("rw_boolean", "rw_char", "rw_int", "classId"))
            tipo();
        else if (is("rw_void"))
            match("rw_void");
        else throw new SyntaxException("boolean, char, int, class identifier or void", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void tipo() {
        if (isOneOf("rw_boolean", "rw_char", "rw_int"))
            tipoPrimitivo();
        else if(is("classId")){
            match("classId");
            genericidadOpcional();
        } else
            throw new SyntaxException("boolean, char, int or class identifier", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void tipoPrimitivo() {
        match(currentToken.token());
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
        tipo();
        match("methodVarId");
    }

    private void bloqueOpcional() {
        if (is("{"))
            bloque();
        else if(is(";"))
            match(";");
        else throw new SyntaxException("{ or ;", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void bloque() {
        match("{");
        listaSentencias();
        match("}");
    }

    private void listaSentencias() {
        if (isSentenciaStart()) {
            sentencia();
            listaSentencias();
        }
    }

    private void sentencia() {
        if (is(";")) {
            match(";");
        } else if (is("rw_var")) {
            varLocal();
            match(";");
        } else if (is("rw_return")) {
            returnSentence();
            match(";");
        } else if (is("rw_if")) {
            ifSentence();
        } else if (is("rw_while")) {
            whileSentence();
        } else if(is("rw_for")){
            forSentence();
        } else if (is("{")) {
            bloque();
        } else if(isExpresionStart()){
            expresion();
            match(";");
        } else
            throw new SyntaxException("Valid sentence", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void varLocal() {
        match("rw_var");
        match("methodVarId");
        match("=");
        expresionCompuesta();
    }

    private void returnSentence() {
        match("rw_return");
        expresionOpcional();
    }

    private void expresionOpcional() {
        if (isExpresionStart())
            expresion();
    }

    private void ifSentence() {
        match("rw_if");
        match("(");
        expresion();
        match(")");
        sentencia();
        ifPrima();
    }

    private void ifPrima() {
        if (is("rw_else")) {
            match("rw_else");
            sentencia();
        }
    }

    private void whileSentence() {
        match("rw_while");
        match("(");
        expresion();
        match(")");
        sentencia();
    }

    private void forSentence(){
        match("rw_for");
        match("(");
        forPrima();
        match(")");
        sentencia();
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

    private void expresion() {
        expresionCompuesta();
        expresionPrima();
    }

    private void expresionPrima() {
        if (is("=")) {
            operadorAsignacion();
            expresionCompuesta();
        }
    }

    private void operadorAsignacion() {
        match(currentToken.token());
    }

    private void expresionCompuesta() {
        expresionBasica();
        ternariaOpcional();
        expresionCompuestaPrima();
    }

    private void expresionCompuestaPrima() {
        if (isOperadorBinario()) {
            operadorBinario();
            expresionBasica();
            ternariaOpcional();
            expresionCompuestaPrima();
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

    private void expresionBasica() {
        if (isOperadorUnario()) {
            operadorUnario();
            operando();
        } else if(isPrimitivo() || isPrimario())
            operando();
        else
            throw new SyntaxException("Valid expression", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void operadorUnario() {
        match(currentToken.token());
    }

    private void operando() {
        if (isPrimitivo())
            primitivo();
        else if(isPrimario())
            referencia();
        else
            throw new SyntaxException("Valid operand", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void primitivo() {
        match(currentToken.token());
    }

    private void referencia() {
        primario();
        referenciaPrima();
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

    private void primario() {
        if (is("rw_this"))
            match("rw_this");
        else if (is("stringLiteral"))
            match("stringLiteral");
        else if (is("rw_new"))
            llamadaConstructor();
        else if (is("("))
            expresionParentizada();
        else if (is("methodVarId")) {
            match("methodVarId");
            primarioPrima();
        } else if (is("classId"))
            llamadaMetodoEstatico();
        else
            throw new SyntaxException("this, new, string literal, method identifier, variable identifier or class identifier", currentToken.lexeme(), currentToken.lineNumber());
    }

    private void primarioPrima(){
        if (is("(")) {
            argsActuales();
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

    private void argsActuales() {
        match("(");
        listaExpsOpcional();
        match(")");
    }

    private void listaExpsOpcional() {
        if (isExpresionStart())
            listaExps();
    }

    private void listaExps() {
        expresion();
        listaExpsPrima();
    }

    private void listaExpsPrima() {
        if (is(",")) {
            match(",");
            listaExps();
        }
    }
}
