package battleship;

public class Battleship extends Boat implements Attacker{

	public Battleship(int tm, Coordinates loc, int dir) {
		super(tm, loc, dir, 4, 3, 1); 
	} 

	public String attack(World w) {
		Boat target = w.getOccupant(Boat.sonar(this.getVision(), this.getDirectionInt(), this.getLocation(), w));
		if (target != null && target != this) {
			int strength = (int)(Math.random()*target.getStrength()) + 1;
				return "Fire cannons! " + target.takeHit(strength, w) + "\n" + target.takeHit(strength, w);						
			}
			return "There are no boats in range currently.";
	}

	public String getID() {
		return "B" + this.getTeam();
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
		return "Choose any of the following actions for the Battleship:\r\n"
				+ " 1. Move\n"
				+ " 2. Turn left\n"
				+ " 3. Turn right\n"
				+ " 4. Attack\n";
	}
	
}
