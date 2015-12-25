import java.util.ArrayList;


public interface Vertex {
   // Unique identifier for this vertext.
   // First vertex created is 0, second is 1, etc.
   public int getId( );
   
   // Getters and Setters for Graph Color
   public GraphColor getColor( );
   public void setColor( GraphColor newVal );
   
   // List edges connected to the vertex
   public ArrayList< Edge > incidentEdges( );
   // List vertices connected to this vertex by a single edge
   public ArrayList< Vertex > adjacentVertices( );
}