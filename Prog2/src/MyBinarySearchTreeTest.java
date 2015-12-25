import static org.junit.Assert.*;

import org.junit.Test;


public class MyBinarySearchTreeTest {

	@Test
	public void getTest() {
		MyBinarySearchTree<Integer, String> tree1 = new MyBinarySearchTree<Integer, String>();
		
		//test when empty
		if (tree1.get(10) != null)
			fail("Get failed.");	
		
		tree1.put(10, "ten");
		tree1.put(3, "three");
		tree1.put(12, "twelve");
		tree1.put(1, "one");
		tree1.put(11, "eleven");
		
		//test two normal values
		if (tree1.get(3).compareTo("three") != 0)
			fail("Get failed.");
		if (tree1.get(11).compareTo("eleven") != 0)
			fail("Get failed.");
		//test a value that doesn't exist
		if (tree1.get(8) != null)
			fail("Get failed.");
		//test null
		if (tree1.get(null) != null)
			fail("Get failed.");
		
		tree1.remove(12);
		if (tree1.get(12) != null)
			fail("Get failed.");
		
		MyBinarySearchTree<String, Integer> tree2 = new MyBinarySearchTree<String, Integer>();
		//test when empty
		if (tree2.get("ten") != null)
			fail("Get failed.");
		
		tree2.put("ten", 10);
		tree2.put("three", 3);
		tree2.put("twelve", 12);
		tree2.put("one", 1);
		tree2.put("eleven", 11);
		
		//test two normal values
		if (tree2.get("three") != 3)
			fail("Get failed.");
		if (tree2.get("eleven") != 11)
			fail("Get failed.");
		//test a value that doesn't exist
		if (tree2.get("eight") != null)
			fail("Get failed.");
		//test null
		if (tree2.get(null) != null)
			fail("Get failed.");		
	}
	
	@Test
	public void putTest() {
		MyBinarySearchTree<Integer, String> tree1 = new MyBinarySearchTree<Integer, String>();
		
		//test null inserts on keys and values
		if (tree1.put(10, null) != null || tree1.getSize() != 0)
			fail("Put failed.");		
		if (tree1.put(null, "10") != null || tree1.getSize() != 0)
			fail("Put failed.");		
		if (tree1.put(null, null) != null || tree1.getSize() != 0)
			fail("Put failed.");
		
		//put when empty
		if (tree1.put(10, "ten") != null)
			fail("Put failed.");
		//put when non-empty
		if (tree1.put(3, "three") != null)
			fail("Put failed.");
		
		tree1.put(12, "twelve");
		tree1.put(1, "one");
		tree1.put(11, "eleven");
		
		if (tree1.getSize() != 5)
			fail("Put failed.");
		//test putting when a value already exists
		if ("three".compareTo(tree1.put(3, "3")) != 0)
			fail("Put failed.");
		
		MyBinarySearchTree<String, Integer> tree2 = new MyBinarySearchTree<String, Integer>();
		//put when empty
		if (tree2.put("ten", 10) != null)
			fail("Put failed.");
		//put when non-empty
		if (tree2.put("three", 3) != null)
			fail("Put failed.");
		
		tree2.put("twelve", 12);
		tree2.put("one", 1);
		tree2.put("eleven", 11);
		
		if (tree2.getSize() != 5)
			fail("Put failed.");
		//test putting when a value already exists
		if (tree2.put("three", 4) != 3)
			fail("Put failed.");
		
	}
	
	@Test
	public void removeTest() {
		MyBinarySearchTree<String, Integer> tree1 = new MyBinarySearchTree<String, Integer>();
		//test null when empty
		if (tree1.remove(null) != null)
			fail("Remove failed.");
		//test a value when empty
		if (tree1.remove("ten") != null)
			fail("Remove failed.");
		
		tree1.put("ten", 10);
		tree1.put("three", 3);
		tree1.put("twelve", 12);

		//test on something that never existed
		if (tree1.remove("eight") != null)
			fail("Remove failed.");
		
		//test regular removal
		if (tree1.remove("ten") != 10)
			fail("Remove failed.");
		tree1.remove("twelve");
		tree1.remove("three");
		//test on something that has been removed
		if (tree1.remove("three") != null)
			fail("Remove failed.");
		
		MyBinarySearchTree<Integer, String> tree2 = new MyBinarySearchTree<Integer, String>();
		tree2.put(5, "five");
		tree2.put(3, "three");
		tree2.put(2, "two");
		tree2.put(4, "four");
		tree2.put(7, "seven");
		tree2.put(6, "six");
		tree2.put(8, "eight");
		//System.out.println(tree2.inOrderTraversal());

		if (tree2.remove(2).compareTo("two") != 0 || tree2.getSize() != 6) 
			fail("Remove failed.");
		if (tree2.remove(2) != null) 
			fail("Remove failed.");

		tree2.remove(3);
		tree2.remove(5);
		tree2.remove(4);
		tree2.remove(6);
		
		if (tree2.remove(7).compareTo("seven") != 0 || tree2.getSize() != 1) 
			fail("Remove failed.");
		tree2.remove(8);

		if (tree2.remove(8) != null) 
			fail("Remove failed.");
		tree2.remove(8);
	}

	@Test
	public void preOrderTraversalTest() {
		MyBinarySearchTree< String, Integer > tree = new MyBinarySearchTree< String, Integer >( );
		//check empty size
		if (tree.preOrderTraversal().size() != 0)
			fail("PreOrder failed.");
			
		String pre = "[ <P, 1>, <C, 26>, <B, 465>, <H, 3>, <K, 293>, <L, 42>, <R, 1>, <Z, 12>, ]";

		tree.put( "P", 1 );
		tree.put( "C", 26 );
		tree.put( "H", 3 );
		tree.put( "K", 293 );
		tree.put( "R", 1 );
		tree.put( "Z", 12 );
		tree.put( "L", 42 );
		tree.put( "B", 465 );
		
		List<KeyValuePair<String, Integer>> list = tree.preOrderTraversal();
		//check returned size
		if (list.size() != 8)
			fail("PreOrder failed.");
		//spot check list
		list.reset(); list.next();
		if (list.get().getValue() != 26)
			fail("PreOrder failed.");
		list.next(); list.next();
		if (list.get().getValue() != 3)
			fail("PreOrder failed.");
		//check correct string against returned string
		if (tree.preOrderTraversal().toString().compareTo(pre) != 0)
			fail("PreOrder failed.");
		
	}

	@Test
	public void inOrderTraversalTest() {
		MyBinarySearchTree< String, Integer > tree = new MyBinarySearchTree< String, Integer >( );
		//check empty size
		String in = "[ <B, 465>, <C, 26>, <H, 3>, <K, 293>, <L, 42>, <P, 1>, <R, 1>, <Z, 12>, ]";
		if (tree.preOrderTraversal().size() != 0)
			fail("PreOrder failed.");

		tree.put( "P", 1 );
		tree.put( "C", 26 );
		tree.put( "H", 3 );
		tree.put( "K", 293 );
		tree.put( "R", 1 );
		tree.put( "Z", 12 );
		tree.put( "L", 42 );
		tree.put( "B", 465 );
		
		List<KeyValuePair<String, Integer>> list = tree.inOrderTraversal();
		//check returned size
		if (list.size() != 8)
			fail("InOrder failed.");
		//spot check list
		list.reset(); list.next();
		if (list.get().getValue() != 26)
			fail("InOrder failed.");
		list.next(); list.next();
		if (list.get().getValue() != 293)
			fail("InOrder failed.");
		//check correct string against returned string
		if (tree.inOrderTraversal().toString().compareTo(in) != 0)
			fail("InOrder failed.");
	}

	@Test
	public void postTraversalTest() {
		MyBinarySearchTree< String, Integer > tree = new MyBinarySearchTree< String, Integer >( );
		//check empty size
		String post = "[ <B, 465>, <L, 42>, <K, 293>, <H, 3>, <C, 26>, <Z, 12>, <R, 1>, <P, 1>, ]";
		if (tree.preOrderTraversal().size() != 0)
			fail("PreOrder failed.");

		tree.put( "P", 1 );
		tree.put( "C", 26 );
		tree.put( "H", 3 );
		tree.put( "K", 293 );
		tree.put( "R", 1 );
		tree.put( "Z", 12 );
		tree.put( "L", 42 );
		tree.put( "B", 465 );
		
		List<KeyValuePair<String, Integer>> list = tree.postOrderTraversal();
		//check returned size
		if (list.size() != 8)
			fail("PostOrder failed.");
		//spot check list
		list.reset(); list.next();
		if (list.get().getValue() != 42)
			fail("PostOrder failed.");
		list.next(); list.next();
		if (list.get().getValue() != 3)
			fail("PostOrder failed.");
		//check correct string against returned string
		if (tree.postOrderTraversal().toString().compareTo(post) != 0)
			fail("PostOrder failed.");
	}

	@Test
	public void breadthFirstTraversalTest() {
		MyBinarySearchTree< String, Integer > tree = new MyBinarySearchTree< String, Integer >( );
		//check empty size
		String br = "[ <P, 1>, <C, 26>, <R, 1>, <B, 465>, <H, 3>, null, <Z, 12>, null, null, null, <K, 293>, null, null, null, null, null, null, null, null, null, null, null, <L, 42>, null, null, null, null, null, null, null, null, null ]";

		if (tree.breadthFirstTraversal().length != 0)
			fail("BreadthFirst failed.");

		tree.put( "P", 1 );
		tree.put( "C", 26 );
		tree.put( "H", 3 );
		tree.put( "K", 293 );
		tree.put( "R", 1 );
		tree.put( "Z", 12 );
		tree.put( "L", 42 );
		tree.put( "B", 465 );
		
		KeyValuePair<String, Integer>[] array = tree.breadthFirstTraversal();
		//check size
		if (array.length != 32)
			fail("BreadthFirst failed.");
		
		//spot check array
		if (array[2].getValue() != 1)
			fail("BreadthFirst failed.");
		if (array[5] != null)
			fail("BreadthFirst failed.");
		
		//create string from array
		String str = "[ ";
		for (int i = 0; i < array.length; i++) {
			str += array[i] + ", ";
		}
		str = str.substring(0, str.length() - 2) + " ]";
		//check correct string against returned string
		if (str.compareTo(br) != 0)
			fail("BreadthFirst failed.");
	}
	
	@Test
	public void getSizeTest() {
		MyBinarySearchTree<String, Integer> tree1 = new MyBinarySearchTree<String, Integer>();
		//test when empty
		if (tree1.getSize() != 0)
			fail("GetSize failed.");
		
		tree1.put("ten", 10);
		//test inserting a single value
		if (tree1.getSize() != 1)
			fail("GetSize failed.");
		tree1.put("three", 3);
		tree1.put("twelve", 12);

		//test multiple inserts
		if (tree1.getSize() != 3)
			fail("GetSize failed.");

		//test multiple removes
		tree1.remove("twelve");
		if (tree1.getSize() != 2)
			fail("GetSize failed.");
		
		tree1.remove("three");
		if (tree1.getSize() != 1)
			fail("GetSize failed.");

		tree1.put("seven", 7);
		if (tree1.getSize() != 2)
			fail("GetSize failed.");
		
		tree1.remove("ten");
		if (tree1.getSize() != 1)
			fail("GetSize failed.");

		tree1.remove("seven");
		if (tree1.getSize() != 0)
			fail("GetSize failed.");
		
		//test after removing something that doesn't exist
		tree1.remove("ten");
		if (tree1.getSize() != 0)
			fail("GetSize failed.");

	}

	@Test
	public void isEmptyTest() {
		MyBinarySearchTree<String, Integer> tree1 = new MyBinarySearchTree<String, Integer>();
		//test when empty
		if (!tree1.isEmpty())
			fail("IsEmpty failed.");
		
		tree1.put("ten", 10);
		//test inserting a single value
		if (tree1.isEmpty())
			fail("IsEmpty failed.");
		tree1.put("three", 3);
		tree1.put("twelve", 12);

		//test multiple inserts
		if (tree1.isEmpty())
			fail("IsEmpty failed.");

		//test multiple removes
		tree1.remove("twelve");
		if (tree1.isEmpty())
			fail("IsEmpty failed.");
		tree1.remove("three");
		if (tree1.isEmpty())
			fail("IsEmpty failed.");
		tree1.remove("ten");
		if (!tree1.isEmpty())
			fail("IsEmpty failed.");

		//test after removing something that doesn't exist
		tree1.remove("ten");
		if (!tree1.isEmpty())
			fail("IsEmpty failed.");
	}

	@Test
	public void toStringTest() {
		MyBinarySearchTree< String, Integer > tree = new MyBinarySearchTree< String, Integer >( );
		//check empty size
		if (tree.toString().compareTo("[ ]") != 0)
			fail("ToString failed.");
		
		String br = "[ <P, 1>, <C, 26>, <R, 1>, <B, 465>, <H, 3>, null, <Z, 12>, null, null, null, <K, 293>, null, null, null, null, null, null, null, null, null, null, null, <L, 42>, null, null, null, null, null, null, null, null, null ]";

		if (tree.breadthFirstTraversal().length != 0)
			fail("ToString failed.");

		tree.put( "P", 1 );
		
		//test with size = 1
		if (tree.toString().compareTo("[ <P, 1> ]") != 0)
			fail("ToString failed.");
			
		tree.put( "C", 26 );
		tree.put( "H", 3 );
		tree.put( "K", 293 );
		tree.put( "R", 1 );
		tree.put( "Z", 12 );
		tree.put( "L", 42 );
		tree.put( "B", 465 );
		
		//check correct string against returned string
		if (tree.toString().compareTo(br) != 0)
			fail("ToString failed.");
	}
	
	
}
