package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameManager {
	public static int grid_length = 4 ;
	public static int grid_width  = 4 ;

	static List<Grid> all_grids = new ArrayList<>();
	static Grid current_grid ; 
	static Grid inital_state ;
	public static void user_game() {
		while(!GameManager.current_grid.is_goal()) {
		// 1- get the cordenates of the cell you want to change . 
		// 2- get the direction you want to change it .
		System.out.println("enter the x cordinations for the cell");
		int x = 0;
		if(in.hasNextInt()) {
		x = in.nextInt();
		}else {
			System.out.println("invalid input");
		}
		System.out.println("enter the y cordinations for the cell");
		int y = in.nextInt();
		System.out.println("enter the direction to swap the cell");
		System.out.println("U => Up");
		System.out.println("D => Down");
		System.out.println("L => Left");
		System.out.println("R => Right");
		char direction = in.next().charAt(0);
		if(!Grid.is_valid_move(x, y, direction)) {
			System.out.println("the data entired is invalid");
			continue ; 
		}
		GameManager.current_grid.swap(x, y, direction);
		}
		System.out.println("congrats you finished the game");

	}
	
	static Scanner in = new Scanner(System.in);
	

	
	public static void game_init() {
		System.out.println("enter the size for the game :");
		int grid_size = in.nextInt();
		grid_length = grid_size;
		grid_width = grid_size;
		Cell.cells_init(grid_size); // to initalize the counter for random colors .
		GameManager.inital_state = new Grid(grid_size);
		// the real start of the game ^_^
		current_grid = inital_state;
		print_grid(current_grid);
		//user_game();
		in.close();
	}
	public static void print_grid(Grid printable) {
		for (int i = 0; i < grid_length; i++) {
		    for (int j = 0; j < grid_width ; j++) {
		        System.out.print("i = " + i + " j = " + j + "| " + printable.matrix[i][j].color + " ");
		    }
		    System.out.println(); 
		}	
	}
	
}
