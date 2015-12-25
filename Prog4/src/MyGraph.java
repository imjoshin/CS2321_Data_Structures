import java.util.ArrayList;

/**
 * @author Josh Johnson
 */

public class MyGraph implements Graph {

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
		//remove all edges containing the vertex
		for (int i = 0; i < edges.size(); i++) {
			//check edge for vertex, remove if there
			if (((MyEdge) edges.get(i)).contains(v)) {
				edges.remove(i);
				//decrement i due to reduced size
				i--;
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
		if (v1.getId() == v2.getId()) return null;
		//if v1 or v2 is null, return null
		if (v1 == null || v2 == null) return null;
		
		//create new MyEdge
		MyEdge newEdge = new MyEdge(v1, v2);
		edges.add(newEdge);

		for (int i = 0; i < vertices.size(); i++) {
			//add adjacent vertex and incedent edge to each vertex
			if (vertices.get(i).getId() == v1.getId()) {
				((MyVertex) vertices.get(i)).addVertex(v2);
				((MyVertex) vertices.get(i)).addEdge(newEdge);
			} else if (vertices.get(i).getId() == v2.getId()) {
				((MyVertex) vertices.get(i)).addVertex(v1);
				((MyVertex) vertices.get(i)).addEdge(newEdge);
			}
		}
		
		return newEdge;
	}

	@Override
	public Edge addEdge(Edge e) {
		return addEdge(e.vertices().get(0), e.vertices().get(1));
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
		return findEdge(v1, v2) != null;
	}

	@Override
	public ArrayList<Vertex> adjacentVertices(Vertex v1) {
		return ((MyVertex) v1).adjacentVertices();
	}

	@Override
	public ArrayList<Edge> incidentEdges(Vertex v1) {
		return ((MyVertex) v1).incidentEdges();
	}

	@Override
	public int colorGraph(ArrayList<GraphColor> colors) {
		//reset colors
		for (int i = 0; i < vertices.size(); i++)
			vertices.get(i).setColor(null);
		
		ArrayList<GraphColor> available = colors;
		ArrayList<GraphColor> usedColors = new ArrayList<GraphColor>();
		for (int i = 0; i < vertices.size(); i++) {
			//reset
			available = new ArrayList<GraphColor>();
			for (int j = 0; j < colors.size(); j++) available.add(colors.get(j));
			
			MyVertex v = (MyVertex) vertices.get(i);
			for (int j = 0; j < v.incidentEdges().size(); j++) {
				available.remove(((MyVertex) ((MyEdge) v.incidentEdges().get(j)).getAdjacent(vertices.get(i))).getColor());
			}
			
			//cannot be colored
			if (available.size() == 0) {
				//reset previously colored vertices to the null color
				for (int j = 0; j < i; j++ ) {
					vertices.get(j).setColor(null);
				}
				return -1;
			} else {
				//set vertex color
				v.setColor(available.get(0));
				//add the color to used colors if not already there
				if (!usedColors.contains(available.get(0)))
					usedColors.add(available.get(0));
			}
			
		}
		
		return usedColors.size();
	}
	
	public String toString() {
		return "<Graph:" + vertices + ", " + edges + ">";
	}

}
