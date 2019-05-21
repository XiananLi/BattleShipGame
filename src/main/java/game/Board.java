package game;

import java.util.ArrayList;

// char O => Occupy
// char S => Sunk
// char M => Miss

public class Board {
	public char[][] grid;
	public ArrayList<Ship> shipList;
	public char[][] guessGrid;

	public Board(int row, int column) {
		this.grid = new char[row][column];
		this.guessGrid = new char[row][column];
		this.shipList = new ArrayList<Ship>();
	}

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

	public void placeAShip(int row, int column, int shipLength, String vertical) {
		if (vertical.equals("yes") || vertical.equals("y")) {
			Ship ship = new Ship(shipLength, true);
			for(int i = row; i < row + shipLength; i++) {
				grid[i][column] = 'O';
				ship.indexTable.add(new ShipIndex(i, column));
			}
			shipList.add(ship);
		} else {
			Ship ship = new Ship(shipLength, false);
			for(int j = column; j < column + shipLength; j++) {
				grid[row][j] = 'O';
				ship.indexTable.add(new ShipIndex(row, j));
			}
			shipList.add(ship);
		}

	}
	
	public String guess(int row, int column, Board oppBoard) {
		if(oppBoard.grid[row][column] == 'O') {
			System.out.println("You hit a part of enemy's ship");
			guessGrid[row][column] = 'S';
			boolean sunk = hittenShip(row, column, oppBoard);
			if(sunk) {
				return "sunk";
			} else {
				return "hit";
			}
		} else {
			System.out.println("You miss the shot");
			guessGrid[row][column] = 'M';
			return "Miss";
		}
	}
	
	public boolean hittenShip(int row, int column,  Board oppBoard) { 
		
		for(int i = 0; i < oppBoard.shipList.size(); i++) {
			for(int j = 0; j < oppBoard.shipList.get(i).indexTable.size(); j++) {
				if(oppBoard.shipList.get(i).indexTable.get(j).row == row 
						&& oppBoard.shipList.get(i).indexTable.get(j).column == column ) {
					oppBoard.shipList.get(i).hit();
					if(!oppBoard.shipList.get(i).isAlive()) {
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
