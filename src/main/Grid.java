package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
	int grid_length = GameManager.grid_length;
	int grid_width = GameManager.grid_width;
	Cell[][] matrix = new Cell[grid_length][grid_width];

	Grid parent ; 
	List<Grid> children = new ArrayList<>();
	
	Map<Integer , String> row_douples = new HashMap<>();
	Map<Integer , String> col_douples = new HashMap<>();
	public Grid() {
        for (int i = 0; i < grid_length; i++) {
            for (int j = 0; j < grid_width; j++) {
                matrix[i][j] = new Cell();
            }
        }
        this.counts_init();
    }
	public void counts_init() {
		row_douples = Helpers.count_doubles_in_set_lines(this.matrix ,true);
		col_douples = Helpers.count_doubles_in_set_lines(this.matrix, false);
	}
	
	static boolean is_valid_move(int x , int y , char direction) {
		if(x == GameManager.grid_length && direction == 'L') return false; 
		else if(x == 0 && direction == 'R') return false; 
		else if(y == GameManager.grid_width && direction == 'D') return false; 
		else if(y == 0 && direction == 'U') return false;
		return true;
	}
	void swap (int x , int y , char direction) {
		int target_x =0  , target_y =0  ; 
		switch(direction) {
		case 'U':
			target_x = x ; 
			target_y = y - 1 ;
			break; 
		case 'D':
			target_x = x ; 
			target_y = y + 1 ;
			break;
		case 'L':
			target_x = x - 1; 
			target_y = y ;
			break;
		case 'R':
			target_x = x + 1; 
			target_y = y ;
			break;
		}
		Grid child = this;
		this.children.add(child);
		child.parent = this; 
		Cell cell1 = child.matrix[x][y];
		Cell cell2 = child.matrix[target_x][target_y];
		cell1.swap(cell2);
		if(Helpers.is_new_grid(child))
			GameManager.all_grids.add(child);
		child.counts_init();// to re-count the douplicates in a grid each step . 
		GameManager.current_grid = child;
		GameManager.print_grid(child);

	}
	public boolean is_goal() {
		if(row_douples.isEmpty() && col_douples.isEmpty()) {
			return true;
		}
		return false; 
	}
	
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof Grid)) return false;
	    Grid other = (Grid) obj;

	    // Compare cell-by-cell
	    for (int i = 0; i < grid_length; i++) {
	        for (int j = 0; j < grid_width; j++) {
	            if (!(this.matrix[i][j].color == other.matrix[i][j].color)) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	
}
