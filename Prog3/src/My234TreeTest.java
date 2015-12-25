import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Josh Johnson
 */

public class My234TreeTest {
	
	@Test
	public void getTest() {
		My234Tree<Integer, Integer> tree1 = new My234Tree<Integer, Integer>();
		//add 20 ints
		for (int i = 0; i < 20; i++ ) {
			tree1.put(i, i);
			
			if (i >= 2)
				if ( (tree1.get(i - 2)) != i - 2)
					fail("Get failed.");
		}
		//test with string, integer
		My234Tree<String, Integer> tree2 = new My234Tree<String, Integer>();
		if (tree2.get("two") != null)
			fail("Get failed.");
		
		tree2.put("two", 2);
		tree2.put("TWO", 2);
		tree2.put("seven", 7);
		tree2.get("seven");
		tree2.put("FoUr", 4);
		tree2.put("Pi * 100", 314);
		tree2.put("big #", Integer.MAX_VALUE);
		if (tree2.get("two") != 2)
			fail("Get failed.");
		if (tree2.get("Two") != null)
			fail("Get failed.");
		
		//test with string, string
		My234Tree<String, String> tree3 = new My234Tree<String, String>();
		
		tree3.put("pig", ":@)");
		tree3.put("ugly fish", "><(((('>");
		tree3.put("PARTY!!!", "  \\(^o^)/  ");
		tree3.put("scary-pointy-thing", "o()xxxx[{:::::::::::::::::::::::::::>");
		tree3.put("duck", "<')___/");
		tree3.put("jazzy", "-=iii=<()");
		tree3.put("mouse", "<:3)~  ");
		tree3.put("sauron", "<•>");
		
		if (tree3.getNumberOfElements() != 8)
			fail("Put failed.");
		if (tree3.get("pig").compareTo(":@)") != 0)
			fail("Put failed.");
		if (tree3.get("scary-pointy-thing").compareTo("o()xxxx[{:::::::::::::::::::::::::::>") != 0)
			fail("Put failed.");
	}
	
	@Test
	public void putTest() {
		My234Tree<Integer, Integer> tree1 = new My234Tree<Integer, Integer>();
		//add 20 ints
		for (int i = 0; i < 20; i++ ) {
			if (tree1.put(i, i) != null) 
				fail("Put failed.");
		}
		if (tree1.getNumberOfElements() != 20)
			fail("Put failed.");
		//add 1 more
		if (tree1.put(20, 20) != null) 
			fail("Put failed.");
		//add something that already exists, check return value
		if (tree1.put(3, 33) != 3)
			fail("Put failed.");
		//reverse it
		if (tree1.put(3, 3) != 33)
			fail("Put failed.");

		//test with string, integer
		My234Tree<String, Integer> tree2 = new My234Tree<String, Integer>();
		
		tree2.put("two", 2);
		if (tree2.put("TWO", 2) != null)
			fail("Put failed.");
		tree2.put("seven", 7);
		if (tree2.get("seven") != 7)
			fail("Put failed.");
		tree2.put("FoUr", 4);
		tree2.put("Pi * 100", 314);
		tree2.put("big #", Integer.MAX_VALUE);
		if (tree2.get("two") != 2)
			fail("Put failed.");
		if (tree2.put("FoUr", 5) != 4)
			fail("Put failed.");
		
		//test with string, string
		My234Tree<String, String> tree3 = new My234Tree<String, String>();
		
		tree3.put("pig", ":@)");
		tree3.put("ugly fish", "><(((('>");
		tree3.put("PARTY!!!", "  \\(^o^)/  ");
		tree3.put("scary-pointy-thing", "o()xxxx[{:::::::::::::::::::::::::::>");
		tree3.put("duck", "<')___/");
		tree3.put("jazzy", "-=iii=<()");
		tree3.put("mouse", "<:3)~  ");
		tree3.put("sauron", "<•>");
		
		if (tree3.getNumberOfElements() != 8)
			fail("Put failed.");
		if (tree3.get("pig").compareTo(":@)") != 0)
			fail("Put failed.");
		if (tree3.get("scary-pointy-thing").compareTo("o()xxxx[{:::::::::::::::::::::::::::>") != 0)
			fail("Put failed.");
		if (tree3.put("scary-pointy-thing", "<:::::::::::::::::::::::::::}]xxxx()o").compareTo("o()xxxx[{:::::::::::::::::::::::::::>") != 0)
			fail("Put failed.");
		if (tree3.put("pig", "pig").compareTo(":@)") != 0)
			fail("Put failed.");		
		
	}

	@Test
	public void removeTest() {
		My234Tree<Integer, Integer> tree1 = new My234Tree<Integer, Integer>();
		
		if (tree1.remove(0) != null)
			fail("Remove failed.");
			
		//add 8 ints
		for (int i = 0; i < 8; i++ ) {
			if (tree1.put(i, i) != null) 
				fail("Put failed.");
		}
		
		if (tree1.remove(8) != null)
			fail("Remove failed.");

		for (int i = 7; i >= 0; i--) {
			if (tree1.remove(i) != i)
				fail("Put failed.");
		}

		//test with string, integer
		My234Tree<String, Integer> tree2 = new My234Tree<String, Integer>();
		
		tree2.put("two", 2);
		tree2.put("seven", 7);
		tree2.put("FoUr", 4);
		tree2.put("Pi * 100", 314);
		tree2.put("big #", Integer.MAX_VALUE);

		if (tree2.remove("TWO") != null)
			fail("Remove failed.");
		if (tree2.remove("Pi * 100") != 314)
			fail("Remove failed.");
		if (tree2.remove("FoUr") != 4)
			fail("Remove failed.");
		if (tree2.remove("seven") != 7)
			fail("Remove failed.");
		if (tree2.remove("big #") != Integer.MAX_VALUE)
			fail("Remove failed.");
		
		if (tree2.remove("big #") != null)
			fail("Remove failed.");
		
		
		//test with string, string
		My234Tree<String, String> tree3 = new My234Tree<String, String>();
		
		tree3.put("pig", ":@)");
		if (tree3.remove("pig").compareTo(":@)") != 0)
			fail("Remove failed.");	
		tree3.put("pig", ":@)");
		if (tree3.remove("pig").compareTo(":@)") != 0)
			fail("Remove failed.");	
		
		tree3.put("pig", ":@)");
		tree3.put("ugly fish", "><(((('>");
		tree3.put("PARTY!!!", "  \\(^o^)/  ");
		tree3.put("scary-pointy-thing", "o()xxxx[{:::::::::::::::::::::::::::>");
		tree3.put("duck", "<')___/");
		tree3.put("jazzy", "-=iii=<()");
		tree3.put("mouse", "<:3)~  ");
		tree3.put("sauron", "<•>");
		
		if (tree3.remove("sauron").compareTo("<•>") != 0)
			fail("Remove failed.");		
		if (tree3.remove("PARTY!!!").compareTo("  \\(^o^)/  ") != 0)
			fail("Remove failed.");		
		if (tree3.remove("sauron") != null)
			fail("Remove failed.");	
		if (tree3.remove("mouse").compareTo("<:3)~  ") != 0)
			fail("Remove failed.");	
		
	}

	@Test
	public void getSizeTest() {
		My234Tree<Integer, Integer> tree = new My234Tree<Integer, Integer>();
	
		if (tree.getSize() != 0)
			fail("GetSize failed.");
		tree.put(0, 0);
		if (tree.getSize() != 1)
			fail("GetSize failed.");
		tree.put(1, 1);
		if (tree.getSize() != 1)
			fail("GetSize failed.");
		tree.put(2, 2);
		if (tree.getSize() != 1)
			fail("GetSize failed.");
		tree.put(3, 3);
		if (tree.getSize() != 3)
			fail("GetSize failed.");
		
		for (int i = 4; i < 10; i++ ) {
			tree.put(i, i);
		}
		
		if (tree.getSize() != 5)
			fail("GetSize failed.");

		tree.remove(9);
		if (tree.getSize() != 5)
			fail("GetSize failed.");
		tree.remove(8);
		if (tree.getSize() != 4)
			fail("GetSize failed.");
		tree.remove(7);
		if (tree.getSize() != 4)
			fail("GetSize failed.");
		tree.remove(6);
		if (tree.getSize() != 4)
			fail("GetSize failed.");
		tree.remove(5);
		if (tree.getSize() != 3)
			fail("GetSize failed.");
		tree.remove(4);
		if (tree.getSize() != 3)
			fail("GetSize failed.");
		tree.remove(3);
		if (tree.getSize() != 3)
			fail("GetSize failed.");
		tree.remove(2);
		if (tree.getSize() != 1)
			fail("GetSize failed.");
		tree.remove(1);
		if (tree.getSize() != 1)
			fail("GetSize failed.");
		tree.remove(0);
		if (tree.getSize() != 0)
			fail("GetSize failed.");
		
		tree.put(10, 10);
		if (tree.getSize() != 1)
			fail("GetSize failed.");
		tree.remove(10);
		if (tree.getSize() != 0)
			fail("GetSize failed.");
		
			
	}
	
	@Test
	public void getNumberOfElementsTest() {
		My234Tree<Integer, Integer> tree = new My234Tree<Integer, Integer>();
		
		if (tree.getNumberOfElements() != 0)
			fail("GetNumberOfElements failed.");
		
		for (int i = 0; i < 10; i++) {
			tree.put(i, i);
			if (tree.getNumberOfElements() != i + 1)
				fail("GetNumberOfElements failed.");
		}	
		
		
		tree.remove(0);
		if (tree.getNumberOfElements() != 9)
			fail("GetNumberOfElements failed.");
		for (int i = 1; i < 9; i++) {
			tree.remove(i);
			if (tree.getNumberOfElements() != 9 - i)
				fail("GetNumberOfElements failed.");
		}
		
		tree.remove(9);
		if (tree.getSize() != 0)
			fail("GetNumberOfElements failed.");
		
	}

	@Test
	public void isEmptyTest() {
		My234Tree<Integer, Integer> tree = new My234Tree<Integer, Integer>();
		if (!tree.isEmpty())
			fail("isEmpty failed.");
		tree.put(1, 1);
		if (tree.isEmpty())
			fail("isEmpty failed.");
		tree.put(2, 2);
		tree.put(3, 3);
		tree.put(4, 4);
		if (tree.isEmpty())
			fail("isEmpty failed.");
		
		
		tree.remove(2);
		tree.remove(3);
		tree.remove(4);
		if (tree.isEmpty())
			fail("isEmpty failed.");
		tree.remove(1);
		if (!tree.isEmpty())
			fail("isEmpty failed.");		
		
	}

	@Test
	public void toStringTest() {
		My234Tree<Integer, String> tree = new My234Tree<Integer, String>();
		tree.put( 50, "a" );
		tree.put( 40, "b" );
		tree.put( 60, "c" );
		tree.put( 100, "d" );
		tree.put( 10, "e" );
		tree.put( 30, "f" );
		tree.put( 55, "g" );
		tree.put( 57, "h" );
		tree.put( 59, "i" );
		tree.put( 65, "j" );
		tree.put( 75, "k" );
		tree.put( 115, "l" );
		
		if (tree.toString().compareTo("[ [ [ <10:e> <30:f> ] <40:b> [ <50:a> <55:g> ] <57:h> [ <59:i> ] ] <60:c> [ [ <65:j> <75:k> ] <100:d> [ <115:l> ] ] ]") != 0)
			fail("ToString failed.");
		
	}

	@Test
	public void preOrderTest() {
		My234Tree<Integer, String> tree = new My234Tree<Integer, String>();
		tree.put( 50, "a" );
		tree.put( 40, "b" );
		tree.put( 60, "c" );
		tree.put( 100, "d" );
		tree.put( 10, "e" );
		tree.put( 30, "f" );
		tree.put( 55, "g" );
		tree.put( 57, "h" );
		tree.put( 59, "i" );
		tree.put( 65, "j" );
		tree.put( 75, "k" );
		tree.put( 115, "l" );
		
		if (tree.preOrderTraversal().toString().compareTo("[ <60:c> <40:b> <57:h> <10:e> <30:f> <50:a> <55:g> <59:i> <100:d> <65:j> <75:k> <115:l> ]") != 0)
			fail("PreOrder failed.");
		
		tree.remove(50);
		tree.remove(55);
		tree.remove(57);

		if (tree.preOrderTraversal().toString().compareTo("[ <60:c> <30:f> <10:e> <40:b> <59:i> <100:d> <65:j> <75:k> <115:l> ]") != 0)
			fail("PreOrder failed.");
		
	}

	@Test
	public void inOrderTest() {
		My234Tree<Integer, String> tree = new My234Tree<Integer, String>();
		tree.put( 50, "a" );
		tree.put( 40, "b" );
		tree.put( 60, "c" );
		tree.put( 100, "d" );
		tree.put( 10, "e" );
		tree.put( 30, "f" );
		tree.put( 55, "g" );
		tree.put( 57, "h" );
		tree.put( 59, "i" );
		tree.put( 65, "j" );
		tree.put( 75, "k" );
		tree.put( 115, "l" );
		
		if (tree.inOrderTraversal().toString().compareTo("[ <10:e> <30:f> <40:b> <50:a> <55:g> <57:h> <59:i> <60:c> <65:j> <75:k> <100:d> <115:l> ]") != 0)
			fail("InOrder failed.");
		
		tree.remove(50);
		tree.remove(55);
		tree.remove(57);

		if (tree.inOrderTraversal().toString().compareTo("[ <10:e> <30:f> <40:b> <59:i> <60:c> <65:j> <75:k> <100:d> <115:l> ]") != 0)
			fail("InOrder failed.");
		
	}

	@Test
	public void postOrderTest() {
		My234Tree<Integer, String> tree = new My234Tree<Integer, String>();
		tree.put( 50, "a" );
		tree.put( 40, "b" );
		tree.put( 60, "c" );
		tree.put( 100, "d" );
		tree.put( 10, "e" );
		tree.put( 30, "f" );
		tree.put( 55, "g" );
		tree.put( 57, "h" );
		tree.put( 59, "i" );
		tree.put( 65, "j" );
		tree.put( 75, "k" );
		tree.put( 115, "l" );
		
		if (tree.postOrderTraversal().toString().compareTo("[ <10:e> <30:f> <50:a> <55:g> <59:i> <40:b> <57:h> <65:j> <75:k> <115:l> <100:d> <60:c> ]") != 0)
			fail("PostOrder failed.");
		
		tree.remove(50);
		tree.remove(55);
		tree.remove(57);

		if (tree.postOrderTraversal().toString().compareTo("[ <10:e> <40:b> <59:i> <30:f> <65:j> <75:k> <115:l> <100:d> <60:c> ]") != 0)
			fail("PostOrder failed.");
	}

	@Test
	public void pathToRootTest() {
		My234Tree<Integer, String> tree = new My234Tree<Integer, String>();
		tree.put( 50, "a" );
		tree.put( 40, "b" );
		tree.put( 60, "c" );
		tree.put( 100, "d" );
		tree.put( 10, "e" );
		tree.put( 30, "f" );
		tree.put( 55, "g" );
		tree.put( 57, "h" );
		tree.put( 59, "i" );
		tree.put( 65, "j" );
		tree.put( 75, "k" );
		tree.put( 115, "l" );
		
		if (tree.pathToRoot(30).toString().compareTo("[ [ <10:e> <30:f> ] [ <40:b> <57:h> ] [ <60:c> ] ]") != 0)
			fail("PostOrder failed.");
		if (tree.pathToRoot(60).toString().compareTo("[ [ <60:c> ] ]") != 0)
			fail("PostOrder failed.");
		if (tree.pathToRoot(100).toString().compareTo("[ [ <100:d> ] [ <60:c> ] ]") != 0)
			fail("PostOrder failed.");
		
	}
}
