import java.util.Random;

public class Revolver extends Gun {
	private int chamberPos;
	
	public Revolver() {
		super(6);
		chamberPos = 0;
	}
	
	@Override
	public boolean pushBullet() {
		if (super.pushBullet()) {
			if (chamberPos < getChamberSize()) turnCCW();
			return true;
		}
		return false;
	}
	
	
	public void turnCW() {
		if (chamberPos > 0) chamberPos--;
		else chamberPos = getChamberSize() - 1;
	}
	
	public void turnCCW() {
		if (chamberPos < getChamberSize() - 1) chamberPos++;
		else chamberPos = 0;
	}
	
	public void spinChamber() {
		Random rng = new Random();
		chamberPos = rng.nextInt(getChamberSize());
	}
}
