import java.util.ArrayList;


/**
 * @author Joshua Johnson
 * @author Brian Keith
 * @version 1.0
 * Class: CS 2321
 * Assignment: Program 5 - MyMaze
 * Description: The maze class generates a maze with designated rows and columns
 * 				and is used in the Main.java class when drawing the GUI
 */
public class MyMaze implements Maze {

	//The graph representation of our maze
	private MyGraph graphMaze;
	//The array representation of the graph
	private MyVertex [ ][ ] graphArray;
	//The start and finish vertices of the maze
	private MyVertex start, finish;

	//The number of rows and columns in the maze
	int rows, columns;
	
	/**
	 * Generates a Maze with the passed in number of rows and columns
	 * @param rows Number of rows in the maze
	 * @param columns Number of columns in the maze
	 */
	@Override
	public void generateMaze(int rows, int columns) {

		//create the graphMaze and graphArray
		graphMaze = new MyGraph();
		start = null;
		finish = null;

		if ( (rows <= 0 || columns <= 0 ) || ( rows == 1 && columns == 1 ) ) {
			graphArray = new MyVertex [ 0 ] [ 0 ];
			this.rows = 0; 
			this.columns = 0;
			return;
		} 
	
		else {
			graphArray = new MyVertex [ rows ] [ columns ]; 
		}
		
		this.rows = rows;
		this.columns = columns;

		//add vertices into graph
		addVertices();

		//set start and finish randomly, not using util.random
		setStartAndFinish();

		//generate paths in the maze
		depthFirstGeneration();
		
		//add the connected vertices into the graphArray
		for ( int i = 0; i < graphMaze.vertices().size(); i++ ) {
			MyVertex temp = ( MyVertex ) graphMaze.vertices().get( i );
			graphArray [ temp.getX() ] [ temp.getY() ] = temp;
		}
		
	}
	
	
	/**
	 * Sets the start and finish vertices for the maze.
	 * If the maze has 0 rows or columns they are not set and are null
	 * @param rows
	 * @param columns
	 */
	private void setStartAndFinish( ) {

		int min = 0;
		int max = rows*columns;
		int startNum = min + (int)(Math.random() * ((max - min) ));
		int finishNum = min + (int)(Math.random() * ((max - min) ));
	
		//if they are the same randomize until different
		while( finishNum == startNum ) {
			finishNum = min + (int)(Math.random() * ((max - min) ));
		}

		start = ( MyVertex ) graphMaze.vertices().get( startNum );
		finish = ( MyVertex ) graphMaze.vertices().get( finishNum );


	}

	/**
	 * Generates the paths in the maze using a depth-first approach.
	 * Will only generate if rows and columns are above 0
	 * @param rows The number of rows in the maze.
	 * @param columns The number of columns in the maze.
	 */
	private void depthFirstGeneration(  ) {

		//To keep track of where you have been, this is used as a stack
		ArrayList< MyVertex > locations = new ArrayList< MyVertex >();
		int numOfVertices = graphMaze.vertices().size();

		int min = 0;
		int max = rows*columns;
		//Randomly pick a starting vertex to generate from
		MyVertex currentLocation = ( MyVertex ) ( graphMaze.vertices().get( min + ( int )( Math.random() * ( ( max - min ) ) ) ) );
		int visitedLocations = 1;
		//Add edges until every vertex has one edge
		while ( visitedLocations < numOfVertices ) {
			//find all vertices next to currentCell with no adjacent vertices
			ArrayList< MyVertex > unTouchedLocations = new ArrayList< MyVertex >();
			int curX = currentLocation.getX();
			int curY = currentLocation.getY();

			//This finds all neighbors to the current position that have not been visited and adds it to the List
			for ( int tempV = 0; tempV < graphMaze.vertices().size(); tempV++ ) {
				//so we can access the x and y
				MyVertex temp = (MyVertex) graphMaze.vertices().get( tempV );
				//If it is next to our current location
				if ( ( ( temp.getX() - 1 == curX || temp.getX() + 1 == curX ) && temp.getY() == curY ) ||  ( ( temp.getY() - 1 == curY || temp.getY() + 1 == curY ) && temp.getX() == curX ) ) {
					//if it has nothing adjacent to it
					if ( temp.adjacentVertices().size() == 0 ) {
						unTouchedLocations.add( temp );
					}
				}
			}
			//if we have locations next to us with no connections
			if ( unTouchedLocations.size() > 0 ) {
				//choose one at random
				int randVer =  ( int )( Math.random() *   unTouchedLocations.size() );
				MyVertex nextVer = unTouchedLocations.get( randVer );

				//connect them and move to the new location
				graphMaze.addEdge( currentLocation, nextVer );
				locations.add( currentLocation );

				currentLocation = nextVer;
				visitedLocations++;
			}
			//no untouched locations next to the current location
			else {
				//making the ArrayList act like a stack
				//Go back a step to look for neighbors with no connections
				currentLocation = locations.get( locations.size() - 1 );
				locations.remove( locations.size() - 1 );
			}
		}
	}

	/**
	 * This is a helper method that inserts all the vertices into the graphMaze and the grapArray
	 * Will only add vertices if rows and columns > 0
	 * @param rows The number of rows in the maze.
	 * @param columns The number of columns in the maze.
	 */
	private void addVertices( ) {

		//Loop through all vertices and set their x and y variables, then add them to the graph
		for ( int row = 0; row < rows ; row++ ) {
			for ( int col = 0; col < columns ; col++ ) {
				//create the vertex
				MyVertex temp = new MyVertex( );
				temp.setY( col );
				temp.setX( row );
				
				graphMaze.addVertex( temp );
			}
		}
	}

	/**
	 * @return Returns an ArrayList of Vertices that is the 
	 *         path from the start to the finish of the maze.
	 */
	@Override
	public ArrayList< Vertex > solveMaze() {

		return graphMaze.shortestPath( start, finish );

	}

	/**
	 * @return Returns a graph representation of the maze.
	 */
	@Override
	public Graph toGraph( ) {
		return ( Graph ) graphMaze;
	}

	/**
	 * @return Returns an Array representation of the maze.
	 */
	@Override
	public Vertex[][] toArray( ) {
		return ( Vertex[ ][ ] ) graphArray;
	}

	/**
	 * @return Returns the start vertex of the maze.
	 */
	@Override
	public Vertex startVertex( ) {
		return ( Vertex ) start;
	}

	/**
	 * @return Returns the finish vertex of the maze.
	 */
	@Override
	public Vertex finishVertex( ) {
		return ( Vertex ) finish;
	}

	/**
	 * Returns a string representation of the maze
	 */
	@Override
	public String toString( ) {
		if ( rows <= 0 || columns <= 0 ) return "";
		String str = "";
		ArrayList< Vertex > solution = solveMaze( );

		for ( int i = 0; i < rows; i++ ) {

			//vertical edges
			if ( i != 0 ) {
				for ( int j = 0; j < columns; j++ ) {
					if ( graphMaze.areConnected( graphArray[i - 1][j], graphArray[i][j] ) ){
						//Edge is in solution
						if ( solution.contains( graphArray[i - 1][j] ) && solution.contains( graphArray[i][j] ) )
							str += "!  "; //CHANGE THIS WHEN WE FIND OUT WHICH CHARACTER TO USE, OR IF WE SHOULD CONVERT TO UTF-8
						//Normal Edge
						else
							str += "|  ";
					}
					//No Edge
					else
						str += "   ";
					
				}
			}
			
			str += "\n";
			
			//vertices and horizontal edges
			for ( int j = 0; j < columns; j++ ) {
				//Finish
				if ( graphArray[i][j].getId() == finish.getId() )
					str += "F";
				//Start
				else if ( graphArray[i][j] == start )
					str += "S";
				//Vertex is in solution
				else if ( solution.contains( graphArray[i][j] ) )
					str += "o";
				//Normal Vertex
				else
					str += "•";
				
				if ( j != columns - 1 ) {
					if ( graphMaze.areConnected( graphArray[i][j], graphArray[i][j + 1] ) ){
						//Edge is in solution
						if ( solution.contains( graphArray[i][j]) && solution.contains( graphArray[i][j + 1] ) )
							str += "==";
						//Normal Edge
						else
							str += "--";
					}
					//No Edge
					else
						str += "  ";
				}
				
			}
			
			str += "\n";
		}
		
		return str;
	}

}
