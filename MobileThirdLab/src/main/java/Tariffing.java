
import java.time.LocalTime;

class Tariffing {

    double CallPrice(double inDuration, double outDuration, int smsCount, LocalTime inTimestamp, LocalTime outTimestamp) {
        double price = 0.0;
        if ((inTimestamp.getMinute() < 30)) price += 4.0 * inDuration;
        else price += 2.0 * inDuration;
        if ((outTimestamp.getMinute() < 30)) price += 4.0 * outDuration;
        else price += 2.0 * outDuration;
        price += smsCount * 1.5;
        return price;
    }

    double TrafficPrice(int trafficCount) {
        double price = 2 * (double) trafficCount / Math.pow(2.0, 10.0);
        price *= 100;
        price = Math.round(price) / 100.0;
        return price;
    }
}
