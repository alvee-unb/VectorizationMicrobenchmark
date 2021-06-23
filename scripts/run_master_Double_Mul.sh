#!/bin/bash

echo "Running Double Mul master script."

#time /local_scratch/openj9omr/openj9-openjdk-jdk11/build_master/linux-aarch64-normal-server-release/jdk/bin/javac *.java

TIMEFORMAT='%3R'

echo "Warmup_time, Measured_time" |& tee -a DoubleMul_master.csv

x=1
while [ $x -le 30 ]
do
	#time /local_scratch/openj9omr/openj9-openjdk-jdk11/build_master/linux-aarch64-normal-server-release/jdk/bin/javac VectorizationMicroBenchmark.java
	sleep 1
	{ /local_scratch/openj9omr/openj9-openjdk-jdk11/build/linux-aarch64-normal-server-release/jdk/bin/java -Xjit:disableAutoSIMD -Xms4G -Xmx4G VectorizationMicroBenchmark Double Mul ; } |& tee -a DoubleMul_master.csv
	sleep 1

	x=$(( $x + 1 ))
done

unset TIMEFORMAT
mv DoubleMul_master.csv "$(date +"%FT%H_%M_%S")_DoubleMul_master.csv"
