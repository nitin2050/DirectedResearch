package ChessAPI;

public class Queen extends Piece{
	
	public Queen() {
		super();
	}
	
	public Queen(Color c, Square s, Type t) {
		super(c,s,t);
	}

	@Override
	public boolean moveTo(Square destination) {
		
		System.out.println("Queen");
		//logic for checking if the Queen can move from current Location to this Destination
		//i.e validate()
		//return true and move else return false
		
		return true;
	}
	
	
	//the class will also include other functions specific to Queen Object

}
