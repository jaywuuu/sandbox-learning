import java.util.Hashtable;


public class ArrayAndStrings {
	
	public static void main(String[] args) {
		P2 p2 = new P2("this is a string\n");
		p2.run();
	}
	
	private static class P1 {
		private String inputString;
		private Hashtable<Character, Integer> htable;
		
		private P1(String str) {
			inputString = str;
			htable = new Hashtable<Character, Integer>(str.length());
		}
		
		public void run() {
			System.out.println("All unique characters? " + isAllCharUnique());
		}
		
		// look at htable to see if unique or not
		public boolean isAllCharUnique() {
			for (int i = 0; i < inputString.length(); i++) {
				char c = inputString.charAt(i);
				if (htable.containsKey(c)) return false;
				else htable.put(c,  1);
			}
			return true;
		}
	}
	
	private static class P2 {
		private char[] inputString;
		private int len; 
		
		private P2(String str) {
			inputString = str.toCharArray();
			len = str.length();
		}
		
		public void run() {
			System.out.println("Input string: " + new String(inputString));
			reverse();
			System.out.println("Reverse string: " + new String(inputString));
		}
		
		private void reverse() {
			int j = len-2; // -2 to ignore null ending for C-style strings or line break
			for (int i = 0; i < j; i++, j--) {
				char swap = inputString[i];
				inputString[i] = inputString[j];
				inputString[j] = swap;
			}
		}
	}
}
