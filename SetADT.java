import java.util.Iterator;

// 规定了「集合」这一数据结构应具备的通用操作
// 集合是无序的，而且不允许重复。正是因为这两个特性，所以不能用 ArrayList 去替代
public interface SetADT<T> {
    public void add(T element);     // 即使重复调用 add 添加同一个东西，最终也只会留下一个在集合里
    public T remove(T element) throws RuntimeException;
    public int size();
    public boolean isEmpty();
    public boolean contains(T element);
    public Iterator<T> iterator();  // 返回一个用于遍历集合的迭代器
    public String toString();       // 返回字符串形式表示的集合
}
