package cs.vsu.ru.kapustin.windowProgram;

import cs.vsu.ru.kapustin.Data;
import cs.vsu.ru.kapustin.FindingCandiesAndRemainder;
import cs.vsu.ru.kapustin.utils.JTableUtils;
import cs.vsu.ru.kapustin.utils.SwingUtils;
import cs.vsu.ru.kapustin.utils.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableInput;
    private JButton loadFromFileButton;
    private JButton performCalculationButton;
    private JButton saveToFileButton;
    private JTable tableOutput;
    private JButton saveResultToFileButton;
    private JLabel labelInputCandies;
    private JLabel labelOutputCandies;
    private JLabel labelInputMoney;
    private JLabel labelOutputMoney;
    private JTextField textFieldForInput;
    private JTextField textFieldForOutput;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("Task 10");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600, 150, 800, 400);
        this.setResizable(false);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 227, false, true, true, false, 25, 15);
        JTableUtils.initJTableForArray(tableOutput, 227, false, true, true, false, 25, 15);
        tableInput.setRowHeight(30);
        tableOutput.setRowHeight(30);

        labelInputCandies.setText("Enter the names of the candies and their prices per kilogram:");
        labelInputMoney.setText("How much money do you have? ($)");
        labelOutputCandies.setText("You should buy candies:");
        labelOutputMoney.setText("After the purchase you will have: ");

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        JMenuBar menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        JMenu menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("View");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        this.pack();

        loadFromFileButton.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    Data data = Utils.readDataFromFile(fileChooserOpen.getSelectedFile().getPath());

                    if (data == null) {
                        ErrorMessages.showErrorMessage(1);
                    } else {
                        String budget = "" + BigDecimal.valueOf(data.getBudget()).setScale(2,
                                RoundingMode.HALF_EVEN).doubleValue();
                        textFieldForInput.setText(budget);

                        String[][] result = Utils.toStringMatrix(data.getNames(), data.getPrices());
                        JTableUtils.writeArrayToJTable(tableInput, result);
                    }
                }
            } catch (FileNotFoundException ex) {
                ErrorMessages.showErrorMessage(0);
            }
        });

        performCalculationButton.addActionListener(e -> {
            Data data = Utils.readDataFromJTable(tableInput, textFieldForInput);

            if (data == null || data.getBudget() <= 0) {
                ErrorMessages.showErrorMessage(1);
            } else {
                List<String> names = data.getNames();
                List<Double> prices = data.getPrices();
                double budget = data.getBudget();

                FindingCandiesAndRemainder finding = new FindingCandiesAndRemainder(names, prices, budget);
                Data newData = finding.findCandiesAndRemainder();

                String[][] result = Utils.toStringMatrix(newData.getNames(), newData.getPrices());
                JTableUtils.writeArrayToJTable(tableOutput, result);

                String remainder = "" + BigDecimal.valueOf(newData.getBudget()).setScale(2,
                        RoundingMode.HALF_EVEN).doubleValue();
                textFieldForOutput.setText(remainder);
            }
        });

        saveToFileButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    Data data = Utils.readDataFromJTable(tableInput, textFieldForInput);

                    if (data == null) {
                        ErrorMessages.showErrorMessage(1);
                    } else {
                        String file = fileChooserSave.getSelectedFile().getPath();

                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }

                        Utils.writeDataToFile(data, file);
                    }
                }
            } catch (FileNotFoundException ex) {
                ErrorMessages.showErrorMessage(2);
            }
        });

        saveResultToFileButton.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    Data data = Utils.readDataFromJTable(tableOutput, textFieldForOutput);

                    String file = fileChooserSave.getSelectedFile().getPath();

                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }

                    Utils.writeDataToFile(data, file);
                }
            } catch (FileNotFoundException ex) {
                ErrorMessages.showErrorMessage(2);
            }

        });
    }
}
