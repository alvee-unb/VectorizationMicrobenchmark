#!/bin/bash
#	Author: Md Alvee Noor (alvee.n@unb.ca, alvee.e1tech@gmail.com)
#	Centre for Advanced Studies-Atlantic (CAS-Atlantic)
#   Univerity of New Brunswick

if [[ $1 != "" ]] && [[ $2 != "" ]] && [[ $3 != "" ]] && [[ $4 != "" ]] && [[ $5 != "" ]]; then
    JAVA_HOME="$1"
    JAVAC_HOME=$JAVA_HOME"c"
    ITERATIONS=$2
    ARRAY_SIZE=$3
    WARMUP_ITERATIONS=$4
    MEASURED_ITERATIONS=$5
    echo "Iterations set: $ITERATIONS, Array size: $ARRAY_SIZE, Warmups: $WARMUP_ITERATIONS, Measures: $MEASURED_ITERATIONS"
else
    echo -e "Usage: ./run_all.sh <java_path> <iterations> <array_size> <warmup_iterations> <measured_iterations>"
    exit -1
fi

dir="$(pwd)"
echo "dir: $dir"
srcDir="$(dirname "$(pwd)")"
srcDir=$srcDir"/src"
echo "srcDir: $srcDir"
echo "Compiling Java files..."

$JAVAC_HOME $srcDir/*.java
echo "Compiled Java files."

echo "Current dir: $(pwd)"

echo "Deleting existing csv files."
rm -r *.csv

declare -a dataType=("Int" 
		"Long"
                "Float"
		"Double")

declare -a operationType=("Add"
			"Sub"
        	        "Mul"
			"Div")

# Calls like runProgram "filename" ""
runProgram ()
{
    # Sets the filename depending on the parameter passed on; i.e. -Xjit:disableAutoSIMD
    if [[ $2 == "" ]]; then
        currFileName="$1.csv"
    else
        currFileName="$1_master.csv"
        
    fi

    echo "Current filename: $currFileName"
    echo "Warmup_time, Measured_time" |& tee -a $currFileName

    x=1
    while [ $x -le $ITERATIONS ]
    do
        # Executes java class with SIMD and w/o SIMD
        if [[ $2 == "" ]]; then
            ## do the main thing here
            $JAVA_HOME -Xjit:limit={*Op.*},count=0,optlevel=hot -Xms4G -Xmx4G -cp $srcDir VectorizationMicroBenchmark $i $j $ARRAY_SIZE $WARMUP_ITERATIONS $MEASURED_ITERATIONS |& tee -a $currFileName
        else
            ## -Xjit:disableAutoSIMD for disabling SIMD
            $JAVA_HOME -Xjit:limit={*Op.*},count=0,optlevel=hot,disableAutoSIMD -Xms4G -Xmx4G -cp $srcDir VectorizationMicroBenchmark $i $j $ARRAY_SIZE $WARMUP_ITERATIONS $MEASURED_ITERATIONS |& tee -a $currFileName
        fi
    
        x=$(( $x + 1 ))
    done
}

# Loops through the above array
for i in "${dataType[@]}"
do
    for j in "${operationType[@]}"
    do
        if ([ "$i" == "Int" ] && [ "$j" == "Div" ]) || ([ "$i" == "Long" ] && [ "$j" == "Mul" ]) || ([ "$i" == "Long" ] && [ "$j" == "Div" ]); then
            echo "Skipping $i $j"
        else
            echo "Running vector- $i $j"
            runProgram "$i$j"
            runProgram "$i$j" "-Xjit:disableAutoSIMD"
                
        fi
    done
done
