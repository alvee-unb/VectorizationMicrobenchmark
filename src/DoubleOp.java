/**
 *	Author: Md Alvee Noor (alvee.n@unb.ca, alvee.e1tech@gmail.com)
 *	Centre for Advanced Studies-Atlantic (CAS-Atlantic)
 *	Univerity of New Brunswick
*/

public class DoubleOp {

    static long addD(double[] x, double y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] += y[i];
        }

        return System.nanoTime() - startTime;
    }

    static long subD(double[] x, double y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] -= y[i];
        }

        return System.nanoTime() - startTime;
    }

    static long mulD(double[] x, double y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] *= y[i];
        }

        return System.nanoTime() - startTime;
    }

    static long divD(double[] x, double y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] /= y[i];
        }

        return System.nanoTime() - startTime;
    }
}
