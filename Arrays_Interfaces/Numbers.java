package PA4;

interface Number {
	int numerator();
	
	int denominator();
	
	Number add(Number other);
	
	Number multiply(Number other);
	
	String toText();
	
	double toDouble();
	
	int compare(Number other);
}

class WholeNumber implements Number {
	int n;
	
	// Constructor
	public WholeNumber(int n) {
		this.n = n;
	}

	@Override
	public int numerator() {
		// returns the value of n
		return n;
	}

	@Override
	public int denominator() {
		// returns 1
		return 1;
	}

	@Override
	public Number add(Number other) {
		// returns a new Number that represents
		// adding this WholNumber to the one provided as an argument 
		if(other.denominator() == 1) {
			return new WholeNumber(n + other.numerator());
		}
		else {
			return new Fraction((1 * other.numerator()) + (other.denominator() * n), (1 * other.denominator()));
		}
		
	}

	@Override
	public Number multiply(Number other) {
		// returns a new Number that represents
		// multiplying this whole number to the one provided as an argument
		if(other.denominator() == 1) {
			return new WholeNumber(n * other.numerator());
		}
		else {
			return new Fraction((n * other.numerator()), (1 * other.denominator()));
		}
	}

	@Override
	public String toText() {
		// returns the value of n as a String
		return String.valueOf(n);
	}

	@Override
	public double toDouble() {
		// returns the value of n as a double
		return (double)n;
	}

	@Override
	public int compare(Number other) {
		// returns 1 if this > other
		// -1 if this < other
		// and 0 if this == other
		double toCompare = other.numerator()/other.denominator();
		if(this.numerator() > toCompare) {
			return 1;
		} else if(this.numerator() < toCompare) {
			return -1;
		} else {
			return 0;
		}
	}
	
}

class Fraction implements Number {
	int n;
	int d;
	
	// Constructor
	public Fraction(int n, int d) {
		this.n = n;
		this.d = d;
	}

	@Override
	public int numerator() {
		// returns the value of n
		return n;
	}

	@Override
	public int denominator() {
		// returns the value of d
		return d;
	}

	@Override
	public Number add(Number other) {
		// returns a new Number that represents
		// adding this Fraction to the one provided as an argument
		return new Fraction((d * other.numerator()) + (other.denominator() * n), (d * other.denominator()));
	}

	@Override
	public Number multiply(Number other) {
		// returns a new Number that represents
		// multiplying this Fraction to the one provided as an argument
		return new Fraction((n * other.numerator()), (d * other.denominator()));
	}

	@Override
	public String toText() {
		// returns a String in format "n/d":
		return String.valueOf(n) + "/" + String.valueOf(d);
	}

	@Override
	public double toDouble() {
		// returns n/d as a double 
		return (double)n/(double)d;
	}

	@Override
	public int compare(Number other) {
		// returns 1 if this > other
		// -1 if this < other
		// and 0 if this == other
		double toCompare = other.numerator()/other.denominator();
		if(this.numerator()/this.denominator() > toCompare) {
			return 1;
		} else if(this.numerator()/this.denominator() < toCompare) {
			return -1;
		} else {
			return 0;
		}
		
	}
	
}

class Decimal implements Number {
	int mantissa;
	int exponent;
	
	public Decimal(String num) {
		double numD = Double.valueOf(num);
		// How far away the decimal is from the end
		int digitsDecimal = num.length() - 1 - num.indexOf('.');
		int denominator = 1;
		for(int i = 0; i < digitsDecimal; i++) {
			numD *= 10;
			denominator *= 10;
		}
		
		int numI = (int)Math.round(numD);
		
		this.mantissa = numI;
		this.exponent = denominator;
	}

	@Override
	public int numerator() {
		// returns the numerator of the decimal when expressed as a fraction
		return mantissa;
	}

	@Override
	public int denominator() {
		// returns the denominator of the decimal when expressed as a fraction
		return exponent;
	}

	@Override
	public Number add(Number other) {
		// returns a new Number that represents adding this decimal to the one provided as an argument
		return new Fraction((exponent * other.numerator()) + (other.denominator() * mantissa), (exponent * other.denominator()));
	}

	@Override
	public Number multiply(Number other) {
		// returns a new Number that represents multiplying this decimal to the one provided as an argument
		return new Fraction((mantissa * other.numerator()), (exponent * other.denominator()));
	}

	@Override
	public String toText() {
		// returns a String in the format #.#
		return String.valueOf((double)mantissa/(double)exponent);
	}

	@Override
	public double toDouble() {
		// returns the value of the number expressed by the mantissa and exponent as a double
		return (double)mantissa/(double)exponent;
	}

	@Override
	public int compare(Number other) {
		// returns 1 if this > other
		// -1 if this < other
		// and 0 if this == other
		double toCompare = other.numerator()/other.denominator();
		if(this.numerator()/this.denominator() > toCompare) {
			return 1;
		} else if(this.numerator()/this.denominator() < toCompare) {
			return -1;
		} else {
			return 0;
		}
	}
	
}

class testNumbers {
	public static void main(String[] args) {
		Number n1 = new WholeNumber(5);
	    Number n2 = new WholeNumber(7);
	    Number n3 = new Fraction(7, 2);
	    Number n4 = new Fraction(1, 2);
	    Number n5 = new Decimal("3.25");
	    Number n6 = new Decimal("5.5");
	    
	    System.out.println("Add Test");
	    System.out.println(n1.add(n2).toDouble() + " : 12.0");
	    System.out.println(n1.add(n3).toDouble() + " : " + String.valueOf(5 + 7.0/2.0));
	    System.out.println(n3.add(n3).toDouble() + " : 7.0");
	    System.out.println(n5.add(n4).toDouble() + " : 3.75");
	    System.out.println();
	    
	    System.out.println("Multiply Test");
	    System.out.println(n1.multiply(n4).toDouble() + " : 2.5");
	    System.out.println(n3.multiply(n4).toDouble() + " : " + String.valueOf(7.0/4.0));
	    System.out.println(n6.multiply(n1).toDouble() + ": 27.5");
	    System.out.println();
	    
	    System.out.println("Numerator and Denominator Test");
	    System.out.println(n3.numerator() + " : 7");
	    System.out.println(n1.numerator() + " : 5");
	    System.out.println(n5.numerator() + " : 325");
	    System.out.println(n4.denominator() + " : 2");
	    System.out.println(n2.denominator() + " : 1");
	    System.out.println(n6.denominator() + " : 10");
	    System.out.println();
	    
	    System.out.println("To String Tester");
	    System.out.println(n4.toText() + " : 1/2");
	    System.out.println(n3.toText() + " : 7/2");
	    System.out.println(n2.toText() + " : 7");
	    System.out.println(n5.toText() + " : 3.25");	    
	}
}
