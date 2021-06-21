public class VectorizationMicroBenchmark {
    static int N = 1024;
    static int[] a_i = new int[N];
    static int[] b_i = new int[N];
    static long[] a_l = new long[N];
    static long[] b_l = new long[N];
    static float[] a_f = new float[N];
    static float[] b_f = new float[N];
    static double[] a_d = new double[N];
    static double[] b_d = new double[N];

    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: java <data-type> <operation>\n" +
                    "Supported data-types: Int, Long, Float, Double.\n" +
                    "Supported operations: Add, Sub, Mul, Div.\n" +
                    "Un-supported combinations: \"Int Div\", \"Long Div\", \" Long Mul\" ");
            return;
        }
        else if ((args[0].equals("Long") && args[1].equals("Mul")) ||
                (args[0].equals("Int") && args[1].equals("Div")) ||
                (args[0].equals("Long") && args[1].equals("Div"))) {
            System.out.println("Operation '" + args[1] + "' of data-type '" + args[0] + "' is not supported.");
            return;
        }

        boolean allOp = args[0].equals("All");

        // Fill-up the respective arrays
        for (int i = 0; i < N; i++) {
            if (args[0].equals("Int") || allOp) {
                a_i[i] = i;
                b_i[i] = N - 1 - i;
            }
            else if (args[0].equals("Long") || allOp) {
                a_l[i] = i;
                b_l[i] = N - 1 - i;
            }
            else if (args[0].equals("Float") || allOp) {
                a_f[i] = (float) i;
                b_f[i] = (float) (N - 1 - i);
            }
            else if (args[0].equals("Double") || allOp) {
                a_d[i] = (double) i;
                b_d[i] = (double) (N - 1 - i);
            }

        }

        double warmup_time = 0;
        double measured_time = 0;
        long warmup_iteration = 1000 * 1000;
        long measured_iteration = 1000 * 1000 * 10;

        // repeatedly invoke the method under test. this
        // causes the JIT compiler to optimize the method
        for (long i = 0; i < (warmup_iteration + measured_iteration); i++) {

            if (args[0].equals("Int") && args[1].equals("Add")) {
                if (i < warmup_iteration) warmup_time += IntOp.addI(a_i, b_i);
                else measured_time += IntOp.addI(a_i, b_i);
            }

            else if (args[0].equals("Int") && args[1].equals("Sub")) {
                if (i < warmup_iteration) warmup_time += IntOp.subI(a_i, b_i);
                else measured_time += IntOp.subI(a_i, b_i);
            }

            else if (args[0].equals("Int") && args[1].equals("Mul")) {
                if (i < warmup_iteration) warmup_time += IntOp.mulI(a_i, b_i);
                else measured_time += IntOp.mulI(a_i, b_i);
            }

            else if (args[0].equals("Long") && args[1].equals("Add")) {
                if (i < warmup_iteration) warmup_time += LongOp.addL(a_l, b_l);
                else measured_time += LongOp.addL(a_l, b_l);
            }

            else if (args[0].equals("Long") && args[1].equals("Sub")) {
                if (i < warmup_iteration) warmup_time += LongOp.subL(a_l, b_l);
                else measured_time += LongOp.subL(a_l, b_l);
            }

            else if (args[0].equals("Float") && args[1].equals("Add")) {
                if (i < warmup_iteration) warmup_time += FloatOp.addF(a_f, b_f);
                else measured_time += FloatOp.addF(a_f, b_f);
            }

            else if (args[0].equals("Float") && args[1].equals("Sub")) {
                if (i < warmup_iteration) warmup_time += FloatOp.subF(a_f, b_f);
                else measured_time += FloatOp.subF(a_f, b_f);
            }

            else if (args[0].equals("Float") && args[1].equals("Mul")) {
                if (i < warmup_iteration) warmup_time += FloatOp.mulF(a_f, b_f);
                else measured_time += FloatOp.mulF(a_f, b_f);
            }

            else if (args[0].equals("Float") && args[1].equals("Div")) {
                if (i < warmup_iteration) warmup_time += FloatOp.divF(a_f, b_f);
                else measured_time += FloatOp.divF(a_f, b_f);
            }

            else if (args[0].equals("Double") && args[1].equals("Add")) {
                if (i < warmup_iteration) warmup_time += DoubleOp.addD(a_d, b_d);
                else measured_time += DoubleOp.addD(a_d, b_d);
            }

            else if (args[0].equals("Double") && args[1].equals("Sub")) {
                if (i < warmup_iteration) warmup_time += DoubleOp.subD(a_d, b_d);
                else measured_time += DoubleOp.subD(a_d, b_d);
            }

            else if (args[0].equals("Double") && args[1].equals("Mul")) {
                if (i < warmup_iteration) warmup_time += DoubleOp.mulD(a_d, b_d);
                else measured_time += DoubleOp.mulD(a_d, b_d);
            }

            else if (args[0].equals("Double") && args[1].equals("Div")) {
                if (i < warmup_iteration) warmup_time += DoubleOp.divD(a_d, b_d);
                else measured_time += DoubleOp.divD(a_d, b_d);
            }

            else if (args[0].equals("All") && args[1].equals("Op")) {
                if (i < warmup_iteration) {
                    warmup_time += IntOp.addI(a_i, b_i);
                    warmup_time += IntOp.subI(a_i, b_i);
                    warmup_time += IntOp.mulI(a_i, b_i);
                    warmup_time += LongOp.addL(a_l, b_l);
                    warmup_time += LongOp.subL(a_l, b_l);
                    warmup_time += FloatOp.addF(a_f, b_f);
                    warmup_time += FloatOp.subF(a_f, b_f);
                    warmup_time += FloatOp.mulF(a_f, b_f);
                    warmup_time += FloatOp.divF(a_f, b_f);
                    warmup_time += DoubleOp.addD(a_d, b_d);
                    warmup_time += DoubleOp.subD(a_d, b_d);
                    warmup_time += DoubleOp.mulD(a_d, b_d);
                    warmup_time += DoubleOp.divD(a_d, b_d);
                }
                else {
                    measured_time += IntOp.addI(a_i, b_i);
                    measured_time += IntOp.subI(a_i, b_i);
                    measured_time += IntOp.mulI(a_i, b_i);
                    measured_time += LongOp.addL(a_l, b_l);
                    measured_time += LongOp.subL(a_l, b_l);
                    measured_time += FloatOp.addF(a_f, b_f);
                    measured_time += FloatOp.subF(a_f, b_f);
                    measured_time += FloatOp.mulF(a_f, b_f);
                    measured_time += FloatOp.divF(a_f, b_f);
                    measured_time += DoubleOp.addD(a_d, b_d);
                    measured_time += DoubleOp.subD(a_d, b_d);
                    measured_time += DoubleOp.mulD(a_d, b_d);
                    measured_time += DoubleOp.divD(a_d, b_d);
                }
            }
        }

        System.out.println((warmup_time / warmup_iteration)
                + ", " + (measured_time / measured_iteration));
    }
}
