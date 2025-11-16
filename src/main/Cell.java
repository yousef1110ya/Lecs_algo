package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Cell {
	char color ;
	/*
	 * G = green 
	 * R = red 
	 * B = blue 
	 * Y = yello
	 * N = no assigned color yet 
	 * */
	static Map<Character , Integer> remaining_colores = new HashMap<>();// this map will help the generate random_color function to choose a color without douplicates

	
	public static void cells_init(int color_count) {
		for(int i=0 ; i< color_count ; i ++) {
			char c = (char) ('a' + i);
			remaining_colores.put(c , color_count);
		}
	}
	public Cell() {
		this.color = Cell.generate_random_color();
	}
	
	static Random rand = new Random();
	// =========================================
	// this function would take the remaining colores from the map and then update it to suggest a color 
	// =========================================
	public static char generate_random_color() {
		List<Character> active_keys = remaining_colores.entrySet().stream().filter(e -> e.getValue() > 0).map(Map.Entry::getKey).toList();
		//System.out.println(active_keys);
		//if(active_keys.isEmpty())	return (Character) null;

		char chosen = active_keys.get(rand.nextInt(active_keys.size()));
		
		remaining_colores.put(chosen, remaining_colores.get(chosen) - 1);
		int remaining_pulls = remaining_colores.get(chosen);
		//System.out.println("chosen char: "+ chosen + " , remaining pulls: "+ remaining_pulls);
		return chosen;
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
