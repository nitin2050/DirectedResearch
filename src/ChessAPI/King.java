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
		//logic for checking if the King can move from current Location to this Destination
		//i.e validate()
		//return true and move else return false
		
		return true;
	}
	
	
	//the class will also include other functions specific to King Object
}
