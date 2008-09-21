#!/bin/bash
#gnuplot script

XLB="Tempo (sec)"
YLB="Valore (%)"

if [ "$1" = "" ]; then
	echo "Usage: run.sh <source_data>"
	exit -1
fi

echo $1
echo "set size 1.5" > aux.gnu
echo "set output \"$1.eps\"" >> aux.gnu
#echo "set title \"Test\"" >> aux.gnu
echo "set xlabel \"$XLB\"" >> aux.gnu
echo "set yrange [ 0 : 100 ]" >> aux.gnu
echo "set ylabel \"$YLB\"" >> aux.gnu

PLOT="plot \"$1\" notitle with lines lt 1 linewidth 1"
TEST=`echo $1 | grep 'index*'`
TEST1=`echo $1 | grep 'cpu*'`
TEST2=`echo $1 | grep 'ram*'`
TEST3=`echo $1 | grep 'energy*'`
echo $TEST
echo $TEST1
echo $TEST2
OUT="$PLOT"
echo "out: $OUT"
if [ "$TEST" != "" ]; then
	OUT="$OUT, 52 notitle with lines lt 3 linewidth 1"
fi
if [ "$TEST1" != "" ]; then
	OUT="$OUT, 60 notitle with lines lt 3 linewidth 1"
fi
if [ "$TEST2" != "" ]; then
	OUT="$OUT, 60 notitle with lines lt 3 linewidth 1"
fi
if [ "$TEST3" != "" ]; then
	OUT="$OUT, 10 notitle with lines lt 3 linewidth 1"
fi
echo "$OUT" >> aux.gnu

gnuplot script.input
wait
epstopdf $1.eps
wait
rm $1.eps
