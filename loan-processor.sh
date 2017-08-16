#!/bin/bash
printf "\n\nWELCOME TO THE LOAN PROCESSOR APP\n\n"
if [ -z "$1" ]
  then
    printf "Error: No input file supplied. Closing application.\n"
    exit 1
fi
if [ -z "$2" ]
  then
    printf "Error: No output file supplied. Closing application.\n"
    exit 1
fi
sleep 2
printf "*input file: $1\n"
sleep 1
printf "*output file: $2\n\n"
sleep 1
printf "Processing request. Please wait...\n\n"
echo -ne '#####                     (33%)\r'
sleep 1
echo -ne '#############             (66%)\r'
sleep 1
echo -ne '#######################   (100%)\r'
echo -ne '\n\n'
mvn package > /dev/null
cd target/
java -Dinput="$1" -Doutput="$2" -jar loan-processor-1.0-jar-with-dependencies.jar 
printf "PROCESSING IS COMPLETE.\n\n"
printf "The '$1' file has been processed. \n"
printf "The output file can be found at '$2'\n\n"