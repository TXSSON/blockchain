package pow;

import utils.Network;
import utils.SHA256Helper;

public class Block {
    private int index;
    private Long timestamp;
    private Transaction transaction;
    private String previousHash;
    private int nonce = 0;
    private String hash;
    private int  difficulty;
    public Block(int index, String previousHash, Transaction transaction) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.previousHash = previousHash;
        this.transaction = transaction;
        this.difficulty = Network.getDifficulty();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String dataToHash = index + Long.toString(timestamp) + transaction.toString()
                + previousHash + Integer.toString(this.nonce);
        return SHA256Helper.hash(dataToHash);
    }

    public int getIndex() {
        return index;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public int getNonce() {
        return nonce;
    }

    public String getHash() {
        return hash;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
        this.hash = calculateHash();
    }
}
