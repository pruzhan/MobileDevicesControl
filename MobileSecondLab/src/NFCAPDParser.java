import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NFCAPDParser {

    String csvFileName;

    public NFCAPDParser(String csvFilePath) {
        this.csvFileName = csvFilePath;
    }

    public void ParseCSV() throws IOException {
        int trafficCount = 0;
        double price;
        int o = 0;
        List<String> fileLines = Files.readAllLines(Paths.get(csvFileName));
        ArrayList<Integer> ts = new ArrayList<>();
        ArrayList<Integer> te = new ArrayList<>();
        ArrayList<Integer> traffics = new ArrayList<>();
        //traffics.add(0);
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
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
                    StringBuilder timestring1 = new StringBuilder(columnList.get(0));
                    StringBuilder timestring2 = new StringBuilder(columnList.get(1));
                    timestring1.delete(0, timestring1.indexOf(" ") + 1);
                    timestring2.delete(0, timestring2.indexOf(" ") + 1);
                    if (ts.indexOf(LocalTime.parse(timestring1).toSecondOfDay() - 43200) == -1) {
                        ts.add(LocalTime.parse(timestring1).toSecondOfDay() - 43200);
                        trafficCount += Integer.parseInt(columnList.get(12));
                        traffics.add(trafficCount);
                        o++;
                    } else {
                        trafficCount += Integer.parseInt(columnList.get(12));
                        traffics.set(o-1, trafficCount);
                    }
                    if (te.indexOf(LocalTime.parse(timestring2).toSecondOfDay() - 43200) == -1)
                        te.add(LocalTime.parse(timestring2).toSecondOfDay() - 43200);
                }
            }
        }
        Tariffing tariffing = new Tariffing();
        price = tariffing.TrafficPrice(trafficCount);
        System.out.println("Price: " + price + " R.");
        int[] TIMESstart = new int[ts.size()];
        for (int i = 0; i < ts.size(); i++) TIMESstart[i] = ts.get(i);
        int[] TIMESend = new int[te.size()];
        for (int i = 0; i < te.size(); i++) TIMESend[i] = te.get(i);
        int[] TRAFFICS = new int[traffics.size()];
        for (int i = 0; i < traffics.size(); i++) TRAFFICS[i] = traffics.get(i);
        new Draw(TIMESstart, TIMESend, TRAFFICS);
    }

    private boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
