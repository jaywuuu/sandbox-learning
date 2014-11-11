
public class Gun {
	private int chamber[];
	private int chamberSize;
	private int bullets;
	private boolean hammerState;
	
	public Gun(int chamberSize) {
		this.chamberSize = chamberSize;
		chamber = new int[chamberSize];
		for (int i = 0; i < chamberSize; i++) chamber[i] = 0;
		bullets = 0;
		hammerState = false;
	}
	
	public int bullets() {
		return bullets;
	}
	
	public int getChamberSize() {
		return chamberSize;
	}
	
	// returns bullets not loaded
	public int loadGun(int numBullets) {
		while (pushBullet() && numBullets > 0) numBullets--;
		return numBullets;
	}
	
	// returns if successful
	public boolean pushBullet() {
		if (isFull()) return false;
		if (chamber[bullets] == 0) {
			chamber[bullets] = 1;
			bullets++;
		}
		return true;
	}
	
	
	// pops the type of bullet back
	public int popBullet() {
		if (isEmpty()) return 0;
		int retval = chamber[bullets-1];
		chamber[bullets-1] = 0;
		bullets--;
		return retval;
	}
	
	public boolean isFull() {
		return bullets == chamberSize;
	}
	
	public boolean isEmpty() {
		return bullets == 0;
	}
	
	public boolean fire() {
		if (popBullet() == 1) return true;
		return false;
	}
	
	public boolean isHammerBack() {
		return hammerState;
	}
	
	// take a look at the bullet in position i.
	public int peek(int i) {
		if (i >= 0 && i < chamberSize) return chamber[i];
		return -1;
	}
	
	/* testing */
	public static void main(String [] args) {
		Gun gun = new Gun(10);
		int bulletsLeft = gun.loadGun(12);
		System.out.println("Bullets loaded: " + gun.bullets());
		System.out.println("Bullets left: " + bulletsLeft);
		System.out.println("Empty? " + gun.isEmpty());
		System.out.println("Full? " + gun.isFull());
		
		for (int i = 0; i < 10; i++) {
			System.out.println("pop bullet: " + gun.popBullet());
			System.out.println("Bullets loaded: " + gun.bullets());
		}
		System.out.println("push bullet: " + gun.pushBullet());
		System.out.println("Bullets loaded: " + gun.bullets());
		System.out.println("pop bullet: " + gun.popBullet());
		System.out.println("Bullets loaded: " + gun.bullets());
	}
}
