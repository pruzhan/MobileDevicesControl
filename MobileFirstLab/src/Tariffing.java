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
        if ((inTimestamp.getMinute() < 30)) price += 4.0 * inDuration;
        else price += 2.0 * inDuration;
        if ((outTimestamp.getMinute() < 30)) price += 4.0 * outDuration;
        else price += 2.0 * outDuration;
        price += smsCount * 1.5;
        return price;
    }
}

