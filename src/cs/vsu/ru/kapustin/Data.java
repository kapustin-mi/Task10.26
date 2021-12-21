package cs.vsu.ru.kapustin;

import java.util.List;

public class Data {
    private final List<String> names;
    private final List<Double> prices;
    private final double budget;

    public Data(List<String> names, List<Double> prices, double money) {
        this.names = names;
        this.prices = prices;
        this.budget = money;
    }

    public double getBudget() {
        return budget;
    }

    public List<String> getNames() {
        return names;
    }

    public List<Double> getPrices() {
        return prices;
    }
}
