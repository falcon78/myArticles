package Chapter1.Chapter1_2;

import Chapter1.Chapter1_1.EuclidsAlgorithm;

public class Rational {
    final int numerator;
    final int denominator;

    public Rational(int numerator, int denominator) {
        if (denominator == 0) throw new IllegalArgumentException();

        int gcd = EuclidsAlgorithm.greatestCommonDivisor(numerator, denominator);

        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public Rational plus(Rational r) {
        int numerator = this.numerator * r.denominator + this.denominator * r.numerator;
        int denominator = this.denominator * r.denominator;

        return new Rational(numerator, denominator);
    }

    public Rational minus(Rational r) {
        int numerator = this.numerator * r.denominator - this.denominator * r.numerator;
        int denominator = this.denominator * r.denominator;

        return new Rational(numerator, denominator);
    }

    public Rational times(Rational r) {
        int numerator = this.numerator * r.numerator;
        int denominator = this.denominator * r.denominator;

        return new Rational(numerator, denominator);
    }

    public boolean equals(Rational that) {
        return this.numerator == that.numerator &&
                this.denominator == that.denominator;
    }

    public Rational divides(Rational r) {
        Rational flippedRational = new Rational(r.denominator, r.numerator);

        return times(flippedRational);
    }

    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    public static void main(String[] args) {
        Rational rational1 = new Rational(2, 3);
        Rational rational2 = new Rational(1, 2);

        System.out.println("Initialize");
        System.out.println(rational1);
        System.out.println(rational2);

        System.out.println("add");
        System.out.println(rational1.plus(rational2));

        System.out.println("subtract");
        System.out.println(rational1.minus(rational2));

        System.out.println("multiply");
        System.out.println(rational1.times(rational2));

        System.out.println("divide");
        System.out.println(rational1.divides(rational2));

        System.out.println("isEqual");
        System.out.println(rational1.equals(rational2));
    }
}
