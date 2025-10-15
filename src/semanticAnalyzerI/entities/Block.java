package semanticAnalyzerI.entities;

import semanticAnalyzerII.nodes.sent.NodoBloque;

public class Block {
    NodoBloque block;

    public Block(){
        this.block = new NodoBloque();
    }

    public NodoBloque getBlock() {
        return block;
    }
}
