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

		System.out.println(" Pawn ");
		if(this.validateMove(this.getSquare(), destination) == true) {
			//move piece to destination
			destination.setPiece(this);
			Board.setNull(this.getSquare().get_x(), this.getSquare().get_y());
/*
			if (destination.get_x() == 1 || destination.get_x() == 8)
			{
				// Any position other than King
				if (destination.get_y() != 5)
				{
					System.out.println(" Pawn Promotion ");
					if (destination.get_y() == 1 || destination.get_y() == 8)
					{
						promotion = "rook";
						promo_pos = destination.get_y();
					}
					else if (destination.get_y() == 2 || destination.get_y() == 7)
					{
						promotion = "knight";
						promo_pos = destination.get_y();
					}
					else if (destination.get_y() == 2 || destination.get_y() == 6)
					{
						promotion = "bishop";
						promo_pos = destination.get_y();
					}
					else if (destination.get_y() == 4)
					{
						promotion = "queen";
						promo_pos = 4;
					}

				}
			}
*/
			//set piece.square to destination square
			this.setSquare(destination);
			Board.setBoard(destination, this.getSquare().get_x(), this.getSquare().get_y());

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

	public boolean validateMove(Square s, Square d) {
		boolean decision = true;
		if(d.get_x() > 8 || d.get_y() > 8 || d.get_x() < 1 || d.get_y() < 1) {
			decision = false;
			err="Destination square is not a part of the game board, the requested moved is rejected.";
			return decision;
		}

		if(s.get_x() == d.get_x() && s.get_y() == d.get_y()) {
			decision = false;
			err="Source square and destination square can not be the same, the requested move is rejected.";
			return decision;
		}

		if(!this.validateAgainstRule(s, d)) {
			decision=false;
			err="Not a valid move for a Pawn, the requested move is rejected.";
			return decision;
		}
		
		if(this.isObstructed(s, d) == true) {
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
/*
		if (Board.getBoard(d.get_x(), d.get_y()).getPiece() == null)
			System.out.println(" Pawn :: diff_x = " + diff_x + " diff_y = " + diff_y + " Piece is null ");
		else
			System.out.println(" Pawn :: diff_x = " + diff_x + " diff_y = " + diff_y + " piece = " + Board.getBoard(d.get_x(), d.get_y()).getPiece());
*/
		if(diff_x == 1 && diff_y == 0 && d.getPiece() == null)
		{
			result = true;
		} else if (diff_x == 2 && (s.get_x() == 7 || s.get_x() == 2) && diff_y == 0 && d.getPiece() == null)
		{
			result = true;
		}
		// Or it can move diagonally by one square
		// When a piece of opponent is present there
		else if (diff_x == 1 && diff_y == 1 && d.getPiece() != null && d.getPiece().getColor() != this.getColor())
		{
			result = true;
		} 
		// This is only for en-passant
		else if (diff_x == 1 && diff_y == 1)
		{
			int opp_x, opp_y;
			opp_x = s.get_x();
			opp_y = d.get_y();

			if (Board.getBoard(opp_x, opp_y).getPiece() != null)
			{
				// That means it is of opposite color
				if (Board.getBoard(s.get_x(), s.get_y()).getPiece() != null)
				{
					if (Board.getBoard(opp_x, opp_y).getPiece().getColor() != Board.getBoard(s.get_x(), s.get_y()).getPiece().getColor())
					{
						Square sq = null;
						sq = new Square(Board.getBoard(opp_x, opp_y).getColor(), opp_x, opp_y, null);
						Piece p = null;
						p = Board.getBoard(opp_x, opp_y).getPiece();
						p.setIsDead(true);
	
						Board.setBoard(sq, opp_x, opp_y);
						this.enpass = true;
						this.en_x = opp_x;
						this.en_y = opp_y;
	
						result = true;
					}
				}
			}
		}
		return result;
	}

	private boolean isObstructed(Square s, Square d) {
		boolean result = false;
		int s_x = s.get_x();
		int s_y = s.get_y();
		int d_x = d.get_x();
		int d_y = d.get_y();
		int diff_x = d_x - s_x;
		int diff_y = d_y - s_y;

		Square board[][] = new Square[10][10];	
		for (int i = 1; i <= 8; i++)
			for (int j = 1; j <= 8; j++)
			{
				board[i][j] = Board.getBoard(i, j); 
			}

		while(s_x != d_x || s_y != d_y){

			if (diff_x != 0)
				s_x=s_x+diff_x/Math.abs(diff_x);
			if (diff_y != 0)
				s_y=s_y+diff_y/Math.abs(diff_y);

			if (s_x <= 0 || s_y <= 0)
				break;

			if (s_x > 8 || s_y > 8)
				break;

			if(board[s_x][s_y].getPiece() != null){
				//result = true;
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