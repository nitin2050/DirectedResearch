package ChessAPI;

import java.util.ArrayList;
import java.util.List;

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

	boolean isCastling;

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
		isCastling = false;
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

	public boolean moveTonoCheck(Square s, Square d) {
			boolean result = true;
			//d.getPiece().setIsDead(true);
			//System.out.println(d.getPiece().getColor() + " Player's " + d.getPiece().getType() + " at (" + d.get_x() + ", " + d.get_y() + ") is DEAD !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			//d.setPiece(null);

			result = s.getPiece().moveTonoCheck(d);
			s.setPiece(null);
			updateDeadAlive();
			return result;
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

	// 	For checking if king will be land up in check if moved to this new (x, y) Square
	public boolean isInCheck(int x, int y) {
		boolean result = false;
		Square currentBoard[][] = new Square[10][10];

		for (int i = 1; i <= 8; i++)
			for (int j = 1; j <=8; j++)
			{
				currentBoard[i][j] = Board.getBoard(i, j);
			}

		Square kingPosition = new Square(this.color, x, y, this.king);

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
	public Move selectBestMove() {
		int i;
		// If King is InCheck condition Move the King(priority) else we can move anything
		if (this.isInCheck() == true)
		{
			if(this.king.selectBestMove() != null) {
				//A valid Move exists for the King, return it
				//here we also need to check if King is in check even after applying this bestmove
				Move bestMove = this.king.selectBestMove();
				bestMove.setSource(this.king.getSquare());
				return bestMove;
				
			} else {
				//King is in check and there is no valid move that exists for King
				//GAME OVER
				return null;
			}
			
		}

		// Check if we can do Castling
		/*
		1. The king has not previously moved.
		2. The chosen rook has not previously moved.
		3. There are no pieces between the king and the chosen rook.
		4. The king is not currently in check.
		5. The king does not pass through a square that is under attack by enemy pieces.[2]
		6. The king does not end up in check (true of any legal move).
		7. The king and the chosen rook are on the first rank of the player (rank 1 for White, rank 8 for Black, in algebraic notation).[3]
		*/
		
		// System.out.println(" King " + this.king.isKingMoved);
		// 1.1, 2.1 The king has not previously moved.
		if (this.king.isKingMoved == false)
		{
			boolean notObstructed = true, success = true;
			i = 0; 
			int diff_y = 0, x_current = 0;

			// 1.2. The chosen rook has not previously moved.
			if (this.rook[1].isRookMoved == false)
			{
				diff_y = this.king.getSquare().get_y() - this.rook[1].getSquare().get_y();
				x_current = this.rook[1].getSquare().get_x();

				if (diff_y > 0)
				{
					for (i = this.rook[1].getSquare().get_y() + 1; i < this.king.getSquare().get_y(); i++)
					{
						if (Board.getBoard(x_current, i).getPiece() != null)
						{
							notObstructed = false;
							success = false;
							break;
						}
					}
				}

				// 1.3. There are no pieces between the king and the chosen rook.
				if (notObstructed == true)
				{
					// 1.4. The king is not currently in check.
					if (isInCheck() == false)
					{
						x_current = this.rook[1].getSquare().get_x();
						// 1.5. The king does not pass through a square that is under attack by enemy pieces.
						for (i = this.rook[1].getSquare().get_y() + 1; i < this.king.getSquare().get_y(); i++)
						{
							// 1.6. The king does not end up in check (true of any legal move).
							if (isInCheck(x_current, i) == true)
							{								
								success = false;
								break;
							}
						}
					}
				}
				
				// 1.7. The king and the chosen rook are on the first rank of the player
				// (That means on original rows same row as that of their initial rows)
				// isNotMoved flag should serve the purpose
				if (success == true)
				{
					Rook currentRook = this.rook[1];
					Square new_king = new Square(this.color, currentRook.getSquare().get_x(), currentRook.getSquare().get_y() + 2, null);
					Square new_rook = new Square(this.color, currentRook.getSquare().get_x(), currentRook.getSquare().get_y() + 3, null);

					this.moveTonoCheck(this.king.getSquare(), new_king);
					Move bestMove = new Move(new_rook, 100, currentRook.getSquare());
					bestMove.setSource(currentRook.getSquare());
					
					isCastling = true;
					return bestMove;
				}

				if (success == false)
				{
					notObstructed = true; success = true;
					i = 0; 
					diff_y = 0; x_current = 0;
	
					// 2.2. The chosen rook has not previously moved.
					if (this.rook[2].isRookMoved == false)
					{
						diff_y = this.rook[2].getSquare().get_y() - this.king.getSquare().get_y();
						x_current = this.rook[2].getSquare().get_x();
	
						if (diff_y > 0)
						{
							for (i = this.king.getSquare().get_y() + 1; i < this.rook[2].getSquare().get_y(); i++)
							{
								if (Board.getBoard(x_current, i).getPiece() != null)
								{
									notObstructed = false;
									success = false;
									break;
								}
							}
						}
						// 2.3. There are no pieces between the king and the chosen rook.
						if (notObstructed == true)
						{
							// 2.4. The king is not currently in check.
							if (isInCheck() == false)
							{
								x_current = this.rook[2].getSquare().get_x();
								// 2.5. The king does not pass through a square that is under attack by enemy pieces.
								for (i = this.king.getSquare().get_y(); i < this.rook[2].getSquare().get_y(); i++)
								{
									// 2.6. The king does not end up in check (true of any legal move).
									if (isInCheck(x_current, i) == true)
									{
										success = false;
										break;
									}
								}
							}
						}
						
						// 2.7. The king and the chosen rook are on the first rank of the player
						// (That means on original rows same row as that of their initial rows)
						// isNotMoved flag should serve the purpose
						if (success == true)
						{
							Rook currentRook = this.rook[2];
							Square new_king = new Square(this.color, currentRook.getSquare().get_x(), currentRook.getSquare().get_y() - 1, null);
							Square new_rook = new Square(this.color, currentRook.getSquare().get_x(), currentRook.getSquare().get_y() - 2, null);

							this.moveTonoCheck(this.king.getSquare(), new_king);
							Move bestMove = new Move(new_rook, 100, currentRook.getSquare());
							bestMove.setSource(currentRook.getSquare());

							isCastling = true;
							return bestMove;
						}
					}
				}
			}
		} else {
			// If King is moved no Castling can be done
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//If King is not in check, we can move other pieces
		//This list will store best moves obtained for each possible alive piece from calling selectBestMove() for that piece
		List<Move> setOfMoves = new ArrayList<Move>();
		
		//find the best possible move for each Pawn and add it to setOfMoves
		for (i=1; i<=8; i++) {
			if ( this.pawn[i].isPieceDead() == false) {
				//Piece is alive
				if(this.pawn[i].selectBestMove() != null) {
					//A valid Move exists for this piece, so add it to the list of setOfMoves
					Move bestMove = this.pawn[i].selectBestMove();
					bestMove.setSource(this.pawn[i].getSquare());
					setOfMoves.add(bestMove);
				}	
			}
		}
		
		//find the best possible move for each Rook and add it to setOfMoves
		for (i=1; i<=2; i++) {
			if ( this.rook[i].isPieceDead() == false) {
				//Rook is alive
				if(this.rook[i].selectBestMove() != null) {
					//A valid Move exists for this Rook, so add it to the list of setOfMoves
					Move bestMove = this.rook[i].selectBestMove();
					bestMove.setSource(this.rook[i].getSquare());
					setOfMoves.add(bestMove);
				}
			}
		}
	
		//find the best possible move for each Bishop and add it to setOfMoves
		for (i=1; i<=2; i++) {
			if ( this.bishop[i].isPieceDead() == false) {
				//Bishop is alive
				if(this.bishop[i].selectBestMove() != null) {
					//A valid Move exists for this Bishop, so add it to the list of setOfMoves
					Move bestMove = this.bishop[i].selectBestMove();
					bestMove.setSource(this.bishop[i].getSquare());
					setOfMoves.add(bestMove);
				}
			}
		}
		
		//find the best possible move for each Knight and add it to setOfMoves
		for (i=1; i<=2; i++) {
			if ( this.knight[i].isPieceDead() == false) {
				//Knight is alive
				if(this.knight[i].selectBestMove() != null) {
					//A valid Move exists for this Knight, so add it to the list of setOfMoves
					Move bestMove = this.knight[i].selectBestMove();
					bestMove.setSource(this.knight[i].getSquare());
					setOfMoves.add(bestMove);
				}
			}
		}
		
		//find the best possible move for a Queen and add it to setOfMoves
		if ( this.queen.isPieceDead() == false) {
			//Queen is alive
			if(this.queen.selectBestMove() != null) {
				//A valid Move exists for this Queen, so add it to the list of setOfMoves
				Move bestMove = this.queen.selectBestMove();
				bestMove.setSource(this.queen.getSquare());
				setOfMoves.add(bestMove);
			}
		}
		
		//find the best possible move for a King and add it to setOfMoves
		if ( this.king.isPieceDead() == false) {
			//Queen is alive
			if(this.king.selectBestMove() != null) {
				//A valid Move exists for this Queen, so add it to the list of setOfMoves
				Move bestMove = this.king.selectBestMove();
				bestMove.setSource(this.king.getSquare());
				setOfMoves.add(bestMove);
			}
		}
		
		//At this stage we have a list of best possible move for each alive piece
		//Now we need to find the best of this list, so that we can find the best Heuristic move out of all
		
		if(!setOfMoves.isEmpty()) {
			Move bestMove = setOfMoves.get(0);
			float bestHeuristicValue = bestMove.getHeuristicValue();
			
			if(setOfMoves.size() > 1){
				//there are more than one moves in the list, lets find the best
				for(i=1; i<setOfMoves.size(); i++) {
					//check if the current Move's Heuristic value is better than the bestHeuristicValue
					if(setOfMoves.get(i).getHeuristicValue() > bestHeuristicValue){
						//it is better. So update bestMove & bestHeuristicValue else don't do anything
						bestMove = setOfMoves.get(i);
						bestHeuristicValue = setOfMoves.get(i).getHeuristicValue();
					}
				}
				
				return bestMove;
				
			}else {
				//there is only one move in the list, so that is the best move
				return bestMove;
			}
			
		}else {
			//There is no possible move for this player
			return null;
		}

	}//end randomMove()

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
			//System.exit(0);
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
