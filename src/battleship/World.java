package battleship;

public class World {

	public static final int NORTH = 0;
	public static final int NORTHEAST = 1;
	public static final int EAST = 2;
	public static final int SOUTHEAST = 3; 
	public static final int SOUTH = 4; 
	public static final int SOUTHWEST = 5; 
	public static final int WEST = 6; 
	public static final int NORTHWEST = 7;
	
	
	private int width;
	private int height;
	Boat[][] map;
	
	@SuppressWarnings("unused")
	public World(int w, int h) {
		if (h < 4) this.height = 4;
		if (h > 10) this.height = 10;
		if (w < 4) this.width = 4;
		if (w > 10) this.width = 10;
		else {
			this.height = h;
			this.width = w;
		}
		map = new Boat[h][w];
		for (Boat[] section: map) {
			for (Boat board : section) {
				board = null;
			}
		}
	}
	
	public static void main(String[] args) {
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Boat getOccupant(Coordinates c) {
		return map[c.getY()][c.getX()];
	}
	
	public boolean isLocationValid(Coordinates c) {
		if (c == null) {
			return false;
		}
		int x = c.getX();
		int y = c.getY();
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return false;
		}
		return true;
	}
	
	public boolean isLocationOccupied(Coordinates c) {
		if (isLocationValid(c)) {
			return (map[c.getY()][c.getX()] != null);
		}
		return false;
	}
	
	public boolean setOccupant(Boat b, Coordinates c) {
		if (isLocationOccupied(c)) {
			return false;
		}
		map[c.getY()][c.getX()] = b;	
		return true;
	}
	
	public void resetOccupant(Coordinates c) {
		map[c.getY()][c.getX()] = null;	
	}
	
	public Coordinates getAdjacentLocation(Coordinates c, int dir) {
		int x = c.getX();
		int y = c.getY();
		if (x <= 0 || x >= this.getWidth() || y <= 0 || y >= this.getHeight()) {
			return null;
		}		
		if (dir == 0) {
			y = c.getY()-1;
			x = c.getX();
		}
		if (dir == 1) {
			y = c.getY()-1;
			x = c.getX()+1;
		}
		if (dir == 2) {
			y = c.getY();
			x = c.getX()+1;
		}
		if (dir == 3) {
			y = c.getY()+1;
			x = c.getX()+1;
		}
		if (dir == 4) {
			y = c.getY()+1;
			x = c.getX();
		}
		if (dir == 5) {
			y = c.getY()+1;
			x = c.getX()-1;
		}
		if (dir == 6) {
			y = c.getY();
			x = c.getX()-1;
		}
		if (dir == 7) {
			y = c.getY()-1;
			x = c.getX()-1;
		}
		Coordinates ans = new Coordinates(x, y);
		return ans;
	}
	
	public String drawTeamMap(Boat[] set, int view) {
		int rows = this.getHeight();
		int columns = this.getWidth();
		Boat target;
		String[][] board = new String[rows][columns];
		int[] vis = new int[set.length];
		for (int h = 0; h < set.length; h++) {
			Boat b = set[h];
			vis[h] = b.getVision();
		}
		for (int r = 0; r < rows; r++) {
			if (view == 1) {
				for (int c = 0; c < columns; c++) {
					board[r][c] = "###";
				}
			}
			else {
				for (int c = 0; c < columns; c++) {
					target = map[r][c];	
					if (target != null && target.getTeam() == set[0].getTeam()) {
						if (view == 2) board[r][c] = target.getDirection() + target.toString();
						if (view == 3) board[r][c] = target.getHealth() + target.toString();
					}
					else if (range(set, r, c)) {
						if (target != null) {
							if (view == 2) board[r][c] = target.getDirection() + target.toString();
							if (view == 3) board[r][c] = target.getHealth() + target.toString();
						}
						else board[r][c] = "~~~";
					} 
					else {
						board[r][c] = "###";
					}
				}
			}
		}
		String ans = " @ ";
		int r = 0;
		for (int num = 1; num <= columns; num++) {
			ans += " " + num + " ";
		}
		for (String i[] : board) {
			ans += "\n";
			ans += " " + (char)(r + 65) + " ";
			for (String j : i) {
				ans += j;
			}
			r++;
		}
		return ans;
	}

	public boolean range(Boat[] team, int r, int c) {
		for (Boat b : team) {
			int vis = b.getVision();
			for (int i = 0; i <= vis; i++) {
				for (int j = 0; j <= vis; j++) {
					if (this.isLocationValid(new Coordinates(c-i, r-j))) {
						if (map[r-j][c-i] == b) return true;
					}
					if (this.isLocationValid(new Coordinates(c-i, r))) {
						if (map[r][c-i] == b) return true;
					}
					if (this.isLocationValid(new Coordinates(c-i, r+j))) {
						if (map[r+j][c-i] == b) return true;
					}
					if (this.isLocationValid(new Coordinates(c, r-j))) {
						if (map[r-j][c] == b) return true;
					}
					if (this.isLocationValid(new Coordinates(c, r+j))) {
						if (map[r+j][c] == b) return true;
					}
					if (this.isLocationValid(new Coordinates(c+i, r-j))) {
						if (map[r-j][c+i] == b) return true;
					}
					if (this.isLocationValid(new Coordinates(c+i, r))) {
						if (map[r][c+i] == b) return true;
					}
					if (this.isLocationValid(new Coordinates(c+i, r+j))) {
						if (map[r+j][c+i] == b) return true;
					}
				}
			}
		}
		return false;
	}
	
}
