/** Kyle Davis
  * 4/30/14
  * Project 4 - Hashtables 
  * CSC230 - Dr. DePasquale 
  */
package jsjf;
import java.util.*;

/** The LinkedChainedHashtable class is designed to store Comparable objects into a LinkedList at a particular bucket of the array - 
  * the bucket is determined by the hashing function, and elements are only added to the LinkedList if an object with the same resource
  * name is not already contained. Contains methods to add elements to the table, find & return elements contained in the table, print
  * the table & provides 2 iterators for accessing elements in the table. 
  *
  * @author Kyle Davis
  */
public class LinkedChainedHashtable<T> implements HashtableADT<T>, Iterable<T> {
	/** int tableSize - the size of the LinkedList array */
	private int tableSize = 0;
	/** LikedList hashtable - makes an array of generic LinkedLists to place the data in */
	public LinkedList<T>[] hashtable;
	/** int tableCount - total number of elements contained within each LinkedList of the array */
	private int tableCount = 0;
	
	/** LinkedChainedHashtable - if an object is instantiated without a size - gives the hashtable the default size by 
	  * calling the parameterized constructor 
	  */
	public LinkedChainedHashtable() {
		/** Gives the array a default size of 10 */
		this(10);
	}
	
	/** LinkedChainedHashtable - the constructor. Takes in a size and creates an array of generic type LinkedLists 
	  *
	  * @param size - the size of the hashtable
	  */
	public LinkedChainedHashtable(int size) {
		hashtable = (LinkedList<T>[]) (new LinkedList[size]);
		tableSize = size;
		for (int i = 0; i < size; i++) {
			hashtable[i] = new LinkedList<T>();
		}
	}
	
	/** The addElement method is passed an object, passes the object to the hashingFunction method to determine which 
	  * bucket to place it in, and determines if the object is already contained in the LinkedList at that bucket.
	  * If it is not contained, the object is added to the LinkedList & it returns true - otherwise it returns false 
	  *
	  * @param newData - the object passed in to be placed into the hashtable
	  * @return a boolean true/false whether or not the object was added 
	  */
	public boolean addElement(T newData) {
		/** int index - determines which bucket newData is placed in based on a function in the hashingFunction method */
		int index = this.hashingFunction(newData);
		/** If the data is not already contained in the LinkedList at this position, then it is added & returns true */
		if (this.find(newData) == null) {
			hashtable[index].add(newData);
			tableCount++;
			return true;
		}
		/** if the data is already contained, we return a false so the method that called this can deal with the issue */
		else {
			return false;
		}
		
	}
 	
	/** The find method determines if a given object is contained at the bucket the hashingfunction provides 
	  * The object passed in & the object in the list must be casted to comparable in order to use their compareTo 
	  * methods 
	  * 
	  * @param newData - the object we wish to find in the hashtable
	  * @return the object that we found or null 
	  */
	public T find(T newData) {
		/** int index - determines which bucket to check for an element based on the hashingFunction method*/
		int index = this.hashingFunction(newData);
		/** Comparable object1 - the object given as parameters is cast to Comparable */
		Comparable object1 = (Comparable) newData;
		for (int i = 0; i < hashtable[index].size(); i++) {
			/** Comparable object2 - each object contained in the list is cast to Comparable */
			Comparable object2 = (Comparable) hashtable[index].get(i);
			/** If their compareTo methods determine if they are equal, then we return that object */
			if (object1.compareTo(object2) == 0) {
				return hashtable[index].get(i);
			}
		}
		/** If the object was not found at that LinkedList, we return null */
		return null;
	}
	
	/** The size method returns the number of elements that are in the hashtable 
	  * 
	  * @return tableCount - the number of elements that were added to LinkedLists in the hashtable 
	  */
	public int size() {
		return tableCount;
	}
	
	/** The isEmpty method returns a boolean based on whether or not the hashtable is empty 
	  *
	  * @return true if the table is empty and false otherwise 
	  */
	public boolean isEmpty() {
		if (tableCount == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** The toString method returns a String representation of all the elements contained within the hashtable 
	  *
	  * @return a String representation of all the elements within each LinkedList of the hashtable array 
	  */
	public String toString() {
		String result = "";
		Iterator itr = this.iterator();
		while (itr.hasNext()) {
			result += itr.next() + " \n";
		}
		return result;
	}
	/** The length method returns the the number of buckets that the hashtable array contains 
	  * 
	  * @return the size of the hashtable array 
	  */
	public int length () {
		return tableSize;
	}
	
	/** The iterator method returns an iterator for all elements contained within all LinkedLists of the hashtable 
	  * Contains hasNext(), next(), and remove() methods 
	  *
	  * @return an iterator for all the hashtable's elements 
	  */
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {
			/** int bucketIndex - keeps track of what position of the array we are in */
			private int bucketIndex = 0;
			/** int currentIndex - keeps track of what element of the LinkedList we are at */
			private int currentIndex = 0;
			/** hasNext determines if there is another element in the hashtable 
			  * if it does not find an element in its current LinkedList, then it resets the currentIndex,
			  * increases the bucketIndex and moves to another position in the hashtable with a different LinkedList
			  * 
			  * @return true if the hashtable contains another element & false if it does not 
			  */
			public boolean hasNext() {
				boolean result = false; 
				/** int loop - int variable for the while */
				int loop = -1;
				while (loop == -1) {
					/** if there are more LinkedLists in the hashtable to access*/
					if (bucketIndex < tableSize) {
						/** If there are more elements left in the current LinkedList, then result is true */
						if (currentIndex < hashtable[bucketIndex].size()) {
							loop = 1;
							result = true;
						}
						/** else, move on to the LinkedList in the next bucket */
						else {
							currentIndex = 0;
							loop = -1;
							bucketIndex++;
						}
					}
					/** If it has exceeded the last bucket of the hashtable, then there are no more elements left to get*/
					else {
						result = false;
						loop = 1;
					}
				}
				return result;
			}
			/** The next method returns the element located at currentIndex's position of the current LinkedList
			  *
			  * @return the object at the position of the linked list or null 
			  */
			public T next() {
				/** T object - a generic T object used to store the element at currentIndex of the LinkedList */
				T object;
				if (currentIndex < hashtable[bucketIndex].size()) {
					object = (T) hashtable[bucketIndex].get(currentIndex);
				}
				else {
					object = null;
				}
				currentIndex++;
				return object;
			}
			/** The remove method is not supported here */
			public void remove() {
				
			}
		};
		return it;
	}
	
	/** The getChainIterator method returns an iterator for the elements contained in the LinkedList at a given bucket 
	  * 
	  * @return LinkedList's iterator 
	  * @param bucket - the position of the hashtable which LinkedList we wish to get the iterator for 
	  */
	public Iterator<T> getChainIterator(int bucket) {
		if (!hashtable[bucket].isEmpty()) {
			return hashtable[bucket].listIterator(0);
		}
		else {
			return null;
		}
	}
	
	/** The hashingFunction method takes in an object, gets its hashCode and determines where in the hashtable the object
	  * should be placed based on a folding & division function
	  *
	  * @return hashkey - the position of the hashtable the object will be placed at 
	  * @param the object that we wish to place at a certain index in the hashtable
	  */
	private int hashingFunction(T newData) {
		/** String key - a String representation of the hashcode to be used for folding */
		String key = Integer.toString(newData.hashCode());
		/** String number2 - the first 2 elements of key */
		String number2 = key.substring(0,2);
		/** String number3 - the remaining elements of key */
		String number3 = key.substring(2, key.length());
		/** int hashKey - number2 + number3 */
		int hashKey = Integer.parseInt(number2) + Integer.parseInt(number3);
		hashKey = Math.abs(hashKey)% tableSize;
		return hashKey;
	}

}