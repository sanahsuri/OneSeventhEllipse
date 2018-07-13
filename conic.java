import java.util.*;

public class conic {

	// function to get values
	public static ArrayList<Double> getPoints() {
		ArrayList<Double> points = new ArrayList<Double>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a, b, c values:");
		for (int i = 0; i < 3; i++) {
			points.add(sc.nextDouble());
		}
		System.out.println("Enter an S value:");
		points.add(sc.nextDouble());
		sc.close();
		System.out.println();
		return points;
	}

	// function that returns updated s value
	public static double sVal(double initialS, double digits) {
		double s = initialS;
		double k = digits - 1;
		int i = 0;
		while (i < k) {
			s = ((s * 10) + initialS);
			i++;
		}
		return s;
	}

	// function that returns points with given number of digits
	public static ArrayList<Double> updatePoints(ArrayList<Double> points, double digits, double initialS) {
		ArrayList<Double> lst = new ArrayList<Double>(6);
		ArrayList<Double> ret = new ArrayList<Double>(6);
		for (int j = 0; j < 3; j++) {
			lst.add(points.get(j));
			ret.add(points.get(j));
		}
		for (int n = 0; n < 3; n++) {
			lst.add(initialS - points.get(n));
		}

		int i = 0;
		int h = 0;
		double k = digits - 1;
		if (k != 0) {
		while (i < k) {
			ret.set(0, ret.get(0) * 10 + lst.get((1 + h) % 6));
			ret.set(1, ret.get(1) * 10 + lst.get((2 + h) % 6));
			ret.set(2, ret.get(2) * 10 + lst.get((3 + h) % 6));
			i++;
			h++;
		}
		double s = sVal(initialS, digits);
		for (int m = 0; m < 3; m++) {
			ret.add(s - ret.get(m));
		}
		return ret;
		} else {
			return lst;
		}
	}

	// function that determines type of conic
	public static void conicType(ArrayList<Double> points, double initialS, double digits) {

		double a = points.get(0);
		double b = points.get(1);
		double c = points.get(2);
		double s = sVal(initialS, digits);
		double p1 = ((a*a)-(a*b)+(a*c)-(a*s)-(b*b)+(b*c)+(b*s)-(c*c));
		double p2 = ((a*a)-(a*b)-(a*c)+(b*b)+(b*c)-(b*s)-(c*c)+(c*s));
		double p3 = ((a*a)+(a*b)-(a*c)-(a*s)+(b*b)+(b*c)-(2*b*s)+(c*c)-(c*s)+(s*s));
		double p4 = ((a*a)+(a*b)+(a*c)-(2*a*s)-(b*b)+(b*c)+(c*c)-(2*c*s)+(s*s));

		System.out.println("P1: " + p1);
		System.out.println("P2: " + p2);
		System.out.println("P3: " + p3);
		System.out.println("P4: " + p4);

		if ((p1 > 0 && p2 > 0 && p3 > 0 && p4 > 0) ||
				(p1 < 0 && p2 < 0 && p3 > 0 && p4 > 0) ||
				(p1 < 0 && p2 > 0 && p3 < 0 && p4 > 0) ||
				(p1 < 0 && p2 > 0 && p3 > 0 && p4 < 0) ||
				(p1 > 0 && p2 < 0 && p3 < 0 && p4 > 0) ||
				(p1 > 0 && p2 < 0 && p3 > 0 && p4 < 0) ||
				(p1 > 0 && p2 > 0 && p3 < 0 && p4 < 0) ||
				(p1 < 0 && p2 < 0 && p3 < 0 && p4 < 0)) {
			System.out.println("Conic with " + digits + " digit(s) is a hyperbola");
		} else if ((p1 < 0 && p2 < 0 && p3 < 0 && p4 > 0) ||
				(p1 < 0 && p2 < 0 && p3 > 0 && p4 < 0) ||
				(p1 < 0 && p2 > 0 && p3 < 0 && p4 < 0) ||
				(p1 > 0 && p2 < 0 && p3 < 0 && p4 < 0) ||
				(p1 < 0 && p2 > 0 && p3 > 0 && p4 > 0) ||
				(p1 > 0 && p2 < 0 && p3 > 0 && p4 > 0) ||
				(p1 > 0 && p2 > 0 && p3 < 0 && p4 > 0) ||
				(p1 > 0 && p2 > 0 && p3 > 0 && p4 < 0)) {
			System.out.println("Conic with " + digits + " digit(s) is an ellipse");
		} else if (p1 == 0 || p2 == 0 || p3 == 0 || p4 == 0) {
			System.out.println("Conic with " + digits + " digit(s) is degenerate");
		}
		System.out.println("S = " + s);
		System.out.println(points.toString());
		System.out.println();

	}

	// main driver
	public static void main(String args[]) {
		ArrayList<Double> points = getPoints();
		ArrayList<Double> lst = new ArrayList<Double>();
		double s = points.remove(3);
		for (int i = 1; i <= 10; i++) {
			lst = updatePoints(points, i, s);
			conicType(lst, s, i);
		}

	}

}
