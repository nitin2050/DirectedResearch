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

	//Move the piece to Destination square is the move is valid
	public abstract boolean moveTo(Square destination);
	
	//validate and return true or false
	public abstract boolean validateMove(Square source, Square destination);
	
	//find a list of valid Squares where this piece can move legally from its & 
	//return a randomly selectd piece from that list. If list empty then return null
	public abstract Square selectRandomSquare();

}
