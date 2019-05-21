package game;

import java.util.Scanner;

public class CommandUI {
	
	public CommandUI() {	}
	
	public void Start() {
		System.out.println("Welcome to the Battle Ship Game");
		Scanner scan = new Scanner(System.in);
		
		// reading game setup
		System.out.println("How many rows do you want for the Grid?");
		int row = scan.nextInt();
		System.out.println("How many columns do you want for the Grid?");
		int column = scan.nextInt();
		System.out.println("How many ships do you want for the Grid?");
		int shipNumb = scan.nextInt();
		System.out.println("Please enter first player's name: " );
		String userName1 = scan.next();
		System.out.println("Please enter second player's name: " );
		String userName2 = scan.next();
		
		// set up user's board, place the ships
		Board boardA = setupBoard(scan, row, column, userName1, shipNumb);
		System.out.println("You are all set will your setup, next User will Set up for his/her board \n\n");
		Board boardB = setupBoard(scan, row, column, userName2, shipNumb);
		
		
		// game start
		// even -> userName1	odd  odd-> userName2
		int playerTurn = 0;
		// keep checking if there is a winner
		while(boardA.shipList.size() > 0 && boardB.shipList.size() > 0) {
			if(playerTurn % 2 == 0) {
				singleGuess(userName1, userName2, boardA, boardB, scan);
				playerTurn ++;
			} else {
				singleGuess(userName2, userName1, boardB, boardA, scan);
				playerTurn ++;
			}
		}
		
		
		if(boardA.shipList.size() > 0 )  System.out.println("User " + userName1 + ", you `WIN` the game");
		else System.out.println("User " + userName2 + ", you `WIN` the game");
		
		scan.close();
	}
	
	// set up board: users place the ship
	private Board setupBoard(Scanner scan, int row, int column, String userName, int shipNum) {
		Board board = new Board(row, column);
		for(int i = shipNum; i > 0 ; i--) {
			setupShip(scan, board, i, userName);
		}
		return board;	
	}
	
	// user place ship in a specific location
	private void setupShip(Scanner scan, Board board, int shipType, String userName) {

		System.out.println("User " + userName + ", enter the Ship Type No." + shipType +"'s location(start row index, start column index, vertical)?");
		int shiprow = scan.nextInt();
		int shipcol = scan.nextInt();
		String vertical = scan.next();
		board.placeAShip(shiprow, shipcol, shipType, vertical);
		System.out.println("Here is the Current board: \n" + board.getBoard());
	}
	
	// take a single guess and print the result
	private void singleGuess(String selfName, String oppName, Board selfBoard, Board oppBoard, Scanner scan) {
		System.out.println("User " + selfName +" Take your guess(row index, column index)");
		int rowIndex = scan.nextInt();
		int columnIndex = scan.nextInt();
		String result = selfBoard.guess(rowIndex, columnIndex, oppBoard);
		System.out.println(result);
		System.out.println("Number of ship left from User " + oppName + ": " + oppBoard.shipList.size());
		System.out.println("User " + selfName + " here is your guessing board: \n" + selfBoard.guessBoard());
	}
	

}
