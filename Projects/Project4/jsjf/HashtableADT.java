/** Kyle Davis
  * 4/30/14
  * Project 4 - Hashtables 
  * CSC230 - Dr. DePasquale 
  */
package jsjf;
import java.util.*;

public interface HashtableADT<T> {
	
	/** The addElement method is passed an object, passes the object to the hashingFunction method to determine which 
	  * bucket to place it in, and determines if the object is already contained in the LinkedList at that bucket.
	  * If it is not contained, the object is added to the LinkedList & it returns true - otherwise it returns false 
	  *
	  * @param newData - the object passed in to be placed into the hashtable
	  * @return a boolean true/false whether or not the object was added 
	  */
	public boolean addElement(T element);
	
	/** The find method determines if a given object is contained at the bucket the hashingfunction provides 
	  * The object passed in & the object in the list must be casted to comparable in order to use their compareTo 
	  * methods 
	  * 
	  * @param newData - the object we wish to find in the hashtable
	  * @return the object that we found or null 
	  */
	public T find(T element);
	
	/** The size method returns the number of elements that are in the hashtable 
	  * 
	  * @return tableCount - the number of elements that were added to LinkedLists in the hashtable 
	  */
	public int size();
	
	/** The isEmpty method returns a boolean based on whether or not the hashtable is empty 
	  *
	  * @return true if the table is empty and false otherwise 
	  */
	public boolean isEmpty();
	
	/** The toString method returns a String representation of all the elements contained within the hashtable 
	  *
	  * @return a String representation of all the elements within each LinkedList of the hashtable array 
	  */
	public String toString();
	
	/** The iterator method returns an iterator for all elements contained within all LinkedLists of the hashtable 
	  * Contains hasNext(), next(), and remove() methods 
	  *
	  * @return an iterator for all the hashtable's elements 
	  */
	public Iterator<T> iterator();
	
	/** The getChainIterator method returns an iterator for the elements contained in the LinkedList at a given bucket 
	  * 
	  * @return LinkedList's iterator 
	  * @param bucket - the position of the hashtable which LinkedList we wish to get the iterator for 
	  */
	public Iterator<T> getChainIterator(int bucket);
	
}