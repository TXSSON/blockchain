package pow;

import utils.SHA256Helper;

public class Transaction {
    private final String transactionId;
    private String sender;
    private String recipient;
    private String amount;

    public Transaction(String sender, String recipient, String amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.transactionId = SHA256Helper.hash(this.toString());
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return
                "sender='" + sender + '\'' +
                        ", recipient='" + recipient + '\'' +
                        ", amount='" + amount + '\''
                ;
    }

}
