package semanticAnalyzerII.nodes.ref;

public class NodoReferenciaThis extends NodoReferencia{
    public NodoReferenciaThis(){
        this.value = symbolTable.currentEntity.name;
    }


}
