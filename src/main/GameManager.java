package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameManager {
	public static int grid_length = 4 ;
	public static int grid_width  = 4 ;

	static List<Grid> all_grids = new ArrayList<>();
	static Grid current_grid = new Grid(); 
	static Grid inital_state = new Grid();
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
		Cell.cells_init(); // to initalize the counter for random colors .
		System.out.println("enter the color for each of these cells");
		for (int i = 0; i < GameManager.grid_length; i++) {
	        for (int j = 0; j < GameManager.grid_width; j++) {
	            System.out.printf("Enter value for [%d][%d]: ", i, j);
	            inital_state.matrix[i][j].setColor(in.next().charAt(0)); 
	        }
	    }
		// the real start of the game ^_^
		current_grid = inital_state;
		print_grid(current_grid);
		user_game();
		in.close();
	}
	public static void print_grid(Grid printable) {
		for (int i = 0; i < grid_length; i++) {
		    for (int j = 0; j < grid_width ; j++) {
		        System.out.print(printable.matrix[i][j].color + " ");
		    }
		    System.out.println(); 
		}	
	}
	
}
