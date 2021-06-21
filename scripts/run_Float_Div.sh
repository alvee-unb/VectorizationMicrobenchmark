#!/bin/bash

echo "Running Float Div SIMD script."

echo "Starting compilation."
time /local_scratch/openj9omr/openj9-openjdk-jdk11/build/linux-aarch64-normal-server-release/jdk/bin/javac *.java

TIMEFORMAT='%3R'

echo "Warmup_time, Measured_time" |& tee -a FloatDiv.csv

x=1
while [ $x -le 30 ]
do
	#time /local_scratch/openj9omr/openj9-openjdk-jdk11/build/linux-aarch64-normal-server-release/jdk/bin/javac VectorizationMicroBenchmark.java
	sleep 1
	{ /local_scratch/openj9omr/openj9-openjdk-jdk11/build/linux-aarch64-normal-server-release/jdk/bin/java -Xms4G -Xmx4G VectorizationMicroBenchmark Float Div ; } |& tee -a FloatDiv.csv
	sleep 1

	x=$(( $x + 1 ))
done

unset TIMEFORMAT
mv FloatDiv.csv "$(date +"%FT%H_%M_%S")_FloatDiv.csv"

time ./run_master_Float_Div.sh
