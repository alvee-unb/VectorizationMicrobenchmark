public class VectorizationMicroBenchmark {
    static int N;
    static int[] a_i;
    static int[] b_i;
    static long[] a_l;
    static long[] b_l;
    static float[] a_f;
    static float[] b_f;
    static double[] a_d;
    static double[] b_d;

    static long warmup_iteration;
    static long measured_iteration;
    
    // Parse strings like 10^6 into 1000000
    static long parseArgs(String s) {
        if (s.contains("^")) {
            double base = Double.parseDouble(s.substring(0, s.indexOf('^')));
            double power = Double.parseDouble(s.substring(s.indexOf('^') + 1));
            return (long) Math.pow(base, power);
        }
        return Long.parseLong(s);
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 5) {
            System.out.println("Usage: java <data-type> <operation> <array_size> " +
                    "<warmup_iteration> <measured_iteration>\n" +
                    "Supported data-types: Int, Long, Float, Double.\n" +
                    "Supported operations: Add, Sub, Mul, Div.\n" +
                    "Un-supported combinations: \"Int Div\", \"Long Div\", \"Long Mul\" ");
            return;
        }
        else if ((args[0].equals("Long") && args[1].equals("Mul")) ||
                (args[0].equals("Int") && args[1].equals("Div")) ||
                (args[0].equals("Long") && args[1].equals("Div"))) {
            System.out.println("Operation '" + args[1] + "' of data-type '" + args[0] + "' is not supported.");
            System.out.println("Usage: java <data-type> <operation> <array_size> " +
                    "<warmup_iteration> <measured_iteration>\n" +
                    "Supported data-types: Int, Long, Float, Double.\n" +
                    "Supported operations: Add, Sub, Mul, Div.\n" +
                    "Un-supported combinations: \"Int Div\", \"Long Div\", \"Long Mul\" ");
            return;
        }

        String dataType = args[0];
        String opType = args[1];

        N = Integer.parseInt(args[2]);
		a_i = new int[N];
		b_i = new int[N];
		a_l = new long[N];
		b_l = new long[N];
		a_f = new float[N];
		b_f = new float[N];
		a_d = new double[N];
		b_d = new double[N];

        String warmupArg = args[3];
        String measuredArg = args[4];
        warmup_iteration = parseArgs(warmupArg);
        measured_iteration = parseArgs(measuredArg);

        boolean allOp = dataType.equals("All");

        // Fill-up the respective arrays
        for (int i = 0; i < N; i++) {
            if (dataType.equals("Int") || allOp) {
                a_i[i] = i;
                b_i[i] = N - 1 - i;
            }
            else if (dataType.equals("Long") || allOp) {
                a_l[i] = i;
                b_l[i] = N - 1 - i;
            }
            else if (dataType.equals("Float") || allOp) {
                a_f[i] = (float) i;
                b_f[i] = (float) (N - 1 - i);
            }
            else if (dataType.equals("Double") || allOp) {
                a_d[i] = (double) i;
                b_d[i] = (double) (N - 1 - i);
            }
        }

        double warmup_time = 0;
        double measured_time = 0;

        // Repeatedly invoke the method under test to
        // initiate the JIT compiler to optimize the method
        for (long i = 0; i < (warmup_iteration + measured_iteration); i++) {

            if (dataType.equals("Int") && opType.equals("Add")) {
                if (i < warmup_iteration) warmup_time += IntOp.addI(a_i, b_i);
                else measured_time += IntOp.addI(a_i, b_i);
            }

            else if (dataType.equals("Int") && opType.equals("Sub")) {
                if (i < warmup_iteration) warmup_time += IntOp.subI(a_i, b_i);
                else measured_time += IntOp.subI(a_i, b_i);
            }

            else if (dataType.equals("Int") && opType.equals("Mul")) {
                if (i < warmup_iteration) warmup_time += IntOp.mulI(a_i, b_i);
                else measured_time += IntOp.mulI(a_i, b_i);
            }

            else if (dataType.equals("Long") && opType.equals("Add")) {
                if (i < warmup_iteration) warmup_time += LongOp.addL(a_l, b_l);
                else measured_time += LongOp.addL(a_l, b_l);
            }

            else if (dataType.equals("Long") && opType.equals("Sub")) {
                if (i < warmup_iteration) warmup_time += LongOp.subL(a_l, b_l);
                else measured_time += LongOp.subL(a_l, b_l);
            }

            else if (dataType.equals("Float") && opType.equals("Add")) {
                if (i < warmup_iteration) warmup_time += FloatOp.addF(a_f, b_f);
                else measured_time += FloatOp.addF(a_f, b_f);
            }

            else if (dataType.equals("Float") && opType.equals("Sub")) {
                if (i < warmup_iteration) warmup_time += FloatOp.subF(a_f, b_f);
                else measured_time += FloatOp.subF(a_f, b_f);
            }

            else if (dataType.equals("Float") && opType.equals("Mul")) {
                if (i < warmup_iteration) warmup_time += FloatOp.mulF(a_f, b_f);
                else measured_time += FloatOp.mulF(a_f, b_f);
            }

            else if (dataType.equals("Float") && opType.equals("Div")) {
                if (i < warmup_iteration) warmup_time += FloatOp.divF(a_f, b_f);
                else measured_time += FloatOp.divF(a_f, b_f);
            }

            else if (dataType.equals("Double") && opType.equals("Add")) {
                if (i < warmup_iteration) warmup_time += DoubleOp.addD(a_d, b_d);
                else measured_time += DoubleOp.addD(a_d, b_d);
            }

            else if (dataType.equals("Double") && opType.equals("Sub")) {
                if (i < warmup_iteration) warmup_time += DoubleOp.subD(a_d, b_d);
                else measured_time += DoubleOp.subD(a_d, b_d);
            }

            else if (dataType.equals("Double") && opType.equals("Mul")) {
                if (i < warmup_iteration) warmup_time += DoubleOp.mulD(a_d, b_d);
                else measured_time += DoubleOp.mulD(a_d, b_d);
            }

            else if (dataType.equals("Double") && opType.equals("Div")) {
                if (i < warmup_iteration) warmup_time += DoubleOp.divD(a_d, b_d);
                else measured_time += DoubleOp.divD(a_d, b_d);
            }

            else if (dataType.equals("All") && opType.equals("Op")) {
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
