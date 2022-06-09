package PA4;

public class ExampleArrays {
	
	// 1.1 DONE
	Number averageWithThreshold(Number[] numList, Number threshold) {
		// numList is empty; return Number 0
		if(numList.length == 0) {
			return new WholeNumber(0);
		}		
		
		//length of numGreater array
		int greaterLength = 0;
		
		// loop through the Number[] Array 
		// to determine numGreater Array size
		for(int i = 0; i < numList.length; i++) {
			int compared = numList[i].compare(threshold);
			if(compared == 1) {
				greaterLength++;
			}
		}
		
		// if there are no Numbers greater than threshold
		// return 0
		if(greaterLength == 0) {
			return new WholeNumber(0);
		}
		
		Number[] numGreater = new Number[greaterLength];
		// loop again to add Numbers greater than threshold into
		// numGreater array
		int indexTracker = 0;
		for(int j = 0; j < numList.length; j++) {
			int compared = numList[j].compare(threshold);
			if(compared == 1) {
				numGreater[indexTracker] = numList[j];
				indexTracker++;
			}
		}
		
		// get the average of Numbers in numGreater
		Number sum = new WholeNumber(0);
		for(Number num : numGreater) {
			sum = sum.add(num);
		}
		
		Number average = new Decimal(String.valueOf(sum.toDouble()/numGreater.length));
		
		return average;
	}
	
	// 1.2 DONE
	Pair[] findGoodPairs(Pair[] listPairs) {
		int goodPairsCount = 0;
		// loop through listPairs to get goodPairs length
		for(int i = 0; i < listPairs.length; i++) {
			if(listPairs[i].a < listPairs[i].b) {
				goodPairsCount++;
			}
		}
		
		// create array goodPairs with count from above
		Pair[] goodPairs = new Pair[goodPairsCount];
		
		// loop through listPairs again and add good pairs
		// into goodPairs array
		int indexTracker = 0;
		for(int j = 0; j < listPairs.length; j++) {
			if(listPairs[j].a < listPairs[j].b) {
				goodPairs[indexTracker] = listPairs[j];
				indexTracker++;
			}
		}
		
		return goodPairs;
	}
	
	// 1.3 DONE
	Pair[] mergePairs(Pair[] p1, Pair[] p2) {
		Pair[] merge;
		// Check shortest length between p1 and p2
		if(p1.length <= p2.length) {
			merge = new Pair[p1.length];
			// loop through p1 length
			for(int i = 0; i < p1.length; i++) {
				int a;
				// compare first number
				if(p1[i].a < p2[i].a) {
					a = p1[i].a;
				} else {
					a = p2[i].a;
				}
				int b;
				// compare second number
				if(p1[i].b > p2[i].b) {
					b = p1[i].b;
				} else {
					b = p2[i].b;
				}
				// add new Pair to merge array
				merge[i] = new Pair(a, b);
			}
		} else {
			merge = new Pair[p2.length];
			// loop through p2 length
			for(int j = 0; j < p2.length; j++) {
				int a;
				// compare first number
				if(p1[j].a < p2[j].a) {
					a = p1[j].a;
				} else {
					a = p2[j].a;
				}
				int b;
				// compare second number
				if(p1[j].b > p2[j].b) {
					b = p1[j].b;
				} else {
					b = p2[j].b;
				}
				// add new Pair to merge array
				merge[j] = new Pair(a,b);
			}
		}
		return merge;
	}
}

// 1.2 DONE
class Pair {
	int a;
	int b;
	
	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

class testExampleArrays {
	public static void main(String[] args) {
		ExampleArrays ea = new ExampleArrays();
		// averageWithThreshold Test
		Number[] test1 = {new WholeNumber(1) , new Fraction(4, 2), new WholeNumber(3)};
		Number threshold = new WholeNumber(1);
		
		System.out.println(ea.averageWithThreshold(test1, threshold).toText());
		
		// findGoodPair Test
		Pair[] p1 = {new Pair(1,2), new Pair(4,3), new Pair(5,6)};
		Pair[] p2 = {new Pair(2,2), new Pair(4,3), new Pair(7,6)};
		
		Pair[] p1Result = ea.findGoodPairs(p1);
		for(Pair p : p1Result) {
			System.out.print(String.valueOf(p.a) + "," + String.valueOf(p.b) + " ; ");
		}
		System.out.println();
		
		// findGoodPair Test
		Pair[] p2Result = ea.findGoodPairs(p2);
		for(Pair p : p2Result) {
			System.out.print(String.valueOf(p.a) + "," + String.valueOf(p.b) + " ; ");
		}
		System.out.println();
		
		// mergePair Test 1
		Pair[] p3 = {new Pair(1,4), new Pair(4,6)};
		Pair[] p4 = {new Pair(2,3), new Pair(1,5)};
		
		Pair[] mergeTest = ea.mergePairs(p3, p4);
		for(Pair p : mergeTest) {
			System.out.print(String.valueOf(p.a) + "," + String.valueOf(p.b) + " ; ");
		}
		System.out.println();
		
		// mergePair Test 2
		Pair[] p5 = {new Pair(1,4), new Pair(10,15)};
		Pair[] p6 = {new Pair(5,6), new Pair(4,5)};
		
		Pair[] mergeTest2 = ea.mergePairs(p5, p6);
		for(Pair p : mergeTest2) {
			System.out.print(String.valueOf(p.a) + "," + String.valueOf(p.b) + " ; ");
		}
		System.out.println();
	}
}
