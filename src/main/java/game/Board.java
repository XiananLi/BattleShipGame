package game;

import java.util.ArrayList;

//char O => Occupy
//char S => Sunk
//char M => Miss
//char *  => nothing
//char ?  => unknown

public class Board {
	public char[][] grid;					// set up user's own board
	public ArrayList<Ship> shipList;			// keep to check single ship status, and overall ship's status
	public char[][] guessGrid;				// for user to see where did they guessed

	public Board(int row, int column) {
		this.grid = new char[row][column];
		this.guessGrid = new char[row][column];
		this.shipList = new ArrayList<Ship>();
	}
	
	// for user to see their own board
	public String getBoard() {
		String s = new String();
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				if ((int)grid[row][column] == 0) {
					s += "*   ";
				} else {
					s += grid[row][column] + "   ";
				}
			} 
			s += "\n";
		}

		return s;
	}
	
	// for user to see where they guessed
	public String guessBoard() {
		String s = new String();
		for (int row = 0; row < guessGrid.length; row++) {
			for (int column = 0; column < guessGrid[row].length; column++) {
				if ((int)guessGrid[row][column] == 0) {
					s += "?   ";
				} else {
					s += guessGrid[row][column] + "   ";
				}
			} 
			s += "\n";
		}
		return s;
	}

	
	//place a ship in grid, and add ship into the ship list
	public void placeAShip(int row, int column, int shipLength, String vertical) {
		if (vertical.equals("yes") || vertical.equals("y")) {
			Ship ship = new Ship(shipLength, true);
			for(int i = row; i < row + shipLength; i++) {
				grid[i][column] = 'O';
				ship.indexTable.add(new ShipIndex(i, column));
			}
			shipList.add(ship);
		} else if(vertical.equals("no") || vertical.equals("n")){
			Ship ship = new Ship(shipLength, false);
			for(int j = column; j < column + shipLength; j++) {
				grid[row][j] = 'O';
				ship.indexTable.add(new ShipIndex(row, j));
			}
			shipList.add(ship);
		} else {
			//TODO: handle situation when vertical input is something else
			System.out.println("Not a valid input for vertical section");
		}

	}
	
	public String guess(int row, int column, Board oppBoard) {
		//TODO: need to handle when guess index is out of board
		if(oppBoard.grid[row][column] == 'O') {
			System.out.println("You hit a part of enemy's ship");
			guessGrid[row][column] = 'S';
			boolean sunk = hittenShip(row, column, oppBoard);
			if(sunk) {
				return "One enemy's ship `SUNK`";
			} else {
				return "You `HIT` enemy's ship";
			}
		} else {
			guessGrid[row][column] = 'M';
			return "You `MISS` the shot";
		}
	}
	
	public boolean hittenShip(int row, int column,  Board oppBoard) { 
//		oppBoard		
//		ShipList				
//		Ship1				Ship1.indexTable{ 	(0, 1),	(0,2),	(0,3)		}
//		Ship2				Ship2.indexTable{ 	(1, 1),	(1,2)				}
//		Ship3				Ship3.indexTable{ 	(2, 2)						}

		for(int i = 0; i < oppBoard.shipList.size(); i++) {
			for(int j = 0; j < oppBoard.shipList.get(i).indexTable.size(); j++) {
				if(oppBoard.shipList.get(i).indexTable.get(j).row == row 
						&& oppBoard.shipList.get(i).indexTable.get(j).column == column ) {
					// hit the ship: ship's hp -1;
					oppBoard.shipList.get(i).hit();
					
					//if hit check if alive
					if(!oppBoard.shipList.get(i).isAlive()) {
						//not alive remove from shipList, can update overall shipList status
						oppBoard.shipList.remove(oppBoard.shipList.get(i));
						return true;
					} else {
						return false;
					}
						
				}
			}
		}
		return false;
	}
	
	
	
	
}
