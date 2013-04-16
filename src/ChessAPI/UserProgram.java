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

		//b.displayBoard();
		
		String selection = "";
		String exit = "";
		int playMode = 0;
		
		do
		{
			System.out.println("\n****WELCOME TO THE CHESS GAME****");
			
			do
			{
				System.out.println("\nPLEASE SELECT PLAY MODE:");
				System.out.print("1) Player Against Player\n2) Player Against AI\n3) AI Against AI\nQ) Quit");
				try {
					selection = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (selection.equals("1")||selection.equals("2")||selection.equals("3")){
					
					if(selection.equals("1"))
				    	 playMode = 1;
				    if(selection.equals("2"))
				    	 playMode = 2;
				    if(selection.equals("3"))
				    	 playMode = 3;
				}else{
					
					if(selection.toUpperCase().equals("Q")){exit = "Q";}
					else
					System.out.println("Error: Wrong Input, Please Try Again");
				}
			}while(!selection.toUpperCase().equals("Q")&&!selection.equals("1")&&!selection.equals("2")&&!selection.equals("3"));
		
			  if(playMode == 1){
				  boolean result;
				  String originalType = "";
				  b.displayBoard();
				  int i = 0;
					do
					{
						if (i % 2 == 0)
							System.out.println("\n\n -------------------------------- White's Turn ---------------------------------------- ");
						else
							System.out.println("\n\n -------------------------------- Black's Turn ---------------------------------------- ");
			
			
						if (i % 2 == 0)
						{
							int s_x=0,s_y=0,d_x=0,d_y=0;
							do {
								
								System.out.println("White Player Please Make Move (Format:Source_Col,Source_Row,Dest_Col,Dest_Row):");
								String move = "";
								try {
									 move = br.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}
								String[] moves = move.split(",");
								s_x = Integer.parseInt(moves[1]);
								char sy = moves[0].charAt(0);
								s_y = (int) sy-96;
								d_x = Integer.parseInt(moves[3]);
								char dy = moves[2].charAt(0);
								d_y = (int) dy-96;
								originalType = b.getSquare(s_x, s_y).getPiece().getType().toString();
								result = pl1.moveTo(b.getSquare(s_x, s_y), b.getSquare(d_x, d_y));
								
							} while(result == false);
							//System.out.println("Now moving " + originalType + " from (" + s_x + ", " + s_y + ") to (" + d_x + ", " + d_y + ") ");
						} else {
							int s_x2=0,s_y2=0,d_x2=0,d_y2=0;
							do {
								
								System.out.println("Black Player Please Make Move (Format:Source_Col,Source_Row,Dest_Col,Dest_Row):");
								String move = "";
								try {
									 move = br.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}
								String[] moves = move.split(",");
								s_x2 = Integer.parseInt(moves[1]);
								char sy = moves[0].charAt(0);
								s_y2 = (int) sy-96;
								d_x2 = Integer.parseInt(moves[3]);
								char dy = moves[2].charAt(0);
								d_y2 = (int) dy-96;
								originalType = b.getSquare(s_x2, s_y2).getPiece().getType().toString();
								result = pl2.moveTo(b.getSquare(s_x2, s_y2), b.getSquare(d_x2, d_y2));
								
							} while(result == false);
							//System.out.println("Now moving " + originalType + " from (" + s_x2 + ", " + s_y2 + ") to (" + d_x2 + ", " + d_y2 + ") ");
						}
						b.displayBoard();
						System.out.println(" ====================================================================================== ");
						i++;
						more = "n";
						System.out.println("Do you want to continue ? (y/n) : ");
						try {
							more = br.readLine();
						} catch (IOException e) {
							System.out.println("Error!");
							System.exit(1);
						}
					} while (more.charAt(0) == 'y');
					//nitin playing around
					playMode = 0;
				  
			  }
			  
			  if(playMode == 2){
				  boolean result;
				  String originalType = "";
				  Square s_rand = null;
				  b.displayBoard();
				  int i = 0;
					do
					{
						if (i % 2 == 0)
							System.out.println("\n\n -------------------------------- White's Turn ---------------------------------------- ");
						else
							System.out.println("\n\n -------------------------------- Black's Turn ---------------------------------------- ");
			
			
						if (i % 2 == 0)
						{
							int s_x=0,s_y=0,d_x=0,d_y=0;
							do {
								
								System.out.println("White Player Please Make Move (Format:Source_Col,Source_Row,Dest_Col,Dest_Row):");
								String move = "";
								try {
									 move = br.readLine();
								} catch (IOException e) {
									e.printStackTrace();
								}
								String[] moves = move.split(",");
								s_x = Integer.parseInt(moves[1]);
								char sy = moves[0].charAt(0);
								s_y = (int) sy-96;
								d_x = Integer.parseInt(moves[3]);
								char dy = moves[2].charAt(0);
								d_y = (int) dy-96;
								originalType = b.getSquare(s_x, s_y).getPiece().getType().toString();
								result = pl1.moveTo(b.getSquare(s_x, s_y), b.getSquare(d_x, d_y));
								
							} while(result == false);
							//System.out.println("Now moving " + originalType + " from (" + s_x + ", " + s_y + ") to (" + d_x + ", " + d_y + ") ");
						} else {
							do {
								Move randomMove = pl2.selectBestMove();
								result = pl2.moveTo(randomMove.getSource(), randomMove.getDestinationSquare());
							} while(result == false);
							//System.out.println("Now moving " + originalType + " from (" + pl2.originalSquare.get_x() + ", " + pl2.originalSquare.get_y() + ") to (" + s_rand.get_x() + ", " + s_rand.get_y() + ") ");
						}
						b.displayBoard();
						System.out.println(" ====================================================================================== ");
						i++;
						more = "n";
						System.out.println("Do you want to continue ? (y/n) : ");
						try {
							more = br.readLine();
						} catch (IOException e) {
							System.out.println("Error!");
							System.exit(1);
						}
					} while (more.charAt(0) == 'y');
					//nitin playing around
					playMode = 0;
				  
			  }
			
			  if(playMode == 3){
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
			
						//b.displayBoard();
						if (i % 2 == 0)
						{
							do {
								//s_rand = pl1.randomMove();
								Move randomMove = pl1.selectBestMove();
								result = pl1.moveTo(randomMove.getSource(), randomMove.getDestinationSquare());
								//System.out.println("Now moving " + originalType + " from (" + randomMove.getSource().get_x() + ", " + randomMove.getSource().get_y() + " to (" + randomMove.getDestinationSquare().get_x() + ", " + randomMove.getDestinationSquare().get_y());
							} while(result == false);
						} else {
							do {
								//s_rand = pl2.randomMove();
								Move randomMove = pl2.selectBestMove();
								result = pl2.moveTo(randomMove.getSource(), randomMove.getDestinationSquare());
							} while(result == false);
							//System.out.println("Now moving " + originalType + " from (" + pl2.originalSquare.get_x() + ", " + pl2.originalSquare.get_y() + ") to (" + s_rand.get_x() + ", " + s_rand.get_y() + ") ");
						}
						Board.instance.displayBoard();
						System.out.println(" ====================================================================================== ");
						i++;
						more = "n";
						System.out.println("Do you want to continue ? (y/n) : ");
						try {
							more = br.readLine();
						} catch (IOException e) {
							System.out.println("Error!");
							System.exit(1);
						}
					} while (more.charAt(0) == 'y');
					//nitin playing around
					playMode = 0;
			  }
		}while(!exit.equals("Q"));
		System.out.println("\nGoodbye!");
	}
}
