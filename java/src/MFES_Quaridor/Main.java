package MFES_Quaridor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.overture.codegen.runtime.VDMSeq;

public class Main {
	static boolean p1turn;

	public static void main(String[] args) {
		System.out.println("Hello World!"); // Display the string.

		Player p1;
		Player p2;
		try {
			p1 = new Player(askname(1));
			p2 = new Player(askname(2));
		} catch (IOException e) {
			p1 = new Player();
			p2 = new Player();
		}

		Game g = new Game(p1, p2);

		p1turn = true;

		String direction = "";

		while (g.getGameState().toString().equals("3")) {
			try {
				int choice = askWhatPlay();
				if(choice==1){
					direction = requestDirection();
					g.movePiece(direction);
				}
				else{
					int[] coords = requestCoords();
					VDMSeq coords1 = new VDMSeq();
			        VDMSeq coords2 = new VDMSeq();
			        coords1.add(coords[0]);
			        coords1.add(coords[1]);
			        coords2.add(coords[2]);
			        coords2.add(coords[3]);
			        g.placeWall(coords1, coords2);
				}
					
				
				g.printBoard();
				p1turn = !p1turn;
			} catch (IOException e) {
				System.out.println("Something went wrong :( ");
			}
		}

		System.out.println("\n");
		g.printBoard();
		if(g.getGameState().toString().equals("1"))
			System.out.println("Player " + p1.GetName() + " Wins!");
		else if(g.getGameState().toString().equals("1"))
			System.out.println("Player " + p2.GetName() + " Wins!");
		

	}

	public static String askname(int pid) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please insert player name");
		if (pid == 1) {
			System.out.print("Player 1: ");
			return br.readLine();
		} else {
			System.out.print("Player 2: ");
			return br.readLine();
		}

	}

	public static int askWhatPlay() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\n");
		int choice = -1;
		while (choice != 1 && choice != 2) {
			if (p1turn) {
				System.out.println("Player 1 want to Move(1) or PlaceWall(2)?: ");
				choice = Integer.parseInt(br.readLine());
			} else {
				System.out.println("Player 2 want to Move(1) or PlaceWall(2)?: ");
				choice = Integer.parseInt(br.readLine());
			}
		}
		return choice;

	}

	public static String requestDirection() throws IOException{
		/*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		VDMSeq coords1 = new VDMSeq();
        VDMSeq coords2 = new VDMSeq();
        coords1.add(1);
        coords1.add(10);
        coords2.add(3);
        coords2.add(10);*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\n");
		boolean valid_choice = false;
		String choice = "";
		while(!valid_choice)
		{
				System.out.println("In which direction do you wish to move(N,S,E,O)?");
				choice= br.readLine();
				if(choice.equals("N") || choice.equals("E") || choice.equals("S") || choice.equals("O"))
					valid_choice = true;
		}
		return choice;
		
	}
	
	public static int[] requestCoords() throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nWhere do you wanna place the wall?");
		int[] choices = {-1,-1,-1,-1};
		while(choices[0]==-1 || choices[1]==-1)
		{
				System.out.println("first coordinate (row - numeric): ");
				choices[0]= Integer.parseInt(br.readLine());
				System.out.println("first coordinate (column - CAPS letter): ");
				choices[1]= (int)br.readLine().charAt(0) - 64;
				
		}
		while(choices[2]==-1 || choices[3]==-1)
		{
				System.out.println("second coordinate (row - numeric): ");
				choices[2]= Integer.parseInt(br.readLine());
				System.out.println("second coordinate (column - CAPS letter): ");
				choices[3]= (int)br.readLine().charAt(0) - 64;
				
		}
		return choices;
		
	}
}
