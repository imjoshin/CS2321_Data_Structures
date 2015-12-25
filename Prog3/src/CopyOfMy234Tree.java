
public class CopyOfMy234Tree<Key extends Comparable<Key>, Value extends Comparable<Value>> implements My234TreeInterface<Key, Value> {

	//NODE CLASS
	// ------------------------------------------------------------------------------------------------------------------
	
	private class Node {
		
		private Node parent = null;
		
		//children
		private int numChildren = 0;
		private Node child1 = null;
		private Node child2 = null;
		private Node child3 = null;
		private Node child4 = null;
		
		//values
		private int numPairs = 0;
		private KeyValuePair<Key, Value> pair1 = null;
		private KeyValuePair<Key, Value> pair2 = null;
		private KeyValuePair<Key, Value> pair3 = null;
		
		
		public Node(Key key, Value value) {
			pair1 = new KeyValuePair<Key, Value>(key, value);
		}	
		
		public Node(KeyValuePair<Key, Value> pair) {
			pair1 = pair;
		}
		
		//set node's parent
		public void setParent(Node newParent) {
			parent = newParent;
		}		
		
		//get node's parent
		public Node getParent() {
			return parent;
		}
		
		//set child with given number
		public void setChild(int childNum, Node newChild) {
			//if(childNum > numChildren) System.out.println("SETTING CHILD WHERE I SHOULDN'T!!!");
			if (newChild == null)
				numChildren--;
			
			switch (childNum)  {
			case 1:
				child1 = newChild;
				break;
			case 2:
				child2 = newChild;
				break;
			case 3:
				child3 = newChild;
				break;
			case 4:
				child4 = newChild;
				break;
			}
		}
		
		//get child with given number
		public Node getChild(int childNum) {
			switch (childNum)  {
			case 1:
				return child1;
			case 2:
				return child2;
			case 3:
				return child3;
			case 4:
				return child4;
			}
			
			return null;
		}
		
		//set pair with given number
		public void setPair(int pairNum, KeyValuePair<Key, Value> newValue) {
			//if(pairNum > numPairs) System.out.println("SETTING PAIR WHERE I SHOULDN'T!!!");
			if (newValue == null)
				numPairs--;
			
			switch (pairNum)  {
			case 1:
				pair1 = newValue;
				break;
			case 2:
				pair2 = newValue;
				break;
			case 3:
				pair3 = newValue;
				break;
			}
		}
		
		//get pair with given number
		public KeyValuePair<Key, Value> getPair(int pairNum) {
			switch (pairNum)  {
			case 1:
				return pair1;
			case 2:
				return pair2;
			case 3:
				return pair3;
			}
			
			return null;
		}
		
		//set pair with given number, return old value
		public void setValueOfKey(Key key, Value newValue) {			
			if (pair1 != null) {
				if (pair1.getKey().compareTo(key) == 0) {
					pair1.setValue(newValue);
				}
			} else if (pair2 != null) {
				if (pair2.getKey().compareTo(key) == 0) {
					pair2.setValue(newValue);
				}
			} else if (pair3 != null) {
				if (pair3.getKey().compareTo(key) == 0) {
					pair3.setValue(newValue);
				}
			}
		}
		
		//add pair in correct spot
		public void addPair(KeyValuePair<Key, Value> pair) {
			if (numPairs >= 3) { System.out.println("ADDING PAIR WHERE I SHOULDN'T!!!"); return; }
			
			if (numPairs == 0) {
				pair1 = pair;
			} else if (numPairs == 1) {
				if(pair.getKey().compareTo(pair1.getKey()) < 0) {
					pair2 = pair1;
					pair1 = pair;
				} else {
					pair2 = pair;
				}
			} else if (numPairs == 2) {
				if(pair.getKey().compareTo(pair1.getKey()) < 0) {
					pair3 = pair2;
					pair2 = pair1;
					pair1 = pair;
				} if(pair.getKey().compareTo(pair2.getKey()) < 0) {
					pair3 = pair2;
					pair2 = pair;
				} else {
					pair3 = pair;
				} 
			}
			
			numPairs++;
		}
		
		//get number of node's children
		public int getNumChildren() {
			return numChildren;
		}
		
		//get number of node's pairs
		public int getNumPairs() {
			return numPairs;
		}
		
		//get value of given key in node
		public Value get(Key key){
			if (pair1 != null && pair1.getKey().compareTo(key) == 0)
				return pair1.getValue();
			else if (pair2 != null && pair2.getKey().compareTo(key) == 0)
				return pair2.getValue();
			else if (pair3 != null && pair3.getKey().compareTo(key) == 0)
				return pair2.getValue();
			else
				return null;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	
	private Node root = null;
	private int size = 0;
	
	
	private Node findChild(Key key, Node node){
		int i;
		for (i = 0; i < node.getNumPairs(); i++) {
			if ( node.getPair(i).getKey().compareTo(key) > 0) {
				return node.getChild(i);
			}
		}
		
		return node.getChild(i);
	}
	
	@Override
	public Value get(Key key) {
		//set cursor to root
		Node node = root;
		
		while (true) {
			//find if value exists in current node
			Value temp = node.get(key);
			//if its found, return it
			if (temp != null)
				return temp;
			//if not, and not a leaf, go to next child and search again
			else if (node.getNumChildren() != 0)
				node = findChild(key, node);
			//if leaf, value was not found
			else
				return null;
		}
	}
	
	public Value put(Key key, Value value) {
		if (root == null) {
			root = new Node(key, value);
			return null;
		}
		
		Node node = root;
		Value ret = putHelper(key, value);
		
		if (ret != null)
			return ret;
		
		while (true) {
			if (node.getNumPairs() == 3) {
				splitNode(node);
				node = findChild(key, node.getParent());
			} else if (node.getNumChildren() == 0) {
				break;
			} else {
				node = findChild(key, node);
			}
		}
		
		node.addPair(new KeyValuePair<Key, Value>(key, value));
		return null;
	}
	
	private void splitNode(Node node) {
		
		//get/remove last two pairs and children from current node
		KeyValuePair<Key, Value> pair2 = node.getPair(2);
		node.setPair(2, null);
		KeyValuePair<Key, Value> pair3 = node.getPair(3);
		node.setPair(3, null);
		
		Node child3 = node.getChild(3);
		node.setChild(3, null);
		Node child4 = node.getChild(4);
		node.setChild(4, null);
		
		Node temp;
		Node p;
		
		if (node == root) {
			root = new Node(null);
			p = root;
			root.setChild(0, node);
		} else
			p = node.getParent();
		
		
	}
	
	
	//if the value already exists, replace it
	private Value putHelper(Key key, Value value) {
		//set cursor to root
		Node node = root;
		Value ret = null;
		
		while (true) {
			//find if value exists in current node
			Value temp = node.get(key);
			//if its found, return it
			if (temp != null){
				ret = temp;
				node.setValueOfKey(key, value);
				break;
			}
			//if not, and not a leaf, go to next child and search again
			else if (node.getNumChildren() != 0)
				node = findChild(key, node);
			//if leaf, value was not found
			else
				break;
		}
		
		return ret;
	}
	/*
	@Override
	public Value put(Key key, Value value) {
		Node temp = root;
		Node insert = new Node(key, value);
		
		if(root == null) {
			root = insert;
			size++;
			return null;
		}
		
		if (root.getNumPairs() == 3) {
			Node newRoot = new Node(root.getPair(2));
			Node leftNode = new Node(root.getPair(1));
			Node rightNode = new Node(root.getPair(3));
			
			newRoot.setChild(1, leftNode);
			newRoot.setChild(2, rightNode);
			if (root.getChild(1) != null) {
				newRoot.
			}
		}
		
		return null;
	}
	*/

	@Override
	public Value remove(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public List<KeyValuePair<Key, Value>> preOrderTraversal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KeyValuePair<Key, Value>> inOrderTraversal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KeyValuePair<Key, Value>> postOrderTraversal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<KeyValuePair<Key, Value>>> pathToRoot(Key key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return "";
	}

}
