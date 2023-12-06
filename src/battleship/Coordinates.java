package battleship;

public class Coordinates {
	private int x; 
	private int y;
	
	public Coordinates() {
		this.x = 0;
		this.y = 0;
	}
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "" + (char)(this.y + 65) + (this.x + 1);
	}
	
	public static void main(String[] args) {
		Coordinates c = new Coordinates(7, 2);
		System.out.println(c);
	}
}