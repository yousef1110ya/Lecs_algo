package main;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Bot {
	public static void play_all_states(Grid state) {
		for(int i=0 ; i<GameManager.grid_length  ;i++) {
			for(int j=0;j<GameManager.grid_length ;j++) {
				System.out.println("swiping around all of x: " + i + " y: " + j);
				state.swap(i, j, 'U');
				System.out.println();
				state.swap(i, j, 'D');
				System.out.println();
				state.swap(i, j, 'L');
				System.out.println();
				state.swap(i, j, 'R');
				System.out.println();
				System.out.println("the number of all states is : " + GameManager.all_grids.size());
			}
		}	
	}
	/*
	 * V1
	public static void depth(Grid temp) {
		//System.out.println("printing a new Grid");
		play_all_states(temp);
		if (temp.children == null || temp.children.isEmpty() || GameManager.finished_game) {
		        return; // leaf node || or the game is finished → exit
		    };
			for (Grid child : temp.children) {
				depth(child);
			}

	}
	* V2
	public static void depth(Grid start) {
	    Deque<Grid> work = new ArrayDeque<>();
	    work.push(start);

	    while (!work.isEmpty() && !GameManager.finished_game) {
	        Grid current = work.pop();
	        play_all_states(current);

	        if (current.children == null || current.children.isEmpty()) {
	            continue;
	        }

	        for (Grid child : current.children) {
	            work.push(child);
	        }
	        System.out.println("printing a new Grid " + work.size() + "====================================================");
	    }
	}
*/
	public static void depth(Grid start) {
	    Deque<Grid> work = new ArrayDeque<>();
	    Set<Grid> visited = new HashSet<>();

	    work.push(start);

	    while (!work.isEmpty() && !GameManager.finished_game) {
	        Grid current = work.pop();

	        // Skip if we’ve already explored this state
	        if (!visited.add(current)) {
	            continue;
	        }

	        play_all_states(current);

	        if (current.children != null) {
	            for (Grid child : current.children) {
	                work.push(child);
	            }
	        }
	    }
	}

	public static void play() {
		Grid start = GameManager.inital_state;
		System.out.println("starting the solving of the game");
		depth(start);
	}
}
