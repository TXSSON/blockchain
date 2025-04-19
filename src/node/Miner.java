package node;

import pow.Block;
import pow.Blockchain;
import pow.Transaction;
import utils.Network;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;


public class Miner extends FullNode {
    // Constructor nhận blockchain
    private Transaction minerPick;

    public Miner(String name, Blockchain blockchain, PrintStream term) {
        super(name, blockchain, term);
    }

    // Phương thức khai thác (mining)
    public void mineBlock() throws InterruptedException {
        this.term.startMining(super.getBlockchain().getLatestBlock().getIndex());
        // Tạo một block mới từ các giao dịch hiện tại
        Block previousBlock = super.getBlockchain().getLatestBlock();
        int index = previousBlock.getIndex() + 1;
        String previousHash = previousBlock.getPreviousHash();

        Block newBlock = new Block(index,
                previousHash,
                minerPick);
        // Tính toán và tìm nonce hợp lệ
        while (!isValidHash(newBlock)) {
            newBlock.setNonce(newBlock.getNonce() + 1);  // Tăng nonce để thử lại
        }
        Thread.sleep(100);
        this.term.nonceFound(newBlock.getNonce(), newBlock.getDifficulty());
        super.receiveNewBlock(newBlock, this);
    }

    private boolean isValidHash(Block block) {
        String prefix = "0".repeat(Network.getDifficulty());
        return block.getHash().startsWith(prefix);
    }


    public void assignRandomTransactionToMiners() throws InterruptedException {
        Random rand = new Random();
        if (Network.getMempool().isEmpty()) {
            System.out.println("Hết giao dịch trong mempool");
            return;
        }
        int randomIndex = rand.nextInt(Network.getMempool().size());
        this.minerPick = Network.getMempool().get(randomIndex);
        this.term.selectedTransaction(Integer.toString(randomIndex));
        Thread.sleep(500);
        this.mineBlock();
    }

}

