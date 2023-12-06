package battleship;

public class Destroyer extends Boat implements Attacker{

	public Destroyer(int tm, Coordinates loc, int dir) {
		super(tm, loc, dir, 2, 2, 1);
		// TODO Auto-generated constructor stub
	}

	public String attack(World w) {
		Boat target = w.getOccupant(Boat.sonar(this.getVision(), this.getDirectionInt(), this.getLocation(), w));
		if (target != null && target != this) {
			int strength = (int)(Math.random()*target.getStrength()) + 1;
				return "Fire cannons! " + target.takeHit(strength, w);
			}
			return "There are no boats in range currently.";
	}

	public String getID() {
		return "D" + this.getTeam();
	}

	public String act(int[] choices, World w) {
		String reply = "";
		for (int i = 0; i < choices.length; i++) {
			if (choices[i] == 1) reply += this.move(w) + "\n";
			if (choices[i] == 2) reply += this.turn(-1) + "\n";
			if (choices[i] == 3) reply += this.turn(1) + "\n";
			if (choices[i] == 4) reply += this.attack(w) + "\n";
		}
		return reply;
	}


	public String getActions() {
		return "Choose two of the following actions for the Destroyer:\n"
				+ " 1. Move\n"
				+ " 2. Turn left\n"
				+ " 3. Turn right\n"
				+ " 4. Attack\n";
	}

	public String takeHit(int atk, World w) {
		double hit = Math.random();
		if (hit < 0.5) {
			return super.takeHit(atk, w);
		}
		return this.toString() + " avoids the attack!";
	}
}
