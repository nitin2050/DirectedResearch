package ChessAPI;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

	public Bishop() {
		super();
	}

	public Bishop(Color c, Square s, Type t) {
		super(c,s,t);
	}
	public String err="";

	@Override
	public boolean moveTo(Square destination) {

		//System.out.println("Bishop");
		//System.out.println("Piece moving from "+this.getSquare().get_x()+","+ this.getSquare().get_y());
		if(this.validateMove(this.getSquare(), destination)){
			//System.out.println("Piece moved to "+destination.get_x()+","+destination.get_y());

			this.getSquare().setPiece(null);

			//move piece to destination 
			destination.setPiece(this);
			//set piece.square to destination square
			this.setSquare(destination);
			return true;			
		}else{
			System.out.println(err);
			return false;
		}
		//logic for checking if the Queen can move from current Location to this Destination
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
			err="Not a valid move for a Bishop, the requested move is rejected.";
			return decision;
		}
		
		if(this.isObstructed(s, d)){
			decision=false;
			err="Another piece exists in the path to the destination square, the requested move is rejected.";
		}
		return decision;
	}

	private boolean validateAgainstRule(Square s, Square d) {
		boolean result = false; 
		int diff_x = Math.abs(s.get_x()-d.get_x());
		int diff_y = Math.abs(s.get_y() - d.get_y());
		if(diff_x == diff_y)
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
		if(result == true && board[d_x][d_y].getPiece()!=null)
			result = false;
		return result;
	}

	@Override
	public boolean moveTonoCheck(Square destination) {
		// TODO Auto-generated method stub
		return false;
	}

}