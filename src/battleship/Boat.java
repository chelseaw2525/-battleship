package battleship;

public abstract class Boat {

	private int team;
	private Coordinates location;
	private int direction;
	private int health;
	private int strength;
	private int vision;
	
	public Boat(int tm, Coordinates loc, int dir, int hp, int atk, int vis) {
		this.team = tm;
		this.location = loc;
		this.direction = dir;
		this.health = hp;
		this.strength = atk;
		this.vision = vis;
	}

	public int getTeam() {
		return team;
	}

	public Coordinates getLocation() {
		return location;
	}

	public String getDirection() {
		char letter = 'x';
		if (this.direction == 0) letter = '\u2191'; //N
		if (this.direction == 1) letter = '\u2197'; //NE
		if (this.direction == 2) letter = '\u2192'; //E
		if (this.direction == 3) letter = '\u2198'; //SE
		if (this.direction == 4) letter = '\u2193'; //S
		if (this.direction == 5) letter = '\u2199'; //SW
		if (this.direction == 6) letter = '\u2190'; //W
		if (this.direction == 7) letter = '\u2196'; //NW
		return "" + letter;
	}
	
	public int getDirectionInt() {
		return this.direction;
	}
	
	public int getHealth() {
		return health;
	}

	public int getStrength() {
		return strength;
	}

	public int getVision() {
		return vision;
	}
	
	public void setLocation(Coordinates c) {
		this.location = c;
	}
	
	public abstract String getID();
	
	public abstract String act(int[] choices, World w);
	
	public abstract String getActions();
	
	public String move(World w) {
		Coordinates og = this.location;
		Coordinates target = w.getAdjacentLocation(og, this.direction);
		if (!w.isLocationValid(target)) {
			return this.getID() + " cannot move off the map.";
		}
		if (w.setOccupant(this, target)) {
			w.resetOccupant(og);
			this.setLocation(target);			
			return this.getID() + " moves from " + og + " to " + target + ".";
		}
		return this.getID() + " cannot move to " + target + " as it is occupied.";
	}
	
	public String turn(int rotation) {
		String r = null;
		if (rotation == 1) {
			if (this.direction < 7) {
				this.direction++;
			}
			else this.direction = 0;
			r = "right";
		}
		if (rotation == -1) {
			if (this.direction > 0) {
				this.direction--;
			}
			else this.direction = 7;
			r = "left";
		}
		return this.getID() + " turns " + r + ", now facing " + this.getDirection();
	}
	
	public String takeHit(int atk, World w) {
		this.health -= atk;
		if (this.health <= 0) {
			this.health = 0;
			w.resetOccupant(this.getLocation());
			return this.getID() + " is sunk!";
		}
		else return this.getID() + " took " + atk + " damage.";
	}
	
	public String toString() {
		return this.getID();
	}
	
	public static Coordinates sonar(int vis, int dir, Coordinates loc, World w) {
		int r = loc.getY();
		int c = loc.getX();
		for (int i = 1; i <= vis; i++) {
			for (int j = 1; j <= vis; j++) {
				if (dir == World.NORTHWEST && w.isLocationOccupied(new Coordinates(c-i, r-j))) {
					return new Coordinates(c-i, r-j);
				}
				if (dir == World.WEST && w.isLocationOccupied(new Coordinates(c-i, r))) {
					return new Coordinates(c-i, r);
				}
				if (dir == World.SOUTHWEST && w.isLocationOccupied(new Coordinates(c-i, r+j))) {
					return new Coordinates(c-i, r+j);
				}
				if (dir == World.NORTH && w.isLocationOccupied(new Coordinates(c, r-j))) {
					return new Coordinates(c, r-j);
				}
				if (dir == World.SOUTH && w.isLocationOccupied(new Coordinates(c, r+j))) {
					return new Coordinates(c, r+j);
				}
				if (dir == World.NORTHEAST && w.isLocationOccupied(new Coordinates(c+i, r-j))) {
					return new Coordinates(c+i, r-j);
				}
				if (dir == World.EAST && w.isLocationOccupied(new Coordinates(c+i, r))) {
					return new Coordinates(c+i, r);
				}
				if (dir == World.SOUTHEAST && w.isLocationOccupied(new Coordinates(c+i, r+j))) {
					return new Coordinates(c+i, r+j);
				}
			}
		}
		return loc;
	}
	
}

