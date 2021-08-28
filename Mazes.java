import java.util.*;
import java.io.*;
public class Mazes {
	
	public static int rows, cols;
	public static boolean[][] used;
	public static char[][] maze_fin;
	//public static boolean  Adone = false, Bdone = false, Cdone = false, Ddone = false;
	//public static int portalA1x = 0, portalA1y = 0, portalA2x = 0, portalA2y = 0, portalB1x = 0, portalB1y = 0, portalB2x = 0, portalB2y = 0, portalC1x = 0, portalC1y = 0, portalC2x = 0, portalC2y = 0, portalD1x = 0, portalD1y = 0, portalD2x = 0, portalD2y = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(new File("Mazes.txt"));
		int row = scan.nextInt();
		int col = scan.nextInt();
		
		rows = row;
		cols = col;
		
		used = new boolean[row][col];
		char[][] maze = new char[row][col];
		maze_fin = new char[row][col];
		int startx = 0, starty = 0, endx = 0, endy = 0;
		for(int i = 0; i < row; i++)
		{
			String line = scan.next();
			for(int j = 0; j < col; j++)
			{
				maze[i][j] = line.charAt(j);
				used[i][j] = false;

				if(maze[i][j] == ('F'))
				{
					endx = j;
					endy = i;
				}
				if(maze[i][j] == 'S')
				{
					startx = j;
					starty = i;
				}
			}
		}
		maze_fin = maze;
		solveMaze(maze, endy, endx, startx, starty);
		printMaze(maze_fin);
	}
	
	public static boolean solveMaze(char[][] maze, int endy, int endx, int startx, int starty)
	{	
		
		int heuristic = Math.abs(endy - starty) + Math.abs(endx - startx);
		
		/*
		 * If the place being searched is out of bounds or is blocked, return false
		 * If it is the end marker(or the start in this case) return true
		 */
		if(endx < 0 || endx > cols - 1 || endy < 0 || endy > rows - 1)
		{
			heuristic++;
			return false;
		}
		
		if(maze[endy][endx] == '#')
		{
			heuristic++;
			return false;
		}
		if(maze[endy][endx] == 'S')
		{	
			System.out.println(startx + " " + starty + "FFFFFFFF");
			return true;
		}
		
		if(maze[endy][endx] == '.')
		{
			maze_fin[endy][endx] = ' ';
		}
		
		used[endy][endx] = true;
		
		/*if(maze[endy][endx] == 'A' && !Adone)
		{
			System.out.println("AAAAA");
			Adone = true;
			for(int i = 0; i < rows; i++)
				for(int j = 0; j < cols; j++)
					if(endy != i && endx != j && maze[i][j] == 'A')
						solveMaze(maze, i, j, startx, starty);
		}
		
		if(maze[endy][endx] == 'B' && !Bdone)
		{
			System.out.println("BBBBBBB");
			Bdone = true;
			for(int i = 0; i < rows; i++)
				for(int j = 0; j < cols; j++)
					if(endy != i && endx != j && maze[i][j] == 'B')
						solveMaze(maze, i, j, startx, starty);
		}
		
		if(maze[endy][endx] == 'C' && !Cdone)
		{
			Cdone = true;
			for(int i = 0; i < rows; i++)
				for(int j = 0; j < cols; j++)
					if(endy != i && endx != j && maze[i][j] == 'C')
						solveMaze(maze, i, j, startx, starty);
		}
		
		if(maze[endy][endx] == 'D' && !Ddone)
		{
			Ddone = true;
			for(int i = 0; i < rows; i++)
				for(int j = 0; j < cols; j++)
					if(endy != i && endx != j && maze[i][j] == 'D')
						solveMaze(maze, i, j, startx, starty);
		}*/
		
		
		System.out.println(endy + " " + endx);
		
		/*
		 * The code searches for the seemingly shortest path and follows it. If this
		 * path turns up as a dead end, then the next shortest path is chosen.
		 */
		if(!used[endy + 1][endx] && Math.abs(endy + 1 - starty) + Math.abs(endx - startx) < heuristic )//down
		{	
			if(solveMaze(maze, endy + 1, endx, startx, starty))
			{
				maze_fin[endy][endx] = ' ';
				return true;
			}
		}
		
		if(!used[endy - 1][endx] && Math.abs(endy - 1 - starty) + Math.abs(endx - startx) < heuristic )//up
		{	
			if(solveMaze(maze, endy - 1, endx, startx, starty))
			{
				maze_fin[endy][endx] = ' ';
				return true;
			}
		}
		
		if(!used[endy][endx - 1] && Math.abs(endy - starty) + Math.abs(endx - 1 - startx) < heuristic )//left
		{	
			if(solveMaze(maze, endy, endx - 1, startx, starty))
			{
				maze_fin[endy][endx] = ' ';
				return true;
			}
		}
		
		if(!used[endy][endx + 1] && Math.abs(endy - starty) + Math.abs(endx + 1 - startx) < heuristic )//right
		{	System.out.println("right");
			if(solveMaze(maze, endy, endx + 1, startx, starty))
			{
				maze_fin[endy][endx] = ' ';
				return true;
			}	
		}
		
		/*
		 * This chooses a path even if it is farther from the end point
		 */
		if(!used[endy + 1][endx] && solveMaze(maze, endy + 1, endx, startx, starty)) //down
		{
			System.out.println("down");
			maze_fin[endy][endx] = ' ';
				return true;
		}
		
			
		if(!used[endy - 1][endx] && solveMaze(maze, endy - 1, endx, startx, starty)) //up
		{
			System.out.println("up");
			maze_fin[endy][endx] = ' ';
				return true;
		}
		
			
		if(!used[endy][endx + 1] && solveMaze(maze, endy, endx + 1, startx, starty)) //right
		{
			System.out.println("right");
			maze_fin[endy][endx] = ' ';
				return true;
		}
		
		if(!used[endy][endx - 1] && solveMaze(maze, endy, endx - 1, startx, starty)) //left
		{
			maze_fin[endy][endx] = ' ';
				return true;
		}
		
		return false;
	}
	
	/*
	 * Prints the maze.
	 */
	public static void printMaze(char[][] mazes)
	{
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				System.out.print(mazes[i][j]);
				if(j == cols - 1)
					System.out.println();
			}
		}
	}

}
