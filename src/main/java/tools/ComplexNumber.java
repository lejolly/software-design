package tools;

/**
 * A class that supports computing with complex numbers
 */
public class ComplexNumber {

    private final double real;
    private final double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber that) {
        return new ComplexNumber(this.real + that.getReal(), this.imaginary + that.getImaginary());
    }

    public ComplexNumber subtract(ComplexNumber that) {
        return new ComplexNumber(this.real - that.getReal(), this.imaginary - that.getImaginary());
    }

    public ComplexNumber multiply(ComplexNumber that) {
        //(a + bi)(c + di) = (ac - bd) + (bc + ad)i
        return new ComplexNumber((this.real * that.getReal()) - (this.imaginary * that.getImaginary()),
                (this.imaginary * that.getReal()) + (this.real * that.getImaginary()));
    }

    public ComplexNumber divide(ComplexNumber that) {
        //(a + bi)/(c + di) = (((ac + bd) / (c^2 + d^2)) + ((bc - ad) / (c^2 + d^2))i)
        return new ComplexNumber(((this.real * that.getReal()) + (this.imaginary * that.getImaginary())) /
                (Math.pow(that.getReal(), 2) + Math.pow(that.getImaginary(), 2)),
                ((this.imaginary * that.getReal()) - (this.real * that.getImaginary())) /
                        (Math.pow(that.getReal(), 2) + Math.pow(that.getImaginary(), 2)));
    }

    public double getAbsolute() {
        return Math.sqrt((real * real) + (imaginary * imaginary));
    }

    public double getArgument() {
        return Math.atan2(imaginary, real);
    }

    public ComplexNumber getSquare() {
        return multiply(this);
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return this.real == (that.getReal()) && this.imaginary == (that.getImaginary());
    }

}
