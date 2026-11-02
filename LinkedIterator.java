import java.util.*;

// 必须实现 hasNext() 和 next() 两个方法
public class LinkedIterator<T> implements Iterator<T> {

    private LinearNode<T> current;

    // Sets up this iterator using the specified items
    public LinkedIterator(LinearNode<T> node) {
        current = node;
    }

    // 判断是否还有下一个元素
    // Returns true if this iterator has at least one more data item
    // to deliver in the iteration.
    public boolean hasNext() {
        return current != null;
    }

    // 如果还有下一个值就返回，如果没有还调用 next() 就报错
    // Returns the next element in the iteration. If there are no
    // more elements in this iteration, throws an exception.
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        T result = current.getData();
        current = current.getNext();
        return result;
    }
}
