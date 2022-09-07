package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> opCounts = new AList<Integer>();
        for (int N = 1000; N <= 128000; N = N *= 2) {
            SLList<Integer> emptyList = new SLList<Integer>(); //an empty lst has been generated.
            for (int count = 0; count <= N; count ++) {
                emptyList.addLast(count);
            }
            Stopwatch sw = new Stopwatch();
            for (int i = 0; i <= 10000; i ++) {
                emptyList.getLast();
                double timeInSeconds = sw.elapsedTime();
                if (i == 10000) {
                    times.addLast(timeInSeconds);
                    Ns.addLast(N);
                    opCounts.addLast(10000);
                }
            }
        }
    }

}
