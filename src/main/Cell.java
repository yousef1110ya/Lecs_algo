package main;

public class Cell {
	char color ;
	/*
	 * G = green 
	 * R = red 
	 * B = blue 
	 * Y = yello
	 * N = no assigned color yet 
	 * */

	public Cell() {
		this.color = 'N';
	}
	
	public char getColor() {
		return color;
	}
	public void setColor(char color) {
		this.color = color;
	}

	void swap(Cell target) {
		char temp1 = this.color; 
		char temp2 = target.color;
		
		this.setColor(temp2);
		target.setColor(temp1);
	}

	@Override 
	public int hashCode() {
		return this.color;
	}
}
