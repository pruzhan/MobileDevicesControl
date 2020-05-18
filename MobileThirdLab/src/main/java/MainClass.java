
/**
 * Create by Dmitriy Fedrushkov
 * ITMO University
 * N3347, FITS
 * Variant 7
 */

public class MainClass {
    static double price;

    public static void main(String[] args) throws Exception {
        double callprice, netprice;
        TrafficParser callParser = new TrafficParser("data.csv");
        callParser.ParseCSV();
        callprice = callParser.price;
        NFCAPDParser netParser = new NFCAPDParser("data_2.csv");
        netParser.ParseCSV();
        netprice = netParser.price;
        price = callprice + netprice;
        new PDFcreator().create();
    }
}
