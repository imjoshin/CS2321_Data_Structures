public interface ListInterface<Item> {
   
   // add after cur
   public void add( Item value );
   
   // insert before cur
   public void insert( Item value );
   
   // remove item at cur
   // cur gets moved to previous node
   public Item remove( ) throws EmptyListException;
   
   // get current item
   public Item get( ) throws EmptyListException;
   
   // get next item, moving cur
   // return that item
   public Item next( ) throws EmptyListException, EndOfListException;
   
   // Returns true if there exists a next node in the list
   public boolean hasNext( );
   
   // get previous item, moving cur
   // return that item
   public Item prev( ) throws EmptyListException, EndOfListException;
   
   // Returns true if there exists a previous node in the list
   public boolean hasPrev( );
   
   // move cur to head
   public void reset( );
   
   // number of items in list
   public int size( );
   
   // Is the list empty;
   public boolean isEmpty( );
   
}