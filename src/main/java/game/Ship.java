package game;

import java.util.ArrayList;

public class Ship {
	
	public int type;
	public boolean vertical;
	public ArrayList<ShipIndex> indexTable; 
	private int hp;
	
	
	public Ship(int type, boolean vertical) {
		this.type = type;
		this.vertical = vertical;
		this.indexTable = new ArrayList<ShipIndex>();
		this.hp = type;
	}
	
	public void hit() {
		this.hp --;
	}
	
	public boolean isAlive() {
		if(this.hp > 0) return true;
		else return false;
	}

}
