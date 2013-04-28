package ChessAPI;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

	public enum Color{
		black,white
	}
	public enum Type{
		King,Queen,Knight,Pawn,Rook,Bishop
	}

	private Color color;	//color of the Piece
	private Square square;	//current Square the piece is on the Board
	private Type type;      //stores type of the piece
	private boolean isPieceDead;
	public String promotion="";
	public int promo_pos = -1;
	public Move prev_move;

	public boolean enpass;
	public int en_x, en_y;

	public Piece() {
		this.prev_move = null;
		this.enpass = false;
	}

	public Piece(Color c, Square s, Type t) {
		this.color = c;
		this.square = s;
		this.type = t;
		this.isPieceDead = false;
		this.prev_move = null;
		this.enpass = false;
	}

	public void setColor(Color c) {
		this.color = c;
	}
	public Color getColor() {
		return this.color;
	}

	public void setSquare(Square s) {
		this.square = s;
	}
	public Square getSquare() {
		return this.square;
	}

	public void setType(Type t) {
		this.type = t;
	}

	public Type getType() {
		return this.type;
	}

	public boolean isPieceDead()
	{
		return isPieceDead;
	}

	public void setIsDead(boolean dead)
	{
		isPieceDead = dead;
	}

	
	//Gives the Heuristic for a Move starting from the Piece's currentPosition to the Destination square passed as a parameter to this method 
	public float getHeuristic(Square destination) {

		Square currentBoard[][] = new Square[10][10];	
		for (int i = 1; i <= 8; i++)
			for (int j = 1; j <= 8; j++)
			{
				currentBoard[i][j] = Board.getBoard(i, j);
				currentBoard[i][j].setPiece(Board.getBoard(i, j).getPiece());
				//currentBoard[i][j] = new Square();
				//currentBoard[i][j].set_x(Board.getBoard(i, j).get_x()); 
				//currentBoard[i][j].set_y(Board.getBoard(i, j).get_y());
				//currentBoard[i][j].setPiece(Board.getBoard(i, j).getPiece());
				//currentBoard[i][j].setColor(Board.getBoard(i, j).getColor());
			}

		float materialValue = 0;

		for(int i=1; i<Board.ROWS+1; i++  ){
			for(int j=1; j<Board.COLS+1; j++  ){

				Piece tempPiece = currentBoard[i][j].getPiece();
				if( tempPiece != null){
					//a piece is present on square i,j

					if(tempPiece.getColor() == Color.white) {
						//its a WHITE Piece
						//determine the type of piece to add its corresponding material value
						if(tempPiece.getType() == Type.King)
							materialValue = materialValue + 200;
						else if(tempPiece.getType() == Type.Queen)
							materialValue = materialValue + 9;
						else if(tempPiece.getType() == Type.Rook)
							materialValue = materialValue + 5;
						else if(tempPiece.getType() == Type.Bishop)
							materialValue = materialValue + 3;
						else if(tempPiece.getType() == Type.Knight)
							materialValue = materialValue + 3;
						else if(tempPiece.getType() == Type.Pawn)
							materialValue = materialValue + 1;
						

					}else if(tempPiece.getColor() == Color.black) {
						//its a BLACK Piece
						//determine the type of piece to add its corresponding material value
						if(tempPiece.getType() == Type.King)
							materialValue = materialValue - 200;
						else if(tempPiece.getType() == Type.Queen)
							materialValue = materialValue - 9;
						else if(tempPiece.getType() == Type.Rook)
							materialValue = materialValue - 5;
						else if(tempPiece.getType() == Type.Bishop)
							materialValue = materialValue - 3;
						else if(tempPiece.getType() == Type.Knight)
							materialValue = materialValue - 3;
						else if(tempPiece.getType() == Type.Pawn)
							materialValue = materialValue - 1;

					}
				}
			}//end inner for
		}//end outer for

		return materialValue;
	}

	//returns a List of valid moves for a piece with its heuristics 
	public List<Move> validMoves(){

		Square currentBoard[][] = new Square[10][10];	
		for (int i = 1; i <= 8; i++)
			for (int j = 1; j <= 8; j++)
			{
				currentBoard[i][j] = Board.getBoard(i, j); 
				currentBoard[i][j].setPiece(Board.getBoard(i, j).getPiece());
			}

		List<Move> validMovesList = new ArrayList<Move>();

		for(int i=1; i<Board.ROWS+1; i++  ) {
			for(int j=1; j<Board.COLS+1; j++  ){
				if( this.validateMove(this.getSquare(), currentBoard[i][j])) {

					//we can move from current position of this piece to Square(i,j) on the Board
					float currentHeuristic = this.getHeuristic(currentBoard[i][j]);
					//Now check if this move is Heuristically better than bestMove we have.
					Move validMove = new Move(currentBoard[i][j],currentHeuristic,this.getSquare());
					validMovesList.add(validMove);
				}
			}
		}

		promotion = "";
		promo_pos = -1;
		return validMovesList;	
	}

	//Move the piece to Destination square is the move is valid
	public abstract boolean moveTo(Square destination);
	public abstract boolean moveTonoCheck(Square destination);

	//validate and return true or false
	public abstract boolean validateMove(Square source, Square destination);

}