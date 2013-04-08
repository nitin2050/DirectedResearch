package ChessAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ChessAPI.ApiVersion;
import ChessAPI.Board;
import ChessAPI.GameDump;
import ChessAPI.Piece.Color;
import ChessAPI.Player;

public class UserProgram {

	public static void main(String s[]){
		GameDump D;
		Board b;
		//req4 create a dump
		b = new Board();
		D = b.getDump();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String more = null;

		ApiVersion.printVersionNo();

		String version = ApiVersion.getVersionNo();
		System.out.println("Current Version is : "+version);

		b = new Board();
		Player pl1 = new Player(Color.white);
		Player pl2 = new Player(Color.black);

		b.initBoard(pl1, pl2);
		//req5 print board
		//b.displayBoard();
		
		//req6 move a piece, the next line moves W_Pawn from (2,1) to (3,1)
		//pl1.moveTo(b.getSquare(2, 1), b.getSquare(3, 1));
		//pl1.moveTo(b.getSquare(2, 1), b.getSquare(3, 1));
		//b.displayBoard();

		b.displayBoard();
		Square s_rand = null;
		String originalType = "";
		boolean result;

		int i = 0;
		do
		{
			if (i % 2 == 0)
				System.out.println("\n\n -------------------------------- White's Turn ---------------------------------------- ");
			else
				System.out.println("\n\n -------------------------------- Black's Turn ---------------------------------------- ");


			if (i % 2 == 0)
			{
				do {
					s_rand = pl1.randomMove();
					originalType = pl1.originalSquare.getPiece().getType().toString();
					result = pl1.moveTo(b.getSquare(pl1.originalSquare.get_x(), pl1.originalSquare.get_y()), b.getSquare(s_rand.get_x(), s_rand.get_y()));
				} while(result == false);
				System.out.println(" Now moving " + originalType + " from (" + pl1.originalSquare.get_x() + ", " + pl1.originalSquare.get_y() + ") to (" + s_rand.get_x() + ", " + s_rand.get_y() + ") ");
			} else {
				do {
					s_rand = pl2.randomMove();
					originalType = pl2.originalSquare.getPiece().getType().toString();
					result = pl2.moveTo(b.getSquare(pl2.originalSquare.get_x(), pl2.originalSquare.get_y()), b.getSquare(s_rand.get_x(), s_rand.get_y()));
				} while(result == false);
				System.out.println(" Now moving " + originalType + " from (" + pl2.originalSquare.get_x() + ", " + pl2.originalSquare.get_y() + ") to (" + s_rand.get_x() + ", " + s_rand.get_y() + ") ");
			}
			b.displayBoard();
			System.out.println(" ====================================================================================== ");
			i++;
			more = "n";
			System.out.println(" Do you want to continue ? (y/n) : ");
			try {
				more = br.readLine();
			} catch (IOException e) {
				System.out.println("Error!");
				System.exit(1);
			}
		} while (more.charAt(0) == 'y');
		//nitin playing around
	}
}
