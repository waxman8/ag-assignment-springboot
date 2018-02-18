## ag-assignment-springboot

### Assumptions
1) Usernames are case sensitive, so Martin and MARTIN will be two different users
2) When processing the files, lines that do not conform to the format are not important therefore will not be reported and just be skipped. 
(just how important are these tweets :-))
3) When processing the tweet file, tweets of more than 140 characters get truncated to 140 characters and not ignored / bypassed
4) An IO error is a big deal and these are left to bubble all the way to the console 

### There are two options to start the application - both requires Java 1.8 
**A)** - to run with "standard" tweet.txt and user.txt files
place the jar file and two data files (tweet.txt and user.txt) in the same directory
From the command prompt, change to this directory run the java app with java -jar AGAssignment-1.0.0.jar from this directory

**B)** - to run with any two files as input files
place the jar file in any folder, for example /s00/anyJava/assignment
from a command prompt (any directory) run the app by specifying the fully qualified path to the jar and the input files. For example:
java -jar /s00/anyJava/assignment/AGAssignment-1.0.0.jar --file.user=/s00/anyJava/resources/customUser.txt --file.tweet=/s00/anyJava/resources/customTweet.txt 

Either of **A** or **B** above will start the Spring Boot application.
At the end of the startup, you should see something like:
Started AGAssignmentApplication in 2.191 seconds (JVM running for 2.583)

To confirm that the web application is running, go to [Readiness Check](http://localhost:8080)

To run the simulation, go to [Simulation](http://localhost:8080/simulate) 
It will produce the twitter-like feed in the browser as well as at the console

*Note:* Should you wish to invoke the simulation with different files, the application can be left running and after changing / replacing the input files in the same location where they were specified to the app, refresh the page in the browser to see the changes.


## Sample files
**resources** contain two sample files that should produce the following output when running the application

Alan

        @Alan: If you have a procedure with 10 parameters, you probably missed some.

        @Alan: Random numbers should not be generated with a method chosen at random.

Martin

Ward

        @Alan: If you have a procedure with 10 parameters, you probably missed some.

        @Ward: There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.

        @Alan: Random numbers should not be generated with a method chosen at random.
