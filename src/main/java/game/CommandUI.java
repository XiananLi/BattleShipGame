package game;

import java.util.Scanner;

public class CommandUI {
	
	public CommandUI() {
		
	}
	
	public void Start() {
		System.out.println("Welcome to the Battle Ship Game");
		Scanner scan = new Scanner(System.in);
		System.out.println("How many rows do you want for the Grid?");
		int row = scan.nextInt();
		System.out.println("How many columns do you want for the Grid?");
		int column = scan.nextInt();
		Board boardA = new Board(row, column);
//		System.out.println("Here is the Current Look of Game board: \n"+ boardA.getBoard());
		
		System.out.println("Please enter first player's name: " );
		String userName1 = scan.next();
		System.out.println("Please enter second player's name: " );
		String userName2 = scan.next();
		SetupShip(scan, boardA, 3, userName1);
		SetupShip(scan, boardA, 2, userName1);
		SetupShip(scan, boardA, 1, userName1);
		System.out.println("You are all set will your setup, next User will Set up for his/her board");
		
		Board boardB = new Board(row, column);
		SetupShip(scan, boardB, 3, userName2);
		SetupShip(scan, boardB, 2, userName2);
		SetupShip(scan, boardB, 1, userName2);
		
		
		//even -> userName1	odd -> userName2
		int playerTurn = 0;
		while(boardA.shipList.size() > 0 && boardB.shipList.size() > 0) {
			if(playerTurn % 2 == 0) {
				System.out.println("User " + userName1 +" Take your guess(row index, column index)");
				int rowIndex = scan.nextInt();
				int columnIndex = scan.nextInt();
				String result = boardA.guess(rowIndex, columnIndex, boardB);
				System.out.println(result);
				System.out.println("Number of ship left from User " + userName2 + ": " + boardB.shipList.size());
				System.out.println("User " + userName1 + " here is your guessing board: \n" + boardA.guessBoard());
				playerTurn ++;
			} else {
				System.out.println("User " + userName2 + " Take your guess(row index, column index)");
				int rowIndex = scan.nextInt();
				int columnIndex = scan.nextInt();
				String result = boardB.guess(rowIndex, columnIndex, boardA);
				System.out.println(result);
				System.out.println("Number of ship left from User " + userName1 +": " + boardA.shipList.size());
				System.out.println("User " + userName2 +" here is your guessing board: \n" + boardB.guessBoard());
				playerTurn ++;
				
			}
			
		}
		
		if(boardA.shipList.size() > 0 )  System.out.println("User " + userName1 + ", you win the game");
		else System.out.println("User " + userName2 + ", you win the game");
		
		scan.close();
	}
	
	private void SetupShip(Scanner scan, Board board, int shipType, String userName) {

		System.out.println("User " + userName + ", enter the Ship Type No." + shipType +"'s location(start row index, start column index, vertical)?");
		int shiprow = scan.nextInt();
		int shipcol = scan.nextInt();
		String vertical = scan.next();
		board.placeAShip(shiprow, shipcol, shipType, vertical);
		System.out.println("Here is the Current board: \n" + board.getBoard());
	}
	
	

}
