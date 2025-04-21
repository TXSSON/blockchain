package utils;



import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final PrintStream out;


    public Logger(PrintStream out) {
        this.out = out;
    }

    public void receivedNewBlock(int blockIndex, String minerName, String  blockchainAsString) throws InterruptedException {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println( " [" + time.format(formatter) + "] " + Icon.RECEIVE.get() + " Nhận Block " + blockIndex + " từ " + minerName);
        Thread.sleep(500);
        out.println( " [" + time.format(formatter) + "] " +Icon.MESSAGE.get() + " Trạng thái: Đang xác minh Block " + blockIndex + "...");
        Thread.sleep(500);
        out.println( " [" + time.format(formatter) + "] " + Icon.CHAIN.get() + " Số block hiện tại trong chuỗi chính: " + blockchainAsString);
        out.println("\n");
    }
    public void acceptBlock (int blockIndex, String blockchainAsString, String neighborsAsString) throws InterruptedException {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println( " [" + time.format(formatter) + "] " + Icon.SUCCESS.get() + " Đã chấp nhận Block " + blockIndex + " và thêm vào chuỗi chính");
        Thread.sleep(500);
        out.println( " [" + time.format(formatter) + "] " + Icon.CHAIN.get() + " Số block hiện tại trong chuỗi chính: " + blockchainAsString);
        Thread.sleep(500);
        out.println( " [" + time.format(formatter) + "] " + Icon.BROADCAST.get() + " Lan truyền Block " + blockIndex + " tới: " + neighborsAsString);
        Thread.sleep(500);
        out.println("\n");
    }
    public void  sendBlock (int blockIndex, String name){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println( " [" + time.format(formatter) + "] " + Icon.BROADCAST.get() +  " Gửi Block " + blockIndex + " đến " + name  +" thành công");
    }
    public void printName (String name ){
        out.println("======= " + Icon.DEVELOPER.get() + " " + name + " =======" );
        out.println("\n");
    }
    public void selectedTransaction(int transactionIndex){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println( " [" + time.format(formatter) + "] " + " Chọn giao dịch thứ  " + transactionIndex + " để đào");
        out.println("\n");
    }
    public void startMining(int lastestBlockIndex){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println( " [" + time.format(formatter) + "] " + Icon.MINER.get() + " Bắt đầu tìm nonce cho Block " + lastestBlockIndex + 1);
        out.println("\n");
    }
    public void nonceFound(int nonce, int difficulty){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println( " [" + time.format(formatter) + "] " + " Số nonce tìm được là: " + nonce + " với độ khó " + difficulty);
        out.println("\n");
    }
    public void println(String message){
       out.println(message);
    }
}

