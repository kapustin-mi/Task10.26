package cs.vsu.ru.kapustin;

import cs.vsu.ru.kapustin.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FindingCandiesAndRemainder {
    private final List<String> namesOfCandies;
    private final List<Double> pricesOfCandies;
    private final double budget;

    public FindingCandiesAndRemainder(Data data) {
        this.namesOfCandies = data.getNames();
        this.pricesOfCandies = data.getPrices();
        this.budget = data.getBudget();
    }

    public Data findCandiesAndRemainder() {
        Utils.sortData(namesOfCandies, pricesOfCandies);

        int maxCandies = findMaxCandies();
        List<Integer> indexes = findIndexesOfCandies(0, maxCandies, budget, new ArrayList<>());

        List<String> candies = new ArrayList<>();
        fillList(indexes, candies, null, false);

        List<Double> prices = new ArrayList<>();
        fillList(indexes, null, prices, true);

        double remainder = findRemainder(budget, prices);

        return new Data(candies, prices, remainder);
    }

    private int findMaxCandies() {
        double remainder = budget;
        int maxCandies = 0;

        for (int i = pricesOfCandies.size() - 1; i >= 0; i--) {
            remainder -= pricesOfCandies.get(i);

            if (remainder <= 0) {
                break;
            }
            maxCandies++;
        }

        return maxCandies;
    }

    private List<Integer> findIndexesOfCandies(int startIndex, int maxCandies, double remainder, List<Integer> indexes) {
        int inappropriateIndex;

        for (int i = startIndex; i < pricesOfCandies.size(); i++) {
            if (remainder > pricesOfCandies.get(i)) {
                indexes.add(i);
                remainder = remainder - pricesOfCandies.get(i);

                if (indexes.size() < maxCandies) {
                    if (i == pricesOfCandies.size() - 1) {
                        remainder += pricesOfCandies.get(indexes.get(indexes.size() - 1));
                        indexes.remove(indexes.size() - 1);

                        remainder += pricesOfCandies.get(indexes.get(indexes.size() - 1));

                        inappropriateIndex = indexes.get(indexes.size() - 1);
                        indexes.remove(indexes.size() - 1);

                        return findIndexesOfCandies(inappropriateIndex + 1, maxCandies, remainder, indexes);
                    } else {
                        return findIndexesOfCandies(i + 1, maxCandies, remainder, indexes);
                    }
                } else {
                    break;
                }
            } else {
                if (i == pricesOfCandies.size() - 1) {
                    remainder += pricesOfCandies.get(indexes.get(indexes.size() - 1));

                    inappropriateIndex = indexes.get(indexes.size() - 1);
                    indexes.remove(indexes.size() - 1);

                    return findIndexesOfCandies(inappropriateIndex + 1, maxCandies, remainder, indexes);
                }
            }
        }

        return indexes;
    }

    private void fillList(List<Integer> indexes, List<String> names, List<Double> prices, boolean shouldFillPrices) {
        for (Integer index : indexes) {
            if (shouldFillPrices) {
                prices.add(pricesOfCandies.get(index));
            } else {
                names.add(namesOfCandies.get(index));
            }
        }
    }

    private double findRemainder(double budget, List<Double> prices) {
        for (Double price : prices) {
            budget -= price;
        }

        return budget;
    }
}
