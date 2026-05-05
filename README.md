# Java OS Process Scheduling Simulator

Welcome to this absolutely voluntary, definitely passion-driven project.

It was not my idea to create this.  
My OS teacher assigned a minor project to simulate Java processes, and here we are.

## What This Thing Does

Simulates CPU scheduling algorithms, because apparently computers were not complicated enough already.

1. FCFS (First Come First Serve)
2. SJF (Shortest Job First)
3. Round Robin
4. Priority Scheduling

## Current Progress (aka Reality Check)

1. FCFS: Done
2. SJF: Done
3. Round Robin: Done
4. Priority Scheduling: Done

Yes, all 4 methods exist.  
Yes, it runs.  
No, I did not become emotionally attached to this project.

Academic character development arc: complete (fcked).

## Project Structure

- `Main.java`: Starts the chaos
- `methods/`: Scheduling algorithm classes (`FCFS.java`, `SJF.java`, `RoundRobin.java`, `Priority.java`)
- `modules/CsvParser.java`: Reads process data from CSV
- `modules/ProcessData.java`: Process model (`pName`, `arrivalTime`, `burstTime`, `priority`)
- `modules/Schema.java`: Shared abstract contract for scheduling classes
- `processes.csv`: Input file with process data
- `todo.txt`: Required function names so the grading gods stay calm
- `tempCodeRunnerFile.java`: A mysterious artifact from VS Code, spiritually unnecessary

## CSV Input Format (`processes.csv`)

Each row should contain:

1. Process Name
2. Arrival Time
3. Burst Time
4. Priority (`smaller number = higher priority`, because of course)

## Required Methods (per algorithm class)

- `schedule()`
- `buildGanttChart()`
- `averageWaitingTime()`
- `averageTurnaroundTime()`
- `printTable()`
- `printGanttChart()`

## How To Run

- If you were smart enough, I'm sure you really didn't have to read this section, but here you are, collecting instructions like side quests.

From the project root (`os`), compile:

```bash
javac Main.java methods/FCFS.java methods/SJF.java methods/RoundRobin.java methods/Priority.java modules/Schema.java modules/CsvParser.java modules/ProcessData.java
```

Then run:

```bash
java Main
```

If it works on the first try, do not panic. That is allowed. (To be honest, I was more shocked than anyone that this actually worked).

## Expected Output Drama

- Gantt chart appears.
- Waiting time appears.
- Turnaround time appears.
- Inner peace does not appear.

## Planned Additions
1. Submit.
2. Survive.
3. Repeat for next assignment.
4. Pretend this was a "great learning experience" during viva.
5. Say some random things to get good grade.

## Setup Notes

- Keep class and method names exactly as listed in `todo.txt` just like you do to your favourite soft toy.
- If VS Code throws package errors, set source root to project root (`os/`), not `methods/` (I know you are dumb enough to do that silly mistake).
- If Java runtime warnings appear, use JDK `25+` (or at least something newer than old defaults (if you have a little bit of brain)).
- If your code runs but your confidence does not, that is expected in Operating Systems.
- If anyone asks "why Java for scheduling simulation?", nod slowly and change the topic.

## Known Bugs (a.k.a. Features)

- Sometimes I stare at the output longer than needed just to believe it.
- If you put random garbage in `processes.csv`, the program may respond with emotional damage or maybe it can start cursing you 😋.
- Confidence interval of this project: unstable but functional.

## VS Code Java Runtime Fix

1. Press `Ctrl + Shift + P`
2. Search: `Configure Java Runtime`
3. In JDK settings, select `JavaSE-25` (or any newer version)

## Viva Survival Tips

1. Say "CPU scheduling improves resource utilization" at least twice.
2. Point at the Gantt chart confidently, even if your soul is buffering.
3. If asked "why this approach?", say "modular design for extensibility." (even I don't know what that means 😭)
4. If things break, blame "edge cases under unusual process arrival patterns."
5. Drink water. Blink normally. Pass somehow. (the question, not yourself).

---

Built for grades, not for glory.
Engineered under mild academic pressure.
Reviewed by me, judged by deadline.

## Ohh god, please save me
If this gets full marks, miracles are real.

## Frequently Avoided Questions

1. Is this optimized?
No. It is emotionally optimized for submission speed.
2. Is this production-ready?
Only if your production is a classroom projector.
3. Why does the code look like this?
Because deadlines are stronger than design patterns.

## Performance Benchmark

- Runs fast enough to impress people who do not check complexity.
- Slow enough to remind me that life has consequences.
- Accurate enough for marks, questionable enough for NASA.

## Legal Disclaimer

- No CPUs were harmed during this simulation.
- Student sanity may have been partially compromised.
- Side effects include overthinking, coffee dependency, and random confidence spikes.

## Final Words Before Viva

- If examiner smiles, continue speaking.
- If examiner frowns, continue speaking with more confidence.
- If examiner asks extra question, activate "this can be extended in future scope" mode.
