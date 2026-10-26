// 之所以用范型，是为了 LinearNode 类的通用性，让它什么都能往里装
public class LinearNode<T> {

    // 变量 data 的类型是 T
    // T 是一种范型
    private T data;

    // 让 LinearNode 该类可以存储任意类型的数据（如 Integer、String、自定义类等）
    private LinearNode<T> next;

    public LinearNode(T data) {
        this.data = data;
        this.next = null; // 新节点，默认后面没有节点
    }

    // gettter 方法
    public T getData() {
        return data;
    }

    // gettter 方法
    public LinearNode<T> getNext() {
        return next;
    }

    // setter 方法
    public void setData(T data) {
        this.data = data;
    }

    // setter 方法
    public void setNext(LinearNode<T> next) {
        this.next = next;
    }
}
