package ChessAPI;

public class Move {
	private Square destinationSquare;
	private float heuristicValue;
	private String pieceName;
	
	public Move() {
	}
	
	public Move (Square destinationSquare, float heuristicValue, String pieceName){
		this.destinationSquare = destinationSquare;
		this.heuristicValue = heuristicValue; 
		this.pieceName = pieceName;
	}
	
	public void setDestinationSquare(Square destinationSquare) {
		this.destinationSquare = destinationSquare;	
	}
	
	public void setHeuristicValue(float heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	
	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}
	
	public Square getDestinationSquare() {
		return destinationSquare;
	}
	
	public float getHeuristicValue() {
		return heuristicValue;
	}
	
	public String getPieceName() {
		return pieceName;
	}
}

