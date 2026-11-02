public class TransitLine {

    private char lineLetter;  // 线路标识,如 'A'、'B'
    private Station firstStn; // 该线路的第一个站点,即链表头

    public TransitLine(char ln, Station first) {
        lineLetter = ln;
        firstStn = first;
    }

    public char getLineLetter() {
        return lineLetter;
    }

    public Station getFirstStn() {
        return firstStn;
    }

    // 判断站点 stn 在该线路上是否还有下一个站点
    public boolean hasNext(Station stn) {
        Station next = stn.getNext(lineLetter); // 查询 stn 在本线路的 next 指针
        return next != null;
    }

    // 在线路末尾追加新站点,尾插法
    public void addStation(Station stn) {
        Station lastAdded = firstStn;

        // 从头遍历到当前链表末尾
        while (hasNext(lastAdded)) {
            lastAdded = lastAdded.getNext(lineLetter);
        }
        // 此时 lastAdded 是当前最后一个站点

        // 新站点 prev 指向原末尾站点
        stn.setPrev(lastAdded, lineLetter);

        // 原末尾站点 next 指向新站点
        lastAdded.setNext(stn, lineLetter);
    }

    // 返回形如 "Line A:  1  3  5  " 的字符串
    public String toString() {
        String s = "Line " + lineLetter + ":  " + firstStn.getStnNo() + "  ";

        Station curr = firstStn;
        while (hasNext(curr)) {
            curr = curr.getNext(lineLetter);
            s += curr.getStnNo() + "  ";
        }

        return s;
    }
}
