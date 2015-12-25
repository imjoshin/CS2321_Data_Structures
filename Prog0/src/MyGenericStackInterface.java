import java.lang.RuntimeException;
/**
 * This Interface provides the public API for a stack.
 */
public interface MyGenericStackInterface<Item> {

   /*
    * Retrieve the item that was most recently added to the stack,
    * which is the item at the top of the stack.
    * The item is removed from the stack.
    */
   public Item pop( );
   
   /*
    * Retrieve the item at the top of the stack.
    * Does not modify the stack.
    */
   public Item peek( );
   
   /*
    * Add item to the top of the stack.
    */
   public void push( Item item );
   
   /*
    * Return true if the stack is empty
    */
   public boolean isEmpty( );

   /*
    * Return the number of items on the stack
    */
   public int size( );

}