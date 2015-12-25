import java.util.ArrayList;


public class MyVertex implements Vertex {

	private int id = 0;
	private ArrayList<Edge> edges;
	private ArrayList<Vertex> vertices;
	private MyPair element = new MyPair();
	
	
	public MyVertex() {
		edges = new ArrayList<Edge>();
		vertices = new ArrayList<Vertex>();
	}
	
	public int getX() {
		return element.getX();
	}
	
	public void setX( int x ) {
		element.setX(x);;
	}
	
	public int getY() {
		return element.getY();
	}
	
	public void setY( int y ) {
		element.setY(y);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int newID) {
		id = newID;
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	
	public void removeEdge(Vertex v1, Vertex v2) {
		for (int i = 0; i < edges.size(); i++) {
			//if this edge connects v1 and v2
			if (((MyEdge) edges.get(i)).contains(v1, v2)) {
				//find vertices of this edge and remove them
				for (int j = 0; j < vertices.size(); j++ ) {
					if (v1.getElement().getX() == vertices.get(j).getElement().getX() && v1.getElement().getY() == vertices.get(j).getElement().getY() ||
							v2.getElement().getX() == vertices.get(j).getElement().getX() && v2.getElement().getY() == vertices.get(j).getElement().getY())
						vertices.remove(j);
				}
				edges.remove(i);
			}
		}
	}

	
	public void removeVertex(Vertex v) {
		//find and remove vertex
		for (int i = 0; i < vertices.size(); i++) {
			if (v.getElement().getX() == vertices.get(i).getElement().getX() && v.getElement().getY() == vertices.get(i).getElement().getY())
				vertices.remove(i);
		}
		
		//remove all edges containing vertex
		for (int i = 0; i < edges.size(); i++) {
			if (((MyEdge) edges.get(i)).contains(v))
				edges.remove(i);
		}
	}
	
	
	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	@Override
	public ArrayList<Edge> incidentEdges() {
		return edges;
	}

	@Override
	public ArrayList<Vertex> adjacentVertices() {
		return vertices;
	}
	
	public String toString() {
		return "<v" + id + ":" + element.getX() + " , " + element.getY() + ">";
	}

	@Override
	public Pair getElement() {
		return element;
	}

	@Override
	public void setElement(Pair e) {
		element = (MyPair) e;
	}

}
