
public class MyBinarySearchTree<Key extends Comparable<Key>, Value extends Comparable<Value>> implements BinarySearchTreeInterface<Key, Value> {
	private class Node {
		private KeyValuePair<Key, Value> pair = null;
		private Node parent = null;
		private Node left = null;
		private Node right = null;
		
		//create node with given key/value
		public Node(Key key, Value value) {
			KeyValuePair<Key, Value> pair = new KeyValuePair<Key, Value>();
			pair.setKey(key);
			pair.setValue(value);
			this.pair = pair;
		}

		//get item value
		public KeyValuePair<Key, Value> getPair( ) {
			return pair;
		}

		//set key
		public void setKey( Key key ) {
			pair.setKey(key);
		}
		
		//get key
		public Key getKey() {
			return this.pair.getKey();
		}
		
		//set value
		public void setValue( Value value ) {
			pair.setValue(value);
		}
		
		//get value
		public Value getValue() {
			return this.pair.getValue();
		}

		//set next node
		public void setParent( Node parentNode ) {
			parent = parentNode;
		}
		
		//get next node
		public Node getLeft( ) {
			return left;
		}

		//set next node
		public void setLeft( Node leftNode ) {
			left = leftNode;
		}

		//get previous node
		public Node getRight( ) {
			return right;
		}

		//set previous node
		public void setRight( Node rightNode ) {
			right = rightNode;
		}

		/*/print node with next and previous
		//debugging use
		public String toString() {
			String leftValue = "null";
			String rightValue = "null";
			String thisValue = "null";
			String parentValue = "null";
			
			if (pair != null)
				thisValue = pair.toString();
			if (left != null && left.getPair() != null)
				leftValue = left.getPair().toString();
			if (right != null && right.getPair() != null)
				rightValue = right.getPair().toString();
			if (parent != null && parent.getPair() != null)
				parentValue = parent.getPair().toString();

			return " { Pair: " + thisValue + ", Parent: " + parentValue + ", Left: " + leftValue + ", Right: " + rightValue + " }";
		 }*/
	}	
	
	private Node root = null;
	private int size = 0;
	
	@Override
	public Value get(Key key) {
		if (key == null) return null;
		return getHelper(root, key);
	}

	//method to recursively find desired node value
	private Value getHelper(Node node, Key key) {
		if (node == null) return null;
		
		//if key is found, return its value
		if (node.getKey().compareTo(key) == 0) {
			return node.getValue();
		//if key should be found on the left side, move to the left child
		} else if (node.getKey().compareTo(key) > 0) {
			if (node.getLeft() != null) {
				return getHelper(node.getLeft(), key);
			//if no left child, return null
			} else {
				return null;
			}
		//if key should be found on the right side, move to the right child
		} else {
			if (node.getRight() != null) {
				return getHelper(node.getRight(), key);
			//if no right child, return null
			} else {
				return null;
			}
		}
	}
	@Override
	public Value put(Key key, Value value) {
		if (key == null || value == null) return null;
		
		//create node to insert
		Node insert = new Node(key, value);
		size++;
		//if tree is empty, make insert the root
		if (root == null || size == 0) {
			root = insert;
			return null;
		} else {
			return putHelper(root, insert);
		}
	}

	//method to recursively find desired node position (and add)
	private Value putHelper(Node node, Node insert) {
		//if key is found, get key's value to return and set node's value to new value
		if (node.getKey().compareTo(insert.getKey()) == 0) {
			Value ret = node.getValue();
			node.setValue(insert.getValue());
			return ret;
		//if key should be found on the left side, move to the left child
		} else if (node.getKey().compareTo(insert.getKey()) > 0) {
			if (node.getLeft() != null) {
				return putHelper(node.getLeft(), insert);
			//if child doesn't exist, create and insert
			} else {
				insert.setParent(node);
				node.setLeft(insert);
				return null;
			}
		//if key should be found on the right side, move to the right child
		} else {
			if (node.getRight() != null) {
				return putHelper(node.getRight(), insert);
			//if child doesn't exist, create and insert
			} else {
				insert.setParent(node);
				node.setRight(insert);
				return null;
			}
		}	
	}
	
	
	public Value remove(Key key) {
		if (key == null || root == null || size == 0) {
			return null;
		} else {
			//get return value
			Value ret = getHelper(root, key);
			if (ret == null)
				return null;
			
			//if removal node is root and has a single child
			if (root.getKey().compareTo(key) == 0 && (root.getLeft() == null || root.getRight() == null)) {
				size--;
				ret = root.getValue();
				if (size == 0) {
					root = null;
					return ret;
				}
				//if root has one child, set root to that child
				if (root.getLeft() == null && root.getRight() != null) {
					root = root.getRight();
				} else if (root.getRight() == null) {
					root = root.getLeft();
				}
				return ret;
			} else {
				size--;
				removeHelper(root, key); //call helper
				return ret;
			}
		}
	}
	
	
	private Node removeHelper(Node node, Key key) {
		//base case, if null, return null
		if (node == null) return null;
		
		//if found on left side
		if (node.getKey().compareTo(key) > 0) {
			node.setLeft(removeHelper(node.getLeft(), key));
			return node;
		//if found on right side
		} else if (node.getKey().compareTo(key) < 0) {
			node.setRight(removeHelper(node.getRight(), key));
			return node;
		//if this node is the desired deletion node
		} else {
			if (node.getLeft() != null && node.getRight() != null) {
				//Node ret = node;
				
				Node replace = findReplacementNode(node.getLeft());
				node.setKey(replace.getKey());
				node.setValue(replace.getValue());
				
				node.setLeft(removeHelper(node.getLeft(), replace.getKey()));
				
				return node;
			} else if (node.getLeft() != null) {
				return node.getLeft();
			} else if (node.getRight() != null) {
				return node.getRight();
			} else {
				return null;
			}
		}
	}
	//method to grab the right-most node of the node that's passed
	private Node findReplacementNode(Node node) {
		if (node.getRight() == null) {
			return node;
		} else {
			return findReplacementNode(node.getRight());
		}
	}
	
	@Override
	public List<KeyValuePair<Key, Value>> preOrderTraversal() {
		return traverseHelper("pre", root, new List<KeyValuePair<Key, Value>>());
	}

	@Override
	public List<KeyValuePair<Key, Value>> inOrderTraversal() {
		return traverseHelper("in", root, new List<KeyValuePair<Key, Value>>());
	}

	@Override
	public List<KeyValuePair<Key, Value>> postOrderTraversal() {
		return traverseHelper("post", root, new List<KeyValuePair<Key, Value>>());
	}

	//generates a list of whichever traversal is passed
	private List<KeyValuePair<Key, Value>> traverseHelper(String order, Node node, List<KeyValuePair<Key, Value>> list) {
		if (node == null) return list;

		if (order.equals("pre")) list.add(node.getPair()); //if pre, visit parent before children
		list = traverseHelper(order, node.getLeft(), list);//call helper on left child
		if (order.equals("in")) list.add(node.getPair()); //if in, visit parent after left, but before right
		list = traverseHelper(order, node.getRight(), list);//call helper on right child
		if (order.equals("post")) list.add(node.getPair());//if post, visit parent after children

		return list;
	}
	
	@Override
	public KeyValuePair<Key, Value>[] breadthFirstTraversal() {
		KeyValuePair<Key, Value>[] ret = new KeyValuePair[0];
		if (size == 0) return ret;
		
		int n = 0;
		int length = 1;
		if (size > 1) length = (int) Math.pow(2, treeHeight(root) + 1); //length is 2 ^ (height of tree)
		
		ret = new KeyValuePair[length];
		
		//list that acts as a queue
		List<Node> q = new List<Node>();
		//add root to list
		q.add(root);
		
		while (!q.isEmpty() && n < length) {
			//set list to beginning
			q.reset();
			//grab first element
			Node temp = q.remove();
			
			//if this node is null, add a null to list
			if (temp == null)
				ret[n] = null;
			else
				ret[n]= temp.getPair();
			
			//move to the end of the queue for insertion
			while (q.hasNext())
				q.next();
			
			//insert children
			if (temp == null) {
				q.add(null);
				q.add(null);
			} else {
				q.add(temp.getLeft());
				q.add(temp.getRight());
			}
			
			n++;
			
		}
		
		return ret;
	}
	
	//method to get tree height
	private int treeHeight(Node node) {
		if (node == null)
			return -1;
		else
			return 1 + Math.max(treeHeight(node.getLeft()), treeHeight(node.getRight()));
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (root == null || size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		if (size == 0) return "[ ]";
		KeyValuePair<Key, Value>[] breadth = breadthFirstTraversal();
		String ret = "[ ";
		//loop through all elements and add the element plus a comma
		for (int i = 0; i < breadth.length; i++) {
			ret += breadth[i] + ", ";
		}
		return ret.substring(0, ret.length() - 2) + " ]";		
	}
	/*
	//DEBUGGING USE (kinda broken-ish)
	public String getNode(Key key) {
		System.out.println("Root: " + root);
		return getNodeHelper(root, key).toString();
	}
	
	private Node getNodeHelper(Node node, Key key) {
		//if key is found, return its value
		if (node.getKey().compareTo(key) == 0) {
			return node;
		//if key should be found on the left side, move to the left child
		} else if (node.getKey().compareTo(key) > 0) {
			if (node.getLeft() != null) {
				return getNodeHelper(node.getLeft(), key);
			//if no left child, return null
			} else {
				return null;
			}
		//if key should be found on the right side, move to the right child
		} else {
			if (node.getRight() != null) {
				return getNodeHelper(node.getRight(), key);
			//if no right child, return null
			} else {
				return null;
			}
		}
	}
	*/

}
