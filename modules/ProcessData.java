package modules;

public class ProcessData {
    final public String pName;
    final public int arrivalTime;
    public int burstTime;
    final public int priority;

    public ProcessData(String pName, int arrivalTime, int burstTime, int priority) {
        this.pName = pName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    // Overriding toString to print the results easily
    @Override
    public String toString() {
        return "Data{Process Name='" + pName + "', Arrival Time ='" + arrivalTime + "', Burst Time='" + burstTime
                + "', Priority = '"+priority+"'}";
    }

    public void deductBurstTime(){
        this.burstTime-=1;
    }
}