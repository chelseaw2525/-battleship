package battleship;

public class Cruiser extends ScoutBoat {

	public Cruiser(int tm, Coordinates loc, int dir) {
		super(tm, loc, dir, 3, 3);
		// TODO Auto-generated constructor stub
	}

	public String getID() {
		return "C" + this.getTeam();
	}

	public String getActions() {
		return "Choose two of the following actions for the Cruiser:\n"
				+ " 1. Move\n"
				+ " 2. Turn left\n"
				+ " 3. Turn right\n"
				+ " 4. Forfit turn\n";
	}
	
	public String act(int[] choices, World w) {
		String reply = "";
		for (int i = 0; i < 2; i++) {
			if (choices[i] == 1) reply += this.move(w) + "\n";
			if (choices[i] == 2) reply += this.turn(-1) + "\n";
			if (choices[i] == 3) reply += this.turn(1) + "\n";
		}
		return reply;
		
	}

}
