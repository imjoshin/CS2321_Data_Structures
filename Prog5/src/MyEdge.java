import java.util.ArrayList;


public class MyEdge implements Edge {

	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private int element;
	
	
	public MyEdge(Vertex v1, Vertex v2) {
		vertices.add(v1);
		vertices.add(v2);
	}
	
	@Override
	public ArrayList<Vertex> vertices() {
		return vertices;
	}
	
	public Vertex getAdjacent(Vertex v) {
		if (v.getElement().getX() == vertices.get(0).getElement().getX() && v.getElement().getY() == vertices.get(0).getElement().getY())
			return vertices.get(1);
		else if (v.getElement().getX() == vertices.get(1).getElement().getX() && v.getElement().getY() == vertices.get(1).getElement().getY())
			return vertices.get(0);
		else
			return null;
	}
	
	public boolean contains(Vertex v) {
		return (v.getElement().getX() == vertices.get(0).getElement().getX() && v.getElement().getY() == vertices.get(0).getElement().getY() || v.getElement().getX() == vertices.get(1).getElement().getX() && v.getElement().getY() == vertices.get(1).getElement().getY());
	}
	
	//returns true if this edge contains v1 and v2
	public boolean contains(Vertex v1, Vertex v2) {
		return contains(v1) && contains(v2);
	}

	public String toString() {
		return "<v" + ((MyVertex) vertices.get(0)).getId() + "-v" + ((MyVertex) vertices.get(1)).getId() + ">";
	}

	@Override
	public int getElement() {
		return element;
	}

	@Override
	public void setElement(int e) {
		element = e;
	}
}
