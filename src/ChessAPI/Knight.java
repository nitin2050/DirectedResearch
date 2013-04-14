package ChessAPI;

import java.util.ArrayList;
import java.util.List;

import ChessAPI.Piece.Color;

public class Knight extends Piece{

	public String err="";
	
	public Knight() {
		super();
	}
	
	public Knight(Color c, Square s,Type t) {
		super(c,s,t);
	}

	@Override
	public boolean moveTo(Square destination) {
		
		//System.out.println("Knight");
		if(this.validateMove(this.getSquare(), destination)){
			System.out.println("Piece moved to "+destination.get_x()+","+destination.get_y());
			//move piece to destination 
			destination.setPiece(this);
			//set piece.square to destination square
			this.setSquare(destination);
			return true;			
		}else{
			System.out.println(err);
			return false;
		}
		//logic for checking if the Knight can move from current Location to this Destination
		//i.e validate()
		//return true and move else return false
		
	}
	
	public boolean validateMove(Square s, Square d){
		boolean decision = true;
		if(d==null){
			
			decision = false;
			return decision;
		}
		else{
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
				err="Not a valid move for a Knight, the requested move is rejected.";
				return decision;
			}
			if(this.isObstructed(s, d)){
				decision=false;
				err="Another piece exists in the path to the destination square, the requested move is rejected.";
			}
			return decision;
		}
	}
	
	private boolean validateAgainstRule(Square s, Square d) {
		boolean result = false; 
		int diff_x = Math.abs(s.get_x()-d.get_x());
		int diff_y = Math.abs(s.get_y()-d.get_y());
		if( (diff_x==2 && diff_y==1) || (diff_x==1 && diff_y ==2) )
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
		
		int d_x = d.get_x();
		int d_y = d.get_y();
		
		if(board[d_x][d_y].getPiece() != null)
			result = true;
		
		return result;
	}
	
	//add your code here, and return appropriate value
	//I am returning null for syntax purposes right now
	public Square selectRandomSquare(){
		Square board[][] = new Square[10][10];	
		for (int i = 1; i <= 8; i++)
			for (int j = 1; j <= 8; j++)
			{
				board[i][j] = Board.getBoard(i, j); 
			}
		List<Square> validSquares = new ArrayList<Square>();
		Square currentSquare = null;
		
		currentSquare = this.getSquare();
		
		int current_x = currentSquare.get_x();
		int current_y = currentSquare.get_y();

		Square s_ret = null;
		
		if ((current_x + 1) <= 8 && (current_y + 2) <= 8)
		{	
			if(this.validateMove(this.getSquare(), board[current_x+1][current_y+2])) {
						
				validSquares.add(board[current_x+1][current_y+2]);
			}
		}

		if ((current_x + 1) <= 8 && (current_y - 2) > 0)
		{			
			if(this.validateMove(this.getSquare(), board[current_x+1][current_y-2])) {
				
				validSquares.add(board[current_x+1][current_y-2]);
			}
		}

		if ((current_x-1) > 0 && (current_y+2) <= 8)
		{
			if(this.validateMove(this.getSquare(), board[current_x-1][current_y+2])) {				
				validSquares.add(board[current_x-1][current_y+2]);
			}
		}
		
		if ((current_x-1) > 0 && (current_y-2) > 0)
		{
			if(this.validateMove(this.getSquare(), board[current_x-1][current_y-2])) {				
				validSquares.add(board[current_x-1][current_y-2]);
			}
		}
		
		if ((current_x + 2) <= 8 && (current_y+1) <= 8)
		{
			if(this.validateMove(this.getSquare(), board[current_x+2][current_y+1])) {				
				validSquares.add(board[current_x+2][current_y+1]);
			}
		}
		
		if ((current_x + 2) <= 8 && (current_y - 1) > 0)
		{
			if(this.validateMove(this.getSquare(), board[current_x+2][current_y-1])) {	
				validSquares.add(board[current_x+2][current_y-1]);
			}
		}
		
		if ((current_x-2) > 0 && (current_y+1) <= 8)
		{
			if(this.validateMove(this.getSquare(), board[current_x-2][current_y+1])) {	
				validSquares.add(board[current_x-2][current_y+1]);
			}
		}
		
		if ((current_x - 2) > 0 && (current_y - 1) > 0)
		{
			if(this.validateMove(this.getSquare(), board[current_x-2][current_y-1])) {				
				validSquares.add(board[current_x-2][current_y-1]);
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
	}
	
}
