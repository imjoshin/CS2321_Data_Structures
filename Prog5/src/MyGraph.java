import java.util.ArrayList;


public class MyGraph implements Graph{

	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private int idNum = 0;
	
	@Override
	public ArrayList<Vertex> vertices() {
		return vertices;
	}

	@Override
	public Vertex addVertex(Vertex v) {
		MyVertex newVertex = (MyVertex) v;
		//set id
		newVertex.setId(idNum);
		//increment for next addition
		idNum++;
		vertices.add(newVertex);
		return newVertex;
	}
	
	@Override
	public boolean removeVertex(Vertex v) {
		if (v == null) return false;
		
		//remove all edges containing the vertex
		for (int i = 0; i < edges.size(); i++) {
			//check edge for vertex, remove if there
			if (((MyEdge) edges.get(i)).contains(v)) {
				edges.remove(i);
			}
		}
		
		//remove v from all other vertecies
		for (int i = 0; i < vertices.size(); i++) {
			((MyVertex) vertices.get(i)).removeVertex(v);
		}
		//remove vertex
		return vertices.remove(v);
	}
	
	@Override
	public ArrayList<Edge> edges() {
		return edges;
	}

	@Override
	public Edge addEdge(Vertex v1, Vertex v2) {
		//if edge exists, return null
		if (findEdge(v1, v2) != null) return null;
		//if v1 = v2, return null
		if (v1.getElement().getX() == v2.getElement().getX() && v1.getElement().getY() == v2.getElement().getY()) return null;
		
		//create new MyEdge
		MyEdge newEdge = new MyEdge(v1, v2);
		edges.add(newEdge);

		for (int i = 0; i < vertices.size(); i++) {
			if (v1.getElement().getX() == vertices.get(i).getElement().getX() && v1.getElement().getY() == vertices.get(i).getElement().getY()) {
				((MyVertex) vertices.get(i)).addVertex(v2);
				((MyVertex) vertices.get(i)).addEdge(newEdge);
			} else if (v2.getElement().getX() == vertices.get(i).getElement().getX() && v2.getElement().getY() == vertices.get(i).getElement().getY()) {
				((MyVertex) vertices.get(i)).addVertex(v1);
				((MyVertex) vertices.get(i)).addEdge(newEdge);
			}
		}
		
		return newEdge;
	}

	@Override
	public Edge addEdge(Edge e) {
		//create new MyEdge
		MyEdge newEdge = (MyEdge) e;
		edges.add(newEdge);		
		return newEdge;
	}
	
	@Override
	public boolean removeEdge(Vertex v1, Vertex v2) {
		//find edge and remove
		boolean found = false;
		for (int i = 0; i < edges.size(); i++) {
			//if edge contains both points, remove it
			if (((MyEdge) edges.get(i)).contains(v1, v2)) {
				edges.remove(i);
				found = true;
			}
		}
		
		for (int i = 0; i < vertices.size(); i++) {
			((MyVertex) vertices.get(i)).removeEdge(v1,  v2);
		}
		
		return found;
	}

	@Override
	public boolean removeEdge(Edge e) {
		//use other removeEdge method. Send edge vertices as parameters.
		return removeEdge(e.vertices().get(0), e.vertices().get(1));
	}
	
	@Override
	public Edge findEdge(Vertex v1, Vertex v2) {
		//find edge
		for (int i = 0; i < edges.size(); i++) {
			//if edge contains both vertices, return it
			if (((MyEdge) edges.get(i)).contains(v1, v2)) {
				return edges.get(i);
			}
		}
		//edge not found
		return null;
	}

	@Override
	public boolean areConnected(Vertex v1, Vertex v2) {
		//use findEdge to see if two points are connected
		return (findEdge(v1, v2) != null);
	}

	@Override
	public ArrayList<Vertex> adjacentVertices(Vertex v1) {
		return ((MyVertex) v1).adjacentVertices();
	}

	@Override
	public ArrayList<Edge> incidentEdges(Vertex v1) {
		return ((MyVertex) v1).incidentEdges();
	}


	
	public String toString() {
		return "<Graph:" + vertices + ", " + edges + ">";
	}

	
	
	
	//NEW STUFF FOR MAZE
	
	@Override
	public Vertex addVertex(Pair p) {
		//create new vertex with pair p
		MyVertex v = new MyVertex();
		v.setElement(p);
		//add vertex
		return addVertex(v);
	}

	@Override
	public boolean removeVertex(Pair p) {
		//find vertex with pair p and remove it
		return removeVertex(findVertex(p));
	}

	@Override
	public Vertex findVertex(Pair p) {
		//find vertex with same x and y as p
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getElement().getX() == p.getX() && vertices.get(i).getElement().getY() == p.getY())
				return vertices.get(i);
		}
		return null;
	}

	
	private ArrayList< Vertex > path;
	private ArrayList< Vertex > visited;
	/**
	 * Returns a list of vertices that is the shortest path between the two passed in.
	 */
	@Override
	public ArrayList<Vertex> shortestPath(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		path = new ArrayList< Vertex >( );
		visited = new ArrayList< Vertex >( ); 
		
		if ( vertices.size() <= 0 ) {
			return path;
		}
		
		if ( pathHelper( v2, v1 ) ) {
			return path;
		} 
		else {
			return new ArrayList< Vertex >();
		}
		
	}

	public boolean pathHelper( Vertex ver1, Vertex ver2 ){
	
		MyVertex v1 = ( MyVertex ) ver1;
		MyVertex v2 = ( MyVertex ) ver2;
		//this vertex is now visited
		visited.add( ver1 );
		//System.out.println("looking for the finish");
		//start found
		if ( v1.getX() == v2.getX() && v1.getY() == v2.getY() ) { path.add( v2 ); return true; }

		for ( int i = 0; i < v1.adjacentVertices().size(); i++ ) {
			MyVertex next = ( MyVertex ) v1.adjacentVertices().get(i);
			//if the next vertex hasn't been visited
			if ( !visited.contains( next ) ) {
				if ( pathHelper( next, v2 ) ) {
					//add if finish found ahead
					path.add( v1 );
					return true;
				}
			}
		}

		return false;
	}
	
	
	@Override
	public Graph minimumSpanningTree() {
		// TODO Auto-generated method stub
		System.out.println("Making min spanning stree");
		ArrayList< MyVertex > locations = new ArrayList< MyVertex >();
		int numOfVertices = vertices().size();
		MyGraph spanningTree = new MyGraph();
		
		if ( edges.size() > 0 ) {
			return this;
		}
		
		int min = 0;
		int max = vertices().size();
		//Randomly pick a starting vertex to generate from
		MyVertex currentLocation = ( MyVertex ) ( vertices().get( min + ( int )( Math.random() * ( ( max - min ) ) ) ) );
		int visitedLocations = 1;
		//Add edges until every vertex has one edge
		while ( visitedLocations < numOfVertices ) {
			//find all vertices next to currentCell with no adjacent vertices
			ArrayList< MyVertex > unTouchedLocations = new ArrayList< MyVertex >();
			int curX = currentLocation.getX();
			int curY = currentLocation.getY();

			//This finds all neighbors to the current position that have not been visited and adds it to the List
			for ( int tempV = 0; tempV < vertices().size(); tempV++ ) {
				//so we can access the x and y
				MyVertex temp = (MyVertex) vertices().get( tempV );
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
				spanningTree.addEdge( currentLocation, nextVer );
				locations.add( currentLocation );

				currentLocation = nextVer;
				System.out.println(visitedLocations);
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
		
		return spanningTree;
	}

}
