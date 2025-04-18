package node;

import pow.Block;
import pow.Blockchain;
import pow.Transaction;
import utils.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Miner extends FullNode {
    // Constructor nhận blockchain
    public Transaction minerPick;

    public Miner(String name) {
        super(name);
    }

    // Phương thức khai thác (mining)
    public Block mineBlock(Transaction transaction) {
        // Tạo một block mới từ các giao dịch hiện tại
        Block previousBlock = super.getBlockchain().getLatestBlock();
        int index = previousBlock.getIndex() + 1;
        String previousHash = previousBlock.getHash();

        Block newBlock = new Block(index,
                previousHash,
                transaction);
        System.out.println(Network.getDifficulty());
        // Tính toán và tìm nonce hợp lệ
        while (!isValidHash(newBlock)) {
            newBlock.setNonce(newBlock.getNonce() + 1);  // Tăng nonce để thử lại
        }
        System.out.println("Thêm node thành công");
        return newBlock;
    }

    private boolean isValidHash(Block block) {
        String prefix = "0".repeat(Network.getDifficulty());
        return block.getHash().startsWith(prefix);
    }

    public void assignRandomTransactionToMiners() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(Network.getMempool().size());
        System.out.println("Miner " + this.getName() + " chọn giao dịch thứ " + randomIndex );
        this.minerPick = Network.getMempool().get(randomIndex);
    }
}

