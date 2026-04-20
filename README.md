# OS Process Scheduling Simulator (Java)

This is a college Operating Systems project to simulate CPU scheduling algorithms using Java.

## Objective

Implement and compare the following scheduling techniques:

1. FCFS (First Come First Serve)
2. SJF (Shortest Job First)
3. Round Robin
4. Priority Scheduling

## Current Status

1. FCFS: Implemented
2. SJF: Planned
3. Round Robin: Planned
4. Priority Scheduling: Planned

## Project Structure

- `Main.java`: Program entry point
- `methods/`: Scheduling algorithm classes (`FCFS.java`, `SJF.java`, etc.)
- `modules/CsvParser.java`: Reads process data from CSV
- `modules/ProcessData.java`: Process model (`pName`, `arrivalTime`, `burstTime`, `priority`)
- `modules/Schema.java`: Common abstract method contract for scheduling classes
- `processes.csv`: Input process list
- `todo.txt`: Required function names for each algorithm class

## Input Format (`processes.csv`)

Each process row should contain:

1. Process Name
2. Arrival Time
3. Burst Time
4. Priority (lower number = higher priority)

## Required Functions (per algorithm)

All scheduling classes should implement:

- `schedule()`
- `buildGanttChart()`
- `averageWaitingTime()`
- `averageTurnaroundTime()`
- `printTable()`
- `printGanttChart()`

## How to Run

From the project root (`os`), compile:

```bash
javac Main.java methods/FCFS.java modules/Schema.java modules/CsvParser.java modules/ProcessData.java
```

Run:

```bash
java Main
```

## Planned Extensions

1. Add `SJF.java` in `methods/`
2. Add `RoundRobin.java` in `methods/`
3. Add `PriorityScheduling.java` in `methods/`
4. Update `Main.java` to select and run any scheduling algorithm
5. Compare average waiting time and turnaround time across all techniques

## Notes

- Keep class and method names consistent with `todo.txt`.
- If VS Code shows package errors, ensure source root is project root (`os/`), not `methods/`.
