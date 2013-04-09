package ChessAPI;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	public Pawn() {
		super();
	}
	
	public Pawn(Color c, Square s, Type t) {
		super(c,s,t);
	}

	public String err="";

	@Override
	public boolean moveTo(Square destination) {

		if(this.validateMove(this.getSquare(), destination) == true){
			//move piece to destination 
			destination.setPiece(this);
			//set piece.square to destination square
			this.setSquare(destination);
			return true;			
		}else{
			// Print error message and return false as the moveTo was not successful
			//System.out.println(err);
			return false;
		}
		//logic for checking if the Pawn can move from current Location to this Destination
		//i.e validate()
		//return true and move else return false
	}

	public boolean validateMove(Square s, Square d){
		boolean decision = true;
		if(d.get_x()>8 || d.get_y()>8 || d.get_x()<1 || d.get_y()<1){
			decision = false;
			err="Destination square is not a part of the game board, the requested moved is rejected.";
			return decision;
		}

		if(s.get_x()==d.get_x() && s.get_y()==d.get_y()){
			decision = false;
			err="Source square and destination square can not be the same, the requested move is rejected.";
			return decision;
		}

		if(!this.validateAgainstRule(s, d)){
			decision=false;
			err="Not a valid move for a Pawn, the requested move is rejected.";
			return decision;
		}
		
		if(this.isObstructed(s, d) == true){
			decision=false;
			err="Another piece exists in the path to the destination square, the requested move is rejected.";
		}
		
		return decision;
	}
	
	private boolean validateAgainstRule(Square s, Square d) {
		boolean result = false; 
		int diff_x = Math.abs(s.get_x() - d.get_x());
		int diff_y = Math.abs(s.get_y() - d.get_y());

		// Pawn can move by one squares in X direction
		// Or it can move by two squares when it is starting from its initial position 
		if(diff_x == 1 && diff_y == 0)
			result = true;
		else if (diff_x == 2 && (s.get_x() == 7 || s.get_x() == 2) && diff_y == 0)
			result = true;

		return result;
	}

	private boolean isObstructed(Square s, Square d) {
		Board board = Board.getBoardInstance();
		boolean result = false;
		int s_x = s.get_x();
		int s_y = s.get_y();
		int d_x = d.get_x();
		int d_y = d.get_y();
		int diff_x = d_x - s_x;
		int diff_y = d_y - s_y;
	
		while(s_x != d_x || s_y != d_y){

			if (diff_x != 0)
				s_x=s_x+diff_x/Math.abs(diff_x);
			if (diff_y != 0)
				s_y=s_y+diff_y/Math.abs(diff_y);

			if (s_x <= 0 || s_y <= 0)
				break;

			if (s_x > 8 || s_y > 8)
				break;

			if(board.getSquare(s_x, s_y).getPiece() != null){
				result = true;
				break;
			}
		}

		return result;
	}
	
	//add your code here, and return appropriate value
	public Square selectRandomSquare(){

		Board currentBoard = Board.getBoardInstance(); //get the Board
		List<Square> validSquares = new ArrayList<Square>();
		Square currentSquare = null;
		
		currentSquare = this.getSquare();

		Square s_ret = null;
		
		if (currentSquare.getPiece().getColor() == Color.white)
		{
			for(int i = currentSquare.get_x(); i < Board.ROWS + 1; i++) {
				for(int j = currentSquare.get_y(); j < Board.COLS + 1; j++) {
					if(this.validateMove(this.getSquare(), currentBoard.getSquare(i, j))) {
						//we can move from current position of this piece to Square(i,j) on the Board
						//add it to the list
						validSquares.add(currentBoard.getSquare(i, j));
					}
				}
			}
		} else {
			for(int i = currentSquare.get_x(); i > 1; i--) {
				for(int j = currentSquare.get_y(); j > 1; j--) {
					if(this.validateMove(this.getSquare(), currentBoard.getSquare(i, j))) {
						//we can move from current position of this piece to Square(i,j) on the Board
						//add it to the list
						validSquares.add(currentBoard.getSquare(i, j));
					}
				}
			}			
		}
		if(validSquares.isEmpty()) {
			//list is empty i.e no possible move for this Piece
			
			return null;
		} else {
			//list has atleast one Square i.e atleast one move possible for this piece
			if(validSquares.size() == 1){
				//if only one square in the list, no need for randomization
				return validSquares.get(0);
			} else {
				int min = 0;
				int max = validSquares.size() - 1;
				//else randomize
				int randomNum = min + (int) ( Math.random() * ((max - min)+1) );

				s_ret = validSquares.get(randomNum);
				return s_ret;
			}
		}		
	}// end selectRandomSquare

	//the class will also include other functions specific to Pawn Object
}
