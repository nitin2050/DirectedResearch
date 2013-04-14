package ChessAPI;

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
	public Piece() {
	}
	
	public Piece(Color c, Square s, Type t) {
		this.color = c;
		this.square = s;
		this.type = t;
		this.isPieceDead = false;
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
		Square start = this.getSquare();
		//Calculate the Heuristic for a Move from start to destination
		
		return (float) Math.random();
	}

	//returns best Move for a piece from its current position, if it exists 
	public Move selectBestMove(){
		
		Square currentBoard[][] = new Square[10][10];	
		for (int i = 1; i <= 8; i++)
			for (int j = 1; j <= 8; j++)
			{
				currentBoard[i][j] = Board.getBoard(i, j); 
			}
		
		Move bestMove = null;
		float bestHeuristic = 0; // Temporary value. We will set this to lower than the lowest possible value for our heuristic
		
		for(int i=1; i<Board.ROWS+1; i++  ) {
			for(int j=1; j<Board.COLS+1; j++  ){
				if( this.validateMove(this.getSquare(), currentBoard[i][j])) {
					
					//we can move from current position of this piece to Square(i,j) on the Board
					float currentHeuristic = this.getHeuristic(currentBoard[i][j]);
					//Now check if this move is Heuristically better than bestMove we have.
					if(currentHeuristic > bestHeuristic) {
						//this Move has better Heuristic. So update the best
						bestMove = new Move(currentBoard[i][j], currentHeuristic, null);
						bestHeuristic = currentHeuristic;
					}
					
				}
			}
		}
		
		return bestMove;	
	}
	
	//Move the piece to Destination square is the move is valid
	public abstract boolean moveTo(Square destination);
	
	//validate and return true or false
	public abstract boolean validateMove(Square source, Square destination);
	
}
