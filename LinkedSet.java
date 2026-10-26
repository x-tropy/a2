import java.util.Iterator;

// LinkedSet 是一种集合，里面放的元素是范型 T
public class LinkedSet<T> implements SetADT<T> {

	private LinearNode<T> front;   // 链表头节点
	private int numItems;          // 集合中元素个数

	// 构造方法：初始化空集合
	public LinkedSet() {
		front = null;
		numItems = 0;
	}

	// 添加元素（不允许重复）
	public void add(T element) {
		if (contains(element)) {
			return;   // 已存在，不做任何事
		}

		LinearNode<T> newNode = new LinearNode<>(element);

		// 情况1：空链表，新节点成为头节点
		if (isEmpty()) {
			front = newNode;
		}
		// 情况2：非空链表，插入到头部
		else {
			newNode.setNext(front);
			front = newNode;
		}

		numItems++;
	}

	// 判断集合是否为空
	public boolean isEmpty() {
		return numItems == 0;   // 等价于 front == null
	}

	// 返回元素个数
	public int size() {
		return numItems;
	}

	// 判断集合中是否包含指定元素
	public boolean contains(T element) {
		LinearNode<T> current = front;

		while (current != null) {
			if (current.getData().equals(element)) {
				return true;
			}
			current = current.getNext();
		}

		return false;
	}

	// 删除元素，返回被删除的元素；找不到则抛出 SetException
	public T remove(T element) throws SetException {
		LinearNode<T> current = front;
		LinearNode<T> previous = null;

		while (current != null) {
			if (current.getData().equals(element)) {
				T result = current.getData();

				// 情况1：删除的是唯一元素 / 头节点
				if (previous == null) {
					front = current.getNext();
				}
				// 情况2：删除的是中间或尾部节点
				else {
					previous.setNext(current.getNext());
				}

				numItems--;
				return result;
			}

			previous = current;
			current = current.getNext();
		}

		// 遍历完仍未找到则抛出异常
		throw new SetException("Element not found");
	}

	// 返回迭代器
	public Iterator<T> iterator() {
		return new LinkedIterator<>(front);
	}

	// 字符串表示
	public String toString() {
		if (isEmpty()) {
			return "empty set";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("{");

		LinearNode<T> current = front;
		while (current != null) {
			sb.append(current.getData());
			if (current.getNext() != null) {
				sb.append(", ");
			}
			current = current.getNext();
		}

		sb.append("}");
		return sb.toString();
	}
}
