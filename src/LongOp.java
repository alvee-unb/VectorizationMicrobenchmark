public class LongOp {

    static long addL(long[] x, long y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] += y[i];
        }

        return System.nanoTime() - startTime;
    }

    static long subL(long[] x, long y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] -= y[i];
        }

        return System.nanoTime() - startTime;
    }

    /*static long mulL(long[] x, long y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] *= y[i];
        }

        return System.nanoTime() - startTime;
    }

    static long divL(long[] x, long y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] /= y[i];
        }

        return System.nanoTime() - startTime;
    }*/
}
