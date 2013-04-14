package ChessAPI;

import ChessAPI.Piece.Color;

public class Square {
	
	private Color color;
	private Piece piece;
	private int _x;
	private int _y;

	public Square(Color color, int _x, int _y, Piece piece){
		this._x = _x;
		this._y = _y;
		this.color = color;
		this.piece = piece;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public void set_x(int _x) {
		this._x = _x;
	}

	public int get_x() {
		return _x;
	}

	public void set_y(int _y) {
		this._y = _y;
	}
	
	public int get_y() {
		return _y;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public Piece getPiece() {
		return piece;
	}

	//other API functions needed by the SQUARE class will be added here...

}
