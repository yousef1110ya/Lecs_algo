package main; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
	// =================================================
	// Constants  .
	// =================================================
	int grid_length = GameManager.grid_length;
	int grid_width = GameManager.grid_width;
	static int grid_size; 
	Cell[][] matrix;
	Grid parent ; 
	List<Grid> children = new ArrayList<>();
	
	Map<Integer , String> row_douples = new HashMap<>();
	Map<Integer , String> col_douples = new HashMap<>();
	// =================================================
	// Constructors .
	// =================================================
	public static void set_grid_size(int size) {
		grid_size= size;
	}
	public Grid(int grid_size) {
		this.grid_length = grid_size; 
		this.grid_width = grid_size;
		this.matrix = new Cell[grid_size][grid_size];
        for (int i = 0; i < grid_size; i++) {
            for (int j = 0; j < grid_size; j++) {
                matrix[i][j] = new Cell();
                //System.out.println("added a new Cell in pos i: " + i + " j: " + j  );
                }
        }
        this.counts_init();
    }
	
	// =================================================
	// Helper functions .
	// =================================================
	
	public void counts_init() {
		row_douples = Helpers.count_doubles_in_set_lines(this.matrix ,true);
		col_douples = Helpers.count_doubles_in_set_lines(this.matrix, false);
	}
	
	static boolean is_valid_move(int x , int y , char direction) {
		if(x == GameManager.grid_length -1  && direction == 'D') return false; 
		else if(x == 0 && direction == 'U') return false; 
		else if(y == GameManager.grid_length - 1 && direction == 'R') return false; 
		else if(y == 0 && direction == 'L') return false;
		return true;
	}

	public boolean is_goal() {
		if(row_douples.isEmpty() && col_douples.isEmpty()) {
			return true;
		}
		return false; 
	}
	
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Grid other = (Grid) o;

	    // Dimensions must match
	    if (this.grid_length != other.grid_length ||
	        this.grid_width  != other.grid_width) {
	        return false;
	    }

	    // Matrix comparison based on Cell.color
	    for (int i = 0; i < grid_size; i++) {
	        for (int j = 0; j < grid_size; j++) {
	            char c1 = this.matrix[i][j].color;
	            char c2 = other.matrix[i][j].color;
	            if (c1 != c2) return false;
	        }
	    }

	    return true;
	}

	@Override
	public int hashCode() {
	    int result = 1;
	    final int prime = 31;

	    // Hash based solely on matrix cell colors
	    for (int i = 0; i < grid_size; i++) {
	        for (int j = 0; j < grid_size; j++) {
	            result = prime * result + this.matrix[i][j].color;
	        }
	    }

	    return result;
	}
	// =================================================
	// Main functions .
	// =================================================
	
	void swap (int x , int y , char direction) {
		if(!Grid.is_valid_move(x, y, direction)) {
			return ;
		}
		int target_x =0  , target_y =0  ; 
		switch(direction) {
		case 'U':
			target_x = x - 1  ; 
			target_y = y ;
			break; 
		case 'D':
			target_x = x + 1 ; 
			target_y = y  ;
			break;
		case 'L':
			target_x = x; 
			target_y = y - 1;
			break;
		case 'R':
			target_x = x; 
			target_y = y + 1 ;
			break;
		}
		Grid child = this;
		Cell cell1 = child.matrix[x][y];
		System.out.println("the value for x: "+x+" y: "+y + "and the targets are , X: "+ target_x + " Y:"+target_y+"  and the direction is : "+ direction);
		Cell cell2 = child.matrix[target_x][target_y];
		cell1.swap(cell2);
		if(!GameManager.all_grids.contains(child)) {
			System.out.println("adding a new child to the grid");
			GameManager.all_grids.add(child);
			this.children.add(child);
			child.parent = this; 
			child.counts_init();// to re-count the douplicates in a grid each step . 
		}
		//GameManager.current_grid = child;
		GameManager.print_grid(child);
		if(child.is_goal()) {
			Helpers.closing_game(child);
		}

	}	
}
