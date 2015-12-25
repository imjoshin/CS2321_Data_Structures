public class List< Item > implements ListInterface< Item > {
   // Implements a list node as an inner class.
   protected class ListNode {
      private Item value = null;

      public Item getValue( ) {
         return value;
      }

      public void setValue( Item newValue ) {
         value = newValue;
      }

      private ListNode next = null;

      public ListNode getNext( ) {
         return next;
      }

      public void setNext( ListNode nextNode ) {
         next = nextNode;
      }
   }

   // Private Data Members *Needs setters and getters*
   protected ListNode head = null;
   protected ListNode cur  = null;
   protected int              size = 0;

   // add after cur
   @Override
   public void add( Item value ) {
      ListNode temp = new ListNode( );
      temp.setValue( value );
      size++ ;
      if (isEmpty( )) {
         head = temp;
         cur = temp;
      } else {
         temp.setNext( cur.getNext( ) );
         cur.setNext( temp );
         cur = temp;
      }
   }

   // insert before cur
   @Override
   public void insert( Item value ) {
      ListNode temp = new ListNode( );
      temp.setValue( value );
      size++ ;
      if (isEmpty( )) {
         head = temp;
         cur = temp;
      } else if (head == cur) {
         head = temp;
         head.setNext( cur );
         cur = head;
      } else {
         ListNode prev = head;
         while( prev.getNext( ) != cur ) {
            prev = prev.getNext( );
         }
         temp.setNext( prev.getNext( ) );
         prev.setNext( temp );
         cur = temp;
      }
   }

   // Helper Method - Throws EmptyListException when list is empty
   private void errWhenEmpty( ) throws EmptyListException {
      if (isEmpty( )) {
         throw new EmptyListException( );
      }
   }

   // remove item at cur
   // cur gets moved to previous node
   @Override
   public Item remove( ) throws EmptyListException {
      Item result = null;
      errWhenEmpty( );
      if (head == cur) {
         head = head.getNext( );
         cur.setNext( null );
         result = cur.getValue( );
         cur = head;
         size-- ;
      } else {
         // Find previous node
         ListNode prev = head;
         while( prev.getNext( ) != cur ) {
            prev = prev.getNext( );
         }
         prev.setNext( cur.getNext( ) );
         cur.setNext( null );
         result = cur.getValue( );
         cur = prev;
         size-- ;
      }
      return result;
   }

   // get current item
   @Override
   public Item get( ) throws EmptyListException {
      errWhenEmpty( );
      return cur.getValue( );
   }

   // get next item, moving cur
   // return that item
   @Override
   public Item next( ) throws EmptyListException, EndOfListException {
      errWhenEmpty( );
      if (!hasNext( )) {
         throw new EndOfListException( );
      }
      cur = cur.getNext( );
      return cur.getValue( );
   }

   // Returns true if there exists a next node in the list
   public boolean hasNext( ) {
      return( ( !isEmpty( ) ) && ( cur.getNext( ) != null ) );
   }

   // get previous item, moving cur
   // return that item
   @Override
   public Item prev( ) throws EmptyListException, EndOfListException {
      errWhenEmpty( );
      if (!hasPrev( )) {
         throw new EndOfListException( );
      }
      ListNode prev = head;
      while( prev.getNext( ) != cur ) {
         prev = prev.getNext( );
      }
      cur = prev;
      return cur.getValue( );
   }

   // Returns true if there exists a previous node in the list
   public boolean hasPrev( ) {
      return( ( !isEmpty( ) ) && ( cur != head ) );
   }

   // move cur to head
   @Override
   public void reset( ) {
      cur = head;
   }

   // number of items in list
   @Override
   public int size( ) {
      return size;
   }

   // True if the list empty; False otherwise
   @Override
   public boolean isEmpty( ) {
      return( head == null );
   }

}