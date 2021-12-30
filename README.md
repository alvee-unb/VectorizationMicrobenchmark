# VectorizationMicrobenchmark

The VectorizationMicrobenchmark performs simple arithmetic operations of addition, subtraction, multiplication, and division with various data-types including 32-bit and 64-bit integers (`int` and `long` respectively), 32-bit floating-point (single precision), and 64-bit floating-point (double precision) numbers, as supported by common machines.
An integer add operation can take place in the following form.

```
a[i] = b[i] + c[i]
```

These operations take maximum advantage of the SIMD instructions and results of the non-vectorized counterparts are also shown.

## Usage

```
./run_all.sh <java_path> <iterations> <array_size> <warmup_iterations> <measured_iterations>
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE "LICENSE") file for details.