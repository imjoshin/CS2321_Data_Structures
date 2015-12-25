/*
 * CS 2321 Data Structures
 * Exploration 1: Testing Edge Cases
 * Your MyArrayString should implement this interface.
 */
import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

public interface MyArrayStringInterface {
   /*
    * returns true if, and only if, object o is a MyArrayString object
    * representing the same string as this string.
    * 
    * myEquals( ) returns false if the input is null.
    */
   public boolean myEquals( Object o );

   /*
    * returns the char at location index, where the first character is at
    * location 0, etc.
    * 
    * If the input index is out of bounds, your method should throw an
    * IndexOutOfBoundsException.
    */
   public char myCharAt( int index ) throws IndexOutOfBoundsException;

   /*
    * returns this MyArrayString object if otherMyArrayString is the empty
    * string, otherwise returns a new MyArrayString which represents the
    * concatenation of this string with the other string following it.
    * 
    * If an input object reference is null, your method should throw an
    * IllegalArgumentException
    */
   public MyArrayStringInterface myConcat(
                  MyArrayStringInterface otherMyArrayString )
                  throws IllegalArgumentException;

   /*
    * returns -1 if ch does not occur in this string, otherwise returns the
    * smallest location of ch in this string.
     */
   public int myIndexOf( char ch ) throws IndexOutOfBoundsException;

   /*
    * returns the length of this string
    */
   public int myLength( );

   /*
    * sets the character at location index to the character ch.
    * 
    * If the input index is out of bounds, your method should throw an
    * IndexOutOfBoundsException.
    */
   public void mySetAt( int index, char ch ) throws IndexOutOfBoundsException;

   /*
    * returns a new MyArrayString representing the substring of this string from
    * location low up through location high-1.}
    * 
    * If either input index is out of bounds, your method should throw an
    * IndexOutOfBoundsException.
    * 
    * If high < low, your method should throw an IndexOutOfBoundsException.
    */
   public MyArrayStringInterface mySubString( int low, int high )
                  throws IndexOutOfBoundsException;

   /*
    * Returns the internal character array representing the String
    */
   public char[ ] getArrayString( );

} // =========================================================== END CLASS