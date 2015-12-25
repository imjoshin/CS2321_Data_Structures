/*
 * A Priority Queue that returns either the min or max element in the queue
 * NOTE: You must create an EmptyQueueException to use with this interface.
 */
public interface MyPriorityQueueInterface<Item extends Comparable<Item>> {
   
   // Add a new item to the queue
   public void put( Item item );
   
   // Retrieve the MAX item in the queue
   // Removes the item from the queue.
   public Item getMax( ) throws EmptyQueueException;
   
   // Retrieve the MIN item in the queue
   // Removes the item from the queue.
   public Item getMin( ) throws EmptyQueueException;

   // Returns a list of the items in the queue.
   public ListInterface<Item> toList( );

   // number of items in list
   public int size( );
   
   // Is the list empty;
   public boolean isEmpty( );
}