package ChessAPI;

public class Player {

	public enum Color{
		black,white
	}
	
	Pawn pawn[];
	Knight knight[];
	Bishop bishop[];
	Rook rook[];
	King king;
	Queen queen;
	private Color color;
	int noOfAlive, noOfDead;
	
	public Player(Color c)
	{
		color = c;
		noOfAlive = 16;
		noOfDead = 0;
		pawn = new Pawn[8];
		knight = new Knight[2];
		bishop = new Bishop[2];
		rook = new Rook[2];
		king = new King();
		queen = new Queen();
		
		for (int i = 0; i < 8; i++)
			pawn[i] = new Pawn();

		for (int i = 0; i < 2; i++)
		{
			knight[i] = new Knight();
			bishop[i] = new Bishop();
			rook[i] = new Rook();
		}
	
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getNoOfAlive() {
		return noOfAlive;
	}

	public int getNoOfDead() {
		return noOfDead;
	}
	
	//other functions needed by the Player class....
	
}
