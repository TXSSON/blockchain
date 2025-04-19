
import node.Miner;
import utils.Network;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Miner> miners = Network.getMiners();
        ExecutorService executor = Executors.newFixedThreadPool(miners.size());

        for (Miner miner : miners) {
                miner.assignRandomTransactionToMiners();
        }
    }
}