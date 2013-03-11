package ChessAPI;

public class Knight extends Piece{

	public Knight() {
		super();
	}
	
	public Knight(Color c, Square s,Type t) {
		super(c,s,t);
	}

	@Override
	public boolean moveTo(Square destination) {
		
		System.out.println("Knight");
		//logic for checking if the Knight can move from current Location to this Destination
		//i.e validate()
		//return true and move else return false
		
		return true;
	}
	
	
	//the class will also include other functions specific to Knight Object
	
}
