import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Josh Johnson
 */

public class MyGraphTest {

	//@Test
	public void givenTest() {
		ArrayList< GraphColor > colors = new ArrayList< GraphColor >( );
		colors.add( GraphColor.RED );
		colors.add( GraphColor.GREEN );
		colors.add( GraphColor.BLUE );
		MyGraph mygraph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		mygraph.addVertex( v0 );
		mygraph.addVertex( v1 );
		mygraph.addVertex( v2 );
		mygraph.addEdge( v0, v1 );
		mygraph.addEdge( v1, v2 );
		mygraph.addEdge( v2, v0 );

		int numColors = mygraph.colorGraph( colors );
		if (numColors != 3)
			fail("Given test failed.");
		if (mygraph.toString().compareTo("<Graph:[<v0:RED>, <v1:GREEN>, <v2:BLUE>], [<v0-v1>, <v1-v2>, <v2-v0>]>") != 0)
			fail("Given test failed.");
	}


	@Test
	public void verticesTest() {	

		MyGraph graph = new MyGraph();
		if (graph.vertices().size() != 0)
			fail("Vertices failed.");

		MyVertex v = new MyVertex();

		graph.addVertex(v);
		if (graph.vertices().size() != 1)
			fail("Vertices failed.");

		for (int i = 0; i < 20; i++) {
			graph.addVertex(new MyVertex());
			if (graph.vertices().size() != i + 2)
				fail("Vertices failed.");
		}

		if (!graph.vertices().contains(v))
			fail("Vertices failed.");

		for (int i = 20; i > 0; i--) {
			graph.removeVertex(graph.vertices().get(i));
			if (graph.vertices().size() != i)
				fail("Vertices failed.");
		}

		if (!graph.vertices().contains(v))
			fail("Vertices failed.");

		graph.removeVertex(graph.vertices().get(0));

		if (graph.vertices().size() != 0)
			fail("Vertices failed.");

		if (graph.vertices().contains(v))
			fail("Vertices failed.");
	}

	@Test
	public void addVertexTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		graph.addVertex( v0 );
		graph.addVertex( v1 );
		graph.addVertex( v2 );

		if (v0.getId() != 0)
			fail("addVertex failed.");
		if (v1.getId() != 1)
			fail("addVertex failed.");
		if (v2.getId() != 2)
			fail("addVertex failed.");

		if (!graph.vertices().contains(v0))
			fail("addVertex failed.");

		if (graph.vertices().size() != 3)
			fail("addVertex failed.");
	}

	@Test
	public void removeVertexTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  

		if (graph.removeVertex(v0))
			fail("removeVertex Failed.");
		graph.addVertex( v0 );
		if (!graph.removeVertex(v0) || graph.vertices().size() != 0)
			fail("removeVertex Failed.");

		graph.addVertex( v0 );
		graph.addVertex( v1 );
		graph.addVertex( v2 );
		graph.addEdge( v0, v1 );
		graph.addEdge( v1, v2 );
		graph.addEdge( v2, v0 );

		graph.removeVertex(v1);
		if (graph.vertices().size() != 2 || graph.edges().size() != 1 || graph.areConnected(v1, v2))
			fail("removeVertex Failed.");

		graph.removeVertex(v2);
		graph.removeVertex(v0);

		if (graph.vertices().size() != 0 || graph.edges().size() != 0)
			fail("removeVertex Failed.");

	}


	@Test
	public void edgesTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		graph.addVertex( v0 );
		graph.addVertex( v1 );
		graph.addVertex( v2 );
		MyEdge e0 = new MyEdge(v0, v1);
		MyEdge e1 = new MyEdge(v1, v2);
		e0 = (MyEdge) graph.addEdge(e0);
		e1 = (MyEdge) graph.addEdge(e1);

		if (!graph.edges().contains(e0))
			fail("edges Failed.");
		//check for adjacent vertices on v0, should be only one value which is 1
		if (graph.vertices().get(0).adjacentVertices().size() != 1 || graph.vertices().get(0).adjacentVertices().get(0).getId() != 1)
			fail("edges Failed.");

	}

	@Test
	public void addEdgeTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		MyVertex v3 = new MyVertex( );  
		MyVertex v4 = new MyVertex( );  
		graph.addVertex( v0 );
		graph.addVertex( v1 );
		graph.addVertex( v2 );
		graph.addVertex( v3 );
		graph.addVertex( v4 );
		MyEdge e0 = new MyEdge(v0, v1);
		MyEdge e1 = new MyEdge(v1, v2);
		e0 = (MyEdge) graph.addEdge(e0);
		if (e0.vertices().size() != 2 || !e0.vertices().contains(v0) || !e0.vertices().contains(v1))
			fail("addEdge Failed.");

		e1 = (MyEdge) graph.addEdge(e1);
		MyEdge e2 = (MyEdge) graph.addEdge(v2, v3);
		if (e2.vertices().size() != 2 || !e2.vertices().contains(v2) || !e2.vertices().contains(v3))
			fail("addEdge Failed.");

		MyEdge e3 = (MyEdge) graph.addEdge(v3, v4);
		MyEdge e4 = (MyEdge) graph.addEdge(v0, v4);
		MyEdge e5 = (MyEdge) graph.addEdge(v1, v3);

		if (graph.edges().size() != 6 || !graph.edges().contains(e0) || !graph.edges().contains(e1) || !graph.edges().contains(e2) || !graph.edges().contains(e3) || !graph.edges().contains(e4) || !graph.edges().contains(e5))
			fail("addEdge Failed.");
		if (v2.adjacentVertices().size() != 2 || !v2.adjacentVertices().contains(v1) || !v2.adjacentVertices().contains(v3))
			fail("addEdge Failed.");
		if (v2.incidentEdges().size() != 2)
			fail("addEdge Failed.");

		if (graph.addEdge(v0, v0) != null)
			fail("addEdge Failed.");
		if (graph.addEdge(v0, v1) != null)
			fail("addEdge Failed.");
		if (graph.addEdge(v1, v0) != null)
			fail("addEdge Failed.");
	}

	@Test
	public void removeEdgeTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		if (graph.removeEdge(v0, v1))
			fail("removeEdge Failed.");
		MyVertex v2 = new MyVertex( );  
		MyVertex v3 = new MyVertex( );  
		MyVertex v4 = new MyVertex( );  
		graph.addVertex( v0 );
		graph.addVertex( v1 );
		graph.addVertex( v2 );
		graph.addVertex( v3 );
		graph.addVertex( v4 );
		MyEdge e0 = new MyEdge(v0, v1);
		MyEdge e1 = new MyEdge(v1, v2);
		if (graph.removeEdge(e1))
			fail("removeEdge Failed.");

		e0 = (MyEdge) graph.addEdge(e0);		
		e1 = (MyEdge) graph.addEdge(e1);
		MyEdge e2 = (MyEdge) graph.addEdge(v2, v3);
		MyEdge e3 = (MyEdge) graph.addEdge(v3, v4);
		MyEdge e4 = (MyEdge) graph.addEdge(v0, v4);
		MyEdge e5 = (MyEdge) graph.addEdge(v1, v3);

		if (graph.removeEdge(v0, v2))
			fail("removeEdge Failed.");

		if (!graph.removeEdge(e1))
			fail("removeEdge Failed.");
		if (v1.adjacentVertices().contains(v2) || v2.adjacentVertices().contains(v1))
			fail("removeEdge Failed.");


		if (!graph.removeEdge(v0, v1))
			fail("removeEdge Failed.");
		if (!graph.removeEdge(e2))
			fail("removeEdge Failed.");
		if (!graph.removeEdge(v3, v4))
			fail("removeEdge Failed.");
		if (!graph.removeEdge(e4))
			fail("removeEdge Failed.");
		if (!graph.removeEdge(e5))
			fail("removeEdge Failed.");


		if (graph.removeEdge(e3))
			fail("removeEdge Failed.");
	}

	@Test
	public void findEdgeTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		MyVertex v3 = new MyVertex( );  
		MyVertex v4 = new MyVertex( );  
		graph.addVertex( v0 );
		graph.addVertex( v1 );
		if (graph.findEdge(v0, v1) != null)
			fail("findEdge Failed.");

		graph.addVertex( v2 );
		graph.addVertex( v3 );
		graph.addVertex( v4 );
		MyEdge e0 = new MyEdge(v0, v1);
		MyEdge e1 = new MyEdge(v1, v2);
		e0 = (MyEdge) graph.addEdge(e0);		
		e1 = (MyEdge) graph.addEdge(e1);
		graph.addEdge(v2, v3);
		MyEdge e3 = (MyEdge) graph.addEdge(v3, v4);
		graph.addEdge(v0, v4);
		graph.addEdge(v1, v3);

		if (graph.findEdge(v2, v3).vertices().size() != 2 || !graph.findEdge(v2, v3).vertices().contains(v2) || !graph.findEdge(v2, v3).vertices().contains(v3))
			fail("findEdge Failed.");
		if (graph.findEdge(v0,  v2) != null)
			fail("findEdge Failed.");

		if (graph.findEdge(v3, v4) == null)
			fail("findEdge Failed.");
		graph.removeEdge(e3);
		if (graph.findEdge(v3, v4) != null)
			fail("findEdge Failed.");
	}

	@Test
	public void areConnectedTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		MyVertex v3 = new MyVertex( );  
		MyVertex v4 = new MyVertex( );  
		graph.addVertex( v0 );
		graph.addVertex( v1 );
		graph.addVertex( v2 );
		graph.addVertex( v3 );
		graph.addVertex( v4 );
		MyEdge e0 = new MyEdge(v0, v1);
		MyEdge e1 = new MyEdge(v1, v2);

		if (graph.areConnected(v0, v1))
			fail("areConnected Failed.");
		e0 = (MyEdge) graph.addEdge(e0);
		if (!graph.areConnected(v0, v1))
			fail("areConnected Failed.");

		e1 = (MyEdge) graph.addEdge(e1);
		graph.addEdge(v2, v3);
		graph.addEdge(v3, v4);
		graph.addEdge(v0, v4);
		graph.addEdge(v1, v3);

		if (!graph.areConnected(v0, v1))
			fail("areConnected Failed.");
		if (!graph.areConnected(v1, v2))
			fail("areConnected Failed.");
		if (!graph.areConnected(v2, v3))
			fail("areConnected Failed.");
		if (!graph.areConnected(v3, v4))
			fail("areConnected Failed.");
		if (!graph.areConnected(v4, v3))
			fail("areConnected Failed.");


		if (graph.areConnected(v2, v4))
			fail("areConnected Failed.");
		if (graph.areConnected(v0, v3))
			fail("areConnected Failed.");

		if (!graph.areConnected(v1, v3))
			fail("areConnected Failed.");
		graph.removeEdge(v1, v3);
		if (graph.areConnected(v1, v3))
			fail("areConnected Failed.");
	}

	@Test
	public void adjacentVerticesTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		if (graph.adjacentVertices(v0).size() != 0)
			fail("adjacentVertices Failed.");

		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		MyVertex v3 = new MyVertex( );  
		MyVertex v4 = new MyVertex( );  
		graph.addVertex( v0 );
		graph.addVertex( v1 );
		graph.addVertex( v2 );
		graph.addVertex( v3 );
		graph.addVertex( v4 );
		MyEdge e0 = new MyEdge(v0, v1);
		MyEdge e1 = new MyEdge(v1, v2);
		e0 = (MyEdge) graph.addEdge(e0);
		e1 = (MyEdge) graph.addEdge(e1);
		graph.addEdge(v2, v3);
		graph.addEdge(v3, v4);
		graph.addEdge(v0, v4);
		graph.addEdge(v1, v3);

		if (graph.adjacentVertices(v2).size() != 2 || !graph.adjacentVertices(v2).contains(v1) || !graph.adjacentVertices(v2).contains(v3))
			fail("adjacentVertices Failed.");

		graph.removeEdge(v2, v1);
		if (graph.adjacentVertices(v2).size() != 1 || graph.adjacentVertices(v2).contains(v1) || !graph.adjacentVertices(v2).contains(v3))
			fail("adjacentVertices Failed.");

		graph.removeEdge(v2, v3);
		if (graph.adjacentVertices(v2).size() != 0 || graph.adjacentVertices(v2).contains(v1) || graph.adjacentVertices(v2).contains(v3))
			fail("adjacentVertices Failed.");

		graph.addEdge(v2, v3);
		if (graph.adjacentVertices(v2).size() != 1 || graph.adjacentVertices(v2).contains(v1) || !graph.adjacentVertices(v2).contains(v3))
			fail("adjacentVertices Failed.");



		if (graph.adjacentVertices(v4).size() != 2 || !graph.adjacentVertices(v4).contains(v0) || !graph.adjacentVertices(v4).contains(v3))
			fail("adjacentVertices Failed.");

		graph.removeEdge(v4, v0);
		if (graph.adjacentVertices(v4).size() != 1 || graph.adjacentVertices(v4).contains(v0) || !graph.adjacentVertices(v4).contains(v3))
			fail("adjacentVertices Failed.");

		graph.addEdge(v4, v0);
		if (graph.adjacentVertices(v4).size() != 2 || !graph.adjacentVertices(v4).contains(v0) || !graph.adjacentVertices(v4).contains(v3))
			fail("adjacentVertices Failed.");


	}

	@Test
	public void incidentEdgesTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		MyVertex v3 = new MyVertex( );  
		MyVertex v4 = new MyVertex( );  
		graph.addVertex( v0 );
		graph.addVertex( v1 );
		graph.addVertex( v2 );
		graph.addVertex( v3 );
		graph.addVertex( v4 );

		if (graph.incidentEdges(v0).size() != 0)
			fail("incidentEdges Failed.");

		MyEdge e0 = new MyEdge(v0, v1);
		MyEdge e1 = new MyEdge(v1, v2);
		e0 = (MyEdge) graph.addEdge(e0);

		if (graph.incidentEdges(v0).size() != 1)
			fail("incidentEdges Failed.");

		e1 = (MyEdge) graph.addEdge(e1);
		MyEdge e2 = (MyEdge) graph.addEdge(v2, v3);
		MyEdge e3 = (MyEdge) graph.addEdge(v3, v4);
		MyEdge e4 = (MyEdge) graph.addEdge(v0, v4);
		MyEdge e5 = (MyEdge) graph.addEdge(v1, v3);

		if (graph.incidentEdges(v3).size() != 3 || !graph.incidentEdges(v3).contains(e2) || !graph.incidentEdges(v3).contains(e3) || !graph.incidentEdges(v3).contains(e5))
			fail("incidentEdges Failed.");

		if (graph.incidentEdges(v0).size() != 2 || !graph.incidentEdges(v0).contains(e4))
			fail("incidentEdges Failed.");

		graph.removeVertex(v1);


		if (graph.incidentEdges(v3).size() != 2 || !graph.incidentEdges(v3).contains(e2) || !graph.incidentEdges(v3).contains(e3) || graph.incidentEdges(v3).contains(e5))
			fail("incidentEdges Failed.");

		if (graph.incidentEdges(v0).size() != 1 || !graph.incidentEdges(v0).contains(e4))
			fail("incidentEdges Failed.");
	}

	@Test
	public void colorGraphTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		MyVertex v3 = new MyVertex( );  
		MyVertex v4 = new MyVertex( );  
		graph.addVertex( v0 );

		ArrayList< GraphColor > colors = new ArrayList< GraphColor >( );
		graph.colorGraph(colors);
		if (graph.toString().compareTo("<Graph:[<v0:null>], []>") != 0)
			fail("colorGraph Failed.");

		colors.add( GraphColor.RED );
		graph.colorGraph(colors);
		if (graph.toString().compareTo("<Graph:[<v0:RED>], []>") != 0)
			fail("colorGraph Failed.");

		colors.add( GraphColor.GREEN );
		colors.add( GraphColor.BLUE );

		graph.addVertex( v1 );
		graph.addVertex( v2 );
		graph.addVertex( v3 );
		graph.addVertex( v4 );
		MyEdge e0 = new MyEdge(v0, v1);
		MyEdge e1 = new MyEdge(v1, v2);
		e0 = (MyEdge) graph.addEdge(e0);

		graph.colorGraph(colors);
		if (graph.toString().compareTo("<Graph:[<v0:RED>, <v1:GREEN>, <v2:RED>, <v3:RED>, <v4:RED>], [<v0-v1>]>") != 0)
			fail("colorGraph Failed.");

		e1 = (MyEdge) graph.addEdge(e1);
		graph.addEdge(v2, v3);
		graph.addEdge(v3, v4);
		graph.addEdge(v0, v4);
		graph.addEdge(v1, v3);

		graph.colorGraph(colors);
		if (graph.toString().compareTo("<Graph:[<v0:RED>, <v1:GREEN>, <v2:RED>, <v3:BLUE>, <v4:GREEN>], [<v0-v1>, <v1-v2>, <v2-v3>, <v3-v4>, <v0-v4>, <v1-v3>]>") != 0)
			fail("colorGraph Failed.");

		graph.removeEdge(v2, v3);
		graph.colorGraph(colors);
		if (graph.toString().compareTo("<Graph:[<v0:RED>, <v1:GREEN>, <v2:RED>, <v3:RED>, <v4:GREEN>], [<v0-v1>, <v1-v2>, <v3-v4>, <v0-v4>, <v1-v3>]>") != 0)
			fail("colorGraph Failed.");

		colors.remove(0);
		graph.colorGraph(colors);
		if (graph.toString().compareTo("<Graph:[<v0:GREEN>, <v1:BLUE>, <v2:GREEN>, <v3:GREEN>, <v4:BLUE>], [<v0-v1>, <v1-v2>, <v3-v4>, <v0-v4>, <v1-v3>]>") != 0)
			fail("colorGraph Failed.");

		colors.remove(0);
		graph.colorGraph(colors);
		if (graph.toString().compareTo("<Graph:[<v0:null>, <v1:null>, <v2:null>, <v3:null>, <v4:null>], [<v0-v1>, <v1-v2>, <v3-v4>, <v0-v4>, <v1-v3>]>") != 0)
			fail("colorGraph Failed.");


		colors = new ArrayList< GraphColor >( );
		colors.add( GraphColor.WHITE );
		colors.add( GraphColor.BLACK );
		colors.add( GraphColor.VIOLET );
		colors.add( GraphColor.INDIGO );
		colors.add( GraphColor.BLUE );
		colors.add( GraphColor.YELLOW );
		colors.add( GraphColor.GREEN );
		colors.add( GraphColor.RED );
		colors.add( GraphColor.ORANGE );
		
		graph = new MyGraph();
		MyVertex[] vertices = new MyVertex[100];
		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new MyVertex();
			graph.addVertex(vertices[i]);
		}
		
		for (int i = 0; i < vertices.length; i++) {
			graph.addEdge(vertices[i], vertices[i % 8]);
			graph.addEdge(vertices[i], vertices[Math.abs(i - 5)]);
			graph.addEdge(vertices[i], vertices[Math.abs(i - 3)]);
			graph.addEdge(vertices[i], vertices[Math.abs(i - 1)]);
			graph.addEdge(vertices[i], vertices[Math.abs(i - (i % 5))]);
		}
		
		graph.colorGraph(colors);
		if (graph.toString().compareTo("<Graph:[<v0:WHITE>, <v1:BLACK>, <v2:VIOLET>, <v3:BLACK>, <v4:VIOLET>, <v5:BLACK>, <v6:WHITE>, <v7:INDIGO>, <v8:VIOLET>, <v9:INDIGO>, <v10:WHITE>, <v11:INDIGO>, <v12:BLACK>, <v13:INDIGO>, <v14:BLACK>, <v15:VIOLET>, <v16:BLACK>, <v17:WHITE>, <v18:BLACK>, <v19:WHITE>, <v20:BLACK>, <v21:WHITE>, <v22:VIOLET>, <v23:WHITE>, <v24:VIOLET>, <v25:WHITE>, <v26:BLACK>, <v27:INDIGO>, <v28:BLACK>, <v29:INDIGO>, <v30:BLACK>, <v31:WHITE>, <v32:VIOLET>, <v33:WHITE>, <v34:BLUE>, <v35:WHITE>, <v36:BLACK>, <v37:INDIGO>, <v38:BLACK>, <v39:VIOLET>, <v40:BLACK>, <v41:WHITE>, <v42:BLUE>, <v43:WHITE>, <v44:INDIGO>, <v45:WHITE>, <v46:BLACK>, <v47:VIOLET>, <v48:BLACK>, <v49:VIOLET>, <v50:BLACK>, <v51:WHITE>, <v52:INDIGO>, <v53:WHITE>, <v54:INDIGO>, <v55:WHITE>, <v56:BLACK>, <v57:VIOLET>, <v58:BLACK>, <v59:VIOLET>, <v60:BLACK>, <v61:WHITE>, <v62:INDIGO>, <v63:WHITE>, <v64:INDIGO>, <v65:WHITE>, <v66:BLACK>, <v67:VIOLET>, <v68:BLACK>, <v69:VIOLET>, <v70:BLACK>, <v71:WHITE>, <v72:INDIGO>, <v73:WHITE>, <v74:INDIGO>, <v75:WHITE>, <v76:BLACK>, <v77:VIOLET>, <v78:BLACK>, <v79:VIOLET>, <v80:BLACK>, <v81:WHITE>, <v82:INDIGO>, <v83:WHITE>, <v84:INDIGO>, <v85:WHITE>, <v86:BLACK>, <v87:VIOLET>, <v88:BLACK>, <v89:VIOLET>, <v90:BLACK>, <v91:WHITE>, <v92:INDIGO>, <v93:WHITE>, <v94:INDIGO>, <v95:WHITE>, <v96:BLACK>, <v97:VIOLET>, <v98:BLACK>, <v99:VIOLET>], [<v0-v5>, <v0-v3>, <v0-v1>, <v1-v4>, <v1-v2>, <v2-v3>, <v2-v0>, <v4-v3>, <v4-v0>, <v5-v2>, <v5-v4>, <v6-v1>, <v6-v3>, <v6-v5>, <v7-v2>, <v7-v4>, <v7-v6>, <v7-v5>, <v8-v0>, <v8-v3>, <v8-v5>, <v8-v7>, <v9-v1>, <v9-v4>, <v9-v6>, <v9-v8>, <v9-v5>, <v10-v2>, <v10-v5>, <v10-v7>, <v10-v9>, <v11-v3>, <v11-v6>, <v11-v8>, <v11-v10>, <v12-v4>, <v12-v7>, <v12-v9>, <v12-v11>, <v12-v10>, <v13-v5>, <v13-v8>, <v13-v10>, <v13-v12>, <v14-v6>, <v14-v9>, <v14-v11>, <v14-v13>, <v14-v10>, <v15-v7>, <v15-v10>, <v15-v12>, <v15-v14>, <v16-v0>, <v16-v11>, <v16-v13>, <v16-v15>, <v17-v1>, <v17-v12>, <v17-v14>, <v17-v16>, <v17-v15>, <v18-v2>, <v18-v13>, <v18-v15>, <v18-v17>, <v19-v3>, <v19-v14>, <v19-v16>, <v19-v18>, <v19-v15>, <v20-v4>, <v20-v15>, <v20-v17>, <v20-v19>, <v21-v5>, <v21-v16>, <v21-v18>, <v21-v20>, <v22-v6>, <v22-v17>, <v22-v19>, <v22-v21>, <v22-v20>, <v23-v7>, <v23-v18>, <v23-v20>, <v23-v22>, <v24-v0>, <v24-v19>, <v24-v21>, <v24-v23>, <v24-v20>, <v25-v1>, <v25-v20>, <v25-v22>, <v25-v24>, <v26-v2>, <v26-v21>, <v26-v23>, <v26-v25>, <v27-v3>, <v27-v22>, <v27-v24>, <v27-v26>, <v27-v25>, <v28-v4>, <v28-v23>, <v28-v25>, <v28-v27>, <v29-v5>, <v29-v24>, <v29-v26>, <v29-v28>, <v29-v25>, <v30-v6>, <v30-v25>, <v30-v27>, <v30-v29>, <v31-v7>, <v31-v26>, <v31-v28>, <v31-v30>, <v32-v0>, <v32-v27>, <v32-v29>, <v32-v31>, <v32-v30>, <v33-v1>, <v33-v28>, <v33-v30>, <v33-v32>, <v34-v2>, <v34-v29>, <v34-v31>, <v34-v33>, <v34-v30>, <v35-v3>, <v35-v30>, <v35-v32>, <v35-v34>, <v36-v4>, <v36-v31>, <v36-v33>, <v36-v35>, <v37-v5>, <v37-v32>, <v37-v34>, <v37-v36>, <v37-v35>, <v38-v6>, <v38-v33>, <v38-v35>, <v38-v37>, <v39-v7>, <v39-v34>, <v39-v36>, <v39-v38>, <v39-v35>, <v40-v0>, <v40-v35>, <v40-v37>, <v40-v39>, <v41-v1>, <v41-v36>, <v41-v38>, <v41-v40>, <v42-v2>, <v42-v37>, <v42-v39>, <v42-v41>, <v42-v40>, <v43-v3>, <v43-v38>, <v43-v40>, <v43-v42>, <v44-v4>, <v44-v39>, <v44-v41>, <v44-v43>, <v44-v40>, <v45-v5>, <v45-v40>, <v45-v42>, <v45-v44>, <v46-v6>, <v46-v41>, <v46-v43>, <v46-v45>, <v47-v7>, <v47-v42>, <v47-v44>, <v47-v46>, <v47-v45>, <v48-v0>, <v48-v43>, <v48-v45>, <v48-v47>, <v49-v1>, <v49-v44>, <v49-v46>, <v49-v48>, <v49-v45>, <v50-v2>, <v50-v45>, <v50-v47>, <v50-v49>, <v51-v3>, <v51-v46>, <v51-v48>, <v51-v50>, <v52-v4>, <v52-v47>, <v52-v49>, <v52-v51>, <v52-v50>, <v53-v5>, <v53-v48>, <v53-v50>, <v53-v52>, <v54-v6>, <v54-v49>, <v54-v51>, <v54-v53>, <v54-v50>, <v55-v7>, <v55-v50>, <v55-v52>, <v55-v54>, <v56-v0>, <v56-v51>, <v56-v53>, <v56-v55>, <v57-v1>, <v57-v52>, <v57-v54>, <v57-v56>, <v57-v55>, <v58-v2>, <v58-v53>, <v58-v55>, <v58-v57>, <v59-v3>, <v59-v54>, <v59-v56>, <v59-v58>, <v59-v55>, <v60-v4>, <v60-v55>, <v60-v57>, <v60-v59>, <v61-v5>, <v61-v56>, <v61-v58>, <v61-v60>, <v62-v6>, <v62-v57>, <v62-v59>, <v62-v61>, <v62-v60>, <v63-v7>, <v63-v58>, <v63-v60>, <v63-v62>, <v64-v0>, <v64-v59>, <v64-v61>, <v64-v63>, <v64-v60>, <v65-v1>, <v65-v60>, <v65-v62>, <v65-v64>, <v66-v2>, <v66-v61>, <v66-v63>, <v66-v65>, <v67-v3>, <v67-v62>, <v67-v64>, <v67-v66>, <v67-v65>, <v68-v4>, <v68-v63>, <v68-v65>, <v68-v67>, <v69-v5>, <v69-v64>, <v69-v66>, <v69-v68>, <v69-v65>, <v70-v6>, <v70-v65>, <v70-v67>, <v70-v69>, <v71-v7>, <v71-v66>, <v71-v68>, <v71-v70>, <v72-v0>, <v72-v67>, <v72-v69>, <v72-v71>, <v72-v70>, <v73-v1>, <v73-v68>, <v73-v70>, <v73-v72>, <v74-v2>, <v74-v69>, <v74-v71>, <v74-v73>, <v74-v70>, <v75-v3>, <v75-v70>, <v75-v72>, <v75-v74>, <v76-v4>, <v76-v71>, <v76-v73>, <v76-v75>, <v77-v5>, <v77-v72>, <v77-v74>, <v77-v76>, <v77-v75>, <v78-v6>, <v78-v73>, <v78-v75>, <v78-v77>, <v79-v7>, <v79-v74>, <v79-v76>, <v79-v78>, <v79-v75>, <v80-v0>, <v80-v75>, <v80-v77>, <v80-v79>, <v81-v1>, <v81-v76>, <v81-v78>, <v81-v80>, <v82-v2>, <v82-v77>, <v82-v79>, <v82-v81>, <v82-v80>, <v83-v3>, <v83-v78>, <v83-v80>, <v83-v82>, <v84-v4>, <v84-v79>, <v84-v81>, <v84-v83>, <v84-v80>, <v85-v5>, <v85-v80>, <v85-v82>, <v85-v84>, <v86-v6>, <v86-v81>, <v86-v83>, <v86-v85>, <v87-v7>, <v87-v82>, <v87-v84>, <v87-v86>, <v87-v85>, <v88-v0>, <v88-v83>, <v88-v85>, <v88-v87>, <v89-v1>, <v89-v84>, <v89-v86>, <v89-v88>, <v89-v85>, <v90-v2>, <v90-v85>, <v90-v87>, <v90-v89>, <v91-v3>, <v91-v86>, <v91-v88>, <v91-v90>, <v92-v4>, <v92-v87>, <v92-v89>, <v92-v91>, <v92-v90>, <v93-v5>, <v93-v88>, <v93-v90>, <v93-v92>, <v94-v6>, <v94-v89>, <v94-v91>, <v94-v93>, <v94-v90>, <v95-v7>, <v95-v90>, <v95-v92>, <v95-v94>, <v96-v0>, <v96-v91>, <v96-v93>, <v96-v95>, <v97-v1>, <v97-v92>, <v97-v94>, <v97-v96>, <v97-v95>, <v98-v2>, <v98-v93>, <v98-v95>, <v98-v97>, <v99-v3>, <v99-v94>, <v99-v96>, <v99-v98>, <v99-v95>]>") != 0)
			fail("colorGraph Failed.");
	}

	@Test
	public void toStringTest() {
		MyGraph graph = new MyGraph( );
		MyVertex v0 = new MyVertex( );
		MyVertex v1 = new MyVertex( );
		MyVertex v2 = new MyVertex( );  
		MyVertex v3 = new MyVertex( );  
		MyVertex v4 = new MyVertex( );  
		graph.addVertex( v0 );
		if (graph.toString().compareTo("<Graph:[<v0:null>], []>") != 0)
			fail("toString Failed.");

		graph.addVertex( v1 );
		if (graph.toString().compareTo("<Graph:[<v0:null>, <v1:null>], []>") != 0)
			fail("toString Failed.");
		graph.addVertex( v2 );
		graph.addVertex( v3 );
		graph.addVertex( v4 );
		MyEdge e0 = new MyEdge(v0, v1);
		MyEdge e1 = new MyEdge(v1, v2);
		e0 = (MyEdge) graph.addEdge(e0);
		if (graph.toString().compareTo("<Graph:[<v0:null>, <v1:null>, <v2:null>, <v3:null>, <v4:null>], [<v0-v1>]>") != 0)
			fail("toString Failed.");

		e1 = (MyEdge) graph.addEdge(e1);
		graph.addEdge(v2, v3);
		graph.addEdge(v3, v4);
		graph.addEdge(v0, v4);
		graph.addEdge(v1, v3);
		if (graph.toString().compareTo("<Graph:[<v0:null>, <v1:null>, <v2:null>, <v3:null>, <v4:null>], [<v0-v1>, <v1-v2>, <v2-v3>, <v3-v4>, <v0-v4>, <v1-v3>]>") != 0)
			fail("toString Failed.");

		graph.removeVertex(v2);
		if (graph.toString().compareTo("<Graph:[<v0:null>, <v1:null>, <v3:null>, <v4:null>], [<v0-v1>, <v3-v4>, <v0-v4>, <v1-v3>]>") != 0)
			fail("toString Failed.");

		graph.removeEdge(e0);
		if (graph.toString().compareTo("<Graph:[<v0:null>, <v1:null>, <v3:null>, <v4:null>], [<v3-v4>, <v0-v4>, <v1-v3>]>") != 0)
			fail("toString Failed.");


		ArrayList< GraphColor > colors = new ArrayList< GraphColor >( );
		colors.add( GraphColor.RED );
		colors.add( GraphColor.GREEN );
		colors.add( GraphColor.BLUE );
		graph.colorGraph(colors);

		if (graph.toString().compareTo("<Graph:[<v0:RED>, <v1:RED>, <v3:GREEN>, <v4:BLUE>], [<v3-v4>, <v0-v4>, <v1-v3>]>") != 0)
			fail("toString Failed.");

		graph.removeVertex(v0);
		graph.removeVertex(v1);
		graph.removeVertex(v3);
		graph.removeVertex(v4);

		if (graph.toString().compareTo("<Graph:[], []>") != 0)
			fail("toString Failed.");


	}



}
