package battleship;

public class Submarine extends ScoutBoat implements Attacker{
	int numOfTorpedos;
	
	public Submarine(int tm, Coordinates loc, int dir, int torpedos) {
		super(tm, loc, dir, 3, 2);
		this.numOfTorpedos = torpedos;
		// TODO Auto-generated constructor stub
	}
	
	public String getID() {
		return "S" + this.getTeam();
	}
	
	public String getActions() {
		String reply = "Choose any of the following actions for the Submarine:\n"
				+ " 1. Move\n"
				+ " 2. Turn left\n"
				+ " 3. Turn right\n"
				+ " 4. Submerge\n";
			if (this.numOfTorpedos >= 1) {
				reply += " 5. Fire torpedoes\n";
			}
		return reply;
	}

	public String act(int[] choices, World w) {
		String reply = "";
		if (choices[0] == 1) reply += this.move(w) + "\n";
		if (choices[0] == 2) reply += this.turn(-1) + "\n";
		if (choices[0] == 3) reply += this.turn(1) + "\n";
		if (choices[0] == 4) reply += this.submerge(w) + "\n";
		if (choices[0] == 5) reply += this.attack(w) + "\n";
		return reply;
	}

	public String submerge(World w) {
		int curX = this.getLocation().getX();
		int curY = this.getLocation().getY();
		int dx;
		int dy;
		int sign;
		Coordinates og = this.getLocation();
		Coordinates target = og;
		while (target == og || this.inRadius(w, og, target) || !w.isLocationValid(target)) {
			//System.out.println("2345678987654");
			sign = (int)(Math.pow((-1), (int)(Math.random()*2)+1));
			dx = sign * (int)(Math.random() * (w.getWidth()) - curX);
			sign = (int)(Math.pow((-1), (int)(Math.random()*2)+1));
			dy = sign * (int)(Math.random() * (w.getHeight()) - curY);
			target = new Coordinates(curX+dx, curY+dy);
		}
		w.setOccupant(this, target);
		w.resetOccupant(og);
		this.setLocation(target);			
		return this.getID() + " moves from " + og + " to " + target + ".";
	}

	public boolean inRadius(World w, Coordinates og, Coordinates target) {
		for (int dir = 0; dir < 9; dir++) {
			if (w.getAdjacentLocation(og, dir) == target) {
				return true;
			}
		}
		return false;
	}
	
	public String attack(World w) {
		Boat target = w.getOccupant(Boat.sonar(this.getVision(), this.getDirectionInt(), this.getLocation(), w));
		if (this.numOfTorpedos > 0) {
			if (target != null && target != this) {
				int strength = (int)(Math.random()*target.getStrength()) + 1;
				return "Fire torpedos! " + target.takeHit(strength, w);
			}
			return "There are no boats in range currently.";
		}
		return this.getID() + " has no torpedos remaining.";
	}
	
	
	
}