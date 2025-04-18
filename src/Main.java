
import node.Miner;
import pow.Transaction;
import utils.Network;

import java.util.ArrayList;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Miner> miners = Network.getMiners();
        for (Miner miner : miners) {
            miner.assignRandomTransactionToMiners();
        }
    }
}