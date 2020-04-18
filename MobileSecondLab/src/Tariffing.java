public class Tariffing {

    public double TrafficPrice(int trafficCount) {
        double price = 2 * (double) trafficCount / Math.pow(2.0, 10.0);
        price *= 100;
        price = Math.round(price) / 100.0;
        return price;
    }
}
