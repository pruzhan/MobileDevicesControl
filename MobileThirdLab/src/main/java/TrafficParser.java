
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

class TrafficParser {

    private final String csvFileName;
    double price;

    TrafficParser(String filepath) {
        this.csvFileName = filepath;
    }

    void ParseCSV() throws IOException {
        double in = 0.0, out = 0.0;
        int sms = 0;
        LocalTime outTimestamp = LocalTime.now(), inTimestamp = LocalTime.now();
        List<String> fileLines = Files.readAllLines(Paths.get(csvFileName));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<>();
            for (int i = 0; i < splitedText.length; i++) {
                if (IsColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            if ((columnList.get(1)).equals("933156729")) {
                out = Double.parseDouble(columnList.get(3));
                sms = Integer.parseInt(columnList.get(4));
                StringBuilder time = new StringBuilder(columnList.get(0));
                time.delete(0, time.indexOf(" ")+1);
                outTimestamp = LocalTime.parse(time);
            }
            if (columnList.get(2).equals("933156729")) {
                in = Double.parseDouble(columnList.get(3));
                StringBuilder time = new StringBuilder(columnList.get(0));
                time.delete(0, time.indexOf(" ")+1);
                inTimestamp = LocalTime.parse(time);
            }
        }
        Tariffing tariffing = new Tariffing();
        price = tariffing.CallPrice(in, out, sms, inTimestamp, outTimestamp);
    }

    private boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
