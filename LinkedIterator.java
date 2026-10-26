
import java.util.*;

public class LinkedIterator<T> implements Iterator<T> {

	private LinearNode<T> current;
	
	// Sets up this iterator using the specified items
	public LinkedIterator (LinearNode<T> collection) {    
		current = collection;
	}

	// Returns true if this iterator has at least one more data item
	// to deliver in the iteration.
	public boolean hasNext () {
		return (current != null);
	}
	
	// Returns the next element in the iteration. If there are no
	// more elements in this iteration, throws an exception.
	public T next() {
		if (! hasNext())  throw new NoSuchElementException(); 
		T result = current.getData();
		current = current.getNext();
		return result;
	}
}
