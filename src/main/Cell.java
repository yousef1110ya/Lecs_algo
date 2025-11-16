package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {
	char color ;
	/*
	 * G = green 
	 * R = red 
	 * B = blue 
	 * Y = yello
	 * N = no assigned color yet 
	 * */
	public static int color_count = GameManager.grid_length;
	static Map<Character , Integer> remaining_colores = new HashMap<>();// this map will help the generate random_color function to choose a color without douplicates

	
	public static void cells_init() {
		for(int i=0 ; i< color_count ; i ++) {
			char c = (char) ('a' + 1);
			remaining_colores.put(c , color_count);
		}
	}
	
	
	
	public static char generate_random_color() {
//		List<Character> active_keys = remaining_colores.entrySet().stream().filter(e -> e.getValue() > 0).map(Map.Entry::getkey).toList();
	
		return 'z';
	}
	
	
	
	
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
