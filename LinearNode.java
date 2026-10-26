
public class LinearNode<T> {

	private T data;
	private LinearNode<T> next;
	
	public LinearNode (T data) {
		this.data = data;
		this.next = null;
	}
	
	public T getData () {
		return data;
	}
	
	public LinearNode<T> getNext () {
		return next;
	}
	
	public void setData (T data) {
		this.data = data;
	}
	
	public void setNext (LinearNode<T> next) {
		this.next = next;
	}

}
