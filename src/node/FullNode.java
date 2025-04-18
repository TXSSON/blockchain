package node;

import pow.Block;
import pow.Blockchain;

import java.util.ArrayList;
import java.util.List;

public class FullNode {
    private String name;
    private Blockchain blockchain = new Blockchain();
    private List<FullNode> neighbors = new ArrayList<FullNode>();

    public void addNeighbor(FullNode neighbor) {
        neighbors.add(neighbor);
    }
    public FullNode(String name) {
        this.name = name;
    }

    public void receiveBlock(Block newBlock) {
        blockchain.addBlock(newBlock);
    }

    public String getName() {
        return name;
    }
    public Blockchain getBlockchain() {
        return blockchain;
    }
}
