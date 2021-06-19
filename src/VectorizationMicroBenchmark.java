public class VectorizationMicroBenchmark {
    static float[] a = new float[1024];
    static float[] b = new float[1024];

    private static long vecFloat(float[] x, float y[]) {
        long startTime = System.nanoTime();

        for (int i = 0; i < x.length; i++) {
            x[i] += y[i];
        }

        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: java <data-type> <operation>");
            //return;
        }
        else if ((args[0].equals("Long") && args[1].equals("Mul")) ||
                (args[0].equals("Int") && args[1].equals("Div")) ||
                (args[0].equals("Long") && args[1].equals("Div"))) {
            System.out.println(args[1] + " of data-type " + args[0] + " is not supported.");
            //return;
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = i;
            b[i] = b.length - 1 - i;
        }

        double warmup_time = 0;
        double measured_time = 0;
        long warmup_iteration = 1000 * 1000;
        long measured_iteration = 1000 * 1000 * 10;

        // repeatedly invoke the method under test. this
        // causes the JIT compiler to optimize the method
        for (long i = 0; i < (warmup_iteration + measured_iteration); i++) {

            if (i < warmup_iteration) warmup_time += vecFloat(a, b);
            else measured_time += vecFloat(a, b);
        }

        System.out.println((warmup_time / warmup_iteration)
                + ", " + (measured_time / measured_iteration));
    }
}
