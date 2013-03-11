package ChessAPI;

public class King extends Piece{
	
	public King() {
		super();
	}
	
	public King(Color c, Square s, Type t) {
		super(c,s,t);
	}

	@Override
	public boolean moveTo(Square destination) {
		
		System.out.println("King");
		if(destination.getPiece()==null){
			System.out.println("Piece moved to "+destination.get_x()+","+destination.get_y());
			//move piece to destination 
			destination.setPiece(this);
			//set piece.square to destination square
			this.setSquare(destination);
			return true;			
		}else{
			System.out.println("the move is not completed due to existance of another piece in the destination square");
			return false;
		}
		//logic for checking if the King can move from current Location to this Destination
		//i.e validate()
		//return true and move else return false

	}
	
	
	//the class will also include other functions specific to King Object
}
