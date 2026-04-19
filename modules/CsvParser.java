package modules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// import javax.xml.crypto.Data;

public class CsvParser {

    public ArrayList<ProcessData> getData(String fileName) {
        String filePath =fileName + ".csv"; // Path to your .csv
        ArrayList<ProcessData> dataList = new ArrayList<>();
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            // String headerLine = br.readLine();
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(cvsSplitBy);

                if (columns.length >= 4) {
                    ProcessData dataObj = new ProcessData(
                            columns[0],
                            Integer.parseInt(columns[1]),
                            Integer.parseInt(columns[2]),
                            Integer.parseInt(columns[3]));
                    dataList.add(dataObj);
                }
            }
        } catch (IOException e) {
            System.out.println("An unknown error occur.");
        }

        return dataList;
    }

}