import ChessAPI.ApiVersion;
import ChessAPI.Board;
import ChessAPI.GameDump;
import ChessAPI.Piece.Color;
import ChessAPI.Player;

public class UserProgram {

	public static void main(String s[]){
		GameDump D;
		Board b;
		
		b = new Board();
		D = b.getDump();

		ApiVersion.printVersionNo();
		
		String version = ApiVersion.getVersionNo();
		System.out.println("Current Version is : "+version);

		b = new Board();
		Player pl1 = new Player(Color.white);
		Player pl2 = new Player(Color.black);
		
		b.initBoard(pl1, pl2);
		b.displayBoard();

	}
}

