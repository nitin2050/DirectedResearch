package ChessAPI;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
	
	public King() {
		super();
	}
	
	public King(Color c, Square s, Type t) {
		super(c,s,t);
	}

	public String err="";

	@Override
	public boolean moveTo(Square destination) {
		
		//System.out.println("King");
		if(this.validateMove(this.getSquare(), destination) == true){
			System.out.println("Piece moved to "+destination.get_x()+","+destination.get_y());
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
		//logic for checking if the King can move from current Location to this Destination
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
			err="Not a valid move for a King, the requested move is rejected.";
			return decision;
		}

		// isObstructed is not applicable for King
		// as the source and destination squares will be next to each other
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

		// King can move in any direction as long as it is only one square
		// In the same row or column 1 square front, back or along the diagonal
		if(diff_x <= 1 && diff_y <= 1)
			result = true;

		return result;
	}

	private boolean isObstructed(Square s, Square d) {
		Square board[][] = new Square[10][10];	
		for (int i = 1; i <= 8; i++)
			for (int j = 1; j <= 8; j++)
			{
				board[i][j] = Board.getBoard(i, j); 
			}
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
			
			if(board[s_x][s_y].getPiece() != null){
				result = true;
				break;
			}
		}

		return result;
	}

	//add your code here, and return appropriate value
	//I am returning null for syntax purposes right now
	public Square selectRandomSquare(){
	
		List<Square> validSquares = new ArrayList<Square>();
		Square board[][] = new Square[10][10];	
		for (int i = 1; i <= 8; i++)
			for (int j = 1; j <= 8; j++)
			{
				board[i][j] = Board.getBoard(i, j); 
			}

		for(int i=1; i<Board.ROWS+1; i++  ) {
			for(int j=1; j<Board.COLS+1; j++  ){
				if( this.validateMove(this.getSquare(),board[i][j]) ) {
					//we can move from current position of this piece to Square(i,j) on the Board
					//add it to the list
					validSquares.add(board[i][j]);
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
				int max = validSquares.size()-1;
				//else randomize
				int randomNum = min + (int) ( Math.random() * ((max - min)+1) );

				return validSquares.get(randomNum);
			}
		}
	}
}
