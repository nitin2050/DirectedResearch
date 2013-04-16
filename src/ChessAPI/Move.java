package ChessAPI;

public class Move {
	private Square destinationSquare;
	private float heuristicValue;
	private Square source;
	
	public Move() {
	}
	
	public Move (Square destinationSquare, float heuristicValue, Square source){
		this.destinationSquare = destinationSquare;
		this.heuristicValue = heuristicValue; 
		this.source = source;
	}
	
	public void setDestinationSquare(Square destinationSquare) {
		this.destinationSquare = destinationSquare;	
	}
	
	public void setHeuristicValue(float heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	
	public void setSource(Square source) {
		this.source = source;
	}
	
	public Square getDestinationSquare() {
		return destinationSquare;
	}
	
	public float getHeuristicValue() {
		return heuristicValue;
	}
	
	public Square getSource() {
		return source;
	}
}

