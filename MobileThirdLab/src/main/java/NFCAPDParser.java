
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class NFCAPDParser {

    private final String csvFileName;
    double price;

    NFCAPDParser(String csvFilePath) {
        this.csvFileName = csvFilePath;
    }

    void ParseCSV() throws IOException {
        int trafficCount = 0;
        List<String> fileLines = Files.readAllLines(Paths.get(csvFileName));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<>();
            for (String s : splitedText) {
                if (IsColumnPart(s)) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + s);
                } else {
                    columnList.add(s);
                }
            }
            if (columnList.size() >= 3) {
                if (columnList.get(3).equals("87.245.198.147") || columnList.get(4).equals("87.245.198.147")) {
                    trafficCount += Integer.parseInt(columnList.get(12));
                }
            }
        }
        Tariffing tariffing = new Tariffing();
        price = tariffing.TrafficPrice(trafficCount);
    }

    private boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
