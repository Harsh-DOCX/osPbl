
// inbuild classes
import java.util.ArrayList;
import modules.CsvParser;
import modules.ProcessData;

// custom classes (for method of management)
// import management method here

public class Main {
    public static void main(String[] args) {
        CsvParser csv = new CsvParser();
        ArrayList<ProcessData> processes = csv.getData("processes");

        /*
         * create and import the following classes (in saperate file)
         * fcfs -by harsh
         * sjf -by harshita
         * round robin -not dicided yet (choose by your wish)
         * priority -not decided yet (choose by your wish)
         * 
         * functions to implement in classes are written in todo.txt
         * function name should be same
         * make sure while calling functions in main the parameters are correct
         * 
         * I've already created classes to read and format data from csv file
         * CsvParser.java retireve data from csv file
         * ProcessData.java formats and returns the data
         * 
         * create your file in methods package.
         * make sure to name and call functions carefully
         * because same function name will be used in every file
         * so changing one may affect other's code too
         * 
         * the array processes hold the data of all process in a form with
         * {
         *      pName = String
         *      arrivalTime = int
         *      burstTime = int
         *      priority = int  (lower the number higher the priority)
         * } 
         * to add more processes add them in processes.csv through ms excel
         */
    }
}
