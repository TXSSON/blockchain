package utils;

import node.FullNode;
import node.Miner;
import pow.Blockchain;
import pow.Transaction;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {
    // Độ khó của thuat toan PoW
    private static int difficulty = 3;
    // Tạo chuỗi Blockchain gốc
    private static Blockchain blockchain =  new Blockchain();
    // Danh sách các fullNode trong mạng
    private static List<FullNode> nodes;

    static {
        try {
            nodes = initNode();
        } catch (CloneNotSupportedException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    // Danh sách các giao dịch trong mempool
    private static List<Transaction> mempool =  initMempool();
    private static List<Miner> miners;

    static {
        try {
            miners = initMiner();
        } catch (CloneNotSupportedException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Miner> initMiner() throws CloneNotSupportedException, FileNotFoundException {
        List<Miner> miners = new ArrayList<>();
        miners.add(new Miner("Miner 1",blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/21"))));
        miners.add(new Miner("Miner 2",blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/24"))));
        miners.add(new Miner("Miner 3",blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/25"))));
        miners.add(new Miner("Miner 4",blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/26"))));
        for (Miner miner : miners) {
            miner.term.println("\033[H\033[2J");
            miner.term.printName( miner.getName());
        }

        return miners;
    }
    private static List<FullNode> initNode() throws CloneNotSupportedException, FileNotFoundException {
        List<FullNode> nodes = new ArrayList<>();

        nodes.add(new FullNode("FullNode A", blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/27"))));
        nodes.add(new FullNode("FullNode B", blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/28"))));
        nodes.add(new FullNode("FullNode C", blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/29"))));
        nodes.add(new FullNode("FullNode D", blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/30"))));
        nodes.add(new FullNode("FullNode E", blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/31"))));
        nodes.add(new FullNode("FullNode F", blockchain.clone(), new PrintStream(new FileOutputStream("/dev/pts/32"))));
        for (FullNode node : nodes) {
            node.term.println("\033[H\033[2J");
            node.term.printName(node.getName());
        }
        return nodes;
    }
    static {
        initGraph(miners,nodes ); // Gọi hàm static void ở đây
    }
    public static void initGraph(List<Miner> miners, List<FullNode> fullNodes) {
        Map<FullNode, List<FullNode>> graph = new HashMap<>();

        // Lấy đối tượng cụ thể từ danh sách
        Miner m1 = miners.get(0);
        Miner m2 = miners.get(1);
        Miner m3 = miners.get(2);
        Miner m4 = miners.get(3);


        FullNode nA = fullNodes.get(0);
        FullNode nB = fullNodes.get(1);
        FullNode nC = fullNodes.get(2);
        FullNode nD = fullNodes.get(3);
        FullNode nE = fullNodes.get(4);
        FullNode nF = fullNodes.get(5);

        // Thêm các kết nối
        m1.addNeighbor(List.of(nA, nB, nC));
        m2.addNeighbor(List.of(nB, nD, nE));
        m3.addNeighbor(List.of(nC, nE, nF));
        m4.addNeighbor(List.of(nA, nD, nF));

        nA.addNeighbor(List.of(m1, m4, nB, nC));
        nB.addNeighbor(List.of(m1, m2, nA, nD));
        nC.addNeighbor(List.of(m1, m3, nA, nE));
        nD.addNeighbor(List.of(m2, m4, nB, nF));
        nE.addNeighbor(List.of(m2, m3, nC, nF));
        nF.addNeighbor(List.of(m3, m4, nD, nE));
    }
    private static List<Transaction> initMempool() {
        return FileReaderUtil.readFile("/home/son/Documents/code-java/blockchain/src/utils/mempool.txt", Transaction.class, String.class, String.class, String.class);
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
