package methods;

import java.util.ArrayList;
import modules.ProcessData;
import modules.Schema;

public class SJF extends Schema {
    private final ArrayList<ProcessData> processes;
    private final ArrayList<String> ganttLabels;
    private final ArrayList<Integer> ganttTimes;
    private final ArrayList<String> completedNames;
    private final ArrayList<Integer> completedArrival;
    private final ArrayList<Integer> completedBurst;
    private final ArrayList<Integer> completedCompletion;
    private final ArrayList<Integer> completedWaiting;
    private final ArrayList<Integer> completedTurnaround;
    private double avgWaitingTime;
    private double avgTurnaroundTime;
    private boolean scheduled;

    public SJF(ArrayList<ProcessData> processes) {
        this.processes = processes == null ? new ArrayList<>() : new ArrayList<>(processes);
        this.ganttLabels = new ArrayList<>();
        this.ganttTimes = new ArrayList<>();
        this.completedNames = new ArrayList<>();
        this.completedArrival = new ArrayList<>();
        this.completedBurst = new ArrayList<>();
        this.completedCompletion = new ArrayList<>();
        this.completedWaiting = new ArrayList<>();
        this.completedTurnaround = new ArrayList<>();
        this.avgWaitingTime = 0.0;
        this.avgTurnaroundTime = 0.0;
        this.scheduled = false;
    }

    public SJF() {
        this(new ArrayList<>());
    }

    @Override
    public void schedule() {
        ganttLabels.clear();
        ganttTimes.clear();
        completedNames.clear();
        completedArrival.clear();
        completedBurst.clear();
        completedCompletion.clear();
        completedWaiting.clear();
        completedTurnaround.clear();
        avgWaitingTime = 0.0;
        avgTurnaroundTime = 0.0;
        scheduled = false;

        if (processes.isEmpty()) {
            ganttTimes.add(0);
            scheduled = true;
            return;
        }

        int n = processes.size();
        int[] remaining = new int[n];
        boolean[] done = new boolean[n];

        for (int i = 0; i < n; i++) {
            remaining[i] = processes.get(i).burstTime;
            done[i] = false;
        }

        int completed = 0;
        int time = 0;
        ganttTimes.add(0);

        while (completed < n) {
            int best = -1;

            for (int i = 0; i < n; i++) {
                ProcessData p = processes.get(i);
                if (!done[i] && p.arrivalTime <= time && remaining[i] > 0) {
                    if (best == -1 || remaining[i] < remaining[best]
                            || (remaining[i] == remaining[best] && p.arrivalTime < processes.get(best).arrivalTime)
                            || (remaining[i] == remaining[best] && p.arrivalTime == processes.get(best).arrivalTime
                                    && p.pName.compareTo(processes.get(best).pName) < 0)) {
                        best = i;
                    }
                }
            }

            if (best == -1) {
                addGanttStep("IDLE", time + 1);
                time++;
                continue;
            }

            ProcessData run = processes.get(best);
            addGanttStep(run.pName, time + 1);

            remaining[best]--;
            time++;

            if (remaining[best] == 0) {
                done[best] = true;
                completed++;

                int completion = time;
                int turnaround = completion - run.arrivalTime;
                int waiting = turnaround - run.burstTime;

                completedNames.add(run.pName);
                completedArrival.add(run.arrivalTime);
                completedBurst.add(run.burstTime);
                completedCompletion.add(completion);
                completedWaiting.add(waiting);
                completedTurnaround.add(turnaround);

                avgWaitingTime += waiting;
                avgTurnaroundTime += turnaround;
            }
        }

        avgWaitingTime /= n;
        avgTurnaroundTime /= n;
        scheduled = true;
    }

    private void addGanttStep(String label, int endTime) {
        if (ganttLabels.isEmpty() || !ganttLabels.get(ganttLabels.size() - 1).equals(label)) {
            ganttLabels.add(label);
            ganttTimes.add(endTime);
            return;
        }
        ganttTimes.set(ganttTimes.size() - 1, endTime);
    }

    @Override
    public void buildGanttChart() {
        if (!scheduled) {
            schedule();
        }
    }

    @Override
    public void averageWaitingTime() {
        if (!scheduled) {
            schedule();
        }
        System.out.printf("Average Waiting Time: %.2f%n", avgWaitingTime);
    }

    @Override
    public void averageTurnaroundTime() {
        if (!scheduled) {
            schedule();
        }
        System.out.printf("Average Turnaround Time: %.2f%n", avgTurnaroundTime);
    }

    @Override
    public void printTable() {
        if (!scheduled) {
            schedule();
        }

        if (completedNames.isEmpty()) {
            System.out.println("No process data available.");
            return;
        }

        System.out.println("SJF Scheduling Table (Time Slice = 1)");
        System.out.printf(
                "%-10s%-10s%-10s%-12s%-10s%-12s%n",
                "Process",
                "Arrival",
                "Burst",
                "Completion",
                "Waiting",
                "Turnaround");

        for (int i = 0; i < completedNames.size(); i++) {
            System.out.printf(
                    "%-10s%-10d%-10d%-12d%-10d%-12d%n",
                    completedNames.get(i),
                    completedArrival.get(i),
                    completedBurst.get(i),
                    completedCompletion.get(i),
                    completedWaiting.get(i),
                    completedTurnaround.get(i));
        }
    }

    @Override
    public void printGanttChart() {
        if (!scheduled) {
            schedule();
        }

        if (ganttLabels.isEmpty()) {
            System.out.println("No Gantt chart data available.");
            return;
        }

        System.out.println("SJF Gantt Chart");

        StringBuilder top = new StringBuilder();
        for (String label : ganttLabels) {
            top.append("| ").append(label).append(" ");
        }
        top.append("|");
        System.out.println(top);

        StringBuilder times = new StringBuilder();
        times.append(0);
        for (int i = 0; i < ganttTimes.size(); i++) {
            times.append("   ").append(ganttTimes.get(i));
        }
        System.out.println(times);
    }
}
