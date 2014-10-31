
public class OneCounter {
	private int number;
	private int binCount;
	private int decCount;
	
	public OneCounter(int num) {
		this.number = num;
		binCount = 0;
		decCount = 0;
	}
	
	public int getBinCount() {
		return binCount;
	}
	
	public int getDecCount() {
		return decCount;
	}
	
	// Bitshift and AND with 1.
	public void countBinary() {
		int count = 0;
		int a = number;
		
		while (a > 0) {
			if ((a & 1) == 1) count++;
			a = a >> 1;
		}
		
		binCount = count;
	}
	
	/* chop off last digit by rounding down and subtracting that value from
	 * the original number, giving the chopped off digit.  Check if 1 and repeat.
	 */
	public void countDecimal() {
		int a = number;
		int count = 0;
		
		while (a> 0) {
			if (a % 10 == 1) count++;
			a = a/10;
		}
		
		decCount = count;
	}
}
