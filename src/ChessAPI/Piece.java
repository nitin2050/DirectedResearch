package ChessAPI;

public abstract class Piece {
	
	public enum Color{
		black,white
	}
	
	private Color color;	//color of the Piece
	private Square square;	//current Square the piece is on the Board
	
	public Piece() {
	}
	
	public Piece(Color c, Square s) {
		color = c;
		square = s;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	public Color getColor() {
		return color;
	}
	
	public void setSquare(Square s) {
		this.square = s;
	}
	public Square getSquare() {
		return square;
	}
	
	//Move the piece to Destination square is the move is valid
	public abstract boolean moveTo(Square destination);

}
