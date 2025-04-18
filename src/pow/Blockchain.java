package pow;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }
    private Block createGenesisBlock() {
        Transaction genesisTransaction = new Transaction("Son", "Cuong", "500");
        return  new Block(0, "0", genesisTransaction);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        if (isValidNewBlock(newBlock, getLatestBlock())) {
            chain.add(newBlock);
            System.out.println("Thêm khối block" + newBlock.getIndex() + "thành công");
        }else {
            System.out.println("Thêm khối block mới không thành công");
        }
    }
    private boolean isValidNewBlock(Block newBlock, Block previousBlock) {
        return previousBlock.getIndex() + 1 == newBlock.getIndex() &&
                previousBlock.getHash().equals(newBlock.getPreviousHash()) &&
                newBlock.getHash().equals(newBlock.calculateHash());
    }

    public List<Block> getChain() {
        return chain;
    }

    @Override
    public String toString() {
        return "Blockchain{" +
                "chain=" + chain +
                '}';
    }
}
