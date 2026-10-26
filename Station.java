public class Station {

    private int id;
    private static int next_id = 0;

    private int stnNo;
    private int x;
    private int y;

    private Station[] nexts;
    private Station[] prevs;
    private boolean visited;

    // 构造方法
    public Station(int stnNo, int x, int y) {
        this.id = next_id;
        next_id++; // id 递增，保证连续无间隔

        this.stnNo = stnNo;
        this.x = x;
        this.y = y;
        this.visited = false;

        this.nexts = new Station[5]; // 支持 A~E 五条线路
        this.prevs = new Station[5];
    }

    // 重置 id 计数器（用于创建多个独立系统）
    public static void resetIDCounter() {
        next_id = 0;
    }

    // ============ Getters ============
    public int getID() {
        return id;
    }

    public int getStnNo() {
        return stnNo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Station[] getNextArray() {
        return nexts;
    }

    public Station[] getPrevArray() {
        return prevs;
    }

    public boolean isVisited() {
        return visited;
    }

    // 标记为已访问（仅用于 GUI 显示）
    public void visit() {
        visited = true;
    }

    // 设置指定线路的下一个站点
    public void setNext(Station stn, char lineLetter) {
        int index = lineLetter - 65; // A=0, B=1, C=2...
        nexts[index] = stn;
    }

    // 设置指定线路的上一个站点
    public void setPrev(Station stn, char lineLetter) {
        int index = lineLetter - 65;
        prevs[index] = stn;
    }

    // 获取指定线路的下一个站点
    public Station getNext(char lineLetter) {
        int index = lineLetter - 65;
        return nexts[index];
    }

    // 获取指定线路的上一个站点
    public Station getPrev(char lineLetter) {
        int index = lineLetter - 65;
        return prevs[index];
    }

    // 字符串表示
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stn ").append(stnNo).append(" on Lines: ");

        // 遍历所有线路，列出该站点所属的线路
        boolean first = true;
        for (int i = 0; i < 5; i++) {
            if (nexts[i] != null || prevs[i] != null) {
                if (!first) {
                    sb.append(" ");
                }
                sb.append((char) (i + 65)); // 索引转回字母: 0=A, 1=B...
                first = false;
            }
        }

        return sb.toString();
    }
}
