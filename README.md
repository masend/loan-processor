# loan-processor

Welcome to the loan processor.

To run this application follow the steps below:
1. clone the loan-processor repository 

	1.2 git clone <git/repo/to/clone>
2. navigate to the project folder i.e. loan-processor root folder
3. run the following command: ./loan-processor.sh "<full/input/file/path>" "<full/out/file/path>"

   e.g. ./loan-processor.sh "/User/test/Jumo World Assessment.csv" "/User/test/Output.csv"


Considerations:
----------------
-used maven for managing dependencies and packaging

-bash script for simplifying command line interactions with the application and checking input

-java as the programming language due to familiarity and it's capability with file io. 

-TreeMap due to its native sorting and appropriateness for use case

-Inheritance between Group and it's child classes due to common behavior


Third party libraries:
----------------------
-commons-io:2.5 for its FileUtils - file opening, writing, reading and closing is management implicitly by the library

Output:
-------
<Group>(count): <item1>, <item2>, ...
.....
Badinput(count) 
