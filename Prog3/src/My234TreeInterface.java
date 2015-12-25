// API for a 2 - 3 - 4 Tree ( also called 2 - 4 Trees )
// * Internal nodes must have only 2, 3, or 4 children
public interface My234TreeInterface< Key extends Comparable< Key >, Value extends Comparable< Value > > {
   // Return the value at the tree node indicated by the key.
   // If none exists, return null;
   public Value get( Key key );

   // Insert or replace the value at the tree node indicated by key.
   // Return the previous value or null if none existed.
   public Value put( Key key, Value value );

   // Remove the tree node indicated by key.
   // Return the previous value or null if none existed.
   public Value remove( Key key );

   // Returns the number of nodes in tree.
   public int getSize( );

   // Returns the number of elements in tree.
   public int getNumberOfElements( );

   // Return true if there are no nodes in the tree
   public boolean isEmpty( );

   // Return a String representation of the tree.
   // Nodes should be ordered as if by inOrder traversal.
   // Child nodes and KeyValuePairs within nodes should be grouped together.
   // Examples:
   // 1. Empty Tree: [ ]
   // 2. Root with three elements and no children:
   //    [ <40:b> <50:a> <60:c> ]
   // 3. Root with one element and two children:
   //    [ [ <40:b> <50:a> ] <60:c> [ <100:d> ] ]
   // 4. Root with two elements and three children:
   //    [ [ <10:e> <30:f> ] <40:b> [ <50:a> ] <60:c> [ <100:d> ] ]
   // 5. Tree with 8 nodes on 3 levels:
   //    [ [ [ <10:e> <30:f> ] <40:b> [ <50:a> <55:g> ] <57:h> [ <59:i> ] ] <60:c> [ [ <65:j> <75:k> ] <100:d> [ <115:l> ] ] ]
   public String toString( );

   // Return the list of KeyValuePairs produced by a preOrder traversal of the tree.
   // Return an empty list of the tree is empty.
   // Example:
   // 1. The following tree:
   //       tree.put( 50, "a" );
   //       tree.put( 40, "b" );
   //       tree.put( 60, "c" );
   //       tree.put( 100, "d" );
   //       tree.put( 10, "e" );
   //       tree.put( 30, "f" );
   //       tree.put( 55, "g" );
   //       tree.put( 57, "h" );
   //       tree.put( 59, "i" );
   //       tree.put( 65, "j" );
   //       tree.put( 75, "k" );
   //       tree.put( 115, "l" );
   //    Produces the preOrderTraversal as follows:
   //      [ <60:c> <40:b> <57:h> <10:e> <30:f> <50:a> <55:g> <59:i> <100:d> <65:j> <75:k> <115:l> ]
   public List< KeyValuePair< Key, Value > > preOrderTraversal( );

   // Return the list of KeyValuePairs produced by an inOrder traversal of the tree.
   // Return an empty list of the tree is empty.
   // Example:
   // 1. The following tree:
   //       tree.put( 50, "a" );
   //       tree.put( 40, "b" );
   //       tree.put( 60, "c" );
   //       tree.put( 100, "d" );
   //       tree.put( 10, "e" );
   //       tree.put( 30, "f" );
   //       tree.put( 55, "g" );
   //       tree.put( 57, "h" );
   //       tree.put( 59, "i" );
   //       tree.put( 65, "j" );
   //       tree.put( 75, "k" );
   //       tree.put( 115, "l" );
   //    Produces the inOrderTraversal as follows:
   //       [ <10:e> <30:f> <40:b> <50:a> <55:g> <57:h> <59:i> <60:c> <65:j> <75:k> <100:d> <115:l> ]
   public List< KeyValuePair< Key, Value > > inOrderTraversal( );

   // Return the list of KeyValuePairs produced by a postOrder traversal of the tree.
   // Return an empty list of the tree is empty.
   // Example:
   // 1. The following tree:
   //       tree.put( 50, "a" );
   //       tree.put( 40, "b" );
   //       tree.put( 60, "c" );
   //       tree.put( 100, "d" );
   //       tree.put( 10, "e" );
   //       tree.put( 30, "f" );
   //       tree.put( 55, "g" );
   //       tree.put( 57, "h" );
   //       tree.put( 59, "i" );
   //       tree.put( 65, "j" );
   //       tree.put( 75, "k" );
   //       tree.put( 115, "l" );
   //    Produces the postOrderTraversal as follows:
   //       [ <10:e> <30:f> <50:a> <55:g> <59:i> <40:b> <57:h> <65:j> <75:k> <115:l> <100:d> <60:c> ]
   public List< KeyValuePair< Key, Value > > postOrderTraversal( );

   // Return the list containing lists of KeyValuePairs produced by traversing from the node with the specified key, to the root.
   // Each sublist contains all the KeyValuePairs contained in a node along the path.
   // Return an empty list of the tree is empty or the key is not found.
   // Example:
   // 1. The following tree:
   //       tree.put( 50, "a" );
   //       tree.put( 40, "b" );
   //       tree.put( 60, "c" );
   //       tree.put( 100, "d" );
   //       tree.put( 10, "e" );
   //       tree.put( 30, "f" );
   //       tree.put( 55, "g" );
   //       tree.put( 57, "h" );
   //       tree.put( 59, "i" );
   //       tree.put( 65, "j" );
   //       tree.put( 75, "k" );
   //       tree.put( 115, "l" );
   //    Produces the pathToRoot( 30 ) as follows:
   //      [ [ <10:e> <30:f> ] [ <40:b> <57:h> ] [ <60:c> ] ]

   public List< List< KeyValuePair< Key, Value > > > pathToRoot( Key key );
}