package ChessAPI;


public class Queen extends Piece{
	
	public Queen() {
		super();
	}
	
	public Queen(Color c, Square s, Type t) {
		super(c,s,t);
	}
	public String err="";

	@Override
	public boolean moveTo(Square destination) {
		
		System.out.println("Queen");
		if(this.validateMove(this.getSquare(), destination)){
			System.out.println("Piece moved to "+destination.get_x()+","+destination.get_y());
			//move piece to destination 
			destination.setPiece(this);
			//set piece.square to destination square
			this.setSquare(destination);
			return true;			
		}else{
			System.out.println(err);
			return false;
		}
		//logic for checking if the Queen can move from current Location to this Destination
		//i.e validate()
		//return true and move else return false
		
	}
	
	//validates the requested move
	public boolean validateMove(Square s, Square d){
		boolean decision = true;
		if(d.get_x()>8 || d.get_y()>8 || d.get_x()<1 || d.get_y()<1){
			decision = false;
			err="Destination square is not a part of the game board, the requested moved is rejected.";
			return decision;
		}
		if(s.get_x()==d.get_x() && s.get_y()==d.get_y()){
			decision = false;
			err="Source square and destination square can not be the same, the requested move is rejected.";
			return decision;
		}
		if(!this.validateAgainstRule(s, d)){
			decision=false;
			err="Not a valid move for a Queen, the requested move is rejected.";
			return decision;
		}
		if(this.isObstructed(s, d)){
			decision=false;
			err="Another piece exists in the path to the destination square, the requested move is rejected.";
		}
		return decision;
	}

	private boolean isObstructed(Square s, Square d) {
		Board board = Board.getBoardInstance();
		boolean result = false;		
		int s_x = s.get_x();
		int s_y = s.get_y();
		int d_x = d.get_x();
		int d_y = d.get_y();
		int diff_x = d_x - s_x;
		int diff_y = d_y - s_y;
		if(s_x==d_x){
			for(int i = s_y+diff_y/Math.abs(diff_y); i<=d_y; i=i+diff_y/Math.abs(diff_y)){
				if(board.getSquare(s_x, i).getPiece() != null)
					result = true;
			}
		}
		
		if(s_y==d_y){
			for(int i = s_x+diff_x/Math.abs(diff_x); i<=d_x; i=i+diff_x/Math.abs(diff_x)){
				if(board.getSquare(i, s_y).getPiece() != null)
					result = true;
			}
		}

		while(s_x != d_x || s_y != d_y){
			s_x=s_x+diff_x/Math.abs(diff_x);
			s_y=s_y+diff_y/Math.abs(diff_y);
			if(board.getSquare(s_x, s_y).getPiece() != null){
				result = true;
				break;
			}
		}
		return result;
	}

	private boolean validateAgainstRule(Square s, Square d) {
		boolean result = false; 
		int diff_x = Math.abs(s.get_x()-d.get_x());
		int diff_y = Math.abs(s.get_y() - d.get_y());
		if(s.get_x()==d.get_x() || s.get_y()==d.get_y() || diff_x == diff_y)
			result = true;			
		return result;
	}
	
	//add your code here, and return appropriate value
	//I am returning null for syntax purposes right now
	public Square selectRandomSquare(){
			
		return null;
	}
	
}
