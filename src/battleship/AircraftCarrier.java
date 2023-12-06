package battleship;

public class AircraftCarrier extends Boat implements Attacker {
	private boolean hasPlanes = true;
	
	public AircraftCarrier(int tm, Coordinates loc, int dir) {
		super(tm, loc, dir, 5, 1, 1);
	}

	public String getID() {
		return "A" + this.getTeam();
	}

	public String act(int[] choices, World w) {
		String reply = "";
		if (choices[0] == 1) reply += this.move(w) + "\n";
		if (choices[0] == 2) reply += this.turn(-1) + "\n";
		if (choices[0] == 3) reply += this.turn(1) + "\n";
		if (choices[0] == 4) reply += this.attack(w) + "\n";
		return reply;
	}

	public String getActions() {
		String reply = "Choose any of the following actions for the Aircraft Carrier:\r\n"
				+ " 1. Move\n"
				+ " 2. Turn left\n"
				+ " 3. Turn right\n";
			if (this.hasPlanes) {
				reply += " 4. Launch planes\n";
			}
		return reply;
	}
	
	public String attack(World w) { 
		String reply = "Air raid! ";
		boolean noTarget = true;
		double successRate = 1;
		if (this.hasPlanes) {
			for (int dir = 0; dir < 8; dir++) {
				Boat target = w.getOccupant(Boat.sonar(this.getVision(), dir, this.getLocation(), w));
				if (target != null && target != this) {
					int strength = (int)(Math.random()*target.getStrength()) + 1;
					reply += target.takeHit(strength, w) + " ";
					noTarget = false;
				}
 			}
			successRate *= 0.8;
			if (Math.random() > successRate) {
				hasPlanes = false;
				return reply + "\n" + "All planes have been destroyed.";
			}
			if (noTarget) return "There are no boats in range currently.";
			else return reply;
		}
		return this.getID() + " has no planes remaining.";
	}	
}
