public class TransitSystem {

    private Station[] allStations; // 系统全部站点
    private TransitLine[] allLines; // 系统全部线路
    private GUI gui; // 图形界面,可能为 null

    public TransitSystem(Station[] sArr, TransitLine[] tArr, boolean showMap) {
        allStations = sArr;
        allLines = tArr;
        // 只有要求显示地图时才创建 GUI,方便无界面测试
        if (showMap) gui = new GUI(this, showMap);
    }

    public Station[] getStations() {
        return allStations;
    }

    public TransitLine[] getLines() {
        return allLines;
    }

    // 按数组下标取站点,O(1)
    public Station getStationByIndex(int index) {
        return allStations[index];
    }

    // 按业务编号 ID 查找站点,线性搜索,找不到返回 null
    public Station getStationByID(int id) {
        for (int i = 0; i < allStations.length; i++) {
            if (allStations[i].getStnNo() == id) {
                return allStations[i];
            }
        }
        return null;
    }

    // 按数组下标取线路
    public TransitLine getTransitLineByIndex(int index) {
        return allLines[index];
    }

    // 在 GUI 上高亮显示最短路径
    public void showShortestPath(Station[] path) {
        if (gui != null) {
            // 防止无界面模式出错
            gui.showShortestPath(path);
            gui.repaint(); // 强制重绘
        }
    }
}
