package utils;

import node.FullNode;
import node.Miner;
import pow.Blockchain;
import pow.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Network {
    // Độ khó của thuat toan PoW
    private static int difficulty = 3;
    // Tạo chuỗi Blockchain gốc
    private static Blockchain blockchain =  new Blockchain();
    // Danh sách các fullNode trong mạng
    private static List<FullNode> nodes = initNode();
    // Danh sách các giao dịch trong mempool
    private static List<Transaction> mempool =  initMempool();
    private static List<Miner> miners = initMiner();


    public static List<Miner> initMiner() {
        List<Miner> miners = new ArrayList<>();
        miners.add(new Miner("miner1"));
        miners.add(new Miner("miner2"));
        miners.add(new Miner("miner3"));
        miners.add(new Miner("miner4"));
        return miners;
    }



    private static List<FullNode> initNode() {
        List<FullNode> nodes = new ArrayList<>();

        nodes.add(new FullNode("Node A"));
        nodes.add(new FullNode("Bode B"));
        nodes.add(new FullNode("Node C"));
        nodes.add(new FullNode("Node D"));
        nodes.add(new FullNode("Node E"));
        nodes.add(new FullNode("Node F"));

        return nodes;
    }

    private static List<Transaction> initMempool() {
        return FileReaderUtil.readFile("D:\\code-with-java\\blockchain\\src\\utils\\mempool.txt", Transaction.class, String.class, String.class, String.class);
    }


    public static void addMempool(Transaction transaction) {
        mempool.add(transaction);
    }
    public static void removeMempool(Transaction transaction) {
        mempool.remove(transaction);
    }
    public static List<Transaction> getMempool() {
        return mempool;
    }
    // Getter cho độ khó
    public static int getDifficulty() {
        return difficulty;
    }
    // Thêm node vào mạng
    public static void addNode(FullNode node) {
        nodes.add(node);
    }
    // Lấy ra node có blockchain dài nhất
    public static Blockchain getLongestBlockchain() {
        Blockchain longestBlockchain = null;
        int maxLength = 0;
        for (FullNode node : nodes) {
            int length = node.getBlockchain().getChain().size();
            if (length > maxLength) {
                maxLength = length;
                longestBlockchain = node.getBlockchain();
            }
        }
        return longestBlockchain;
    }
    public static List<Miner> getMiners() {
        return miners;
    }
}
