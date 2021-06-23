#!/bin/bash

echo "Running Float Add SIMD script."

echo "Starting compilation."
time /local_scratch/openj9omr/openj9-openjdk-jdk11/build/linux-aarch64-normal-server-release/jdk/bin/javac *.java

TIMEFORMAT='%3R'

echo "Warmup_time, Measured_time" |& tee -a FloatAdd.csv

x=1
while [ $x -le 30 ]
do
	#time /local_scratch/openj9omr/openj9-openjdk-jdk11/build/linux-aarch64-normal-server-release/jdk/bin/javac VectorizationMicroBenchmark.java
	sleep 1
	{ /local_scratch/openj9omr/openj9-openjdk-jdk11/build/linux-aarch64-normal-server-release/jdk/bin/java -Xms4G -Xmx4G VectorizationMicroBenchmark Float Add ; } |& tee -a FloatAdd.csv
	sleep 1

	x=$(( $x + 1 ))
done

unset TIMEFORMAT
mv FloatAdd.csv "$(date +"%FT%H_%M_%S")_FloatAdd.csv"

time ./run_master_Float_Add.sh

