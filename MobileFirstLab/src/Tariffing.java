import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tariffing {

    double inDuration, outDuration;
    int smsCount;
    LocalTime inTimestamp, outTimestamp;

    public Tariffing(double in, double out, int sms, LocalDateTime inTimestamp, LocalDateTime outTimestamp) {
        this.inDuration = in;
        this.outDuration = out;
        this.smsCount = sms;
        this.inTimestamp = inTimestamp.toLocalTime();
        this.outTimestamp = outTimestamp.toLocalTime();
    }

    public double Price() {
        double price = 0.0;
        LocalTime inEnd = inTimestamp.plusSeconds((long)(inDuration*60));
        LocalTime outEnd = outTimestamp.plusSeconds((long)(outDuration*60));
        while (!(inTimestamp.equals(inEnd))) {
            if (inTimestamp.getMinute() < 30) price += 4.0 / 60;
            else price += 2.0 / 60;
            inTimestamp = inTimestamp.plusSeconds(1);
            if (inTimestamp.equals(LocalTime.parse("01:00:00"))) break;
        }
        while (!(outTimestamp.equals(outEnd))) {
            if (outTimestamp.getMinute() < 30) price += 4.0 / 60;
            else price += 2.0 / 60;
            outTimestamp = outTimestamp.plusSeconds(1);
            if (outTimestamp.equals(LocalTime.parse("01:00:00"))) break;
        }
        price += smsCount * 1.5;
        price = Math.round(price * 100);
        price /= 100;
        return price;
    }
}
