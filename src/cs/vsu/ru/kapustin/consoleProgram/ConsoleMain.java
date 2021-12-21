package cs.vsu.ru.kapustin.consoleProgram;

import cs.vsu.ru.kapustin.Data;
import cs.vsu.ru.kapustin.FindingCandiesAndRemainder;
import cs.vsu.ru.kapustin.windowProgram.GuiMain;
import cs.vsu.ru.kapustin.utils.Utils;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public class ConsoleMain {

    public static class CmdParams {
        public boolean window;
        public String inputFile;
        public String outputFile;
        public boolean error;
        public boolean help;
    }

    public static CmdParams parseArgs(String[] args) {
        ConsoleMain.CmdParams params = new CmdParams();

        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }

            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }

            params.inputFile = args[0];
            if (args.length > 1) {
                params.outputFile = args[1];
                return params;
            }
        } else {
            params.help = true;
            params.error = true;
        }

        return params;
    }

    public static void main(String[] args) throws FileNotFoundException {
        CmdParams params = parseArgs(args);

        if (params.help) {
            printHelp(params);
            System.exit(params.error ? 1 : 0);
        } else if (params.window) {
            GuiMain.main(args);
        } else {
            Data data = Utils.readDataFromFile(params.inputFile);

            if (data == null) {
                System.err.println("The data in the file was entered incorrectly.");
            } else {
                FindingCandiesAndRemainder finding = new FindingCandiesAndRemainder(data);
                Data result = finding.findCandiesAndRemainder();
                
                printResult(result, params);
            }
        }
    }

    private static void printHelp(CmdParams params) {
        PrintStream out = params.error ? System.err : System.out;
        out.println("Usage:");
        out.println("  <cmd> args <input-file> (<output-file>)");
        out.println("  <cmd> --help");
        out.println("  <cmd> --window  // show window");
    }

    private static void printResult(Data data, CmdParams params) throws FileNotFoundException {
        if (params.outputFile == null) {
            Utils.printToConsole(data);
        } else {
            Utils.writeDataToFile(data, params.outputFile);
        }
    }
}

