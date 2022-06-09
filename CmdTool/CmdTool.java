package PA6;

import java.util.Arrays;

class Sum implements Command {

	// Computes the sum of an array of numbers. 
	@Override
	public String[] execute(String[] data) {
		int count = 0;
		for(String elem : data) {
			count = count + Integer.parseInt(elem);
		}
		
		String[] sumArray = {Integer.toString(count)};
		
		return sumArray;
	}	
}

class Product implements Command {

	// Computes the product of an array of integers.
	@Override
	public String[] execute(String[] data) {
		int product = 1;
		for(String elem : data) {
			product = product * Integer.parseInt(elem);
		}
		
		String[] productArray = {Integer.toString(product)};
		return productArray;
	}	
}

class Mean implements Command {

	// Computes the mean of an array of integers. 
	@Override
	public String[] execute(String[] data) {
		int length = data.length;
		int sum = 0;
		for(String elem : data) {
			sum = sum + Integer.parseInt(elem);
		}
		
		double mean = sum / length;
		
		String[] meanArray = {Double.toString(mean)};
		
		return meanArray;
	}	
}

class Max implements Command {

	// Computes the maximum of an array of integers.
	@Override
	public String[] execute(String[] data) {
		if(data.length != 0) {
			int max = Integer.parseInt(data[0]);
			for(String elem : data) {
				if(Integer.parseInt(elem) > max) {
					max = Integer.parseInt(elem);
				}
			}
			String[] maxArray = {Integer.toString(max)};
			return maxArray;
		}
		String[] empty = {};
		return empty;
	}	
}

class Min implements Command {

	// Computes the minimum of an array of integers.
	@Override
	public String[] execute(String[] data) {
		if(data.length != 0) {
			int min = Integer.parseInt(data[0]);
			for(String elem : data) {
				if(Integer.parseInt(elem) < min) {
					min = Integer.parseInt(elem);
				}
			}
			String[] minArray = {Integer.toString(min)};
			return minArray;
		}
		String[] empty = {};
		return empty;
	}	
}

class Positive implements Command {

	// Filters the given array to include only strictly positive integers. 
	// Zero is not included.
	@Override
	public String[] execute(String[] data) {
		int positiveCount = 0;
		for(String elem : data) {
			if(Integer.parseInt(elem) > 0) {
				positiveCount++;
			}
		}
		
		String[] positives = new String[positiveCount];
		int posIndex = 0;
		for(String elem : data) {
			if(Integer.parseInt(elem) > 0) {
				positives[posIndex] = elem;
				posIndex++;
			}
		}		
		return positives;
	}	
}

class Negative implements Command {

	// Filters the given array to include only strictly negative integers. 
	// Zero is not included. 
	@Override
	public String[] execute(String[] data) {
		int negativeCount = 0;
		for(String elem : data) {
			if(Integer.parseInt(elem) < 0) {
				negativeCount++;
			}
		}
		
		String[] negatives = new String[negativeCount];
		int negIndex = 0;
		for(String elem : data) {
			if(Integer.parseInt(elem) < 0) {
				negatives[negIndex] = elem;
				negIndex++;
			}
		}		
		return negatives;
	}	
}

class Count implements Command {

	// Computes the size of an array of integers.
	@Override
	public String[] execute(String[] data) {
		int count = data.length;
		
		String[] countArray = {Integer.toString(count)};
		return countArray;
	}	
}

class Greater implements Command {
	int greaterNum;
	
	Greater(String num) {
		greaterNum = Integer.parseInt(num);
	}

	// Filters the given array to include integers that are strictly greater than #.
	@Override
	public String[] execute(String[] data) {
		int numGreater = 0;
		for(String elem : data) {
			if(Integer.parseInt(elem) > greaterNum) {
				numGreater++;
			}
		}
		
		String[] greaterArray = new String[numGreater];
		int greaterIndex = 0;
		for(String elem : data) {
			if(Integer.parseInt(elem) > greaterNum) {
				greaterArray[greaterIndex] = elem;
				greaterIndex++;
			}
		}
		return greaterArray;
	}
}

class Lesser implements Command {
	int lesserNum;
	
	Lesser(String num) {
		lesserNum = Integer.parseInt(num);
	}

	// Filters the given array to include integers that are strictly lesser than #.
	@Override
	public String[] execute(String[] data) {
		int numLesser = 0;
		for(String elem : data) {
			if(Integer.parseInt(elem) < lesserNum) {
				numLesser++;
			}
		}
		
		String[] lesserArray = new String[numLesser];
		int lesserIndex = 0;
		for(String elem : data) {
			if(Integer.parseInt(elem) < lesserNum) {
				lesserArray[lesserIndex] = elem;
				lesserIndex++;
			}
		}
		return lesserArray;
	}	
}

class Equal implements Command {
	String equalNum;
	
	Equal(String num) {
		equalNum = num;
	}

	// Filters the given array to include integers that are equal to #.
	@Override
	public String[] execute(String[] data) {
		int numEqual = 0;
		for(String elem : data) {
			if(elem.equals(equalNum)) {
				numEqual++;
			}
		}
		
		String[] equalArray = new String[numEqual];
		int equalIndex = 0;
		for(String elem : data) {
			if(elem.equals(equalNum)) {
				equalArray[equalIndex] = elem;
				equalIndex++;
			}
		}
		return equalArray;
	}	
}

class CmdList implements Command {
	Command[] cmdList;
	
	CmdList(Command[] cmdList) {
		this.cmdList = cmdList;
	}
	
	// Given one or more command options, 
	// returns the array that is the result of executing command
	@Override
	public String[] execute(String[] data) {
		// Loop through the cmdList and execute each Command on the data
		for(Command cmd : cmdList) {
			data = cmd.execute(data);
		}
		return data;
	}
	
}


public class CmdTool {
	
	// Prints the contents of a String[] on one line
	static void printArray(String[] data) {
		for(String elem : data) {
			System.out.print(elem + " ");
		}
		System.out.println();
	}
	
	// Returns the number of command options on the command line
	static int countCmds(String[] args) {
		int count = 0;
		for(int i = 1; i < args.length; i++) {
			if(isCmd(args[i])) {
				count++;
			}
		}
		return count;
	}
	
	// Returns true if String is a valid command (A Helper method)
	static boolean isCmd(String option) {
		String[] cmdTypes = {"sum", "product", "mean", "max", "min", "count", "positive", "negative", "greater", "lesser", "equal"};
		for(String cmd : cmdTypes) {
			if(option.equals(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	// Returns a Command[] containing Command objects corresponding
	// to all the command options on the command line
	static Command[] processCmdList(String[] args) {
		// Create a list of Commands
		Command[] cmdList = new Command[countCmds(args)];
		int cmdListIndex = 0;
		// Loop through args and for ever command found, create a new Command object
		for(int i = 1; i < args.length; i++) {
			// If command is greater, lesser, or equal; add 1 to i so that the next i moves past the required num
			// Use processCmd to get the required Command object
			if(isCmd(args[i]) && args[i].equals("greater") || args[i].equals("lesser") || args[i].equals("equal")) {
				cmdList[cmdListIndex] = processCmd(Arrays.copyOfRange(args, i, args.length));
				cmdListIndex++;
				i++; // want to skip num value after cmd
			}
			else if(isCmd(args[i])) {
				cmdList[cmdListIndex] = processCmd(Arrays.copyOfRange(args, i, args.length));
				cmdListIndex++;
			}
		}
		return cmdList;
	}
	
	// Returns a String[] containing only the integer data
	static String[] processCmdData(String[] args) {
		String[] data = {};
		int cmdCount = countCmds(args);
		if(args[0].equals("-l") || args[0].equals("-list")) {
			// For list of commands:
			// For every command found, subtract from cmdCount until 0
			// Once 0, get the rest of the Array after the last command
			for(int i = 1; i < args.length; i++) {
				if(isCmd(args[i])) {
					cmdCount--;
					// Since greater, lesser, and equal need an additional number, 
					// move the beginning of the data index over by 2
					// the rest move index over 1
					if(cmdCount == 0 && args[i].equals("greater") || args[i].equals("lesser") || args[i].equals("equal")) {
						data = Arrays.copyOfRange(args, i + 2, args.length);
					}
					else if(cmdCount == 0) {
						data = Arrays.copyOfRange(args, i + 1, args.length);
					}
				}				
			}
		}
		else {
			// For only one command:
			data = new String[args.length - 1];
			for(int i = 1; i < args.length; i++) {
				data[i - 1] = args[i];
			}
			return data;
		}
		return data;
	}
	
	// Returns a Command object corresponding to a command option
	static Command processCmd(String[] args) {
		String cmd = args[0];
		
		switch (cmd) {
		case "sum":
			Sum sumCmd = new Sum();
			return sumCmd;
		case "product":
			Product prodCmd = new Product();
			return prodCmd;
		case "mean":
			Mean meanCmd = new Mean();
			return meanCmd;
		case "max":
			Max maxCmd = new Max();
			return maxCmd;
		case "min":
			Min minCmd = new Min();
			return minCmd;
		case "positive":
			Positive posCmd = new Positive();
			return posCmd;
		case "negative":
			Negative negCmd = new Negative();
			return negCmd;
		case "count":
			Count countCmd = new Count();
			return countCmd;
		case "greater":
			Greater greatCmd = new Greater(args[1]);
			return greatCmd;
		case "lesser":
			Lesser lessCmd = new Lesser(args[1]);
			return lessCmd;
		case "equal":
			Equal equalCmd = new Equal(args[1]);
			return equalCmd;
		case "-l":
			CmdList cmdL = new CmdList(processCmdList(args));
			return cmdL;
		case "-list":
			CmdList cmdList = new CmdList(processCmdList(args));
			return cmdList;
		}
		return null;
	}
	
	public static void main(String[] args) {
		// Get the integer data from arguments
		String[] data = processCmdData(args);
		
		// Get command from arguments
		Command command = processCmd(args);
		
		// Process command
		data = command.execute(data);
		
		// Print final Array
		printArray(data);
	}
}

/* 
 * Test Cases:
 * java CmdTool positive -3 -5 -8 0 3 6 8
 * 3 6 8
 * 
 * java CmdTool lesser 10 2 5 9 11 15
 * 2 5 9
 * 
 * java CmdTool -l sum 2 4 6 8
 * 20
 * 
 * java cmdTool -l positive sum -2 -4 5 5
 * 10
 */