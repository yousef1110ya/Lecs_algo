package main;

import java.util.HashMap;
import java.util.Map;

public class Helpers {

	public static Map<Integer, String> count_doubles_in_line(Cell[][] grid,int index ,boolean isRow) {
		Map<Character,Integer> colorCount = new HashMap<>(); 
		int limit = isRow ? GameManager.grid_width : GameManager.grid_length;
		
		for(int i=0 ; i<limit ; i++) {
			Cell cell = isRow ? grid[index][i] : grid[i][index]; 
			colorCount.merge(cell.getColor(), 1, Integer::sum);
		}
		
		// to snipe the douplicated colors
		StringBuilder doupleicated_colors = new StringBuilder();
		for(var entry : colorCount.entrySet()) {
			if(entry.getValue() > 1) {
				doupleicated_colors.append(entry.getKey());
			}
		}
	
		Map<Integer , String> result = new HashMap<>();
		// to insure that we would only return repeated colors to the set.
		if(doupleicated_colors.length() > 0) {
			result.put(index, doupleicated_colors.toString());
		}
		
		return result; 
	}	
	
	public static Map<Integer , String> count_doubles_in_set_lines(Cell[][] grid , boolean isRow){
		Map<Integer , String> result = new HashMap<>();
		int limit = isRow ? GameManager.grid_width : GameManager.grid_length;
		for(int i=0;i<limit;i++) {
			Map<Integer,String> lineResult = count_doubles_in_line(grid , i , isRow);
			result.putAll(lineResult);

		}
		
		return result;
		
	}
	
	
	
	public static boolean is_new_grid(Grid current) {
		 for (Grid existing : GameManager.all_grids) {
		        if (existing.equals(current)) {
		            return false; 
		            }
		    }
		    return true;
	}
}
