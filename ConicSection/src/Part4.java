import java.util.*;

public class Part4 {
	
	// function to get values
	public static ArrayList<Long> getPoints() {
		ArrayList<Long> points = new ArrayList<Long>();
		Scanner sc = new Scanner(System.in); 
		System.out.println("Enter a, b, c values:");
		for (int i = 0; i < 3; i++) {
			points.add(sc.nextLong());
		}
		System.out.println("Enter an S value:");
		points.add(sc.nextLong());
		sc.close();
		System.out.println();
		return points;
	}
	
	// function that returns updated s value 
	public static long sVal(long initialS, long digits) {
		long s = initialS; 
		long k = digits - 1;
		int i = 0; 
		while (i < k) {
			s = ((s * 10) + initialS);  
			i++; 
		}
		return s; 
	}
	
	// function that returns points with given number of digits
	public static ArrayList<Long> updatePoints(ArrayList<Long> points, long digits, long initialS) {
		ArrayList<Long> lst = new ArrayList<Long>(6);
		ArrayList<Long> ret = new ArrayList<Long>(6);
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
		long s = sVal(initialS, digits);
		for (int m = 0; m < 3; m++) {
			ret.add(s - ret.get(m));
		}
		return ret;
		} else {
			return lst;
		}
	}
	
	// function that determines type of conic 
	public static void conicType(ArrayList<Long> points, long initialS, long digits) {
		
		long a = points.get(0); 
		long b = points.get(1);
		long c = points.get(2);
		long s = sVal(initialS, digits); 
		long p1 = ((a*a)-(a*b)+(a*c)-(a*s)-(b*b)+(b*c)+(b*s)-(c*c));
		long p2 = ((a*a)-(a*b)-(a*c)+(b*b)+(b*c)-(b*s)-(c*c)+(c*s));
		long p3 = ((a*a)+(a*b)-(a*c)-(a*s)+(b*b)+(b*c)-(2*b*s)+(c*c)-(c*s)+(s*s));
		long p4 = ((a*a)+(a*b)+(a*c)-(2*a*s)-(b*b)+(b*c)+(c*c)-(2*c*s)+(s*s));
		
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
		ArrayList<Long> points = getPoints(); 
		ArrayList<Long> lst = new ArrayList<Long>();
		long s = points.remove(3);
		for (int i = 1; i <= 10; i++) {
			lst = updatePoints(points, i, s);
			conicType(lst, s, i); 
		}
		
	}

}
