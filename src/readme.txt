Compile the program with the following command:
javac ConferenceRoom.java Developer.java Employee.java Manager.java Office.java OfficeLogger.java Team.java TeamLead.java TimeTracker.java Main.java

Execute the program with the following command:
java Main


The program is to be executed in Main.java.

When the program is executed, it begins immediately initializing objects
and preparing to start the "day." It then starts all the threads through
a latch. There is no user input needed.

There are a few known constants:
12 - number of developers, with 3 teams containing 4 people (and 1 lead each)
1 - number of Managers
4 - number of Team Leads
1 - number of conference rooms

The program prints out several statements anytime an event occurs, like
an employee asking a question or a manager going to lunch.
When defining the different workers, we assign them a number for their
team and position. One example is Developer "32" - He is the second
developer on team 3. Team leads are considered the first developer in
their team. At the end of our program, work logs are printed for all team leads and managers.

Here's a sample output of our program:

[8:00 am] Manager arrives at the office.
[8:00 am] Manager waits for team leads to start stand-up meeting.
[8:01 am] Developer 32 arrives at the office.
[8:02 am] Developer 33 arrives at the office.
[8:04 am] Developer 21 arrives at the office.
[8:04 am] Developer 21 goes to stand-up meeting.
[8:05 am] Developer 14 arrives at the office.
[8:06 am] Developer 34 arrives at the office.
[8:06 am] Developer 22 arrives at the office.
[8:10 am] Developer 12 arrives at the office.
[8:14 am] Developer 24 arrives at the office.
[8:14 am] Developer 31 arrives at the office.
[8:14 am] Developer 31 goes to stand-up meeting.
[8:24 am] Developer 13 arrives at the office.
[8:26 am] Developer 11 arrives at the office.
[8:26 am] Developer 11 goes to stand-up meeting.
[8:26 am] Manager holds the initial stand-up meeting.
[8:27 am] Developer 23 arrives at the office.
[8:42 am] Manager returns from stand-up meeting.
[8:42 am] Developer 21 returns from stand-up meeting.
[8:42 am] Developer 31 returns from stand-up meeting.
[8:42 am] Developer 11 returns from stand-up meeting.
[8:42 am] Developer 21 is gathering for a team meeting.
[8:43 am] Team 2 is starting the meeting.
[8:58 am] Team 2 has ended the meeting.
[8:58 am] Developer 11 is gathering for a team meeting.
[8:59 am] Developer 13 is gathering for a team meeting.
[8:59 am] Developer 14 is gathering for a team meeting.
[8:59 am] Developer 12 is gathering for a team meeting.
[9:01 am] Team 1 is starting the meeting.
[9:16 am] Team 1 has ended the meeting.
[9:16 am] Developer 33 is gathering for a team meeting.
[9:16 am] Developer 34 is gathering for a team meeting.
[9:16 am] Developer 32 is gathering for a team meeting.
[9:16 am] Developer 31 is gathering for a team meeting.
[9:16 am] Team 3 is starting the meeting.
[9:31 am] Team 3 has ended the meeting.
[10:00 am] Manager starts executive meeting 1.
[11:00 am] Manager returns from executive meeting 1.
[12:00 pm] Developer 34 goes to lunch.
[12:00 pm] Developer 22 goes to lunch.
[12:00 pm] Manager goes to lunch.
[12:00 pm] Developer 31 goes to lunch.
[12:00 pm] Developer 13 goes to lunch.
[12:00 pm] Developer 23 goes to lunch.
[12:00 pm] Developer 14 goes to lunch.
[12:00 pm] Developer 12 goes to lunch.
[12:00 pm] Developer 21 goes to lunch.
[12:00 pm] Developer 32 goes to lunch.
[12:00 pm] Developer 24 goes to lunch.
[12:00 pm] Developer 11 goes to lunch.
[12:00 pm] Developer 33 goes to lunch.
[12:32 pm] Developer 33 returns from lunch.
[12:32 pm] Developer 11 returns from lunch.
[12:32 pm] Developer 22 returns from lunch.
[12:35 pm] Developer 24 returns from lunch.
[12:35 pm] Developer 32 returns from lunch.
[12:41 pm] Developer 23 returns from lunch.
[12:45 pm] Developer 13 returns from lunch.
[12:48 pm] Manager returns from lunch.
[12:53 pm] Developer 34 returns from lunch.
[12:53 pm] Developer 31 returns from lunch.
[12:54 pm] Developer 14 returns from lunch.
[12:57 pm] Developer 21 returns from lunch.
[1:00 pm] Developer 12 returns from lunch.
[2:00 pm] Manager starts executive meeting 2.
[3:00 pm] Manager returns from executive meeting 2.
[3:02 pm] Developer 21 asks manager a question.
[3:07 pm] Developer 24 asks team lead a question.
[3:14 pm] Developer 21's question has been answered.
[3:15 pm] Developer 21 cannot answer Developer 24's question, asking manager...
[3:27 pm] Developer 24's question has been answered.
[4:00 pm] Developer 31 goes to status meeting.
[4:00 pm] Developer 21 goes to status meeting.
[4:00 pm] Developer 23 goes to status meeting.
[4:00 pm] Developer 34 goes to status meeting.
[4:00 pm] Developer 13 goes to status meeting.
[4:00 pm] Developer 12 goes to status meeting.
[4:00 pm] Developer 24 goes to status meeting.
[4:00 pm] Developer 33 goes to status meeting.
[4:00 pm] Developer 14 goes to status meeting.
[4:00 pm] Developer 11 goes to status meeting.
[4:00 pm] Developer 32 goes to status meeting.
[4:00 pm] Developer 22 goes to status meeting.
[4:15 pm] The project status update meeting is now starting.
[4:31 pm] The project status update meeting has finished.
[4:32 pm] Developer 21 leaves the office.
[4:32 pm] Developer 12 leaves the office.
------ Developer 21 Log ------
  Total time working: 508 minutes.
  Time spent at Lunch: 57 minutes.
  Time spent in meetings: 84 minutes.
  Time spent waiting for answers: 11 minutes.
----------------------------
[4:32 pm] Developer 24 leaves the office.
[4:32 pm] Developer 14 leaves the office.
[4:32 pm] Developer 22 leaves the office.
[4:32 pm] Developer 23 leaves the office.
[4:32 pm] Developer 31 leaves the office.
------ Developer 23 Log ------
  Total time working: 484 minutes.
  Time spent at Lunch: 41 minutes.
  Time spent in meetings: 58 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
------ Developer 22 Log ------
  Total time working: 506 minutes.
  Time spent at Lunch: 32 minutes.
  Time spent in meetings: 82 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
[4:32 pm] Developer 32 leaves the office.
------ Developer 14 Log ------
  Total time working: 506 minutes.
  Time spent at Lunch: 54 minutes.
  Time spent in meetings: 102 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
[4:32 pm] Developer 11 leaves the office.
------ Developer 24 Log ------
  Total time working: 497 minutes.
  Time spent at Lunch: 34 minutes.
  Time spent in meetings: 74 minutes.
  Time spent waiting for answers: 19 minutes.
----------------------------
[4:32 pm] Developer 33 leaves the office.
------ Developer 12 Log ------
  Total time working: 501 minutes.
  Time spent at Lunch: 59 minutes.
  Time spent in meetings: 95 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
[4:32 pm] Developer 34 leaves the office.
[4:32 pm] Developer 13 leaves the office.
------ Developer 34 Log ------
  Total time working: 506 minutes.
  Time spent at Lunch: 53 minutes.
  Time spent in meetings: 115 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
------ Developer 33 Log ------
  Total time working: 510 minutes.
  Time spent at Lunch: 31 minutes.
  Time spent in meetings: 119 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
------ Developer 11 Log ------
  Total time working: 486 minutes.
  Time spent at Lunch: 32 minutes.
  Time spent in meetings: 80 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
------ Developer 32 Log ------
  Total time working: 511 minutes.
  Time spent at Lunch: 35 minutes.
  Time spent in meetings: 121 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
------ Developer 31 Log ------
  Total time working: 497 minutes.
  Time spent at Lunch: 52 minutes.
  Time spent in meetings: 107 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
------ Developer 13 Log ------
  Total time working: 488 minutes.
  Time spent at Lunch: 44 minutes.
  Time spent in meetings: 83 minutes.
  Time spent waiting for answers: 0 minutes.
----------------------------
[5:00 pm] Manager leaves the office.
------ Manager Log ------
  Total time working: 539 minutes.
  Time spent at Lunch: 48 minutes.
  Time spent in meetings: 176 minutes.
----------------------------
