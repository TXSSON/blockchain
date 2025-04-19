package utils;



import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final PrintStream out;


    public Logger(PrintStream out) {
        this.out = out;
    }

    public void receivedNewBlock(int blockIndex, String minerName, String  blockchainAsString) {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " + Icon.RECEIVE.get() + " Nhận Block " + blockIndex + " từ " + minerName);
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " +Icon.MESSAGE.get() + " Trạng thái: Đang xử lý Block " + blockIndex + "...");
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " + Icon.CHAIN.get() + " Số block hiện tại trong chuỗi chính: " + blockchainAsString);
        out.println("\n");
    }
    public void acceptBlock (int blockIndex, String blockchainAsString, String neighborsAsString){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " + Icon.SUCCESS.get() + " Đã chấp nhận Block " + blockIndex + " và thêm vào chuỗi chính");
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " + Icon.CHAIN.get() + " Số block hiện tại trong chuỗi chính: " + blockchainAsString);
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " + Icon.BROADCAST.get() + " Lan truyền Block " + blockIndex + " tới: " + neighborsAsString);
        out.println(Icon.MESSAGE.get() + " Thời gian truyền block: ~1000ms");
        out.println("\n");
    }
    public void  sendBlock (int blockIndex, String name){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " + Icon.BROADCAST.get() +  " Gửi Block " + blockIndex + " đến " + name  +" thành công");
    }
    public void printName (String name ){
        out.println("======= " + Icon.DEVELOPER.get() + " " + name + " =======" );
    }
    public void selectedTransaction(String transactionIndex){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " + " Chọn giao dịch thứ  " + transactionIndex + " để đào");
    }
    public void startMining(int lastestBlockIndex){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " + Icon.MINER.get() + " Bắt đầu tìm nonce cho Block " + lastestBlockIndex + 1);
    }
    public void nonceFound(int nonce, int difficulty){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        out.println(Icon.TIME.get() + " [" + time.format(formatter) + "] " + " Số nonce tìm được là: " + nonce + " với độ khó " + difficulty);
    }
    public void println(String message){
       out.println(message);
    }
}

