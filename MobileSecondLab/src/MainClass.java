import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        NFCAPDParser parser = new NFCAPDParser(args[0]);
        parser.ParseCSV();
    }
}
