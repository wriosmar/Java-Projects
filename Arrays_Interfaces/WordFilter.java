package PA4;
 // 3.1 DONE
public class WordFilter {
	public static void main(String[] args) {
		// if array is empty
		if(args.length == 0) {
			System.out.println("Empty Array");
		} else {
			// check how many palindromes are in array
			int palLength = 0;
			for(int i = 0; i < args.length; i++) {
				// start index
				int s = 0;
				// end index
				int e = args[i].length() - 1;
				
				// move in from start to end 
				while(s < e) {
					// check if s and e are equal
					// if not break
					if(args[i].charAt(s) != args[i].charAt(e)) {
						palLength--;
					}				
					// increment start and decrement end (to move inward)
					s++;
					e--;
				}
				palLength++;
			}
			
			String[] palindromes = new String[palLength];
			int indexTracker = 0;
			// loop again to add palindromes to array
			for(int j = 0; j < args.length; j++) {
				// start index
				int s = 0;
				// end index
				int e = args[j].length() - 1;
				// boolean that marks a word a palindrome or not
				boolean isPal = true;
				
				// move in from start to end
				while(s < e) {
					// check if s and e are equal
					// if not mark word as false (not palindrome)
					if(args[j].charAt(s) != args[j].charAt(e)) {
						isPal = false;
					}
					// increment start and decrement end
					s++;
					e--;
				}
				// if word is a palindrome add to array
				if(isPal == true) {
					palindromes[indexTracker] = args[j];
					indexTracker++;
				}
			}
			
			for(int k = 0; k < palindromes.length; k++) {
				System.out.println(palindromes[k]);
			}
		}
	}		
}
