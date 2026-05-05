package methods;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import modules.ProcessData;
import modules.Schema;

public class RoundRobin extends Schema {
    private final ArrayList<ProcessData> processes;
    private final ArrayList<String> ganttLabels;
    private final ArrayList<Integer> ganttTimes;
    private final ArrayList<String> names;
    private final ArrayList<Integer> arrival;
    private final ArrayList<Integer> burst;
    private final ArrayList<Integer> completion;
    private final ArrayList<Integer> waiting;
    private final ArrayList<Integer> turnaround;
    private double avgWT;
    private double avgTAT;
    private boolean scheduled;
    private final int timeQuantum = 2; // Increased quantum for better visualization

    public RoundRobin(ArrayList<ProcessData> processes) {
        this.processes = processes == null ? new ArrayList<>() : new ArrayList<>(processes);
        this.ganttLabels = new ArrayList<>();
        this.ganttTimes = new ArrayList<>();
        this.names = new ArrayList<>();
        this.arrival = new ArrayList<>();
        this.burst = new ArrayList<>();
        this.completion = new ArrayList<>();
        this.waiting = new ArrayList<>();
        this.turnaround = new ArrayList<>();
        this.scheduled = false;
    }

    @Override
    public void schedule() {
        // Reset data
        ganttLabels.clear();
        ganttTimes.clear();
        names.clear();
        arrival.clear();
        burst.clear();
        completion.clear();
        waiting.clear();
        turnaround.clear();
        avgWT = 0;
        avgTAT = 0;

        int n = processes.size();
        if (n == 0) {
            scheduled = true;
            return;
        }

        int[] remainingBurst = new int[n];
        boolean[] isQueued = new boolean[n];
        for (int i = 0; i < n; i++) {
            remainingBurst[i] = processes.get(i).burstTime;
            isQueued[i] = false;
        }

        Queue<Integer> queue = new LinkedList<>();
        int currentTime = 0;
        int completed = 0;
        ganttTimes.add(0);

        while (completed < n) {
            // Add processes that have arrived by the current time
            for (int i = 0; i < n; i++) {
                if (processes.get(i).arrivalTime <= currentTime && remainingBurst[i] > 0 && !isQueued[i]) {
                    queue.add(i);
                    isQueued[i] = true;
                }
            }

            if (queue.isEmpty()) {
                // Handle Idle time if no process has arrived yet
                ganttLabels.add("IDLE");
                // Find the next arrival time to skip to
                int nextArrival = Integer.MAX_VALUE;
                for (ProcessData p : processes) {
                    if (p.arrivalTime > currentTime) {
                        nextArrival = Math.min(nextArrival, p.arrivalTime);
                    }
                }
                currentTime = nextArrival;
                ganttTimes.add(currentTime);
                continue;
            }

            int index = queue.poll();
            isQueued[index] = false;
            ProcessData p = processes.get(index);

            int executeTime = Math.min(remainingBurst[index], timeQuantum);
            ganttLabels.add(p.pName);
            
            // Execute the process
            for(int t = 0; t < executeTime; t++) {
                currentTime++;
                // Check for new arrivals during execution
                for (int j = 0; j < n; j++) {
                    if (processes.get(j).arrivalTime == currentTime && remainingBurst[j] > 0 && !isQueued[j]) {
                        queue.add(j);
                        isQueued[j] = true;
                    }
                }
            }
            
            remainingBurst[index] -= executeTime;
            ganttTimes.add(currentTime);

            if (remainingBurst[index] == 0) {
                completed++;
                int ct = currentTime;
                int tat = ct - p.arrivalTime;
                int wt = tat - p.burstTime;

                names.add(p.pName);
                arrival.add(p.arrivalTime);
                burst.add(p.burstTime);
                completion.add(ct);
                waiting.add(wt);
                turnaround.add(tat);

                avgWT += wt;
                avgTAT += tat;
            } else {
                // If not finished, add back to queue
                queue.add(index);
                isQueued[index] = true;
            }
        }

        avgWT /= n;
        avgTAT /= n;
        scheduled = true;
    }

    @Override
    public void buildGanttChart() {
        if (!scheduled) schedule();
    }

    @Override
    public void averageWaitingTime() {
        if (!scheduled) schedule();
        System.out.printf("Average Waiting Time: %.2f%n", avgWT);
    }

    @Override
    public void averageTurnaroundTime() {
        if (!scheduled) schedule();
        System.out.printf("Average Turnaround Time: %.2f%n", avgTAT);
    }

    @Override
    public void printTable() {
        if (!scheduled) schedule();
        System.out.println("Round Robin Scheduling Table");
        System.out.printf("%-10s%-10s%-10s%-12s%-10s%-12s%n",
                "Process", "Arrival", "Burst", "Completion", "Waiting", "Turnaround");
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%-10s%-10d%-10d%-12d%-10d%-12d%n",
                    names.get(i), arrival.get(i), burst.get(i),
                    completion.get(i), waiting.get(i), turnaround.get(i));
        }
    }

    @Override
    public void printGanttChart() {
        if (!scheduled) schedule();
        System.out.println("Round Robin Gantt Chart");
        StringBuilder top = new StringBuilder();
        for (String label : ganttLabels) {
            top.append("| ").append(label).append(" ");
        }
        top.append("|");
        System.out.println(top);

        StringBuilder timeLine = new StringBuilder();
        for (int i = 0; i < ganttTimes.size(); i++) {
            timeLine.append(ganttTimes.get(i)).append("   ");
        }
        System.out.println(timeLine);
    }
}
