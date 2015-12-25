
/**
 * @author Josh Johnson
 */

public class My234Tree<Key extends Comparable<Key>, Value extends Comparable<Value>> implements My234TreeInterface<Key, Value> {
	
	// NODE CLASS
	//---------------------------------------------------------------------
	
	private class Node {
		private Node parent;
		private List<Node> children = new List<Node>();
		private List<KeyValuePair<Key, Value>> data = new List<KeyValuePair<Key, Value>>();
		
		public Node() {
		}
		
		public Node(Key key, Value value) {
			data.add(new KeyValuePair<Key, Value>(key, value));
		}	
		
		public Node(KeyValuePair<Key, Value> pair) {
			data.add(new KeyValuePair<Key, Value>(pair.getKey(), pair.getValue()));
		}
		
		//return the node's parent
		public Node getParent() {
			return parent;
		}
		
		//set the node's parent
		public void setParent(Node newParent) {
			parent = newParent;
		}
		
		//get the node's children
		public List<Node> getChildren() {
			children.reset();
			return children;
		}
		
		//get a specific child
		public Node getChild(int childNum) {
			if (childNum > children.size()) return null;
			return children.get(childNum);
		}
		
		//remove a child
		public Node removeChild(int childNum) {
			if (childNum > children.size()) return null;
			children.get(childNum);
			return children.remove();
		}
		
		//get the node's data
		public List<KeyValuePair<Key, Value>> getData() {
			data.reset();
			return data;
		}
		
		//get a specific data
		public KeyValuePair<Key, Value> getData(int dataNum) {
			if (dataNum > data.size()) return null;
			return data.get(dataNum);
		}
		
		//remove a data value
		public KeyValuePair<Key, Value> removeData(int dataNum) {
			if (dataNum > data.size()) return null;
			data.get(dataNum);
			return data.remove();
		}
		
		//find value in node from key
		public Value get(Key key) {
			Value ret = null;
			
			data.reset();
			for (int i = 0; i < data.size(); i++) {
				if (data.get().getKey().compareTo(key) == 0) {
					ret = data.get().getValue();
					break;
				}
				data.next();
			}
			data.reset();
			
			return ret;
		}
		
		//get index of a given key
		public int getDataNumber(Key key) {
			int ret = -1;
			
			data.reset();
			//find position of key
			for (int i = 0; i < data.size(); i++) {
				if (data.get().getKey().compareTo(key) == 0) {
					ret = i;
					break;
				}
				data.next();
			}
			data.reset();
			
			return ret;
		}
		
		//add a data value
		public void addData(KeyValuePair<Key, Value> newData) {
			addData(newData.getKey(), newData.getValue());
		}
		
		//add a data value
		public void addData(Key key, Value value) {
			data.reset();
			
			//if no data or should be placed first
			if (data.size() == 0 || data.get().getKey().compareTo(key) > 0) 
				data.insert(new KeyValuePair<Key, Value>(key, value));
			else {
				//find insert position
				while (data.hasNext()) {
					if (data.next().getKey().compareTo(key) > 0) break;
				}
				
				//if before last
				if (data.get().getKey().compareTo(key) > 0) 
					data.insert(new KeyValuePair<Key, Value>(key, value));
				//if after last
				else
					data.add(new KeyValuePair<Key, Value>(key, value));
			}
		}
		
		//get child number that contains a specific key
		public int getChildNumber(Key key) {
			int ret = -1;
			
			children.reset();
			//find child position
			for (int i = 0; i < children.size(); i++) {
				if (children.get().get(key) != null) {
					ret = i;
					break;
				}
				children.next();
			}
			children.reset();
			
			return ret;
		}
		
		//get child number based on a passed node
		public int getChildNumber(Node node) {
			return getChildNumber(node.getData(0).getKey());
		}
		
		//find the empty child
		public int getEmptyChild() {
			for (int i = 0; i < children.size(); i++) {
				//System.out.println("Checking child #" + i + "\t\tChild: " + getChild(i).debug());
				if (getChild(i).getData().size() == 0)
					return i;
			}
			
			return -1;
		}
		
		//get the node's sibling
		public Node getSibling(String leftOrRight) {
			if (parent == null) return null;
			Node ret = null;
			//get this node's position in the parent
			int position;
			
			if (data.size() > 0)
				position = parent.getChildNumber(data.get(0).getKey());
			else
				position = parent.getEmptyChild();
			
			if (leftOrRight.compareTo("left") == 0) {
				if (position != 0)
					ret = parent.getChild(position - 1);
			} else if (leftOrRight.compareTo("right") == 0) {
				if (position != parent.getChildren().size() - 1)
					ret = parent.getChild(position + 1);
			}
			
			return ret;
		}
		
		//add a child to the node
		public void addChild(Node newChild) {
			children.reset();
			
			newChild.setParent(this);

			if (children.size() == 0)
				children.add(newChild);
			else if (children.get().getData(0).getKey().compareTo(newChild.getData(0).getKey()) > 0) 
				children.insert(newChild);
			else {
				while (children.hasNext()) {
					if (children.next().getData(0).getKey().compareTo(newChild.getData(0).getKey()) > 0) break;
				}
				if (children.get().getData(0).getKey().compareTo(newChild.getData(0).getKey()) > 0) 
					children.insert(newChild);
				else
					children.add(newChild);
			}
		}
		
		//create partial tree based on data values
		public Node getPartialTree(int minData, int maxData) {
			Node ret = new Node(data.get(minData));
			
			//if not a leaf, add children
			if (!isLeaf()) {
				ret.addChild(children.get(minData));
				ret.addChild(children.get(minData + 1));
			}
			
			//add next children/data
			for (int i = minData + 1; i <= maxData; i++) {
				ret.addData(data.get(i));
				if (!isLeaf())
					ret.addChild(children.get(i + 1));
			}
			
			return ret;
		}
		
		//put value that already exists in node, return previous value
		public Value putIfExists(Key key, Value value) {
			data.reset();
			
			for (int i = 0; i < data.size(); i++) {
				if (data.get().getKey().compareTo(key) == 0) {
					Value ret = data.get().getValue();
					data.get().setValue(value);
					return ret;
				}
				data.next();
			}
			
			return null;
		}
		
		//return boolean to see if node is a leaf
		public boolean isLeaf() {
			return (children.size() == 0);
		}
		
		//check if node has overflow
		public boolean hasOverflow() {
			return (data.size() > 3);
		}
		
		//chec if node has underflow
		public boolean hasUnderflow() {
			return (data.size() <= 0);
		}
		
		/*
		//DEBUGGING USE
		public String debug() {
			String str = "";
			String parentData = null;
			if (parent != null)
				parentData = parent.getData().toString();
				
			str += "Parent: " + parentData + "\t";
			str += "Data: " + data.toString() + "\t";
			str += "Children: ";
			
			children.reset();
			for (int i = 0; i < children.size(); i++) {
				str += children.get().getData().toString() + ", ";
				children.next();
			}
			
			return str;
		}
		*/
		
		//return string representation of this node recursively
		public String toString() {
			String str = "";
			data.reset(); children.reset();
			
			switch (children.size()) {
			case 0:
				str += data.toString();
				break;
			case 2:
				str += "[ " + getChild(0) + " " + getData(0) +  " " +getChild(1) + " ]";
				break;
			case 3:
				str += "[ " + getChild(0) + " " + getData(0) +  " " + getChild(1) +  " " + getData(1) +  " " + getChild(2) + " ]";
				break;
			case 4:
				str += "[ " + getChild(0) +  " " + getData(0) +  " " + getChild(1) +  " " + getData(1) +  " " + getChild(2) +  " " + getData(2) +  " " + getChild(3) + " ]";
				break;
			}
				
			
			return str;
		}
	}

	//---------------------------------------------------------------------
	
	
	private Node root = null;
	private int elements = 0;
	private int size = 0;
	
	
	public Value get(Key key) {
		Node temp = root;
		
		//cycle through path to key
		while (temp != null) {
			//find which direction to travel
			if (key.compareTo(temp.getData(0).getKey()) < 0) {
				temp = temp.getChild(0);
			//key found!
			} else if (key.compareTo(temp.getData(0).getKey()) == 0) {
				return temp.getData(0).getValue();
			} else if (temp.getData().size() == 1 || key.compareTo(temp.getData(1).getKey()) < 0) {
				temp = temp.getChild(1);
			//key found!
			} else if (key.compareTo(temp.getData(1).getKey()) == 0) {
				return temp.getData(1).getValue();
			} else if (temp.getData().size() == 2 || key.compareTo(temp.getData(2).getKey()) < 0) {
				temp = temp.getChild(2);
			//key found!
			} else if (key.compareTo(temp.getData(2).getKey()) == 0) {
				return temp.getData(2).getValue();
			} else {
				temp = temp.getChild(3);
			}
		}
		
		return null;
	}

	public Value put(Key key, Value value) {

		elements++;
		Value ret = null;
		Node insert = new Node(key, value);
		
		//if root is null, make root and set size/elements
		if (root == null) {
			root = insert;
			size = 1;
			elements = 1;
		} else {
			//find node to add into
			Node focusNode = findFocusNode(key);
			
			//check if key already exists in focus node
			if (focusNode != root) {
				Value parentPutTest = focusNode.getParent().putIfExists(key, value);
				
				//if key already exists in parent, decrement elements and return the old value
				if (parentPutTest != null ) {
					elements--;
					return parentPutTest;
				}
			}
			
			//if key already exists, decrement elements and return the old value
			
			Value putTest = focusNode.putIfExists(key, value);

			if (putTest != null ) {
				elements--;
				return putTest;
			}
			focusNode.addData(key, value);

			//while overflow exists, split necessary nodes
			while (focusNode.hasOverflow()) {
				if (focusNode == root) {					
					root = splitRoot();
					break;
				} else {
					focusNode = split(focusNode);
				}
			}
		}
		return ret;
	}

	
	private Node split(Node splitNode) {
		size++;
		Node parent = splitNode.getParent();
		
		//create left and right trees
		Node left = splitNode.getPartialTree(0, 1);
		Node right = splitNode.getPartialTree(3, 3);

		//remove splitNode from its parent
		parent.removeChild(parent.getChildNumber(splitNode.getData(2).getKey()));
		
		//promote 3rd data value
		parent.addData(splitNode.getData(2));
		//add left and right children
		parent.addChild(right);
		parent.addChild(left);
		
		return parent;
	}
	
	private Node splitRoot() {
		size += 2;
		//create left and right trees
		Node left = root.getPartialTree(0, 1);
		Node right = root.getPartialTree(3, 3);
		
		//promote 3rd data value
		Node newRoot = new Node(root.getData(2));
		newRoot.addChild(left);
		newRoot.addChild(right);
		
		return newRoot;
	}
	
	private Node findFocusNode(Key key) {
		Node ret = root;
		
		//cycle through tree until parent of node containing the key is found
		while (!ret.isLeaf()) {
			//key found!
			if (key.compareTo(ret.getData(0).getKey()) == 0) {
				return ret;
			} else if (key.compareTo(ret.getData(0).getKey()) < 0) {
				ret = ret.getChild(0);
			} else if (ret.getData().size() == 1 || key.compareTo(ret.getData(1).getKey()) < 0) {
				ret = ret.getChild(1);
			} else if (ret.getData().size() == 2 || key.compareTo(ret.getData(2).getKey()) < 0) {
				ret = ret.getChild(2);
			} else {
				ret = ret.getChild(3);
			}
		}
		
		return ret;
	}
	
	private Node findKeyNode(Key key) {
		Node temp = root;
		
		//cycle through tree until the key is found
		while (temp != null) {
			if (key.compareTo(temp.getData(0).getKey()) < 0) {
				temp = temp.getChild(0);
			} else if (key.compareTo(temp.getData(0).getKey()) == 0) {
				return temp;
			} else if (temp.getData().size() == 1 || key.compareTo(temp.getData(1).getKey()) < 0) {
				temp = temp.getChild(1);
			} else if (key.compareTo(temp.getData(1).getKey()) == 0) {
				return temp;
			} else if (temp.getData().size() == 2 || key.compareTo(temp.getData(2).getKey()) < 0) {
				temp = temp.getChild(2);
			} else if (key.compareTo(temp.getData(2).getKey()) == 0) {
				return temp;
			} else {
				temp = temp.getChild(3);
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	//REMOVE IS NOT COMPLETE!!!!!!!!!
	//Doesn't work properly with many values
	
	
	
	
	
	
	public Value remove(Key key) {
		//System.out.println("Removing key " + key + ".");
		//System.out.println("get(" + key + ") = " + get(key));
		//System.out.println(root);
		
		if (isEmpty() || get(key) == null) return null;
		elements--;
		Value ret;
		
		//if no elements left, reset tree
		if (elements == 0) {
			ret = root.getData(0).getValue();
			root = null;
			size = 0;
			return ret;
		}
		
		Node focus = findKeyNode(key);
		
			
		if (!focus.isLeaf()) {
			
			Node replacement = getReplacementKeyNode(key, focus);
			//take greatest value from replacement and add to focus
			focus.addData(replacement.removeData(replacement.getData().size() - 1));
			//take removal value from focus
			ret = focus.removeData(focus.getDataNumber(key)).getValue();
			
			focus = replacement;
		} else {
			ret = focus.removeData(focus.getDataNumber(key)).getValue();
		}
		
		if (focus.getData().size() > 1)
			return ret;

		//cycle up, find overflow in tree
		while (focus != null) {
			if (focus.hasUnderflow()) {
				handleUnderflow(focus);
			}
			
			focus = focus.getParent();
		}

		//System.out.println(root + "\t\tSize: " + size + "\tElements: " + elements);
		
		return ret;
	}

	private Node getReplacementKeyNode(Key key, Node node) {
		//System.out.println("Finding replacement node for " + node.getData());
		Node ret = node.getChild(node.getDataNumber(key));
		
		while (!ret.isLeaf()) {
			ret = ret.getChild(ret.getChildren().size() - 1);
		}
		
		return ret;
	}
	
	private void handleUnderflow(Node node) {
		//System.out.println("UNDERFLOW! " + node.debug());
		
		Node parent = node.getParent();
		
		//underflow in parent
		if (parent == null) {
			//System.out.println("Underflow in root!");
			Node newRoot = new Node();
			//copy all data of root children into new root
			for (int i = 0; i < root.getChildren().size(); i++) {
				for (int j = 0; j < root.getChild(i).getData().size(); j++) {
					newRoot.addData(root.getChild(i).getData(j));
					if (!root.getChild(i).isLeaf()) {
						newRoot.addChild(root.getChild(i).getChild(j));
					}
				}

				if (!root.getChild(i).isLeaf())
					newRoot.addChild(root.getChild(i).getChild(root.getChild(i).getChildren().size()));
			}
			size--;
			root = newRoot;
		} else {
			
			int nodePosInParent = parent.getEmptyChild();
			//if right most child of parent, must borrow from left
			if (nodePosInParent == parent.getChildren().size() - 1 ) {
				//take right most data value from parent
				node.addData(parent.removeData(parent.getData().size() - 1));
				
				if (node.getSibling("left").getData().size() > 1) {
					parent.addData(node.getSibling("left").removeData(node.getSibling("left").getData().size() - 1));
				} else {
					merge(node.getSibling("left"), node);				
				}
				
			} else {
				//take immediate greater value from parent
				//node.addData(parent.removeData(nodePosInParent));

				//if we can steal from right sibling
				if (node.getSibling("right").getData().size() > 1) {
					
					//take immediate greater value from parent
					node.addData(parent.removeData(nodePosInParent));
					
					parent.addData(node.getSibling("right").removeData(0));
					
				//otherwise steal from left
				} else if (nodePosInParent != 0 && node.getSibling("left").getData().size() > 1) {

					//take immediate lesser value from parent
					node.addData(parent.removeData(nodePosInParent - 1));
					
					parent.addData(node.getSibling("left").removeData(node.getSibling("left").getData().size() - 1));
					
				//else, merge
				} else {

					//take immediate greater value from parent
					node.addData(parent.removeData(nodePosInParent));
					
					merge(node, node.getSibling("right"));
				}
			}
		}
	}
	
	public Node merge(Node node1, Node node2) {
		//System.out.println("Merging " + node1.getData() + " and " + node2.getData());
		size--;
		
		Node parent = node1.getParent();
		Node merged = new Node();

		//add all data/children from node1 into merged
		for (int i = 0; i < node1.getData().size(); i++) {
			merged.addData(node1.getData(i));
			if (!node1.isLeaf())
				merged.addChild(node1.getChild(i));
		}
		if (!node1.isLeaf())
			merged.addChild(node1.getChild(node1.getChildren().size() - 1));

		//add all data/children from node2 into merged
		for (int i = 0; i < node2.getData().size(); i++) {
			merged.addData(node2.getData(i));
			if (!node2.isLeaf())
				merged.addChild(node2.getChild(i));
		}
		if (!node2.isLeaf())
			merged.addChild(node2.getChild(node2.getChildren().size() - 1));
		
		//remove node1 and node2 from parent, then add merged
		parent.removeChild(parent.getChildNumber(node1));
		parent.removeChild(parent.getChildNumber(node2));
		parent.addChild(merged);
		
		return merged;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getNumberOfElements() {
		return elements;
	}

	@Override
	public boolean isEmpty() {
		return (root == null || elements == 0);
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

	private List<KeyValuePair<Key, Value>> traverseHelper(String order, Node node, List<KeyValuePair<Key, Value>> list) {
		if (node == null) return list;
		
		if (order.equals("in")) {
			//if in, visit data between children
			for (int i = 0; i < node.getData().size(); i++) {
				list = traverseHelper(order, node.getChild(i), list);
				list.add(node.getData(i));
			}
			list = traverseHelper(order, node.getChild(node.getData().size()), list);
		} else {
			if (order.equals("pre"))
				//if pre, visit parent before children
				for (int i = 0; i < node.getData().size(); i++) 
					list.add(node.getData(i));
		
			for (int i = 0; i < node.getChildren().size(); i++)
				list = traverseHelper(order, node.getChild(i), list);
			
			if (order.equals("post"))
				//if post, visit parent after children
				for (int i = 0; i < node.getData().size(); i++)
					list.add(node.getData(i));		
		}
		
		return list;
	}
	@Override
	public List<List<KeyValuePair<Key, Value>>> pathToRoot(Key key) {
		Node temp = findFocusNode(key);
		//create return list
		List<List<KeyValuePair<Key, Value>>> ret = new List<List<KeyValuePair<Key, Value>>>();
		
		//cycle up to root, add all nodes along the way
		while (temp.getParent() != null) {
			ret.add(temp.getData());
			temp = temp.getParent();
		}
		
		ret.add(root.getData());
		return ret;
	}

	public String toString() {
		if (root == null) return "[ ]";
		return root.toString();
	}
	/*
	private String debugNode(Key key) {
		return findFocusNode(key).debug();
	}
	*/
}
