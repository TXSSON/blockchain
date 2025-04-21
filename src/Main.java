
import node.Miner;
import utils.Network;

import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Miner> miners = Network.getMiners();


        for (Miner miner : miners) {
            miner.mineBlock();
        }
    }
}