package ChessAPI;

import java.nio.channels.SelectableChannel;

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
	int noOfAlive, noOfDead, noOfMoves;
	boolean myTurn;
	public Square originalSquare = null;

	public Player(Color c)
	{
		color = c;
		noOfAlive = 16;
		noOfDead = 0;
		pawn = new Pawn[9];
		knight = new Knight[3];
		bishop = new Bishop[3];
		rook = new Rook[3];
		king = new King(c,null,Type.King);
		queen = new Queen(c,null,Type.Queen);
		
		for (int i = 1; i <= 8; i++){
			pawn[i] = new Pawn(c,null,Type.Pawn);
		}

		for (int i = 1; i <= 2; i++)
		{
			knight[i] = new Knight(c,null,Type.Knight);
			bishop[i] = new Bishop(c,null,Type.Bishop);
			rook[i] = new Rook(c,null,Type.Rook);
		}
	
		noOfMoves = 0;
		myTurn = false;
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

	public boolean moveTo(Square s, Square d) {
		if (s.getPiece() == null) {
			System.out.println("piece object not found at the source location specified");
			return false;
		} else {
			boolean result = true;

			if (d.getPiece() != null && d.getPiece().getColor() == s.getPiece().getColor())
			{
				System.out.println(" Another piece of your color found at the destination location specified");
				return false;
			} else {

				if (d.getPiece() != null && d.getPiece().getColor() != s.getPiece().getColor())
				{
					d.getPiece().setIsDead(true);
					System.out.println(d.getPiece().getColor() + " Player's " + d.getPiece().getType() + " at (" + d.get_x() + ", " + d.get_y() + ") is DEAD !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					d.setPiece(null);
				}
				result = s.getPiece().moveTo(d);
				updateDeadAlive();
			}

			//if a valid move is completed remove piece from souce
			if(result) {
				s.setPiece(null);
				//System.out.println(" move completed from : " + s.get_x() + ", " + s.get_y() + ") to (" + d.get_x() + ", " + d.get_y() + ") ");
			}
			myTurn = false;

			return result;
		}
	}

	//returns true if the player's King is in Check
	//Logic: if a valid move of any of the opponents piece can replace the King
	//than it is in check
	public boolean isInCheck() {
		boolean result = false;
		Square currentBoard[][] = new Square[10][10];

		for (int i = 1; i <= 8; i++)
			for (int j = 1; j <=8; j++)
			{
				currentBoard[i][j] = Board.getBoard(i, j);
			}

		Square kingPosition = this.king.getSquare(); //get Kings current position

		if (kingPosition != null) {
			
			for (int i=1; i<Board.ROWS ; i++){
				for (int j=1; j<Board.COLS ; j++) {
					Square currentSquare = currentBoard[i][j];
					if (currentSquare.getPiece() != null) {
						if (currentSquare.getPiece().getColor() != this.color) {
							
							//if I am here, this means that the Board's square[i][j] has opponents piece
							//now I need to check if this Piece's valid move can capture my King or not
							//If yes, I am in check else I am not
							if (currentSquare.getPiece().validateMove(currentSquare, kingPosition)) {
								//the piece on square[i][j] can Kill my King
								result = true;
								return result;
							}
						}
					}					
				}//end inner for
			}//end outer for
			
		}
		return result;
	}//end isInCheck()
	
		
	//Randomly select a Piece and return a random Square where it can move legally, out of all the possible legal moves
	//If this method returns null, than there is no valid move possible for the randomly selected Piece
	//Suggestion: Re-invoke this method till it returns a non-null value
	public Square randomMove() {
		int min = 1;
		int max = 16;
		
		Square suggestedSquare = null;

		// If King is InCheck condition Move the King

		if (this.isInCheck() == true)
		{
			do {
				originalSquare = this.king.getSquare();
				suggestedSquare = this.king.selectRandomSquare();			
			} while (suggestedSquare == null);
			return suggestedSquare;
		}

		do {
		suggestedSquare = null;
		int randomNum = min + (int) ( Math.random() * ((max - min)+1) );

		if(randomNum >=1 && randomNum <= 8) {
			//if random number is between 1-8, select the corresponding Pawn out of 8
			if (this.pawn[randomNum].isPieceDead() == true)
				suggestedSquare = null;
			else {
				originalSquare = this.pawn[randomNum].getSquare();
				suggestedSquare = originalSquare.getPiece().selectRandomSquare();
			}
		} else if (randomNum == 9) {

			if (this.rook[1].isPieceDead() == true)
				suggestedSquare = null;
			else {
					originalSquare = this.rook[1].getSquare();
					suggestedSquare = this.rook[1].selectRandomSquare();
				}
		} else if (randomNum == 10) {
			if (this.rook[2].isPieceDead() == true)
				suggestedSquare = null;
			else {
				originalSquare = this.rook[2].getSquare();
				suggestedSquare = this.rook[2].selectRandomSquare();
			}
		} else if (randomNum == 11) {
			if (this.bishop[1].isPieceDead() == true)
				suggestedSquare = null;
			else {
				originalSquare = this.bishop[1].getSquare();
				suggestedSquare = this.bishop[1].selectRandomSquare();
			}
		} else if (randomNum == 12) {
			if (this.bishop[2].isPieceDead() == true)
				suggestedSquare = null;
			else {
				originalSquare = this.bishop[2].getSquare();
				suggestedSquare = this.bishop[2].selectRandomSquare();
			}
		} else if (randomNum == 13) {
			if (this.knight[1].isPieceDead() == true)
				suggestedSquare = null;
			else {
				originalSquare = this.knight[1].getSquare();
				suggestedSquare = this.knight[1].selectRandomSquare();
			}
		} else if (randomNum == 14) {
			if (this.knight[2].isPieceDead() == true)
				suggestedSquare = null;
			else {
				originalSquare = this.knight[2].getSquare();
				suggestedSquare = this.knight[2].selectRandomSquare();
			}
		} else if (randomNum == 15) {
			// Don't move King unless it is at Check condition

			/*
			if (this.king.isPieceDead() == true)
				suggestedSquare = null;
			else {	
				originalSquare = this.king.getSquare();
				suggestedSquare = this.king.selectRandomSquare();
			}
			*/
		} else if (randomNum == 16) {
			if (this.queen.isPieceDead() == true)
				suggestedSquare = null;
			else {
				originalSquare = this.queen.getSquare();
				suggestedSquare = this.queen.selectRandomSquare();
			}
		}

	} while (suggestedSquare == null);

		return suggestedSquare;
	}

	public int getNoMoves()
	{
		return noOfMoves;
	}

	public void incMoves()
	{
		noOfMoves++;
	}

	public void updateDeadAlive()
	{
		boolean debug = false;

		noOfDead = 0;
		if (king.isPieceDead() == true)
		{
			if (debug == true)
				System.out.println(" King is DEAD ");
			noOfDead++;
		}
		
		if (queen.isPieceDead() == true)
		{
			if (debug == true)
				System.out.println(" Queen is DEAD ");
			noOfDead++;
		}
		
		for (int i = 1; i <= 8; i++){
			if (pawn[i].isPieceDead() == true)
			{
				if (debug == true)
					System.out.println(" Pawn " + i + " is DEAD ");
				noOfDead++;
			}
		}

		for (int i = 1; i <= 2; i++)
		{
			if (knight[i].isPieceDead() == true)
			{
				if (debug == true)
					System.out.println(" Knight " + i + " is DEAD ");
				noOfDead++;
			}
			if (bishop[i].isPieceDead() == true)
			{
				if (debug == true)
					System.out.println(" Bishop " + i + " is DEAD ");
				noOfDead++;
			}
			if (rook[i].isPieceDead() == true)
			{
				if (debug == true)
					System.out.println(" Rook " + i + " is DEAD ");
				noOfDead++;
			}
		}

		noOfAlive = 16 - noOfDead;
		//System.out.println(" noOfDead  " + noOfDead);
	}

	public boolean getTurn()
	{
		return myTurn;
	}

	public boolean isGameOver()
	{
		boolean result = false;
		
		if (this.king.isPieceDead() == true)
		{
			System.out.println(" The Game is Over.. \n Player (" + this.getColor() + ") is lost.. ");
			result = true;
		}
		return result;
	}
	//other functions needed by the Player class....
}
