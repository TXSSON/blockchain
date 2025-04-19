package pow;

import java.util.ArrayList;
import java.util.List;

public class Blockchain implements Cloneable {
    private List<Block> chain;
    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }
    private Blockchain(List<Block> chain) {
        this.chain = chain;
    }
    private Block createGenesisBlock() {
        Transaction genesisTransaction = new Transaction("Son", "Cuong", "500");
        return  new Block(0, "0", genesisTransaction);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }
    public void addBlock(Block newBlock, String name) {
            chain.add(newBlock);
    }
    public boolean isValidNewBlock(Block newBlock, String name) {
        Block lastestBlock = this.getLatestBlock();
        return lastestBlock.getIndex() + 1 == newBlock.getIndex() &&
                lastestBlock.getPreviousHash().equals(newBlock.getPreviousHash()) &&
                newBlock.getHash().equals(newBlock.calculateHash());
    }
    public List<Block> getChain() {
        return chain;
    }
    @Override
    public Blockchain clone() throws CloneNotSupportedException {
        return new Blockchain(new ArrayList<>(this.chain));
    }
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < chain.size(); i++) {
            result.append(chain.get(i).getIndex());
            if (i < chain.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}