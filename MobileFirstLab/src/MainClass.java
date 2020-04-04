import java.io.IOException;
import java.util.Scanner;

/**
 * Create by Dmitriy Fedrushkov
 * ITMO University
 * N3347, FITS
 * Variant 7
 */

public class MainClass {
    public static void main(String[] args) throws IOException {
        Scanner file = new Scanner(System.in);
        System.out.println("Укажите путь к csv файлу");
        TrafficParser trafficParser = new TrafficParser(file.nextLine());
        trafficParser.ParseCSV();
    }
}
