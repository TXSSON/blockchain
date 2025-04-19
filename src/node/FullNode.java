package node;

import pow.Block;
import pow.Blockchain;
import utils.Logger;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class FullNode {
    protected String name;
    protected Blockchain blockchain;
    protected List<FullNode> neighbors = new ArrayList<FullNode>();
    public Logger term;

    public void addNeighbor(FullNode neighbor) {
        neighbors.add(neighbor);
    }
    public FullNode(String name, Blockchain blockchain, PrintStream term) {
        this.term = new Logger(term);
        this.name = name;
        this.blockchain = blockchain;
    }
    public String getName() {
        return name;
    }
    public Blockchain getBlockchain() {
        return blockchain;

    }
    public String getBlockChainAsString() {
        return blockchain.toString();
    }
    public List<FullNode> getNeighbors() {
        return neighbors;
    }
    public void receiveNewBlock(Block newBlock, FullNode fullNode) throws InterruptedException {
        this.term.receivedNewBlock(newBlock.getIndex(), fullNode.getName(), getBlockChainAsString());
        Thread.sleep(500);
        if (blockchain.isValidNewBlock(newBlock,this.name)){
            blockchain.addBlock(newBlock,this.name);
            this.term.acceptBlock(newBlock.getIndex(), getBlockChainAsString(), getNeighborsAsString());
            newBlock.visitedNodes.add(this);
            neighbors.forEach(neighbor ->{
                if (!newBlock.visitedNodes.contains(neighbor)){
                    this.term.sendBlock(newBlock.getIndex(), neighbor.getName());
                }
            });
            this.term.println("\n");
            neighbors.forEach(neighbor -> {
                if (!newBlock.visitedNodes.contains(neighbor)) {
                    try {
                        neighbor.receiveNewBlock(newBlock, this);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            newBlock.visitedNodes = new HashSet<>();
        } else {
//            this.printTerm(this.name + " từ chối nhận block " + newBlock.getIndex());
        }
    }

    @Override
    public String toString() {
        return
               "Tên: " + name + '\n';
    }
    public String getNeighborsAsString() {
        return neighbors.stream()
                .map(FullNode::getName)
                .collect(Collectors.joining(", "));
    }
    public  void addNeighbor(List<FullNode> neighbors) {
        neighbors.forEach(this::addNeighbor);
    }

}