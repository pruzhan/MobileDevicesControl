import java.awt.*;
import java.time.LocalTime;

public class Draw extends javax.swing.JFrame {

    private int[] x1;
    private int[] x2;
    private int[] y;
    private int[] yTrue;
    private Dimension size = new Dimension(1000, 1000);
    private Dimension startPointXoY = new Dimension(50, 970);
    private int scale = 100;

    public Draw(int[] timestart, int[] timeend, int[] trafficArr) {
        this.x1 = timestart;
        this.x2 = timeend;
        this.y = trafficArr;
        yTrue = new int[y.length];
        reBuildArrays();
        initInterface();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size.width, size.height);

        g.setColor(Color.BLACK);

        LocalTime timemark = LocalTime.parse("12:00:00");

        for (int i = 1; i <= 18; i++) {
            if (i % 3 == 0) {
                g.drawString(timemark.plusSeconds((long) i * 100).toString(), startPointXoY.width - 15 + startPointXoY.width * i, startPointXoY.height + (startPointXoY.width - 25));
                g.drawLine(startPointXoY.width + startPointXoY.width * i, startPointXoY.height - 2, startPointXoY.width + startPointXoY.width * i, startPointXoY.height + 2);
                g.drawString(String.valueOf(i * 1000), 10, startPointXoY.height - startPointXoY.width * i);
                g.drawLine(startPointXoY.width - 2, startPointXoY.height - (startPointXoY.width) * i, startPointXoY.width + 2, startPointXoY.height - startPointXoY.width * i);
            }
        }

        g.drawLine(startPointXoY.width, startPointXoY.height - 950, startPointXoY.width, startPointXoY.height);
        g.drawLine(startPointXoY.width, startPointXoY.height, startPointXoY.width + 900, startPointXoY.height);
        g.drawLine(x1[0], startPointXoY.height, x2[0], yTrue[0]);

        for (int i=0; i<yTrue.length-1; i++) {
            g.drawLine(x2[i], yTrue[i], x1[i+1], yTrue[i]);
            g.drawLine(x1[i+1], yTrue[i], x2[i+1], yTrue[i+1]);
        }
    }

    private void initInterface() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(size);
        setResizable(false);
        setTitle("График зависимости трафика от времени");
        setVisible(true);
    }

    private void reBuildArrays() {
        for (int i = 0; i < x1.length; i++) {
            x1[i] /= 2;
            x1[i] += startPointXoY.width;
            x2[i] /= 2;
            x2[i] += startPointXoY.width;
            y[i] /= 20;
            yTrue[i] = startPointXoY.height - ((int) y[i]);
        }
    }
}
