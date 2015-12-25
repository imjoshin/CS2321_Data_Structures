import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Joshua Johnson
 * @author Brian Keith
 * @version 1.0
 * Class: CS 2321
 * Assignment: Program 5 MyMaze
 * Description: This class tests all the methods in MyMaze.java for edge
 *              cases.
 * Disclaimer One: No Integer.MAX_VALUE or Integer.MIN_VALUE test cases are used
 * 			       due to the large amount of time and memory such a test would 
 * 			       take.
 * 
 * Disclaimer Two: Method that deal with the Pair class are not tested
 * 				   as they are not used. All other methods are tested 
 * 				   by the use of the methods in the MyMaze and MyGraph
 *                 classes which are used extensively. If they don't 
 *                 work the entire maze does not work. We found this
 *                 to be sufficient testing.
 *
 */
public class MyMazeTest {

	/**
	 * Tests the generateMAze( int rows, int columns ) method for edge cases
	 */
	@Test
	public void testGenerateMaze( ) {

		//TestOne
		//Edge Case where there are 0 rows and some number of columns

		MyMaze testMaze = new MyMaze();
		testMaze.generateMaze( 0, 5 );

		//There should be no start 
		if ( testMaze.startVertex() != null ) {
			fail( "Start vertex not null when 0 rows!" );
		}

		//There should be no finish
		if ( testMaze.finishVertex() != null ) {
			fail( "Finish vertex not null when 0 rows!" );
		}

		//There should be no solution
		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "There should be no solution!" );
		}

		//There should be no vertices in the maze
		if ( testMaze.toGraph().vertices().size() > 0 ) {
			fail( "There should be no vertices in the graph!" );
		}

		//The array representation should be of size 0
		if ( testMaze.toArray().length > 0 ) {
			fail( "Array should be of size 0!" );
		}

		//The toString Method should return ""
		if ( !testMaze.toString().equals( "" ) ) {
			fail( "ToString should be empty!" );
		}

		//TestTwo
		//Edge Case where there are 0 columns and some number of rows

		testMaze.generateMaze( 5, 0 );

		//There should be no start 
		if ( testMaze.startVertex() != null ) {
			fail( "Start vertex not null when 0 rows!" );
		}

		//There should be no finish
		if ( testMaze.finishVertex() != null ) {
			fail( "Finish vertex not null when 0 rows!" );
		}

		//There should be no solution
		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "There should be no solution!" );
		}

		//There should be no vertices in the maze
		if ( testMaze.toGraph().vertices().size() > 0 ) {
			fail( "There should be no vertices in the graph!" );
		}

		//The array representation should be of size 0
		if ( testMaze.toArray().length > 0 ) {
			fail( "Array should be of size 0!" );
		}

		//The toString Method should return ""
		if ( !testMaze.toString().equals( "" ) ) {
			fail( "ToString should be empty!" );
		}

		//TestThree
		//Edge Case where rows and columns are both 1

		testMaze.generateMaze( 1, 1 );

		//The start and finish should be the same
		if ( testMaze.startVertex() != testMaze.finishVertex() ) {
			fail( "Start vertex should be the same as the finish vertex!" );
		}


		//There should be no solution
		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "There should be no a solution!" );
		}

		//There should be no vertices in the maze
		if ( testMaze.toGraph().vertices().size() > 0 ) {
			fail( "There should be no vertices in the graph!" );
		}

		//The array representation should be of size 0
		if ( testMaze.toArray().length != 0 ) {
			fail( "Array should be of size 0!" );
		}

		//The toString Method should return ""
		if ( !testMaze.toString().equals( "" ) ) {
			fail( "ToString should be empty!" );
		}

		//TestFour
		//Edge Case where there are a lot of rows and columns, checks to see if there are the right amount of vertices
		testMaze.generateMaze( 80, 50 );

		if ( testMaze.toGraph().vertices().size() != 80 * 50 ) {
			fail( " Did not generate the correct amount of vertices!" );
		}


		//TestFive
		//EdgeCase where negative numbers are used as input
		testMaze.generateMaze(-50, -50);
		if ( testMaze.startVertex() != null ) {
			fail( "Start vertex not null when negative rows and columns!" );
		}

		//There should be no finish
		if ( testMaze.finishVertex() != null ) {
			fail( "Finish vertex not null when negative rows and columns!" );
		}

		//There should be no solution
		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "There should be no solution!" );
		}

		//There should be no vertices in the maze
		if ( testMaze.toGraph().vertices().size() > 0 ) {
			fail( "There should be no vertices in the graph!" );
		}

		//The array representation should be of size 0
		if ( testMaze.toArray().length > 0 ) {
			fail( "Array should be of size 0!" );
		}

		//The toString Method should return ""
		if ( !testMaze.toString().equals( "" ) ) {
			fail( "ToString should be empty!" );
		}

	}

	/**
	 * Tests the solveMaze( ) method for edge cases
	 */
	@Test
	public void testSolveMAze( ) {

		MyMaze testMaze = new MyMaze();
		//TestOne
		//EdgeCase where the maze is not solvable 0 rows and 0 columns
		testMaze.generateMaze( 0, 0 );

		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "The solution should be empty due to 0 rows and 0 columns!" );
		}

		//TestTwo
		//EdgeCase with 0 rows and some number of columns
		testMaze.generateMaze( 0, 50 );

		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "The solution should be empty due to 0 rows and some # of columns!" );
		}

		//TestThree
		//EdgeCase with some number of rows and 0 columns
		testMaze.generateMaze(50, 0);

		if ( testMaze.solveMaze().size() > 0 ) {
			fail( "The solution should be empty due to some # of rows and 0 columns!" );
		}

		//TestThree
		//EdgeCase with equal rows and columns
		testMaze.generateMaze( 20, 20 );

		//check to see if solution is correct
		ArrayList< Vertex > solution = testMaze.solveMaze();
		//check the start and finish are correct
		if ( !testMaze.startVertex().equals( solution.get( 0 ) ) ) {
			fail( "Solution does not start at the start!" ); 
		}

		if ( !testMaze.finishVertex().equals( solution.get( solution.size() - 1 ) ) ) {
			fail( "Solution does not end at the finish!" ); 
		}
		//check to make sure the path is correct
		for ( int ver = 0; ver < solution.size(); ver++ ) {
			//stop once we hit the finish
			if ( solution.get( ver ).equals( testMaze.finishVertex() ) ) {
				break;
			}
			if ( !solution.get( ver ).adjacentVertices().contains(solution.get( ver + 1) ) ) {
				fail( "Solution not connected!" );
			}
		}
	}

	/**
	 * Tests the toGraph( ) method for edge cases
	 */
	@Test
	public void testToGraph( ) {

		MyMaze testMaze = new MyMaze();

		//TestOne
		//EdgeCase where there should be no vertices
		testMaze.generateMaze( 0, 0 );
		Graph testGraph = testMaze.toGraph();

		if ( testGraph.vertices().size() > 0 ) {
			fail( "Should have no vetices!" );
		}
		if ( testGraph.edges().size() > 0 ) {
			fail( "Should be no edges!" );
		}

		//TestTwo
		//EdgeCase where there are a normal amount of rows and columns
		testMaze.generateMaze( 50, 50 );

		if ( testMaze.toGraph().vertices().size() != 50 * 50 ) {
			fail( "Not the correct amount of vertices!" );
		}
		if ( testMaze.toGraph().edges().size() <= 1  ) {
			fail( "Incorrect amount of edges!" );
		}

	}

	/**
	 * Tests the toArray( ) method for edge cases
	 */
	@Test
	public void testToArray( ) {

		MyMaze testMaze = new MyMaze();

		//TestOne
		//EdgeCase with rows columns set to 0 
		testMaze.generateMaze( 0, 0 );
		if ( testMaze.toArray().length > 0 ) {
			fail( "Array not correct size!" );
		}

		//TestTwo
		//EdgeCase with normal rows and columns
		testMaze.generateMaze( 50, 50 );
		if ( testMaze.toArray().length != 50 ) {
			fail( "Array not right size." );
		}

		//TestThree
		//EdgeCase with negative rows and columns
		testMaze.generateMaze( -50, -50 );
		if ( testMaze.toArray().length > 0 ) {
			fail( "Array size greater than 0 with negative rows and columns!" );
		}


	}

	/**
	 * Tests the startVertex( ) method for edge cases.
	 */
	@Test
	public void testStartVertex( ) {

		MyMaze testMaze = new MyMaze();

		//TestOne
		//EdgeCase where the start vertex should be null, rows = 0 columns = 0
		testMaze.generateMaze( 0, 0 );

		if ( testMaze.startVertex() != null ) {
			fail( "Start not null!" );
		}

		//TestTwo
		//EdgeCase where row = 1 columns = 1
		testMaze.generateMaze( 1, 1 );
		if ( testMaze.toGraph().vertices().contains( testMaze.startVertex() )  ) {
			fail( "Start vertex should not be in the the vertices." );
		}

		//TestThree
		//EdgeCase where rows are negative and columns are negative
		testMaze.generateMaze( -50, -50 );
		if ( testMaze.toGraph().vertices().contains( testMaze.startVertex() )  ) {
			fail( "Start vertex should not be in the the vertices." );
		}

		//TestFour
		//EdgeCase where start vertex should exist
		testMaze.generateMaze( 50, 50 );
		if ( !testMaze.toGraph().vertices().contains( testMaze.startVertex() ) ) {
			fail( "Start vertex should be in the list of vertices." );
		}

	}

	/**
	 * Tests the finishVertex( ) method for edge cases.
	 */
	@Test
	public void testFinishVertex( ) {
		MyMaze testMaze = new MyMaze();

		//TestOne
		//EdgeCase where the finish vertex should be null, rows = 0 cols = 0
		testMaze.generateMaze( 0, 0 );

		if ( testMaze.finishVertex() != null ) {
			fail( "finish not null!" );
		}

		//TestTwo
		//EdgeCase where row = 1 columns = 1
		testMaze.generateMaze( 1, 1 );
		if ( testMaze.toGraph().vertices().contains( testMaze.finishVertex() )  ) {
			fail( "finish vertex should not be in the the vertices." );
		}

		//TestThree
		//EdgeCase where rows are negative and columns are negative
		testMaze.generateMaze( -50, -50 );
		if ( testMaze.toGraph().vertices().contains( testMaze.finishVertex() )  ) {
			fail( "finish vertex should not be in the the vertices." );
		}

		//TestFour
		//EdgeCase where finish vertex should exist
		testMaze.generateMaze( 50, 50 );
		if ( !testMaze.toGraph().vertices().contains( testMaze.finishVertex() ) ) {
			fail( "finish vertex not in the list of vertices." );
		}

	}

	/**
	 * Tests t0 ensure the correct amount of edges
	 */
	@Test
	public void testEdgeNumber(  ) {

		MyMaze testMaze = new MyMaze( );

		//TestOne
		//EdgeCase where there should be no edges
		testMaze.generateMaze( 0, 0 );
		if ( testMaze.toGraph().edges().size() > 0 ) {
			fail( "Edges should not exist!" );
		}

		//TestTwo
		//EdgeCase where there should be numbers of vertices - 1 edges
		testMaze.generateMaze( 50, 50 );
		if ( testMaze.toGraph().edges().size() != testMaze.toGraph().vertices().size() - 1 ) {
			fail ( "There should ber number of vertices - 1 edges!" );
		}

	}

	/**
	 * Test for toString EdgeCases
	 */
	@Test
	public void testToString( ) {
		MyMaze testMaze = new MyMaze( );

		//TestOne
		//EdgeCase where toString should be ""
		testMaze.generateMaze(0, 0);
		if ( !testMaze.toString().equals("") ) {
			fail( "Incorrect ToString" );
		}
		
		//TestTwo
		//These must be visibly checked as they are random every time
		testMaze.generateMaze(30, 20);
		System.out.println(testMaze.toString());
	}
	
	/**
	 * Test Case borrowed from Leo Ureel II
	 */
	@Test
	public void testPathBetweenAllVertices( ) {
		MyMaze maze = new MyMaze( );
		maze.generateMaze( 4, 4 );
		Graph graph = maze.toGraph( );
		ArrayList< Vertex > vertices = graph.vertices( );
		for ( int i = 0; i < vertices.size( ); i++ ) {
			for ( int j = 0; j < vertices.size( ); j++ ) {
				Vertex v1 = vertices.get( i );
				Vertex v2 = vertices.get( j );
				if ( v1 != v2 ) {
					if ( graph.shortestPath( (MyVertex) v1, (MyVertex) v2 ).isEmpty( ) ) {
						fail( "Vertex " + v1 + " is not connected to vertex " + v2 + " in graph:" + graph );
					}
				}
			}
		}
	}
	
	/**
	 * Tests for the other interface Methods
	 */
	@Test
	public void testSpanningTree( ) {
		
		MyMaze testMaze = new MyMaze( );
		
		//TestOne
		testMaze.generateMaze( 50, 50 );
		MyGraph spanTree = (MyGraph) testMaze.toGraph().minimumSpanningTree();
		if ( spanTree.vertices() != testMaze.toGraph().vertices() ) {
			fail( "Spanning Tree not generated" );
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
