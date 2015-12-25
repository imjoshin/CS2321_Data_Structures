import java.util.ArrayList;

/**
 * @author Josh Johnson
 */

public class MyVertex implements Vertex {

	private int id = 0;
	private GraphColor color;
	private ArrayList<Edge> edges;
	private ArrayList<Vertex> vertices;
	
	public MyVertex() {
		edges = new ArrayList<Edge>();
		vertices = new ArrayList<Vertex>();
	}
	
	//get vertex id
	public int getId() {
		return id;
	}

	//set vertex id
	public void setId(int newID) {
		id = newID;
	}
	
	//get vertex color
	public GraphColor getColor() {
		return color;
	}

	//set vertex color
	public void setColor(GraphColor newVal) {
		color = newVal;
	}

	//add an incedent edge
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	//remove an incedent edge
	public void removeEdge(Vertex v1, Vertex v2) {
		for (int i = 0; i < edges.size(); i++) {
			//if this edge connects v1 and v2
			if ((edges.get(i).vertices().get(0).getId() == v1.getId() && edges.get(i).vertices().get(1).getId() == v2.getId()) || (edges.get(i).vertices().get(0).getId() == v2.getId() && edges.get(i).vertices().get(1).getId() == v1.getId())) {
				//find vertices of this edge and remove them
				for (int j = 0; j < vertices.size(); j++ ) {
					if (vertices.get(j).getId() == v1.getId() || vertices.get(j).getId() == v2.getId())
						vertices.remove(j);
				}
				edges.remove(i);
			}
		}
	}
	
	//add an adjacent vertex
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	//remove an adjacent vertex
	public void removeVertex(Vertex v) {
		//find and remove vertex
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getId() == v.getId())
				vertices.remove(i);
		}
		
		//remove all edges containing vertex
		for (int i = 0; i < edges.size(); i++) {
			if (((MyEdge) edges.get(i)).contains(v))
				edges.remove(i);
		}
	}
	
	//get incedent edges
	public ArrayList<Edge> incidentEdges() {
		return edges;
	}

	//get adjacent vertices
	public ArrayList<Vertex> adjacentVertices() {
		return vertices;
	}
	
	public String toString() {
		return "<v" + id + ":" + color + ">";
	}

	public String debug() {
		return "Vertex ID: " + id + "\n\tAdjacent: " + vertices + "\n\tEdges: " + edges;
	}

}
