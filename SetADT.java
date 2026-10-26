import java.util.Iterator;

public interface SetADT<T> {
	
	public void add (T element);
	public T remove (T element) throws RuntimeException;
	public int size ();
	public boolean isEmpty ();
	public boolean contains (T element);
	public Iterator<T> iterator ();
	public String toString ();
}
