public class IntOp {

    static long addI(int[] x, int y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] += y[i];
        }

        return System.nanoTime() - startTime;
    }

    static long subI(int[] x, int y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] -= y[i];
        }

        return System.nanoTime() - startTime;
    }

    static long mulI(int[] x, int y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] *= y[i];
        }

        return System.nanoTime() - startTime;
    }

    /*static long divI(int[] x, int y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] /= y[i];
        }

        return System.nanoTime() - startTime;
    }*/
}
