package za.co.jumo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.jumo.io.LoanIO;

/**
 * The following LoanApp is used to process file input, group, calculate totals
 * and store the results in an output file
 *
 * @author masekela malematsa
 * @version 1.0
 */
public class LoanApp {

    private final Network networkInstance = new Network();
    private final Product productInstance = new Product();
    private final Month monthInstance = new Month();

    private static final LoanApp loanApp = new LoanApp();
    private static int badInput = 0;

    public static void main(String[] args) {
        String inputPath = System.getProperty("input");
        String outputPath = System.getProperty("output");
        
        try {

            if (inputPath == null || inputPath.equals("")
                    || outputPath == null || outputPath.equals("")) {
                System.out.println("Either the input or output file path were incorrect.");
                System.exit(0);
            }

            
            boolean firstLine = true;

            LoanIO loanIO = new LoanIO(inputPath);
            List<String> lines = loanIO.read();

            for (String line : lines) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                loanApp.insertLineItem(line);
            }

            //delete previous file
            loanIO.clear(outputPath);
            
            writeMap(outputPath, GroupBy.Network, loanApp.networkInstance.getMap());
            writeMap(outputPath, GroupBy.Product, loanApp.productInstance.getMap());
            writeMap(outputPath, GroupBy.Month, loanApp.monthInstance.getMap());

            StringBuilder sb = new StringBuilder();
            sb.append("\nBad Input(").append(badInput).append(")");

            writeLine(outputPath, sb.toString());
        } catch (IOException io) {
            Logger.getLogger(LoanApp.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    /**
     * Removes the single quotes for easier processing and splits the items in
     * the line by the ',' token and inserts into the map for the respective
     * groups
     *
     * @param line
     */
    private void insertLineItem(String line) {
        //cleaning up line input for processing
        String[] lineInput = line.replaceAll("'", "").split(",");

        if (lineInput.length != 5 || !isFieldsValid(lineInput)) {
            badInput++;
        } else {
            String msisdn = lineInput[0];
            String network = lineInput[1];
            String date = lineInput[2];
            String product = lineInput[3];
            Double amount = new Double(lineInput[4]);

            Loan loanEntry = new Loan(msisdn, product, network, convertMonth(date), amount);

            networkInstance.insertItem(loanEntry);
            productInstance.insertItem(loanEntry);
            monthInstance.insertItem(loanEntry);
        }
    }

    /**
     * converts dateInput into full month human readable string
     *
     * @param dateInput
     * @return month
     */
    private String convertMonth(String dateInput) {

        String month = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            Calendar date = Calendar.getInstance();
            date.setTime(sdf.parse(dateInput));
            month = new SimpleDateFormat("MMMM").format(date.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(LoanApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return month;
    }

    /**
     * Writes the map representing the group to file
     *
     * @param group
     * @param map
     */
    private static void writeMap(String path, GroupBy group, Map<String, Double> map) {

        try {
            LoanIO io = new LoanIO(path);

            StringBuilder sb = new StringBuilder();

            sb.append(group.toString().concat("(" + map.size() + ")").concat(": "));
            for (Entry<String, Double> pair : map.entrySet()) {
                Double amount = BigDecimal.valueOf(pair.getValue()).setScale(2, RoundingMode.HALF_UP).doubleValue();
                //System.out.printf("%s [R %.2f] | ", pair.getKey(), amount);
                sb.append(pair.getKey().concat(" [R ").concat(amount.toString()).concat("], "));
            }

            //clean up output
            sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, "");

            io.write(sb.toString());
            io.write("\n");
        } catch (IOException ex) {
            Logger.getLogger(LoanApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Writes the String representing to file
     *
     * @param String
     */
    private static void writeLine(String path, String line) {

        try {
            LoanIO io = new LoanIO(path);

            io.write(line);
            io.write("\n");
        } catch (IOException ex) {
            Logger.getLogger(LoanApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isFieldsValid(String ...args){
        for(String arg : args){
                if(arg == null || arg.isEmpty()){
                        return false;
                }
        }

        return true;
    }
}
