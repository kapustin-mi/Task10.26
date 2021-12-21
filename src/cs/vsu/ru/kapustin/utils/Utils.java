package cs.vsu.ru.kapustin.utils;

import cs.vsu.ru.kapustin.Data;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Utils {

    public static Data readDataFromJTable(JTable table, JTextField textField) {
        int rows = table.getRowCount();

        List<String> names = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        double budget;

        try {
            for (int i = 0; i < rows; i++) {
                names.add((String) table.getValueAt(i, 0));
                prices.add(Double.parseDouble(String.valueOf(table.getValueAt(i, 1))));
            }

            budget = Double.parseDouble(textField.getText());
        } catch (Exception e) {
            return null;
        }

        return new Data(names, prices, budget);
    }

    public static Data readDataFromFile(String fileName) throws FileNotFoundException {
        Locale.setDefault(Locale.ROOT);

        List<String> lines = readLinesFromFile(fileName);

        List<String> namesOfCandies = new ArrayList<>();
        List<Double> pricesOfCandies = new ArrayList<>();
        double budget;

        try {
            for (int i = 0; i < lines.size() - 1; i++) {
                String[] namesAndPrices = lines.get(i).split(" - ");

                namesOfCandies.add(namesAndPrices[0]);
                pricesOfCandies.add(Double.parseDouble(namesAndPrices[1]));
            }

            budget = Double.parseDouble(lines.get(lines.size() - 1));
        } catch (Exception e) {
            return null;
        }

        return new Data(namesOfCandies, pricesOfCandies, budget);
    }

    private static List<String> readLinesFromFile(String fileName) throws FileNotFoundException {
        Scanner scn = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<>();

        while (scn.hasNext()) {
            lines.add(scn.nextLine());
        }

        return lines;
    }

    public static void sortData(List<String> names, List<Double> prices) {
        String tmp1;
        Double tmp2;

        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {
                if (prices.get(j) > prices.get(i)) {
                    tmp1 = names.get(i);
                    names.set(i, names.get(j));
                    names.set(j, tmp1);

                    tmp2 = prices.get(i);
                    prices.set(i, prices.get(j));
                    prices.set(j, tmp2);
                }
            }
        }
    }

    public static String[][] toStringMatrix(List<String> names, List<Double> prices) {
        String[][] data = new String[names.size()][2];

        for (int i = 0; i < data.length; i++) {
            data[i][0] = names.get(i);
            data[i][1] = prices.get(i).toString();
        }

        return data;
    }

    public static void writeDataToFile(Data data, String fileName) throws FileNotFoundException {
        List<String> names = data.getNames();
        List<Double> prices = data.getPrices();
        double budget = data.getBudget();

        PrintWriter out = new PrintWriter(fileName);

        for (int i = 0; i < names.size(); i++) {
            out.println(names.get(i) + " - " + prices.get(i));
        }

        out.print(BigDecimal.valueOf(budget).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
        out.close();
    }

    public static void printToConsole(Data data) {
        List<String> names = data.getNames();
        List<Double> prices = data.getPrices();
        double budget = data.getBudget();

        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i) + " - " + prices.get(i));
        }

        System.out.print(BigDecimal.valueOf(budget).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
    }
}
