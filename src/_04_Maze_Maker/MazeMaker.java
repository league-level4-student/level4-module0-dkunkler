package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(randGen.nextInt(w),randGen.nextInt(h)));
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> unvisitedCells = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(!unvisitedCells.isEmpty())
		{
			//C1. select one at random.
			int nextCellIndex = randGen.nextInt(unvisitedCells.size());
			//C2. push it to the stack
			uncheckedCells.push(unvisitedCells.get(nextCellIndex));
			//C3. remove the wall between the two cells
			removeWalls(currentCell, unvisitedCells.get(nextCellIndex));
			//C4. make the new cell the current cell and mark it as visited
			currentCell = unvisitedCells.get(nextCellIndex);
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
			
		}
		else
		{
		//D. if all neighbors are visited
			//if(!unvisitedCells.isEmpty())
			//D1. if the stack is not empty
			//{
				if(!uncheckedCells.empty()) 
				{
					// D1a. pop a cell from the stack
					Cell newCell = uncheckedCells.pop();
					// D1b. make that the current cell
					currentCell = newCell;
					// D1c. call the selectNextPath method with the current cell
					selectNextPath(currentCell);
				}
			//}	
		}	
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(((Math.abs(c1.getX()-c2.getX())==1) && (c1.getY() == c2.getY())) ||
				((Math.abs(c1.getY()-c2.getY())==1)) && (c1.getX() == c2.getX()))
		{
			if(c1.getX() == c2.getX()) {
				if(c1.getY()>c2.getY()) {
					//remove South wall from C1, North wall from C2
					c1.setNorthWall(false);
					c2.setSouthWall(false);
				}
				else {
					//remove North wall from C1, South wall from C2
					c1.setSouthWall(false);
					c2.setNorthWall(false);
				}
			}
			else
			{
				if(c1.getX()>c2.getX()) {
					//remove South wall from C1, North wall from C2
					c1.setWestWall(false);
					c2.setEastWall(false);
				}
				else {
					//remove North wall from C1, South wall from C2
					c1.setEastWall(false);
					c2.setWestWall(false);
				}
				
			}
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisited = new ArrayList<Cell>();
		System.out.println("getUnvisitedNeighbors x=" + c.getX() + "  y=" + c.getY());
		if(c.getX()>0)
		{
			if(!maze.cells[c.getX()-1][c.getY()].hasBeenVisited())
				unvisited.add(maze.cells[c.getX()-1][c.getY()]);
		}
		if(c.getX()<maze.getWidth()-1)
		{
			if(!maze.cells[c.getX()+1][c.getY()].hasBeenVisited())
				unvisited.add(maze.cells[c.getX()+1][c.getY()]);
		}
		if(c.getY()>0)
		{
			if(!maze.cells[c.getX()][c.getY()-1].hasBeenVisited())
				unvisited.add(maze.cells[c.getX()][c.getY()-1]);
		}
		if(c.getY()<maze.getHeight()-1)
		{
			if(!maze.cells[c.getX()][c.getY()+1].hasBeenVisited())
				unvisited.add(maze.cells[c.getX()][c.getY()+1]);
		}
		
		
		System.out.println("unvisited.size() = " + unvisited.size());
		
		return unvisited;
	}
}
