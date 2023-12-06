package battleship;

public abstract class ScoutBoat extends Boat {

	public ScoutBoat(int tm, Coordinates loc, int dir, int hp, int vis) {
		super(tm, loc, dir, hp, 1, vis);
	}
	
	public String takeHit(int atks, World w) {
		double hit = Math.random();
		if (hit < 0.25) {
			return super.takeHit(atks, w);
		}
		return this.toString() + " has avoided the attack!";
	}
	
	
	
}
