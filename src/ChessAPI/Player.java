package ChessAPI;

import ChessAPI.Piece.Color;
import ChessAPI.Piece.Type;

public class Player {

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
		king = new King(c,null,Type.King);
		queen = new Queen(c,null,Type.Queen);
		
		for (int i = 0; i < 8; i++){
			pawn[i] = new Pawn(c,null,Type.Pawn);
		}

		for (int i = 0; i < 2; i++)
		{
			knight[i] = new Knight(c,null,Type.Knight);
			bishop[i] = new Bishop(c,null,Type.Bishop);
			rook[i] = new Rook(c,null,Type.Rook);
		}
	
	}
	
	void initPlayer() {
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
