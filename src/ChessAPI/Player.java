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
	int noOfAlive, noOfDead, noOfMoves;
	boolean myTurn;

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
	
	public boolean moveTo(Square s, Square d){
		if(s.getPiece()==null){
			System.out.println("piece object not found at the source location specified");
			return false;
		}else{
			boolean result = true;
			
			if (d.getPiece() != null)
			{
				System.out.println(" Another piece found at the destination location specified");
				return false;
			} else {
				result = s.getPiece().moveTo(d);
			}
			//if a valid move is completed remove piece from souce
			if(result){
				s.setPiece(null);
				System.out.println("move completed");
			}
			myTurn = false;
			return result;
		}
	}
	
	//returns true if the player's King is in Check
	//Logic: if a valid move of any of the opponents piece can replace the King
	//than it is in check
	public boolean isInCheck() {
		Boolean isInCheck = false;
		Board currentBoard = Board.getBoardInstance(); //get the Board
		Square kingPosition = this.king.getSquare(); //get Kings current position
		
		if (kingPosition != null) {
			
			for (int i=1; i<Board.ROWS ; i++){
				for (int j=1; j<Board.COLS ; j++) {
					
					Square currentSquare = currentBoard.getSquare(i, j);
					if (currentSquare.getPiece() != null ){
						if (currentSquare.getPiece().getColor() != this.color) {
							
							//if I am here, this means that the Board's square[i][j] has opponents piece
							//now I need to check if this Piece's valid move can capture my King or not
							//If yes, I am in check else I am not
							if (currentSquare.getPiece().validateMove(currentSquare, kingPosition)) {
								//the piece on square[i][j] can Kill my King
								isInCheck = true;
								return isInCheck;
							}
						}
					}
					
				}//end inner for
			}//end outer for
			
		}
		
		return isInCheck;
	}//end isInCheck()
	
	
	public int getNoMoves()
	{
		return noOfMoves;
	}

	public void incMoves()
	{
		noOfMoves++;
	}

	public boolean getTurn()
	{
		return myTurn;
	}
	//other functions needed by the Player class....
}
