public class RouteFinder {

    // 计算从 start 到 dest 的最短路径
    public static Station[] findShortestPath(TransitSystem system, Station start, Station dest) {
        Station[] allStations = system.getStations();
        int N = allStations.length;

        // 第一步：构建权重矩阵（N x N）
        double[][] matrix = buildWeightMatrix(allStations, N);

        // 第二步：初始化距离表
        double[] dists = new double[N];
        Station[] prevs = new Station[N];

        // 找出 start 在 allStations 数组中的索引
        int startIndex = start.getID();

        // 创建已访问和未访问集合
        LinkedSet<Station> visitedSet = new LinkedSet<Station>();
        LinkedSet<Station> unvisitedSet = new LinkedSet<Station>();

        // 初始化 dists 和 prevs 数组
        for (int i = 0; i < N; i++) {
            dists[i] = matrix[startIndex][i];  // 起点到各站的直接距离
            unvisitedSet.add(allStations[i]);  // 所有站点初始都在未访问集合

            if (matrix[startIndex][i] != Double.MAX_VALUE && i != startIndex) {
                prevs[i] = start;  // 有直接连接的，前驱设为起点
            } else {
                prevs[i] = null;
            }
        }

        // 将起点标记为已访问
        visitedSet.add(start);
        unvisitedSet.remove(start);
        start.visit();

        // 第三步：松弛迭代（Dijkstra 核心）
        while (!unvisitedSet.isEmpty()) {
            // 在未访问集合中找距离最小的站点
            double small = Double.MAX_VALUE;
            int smIndex = -1;

            for (int i = 0; i < N; i++) {
                if (unvisitedSet.contains(allStations[i]) && dists[i] < small) {
                    small = dists[i];
                    smIndex = i;
                }
            }

            // 如果找不到可达的未访问节点，说明剩余节点都不可达，退出
            if (smIndex == -1) {
                break;
            }

            // 将距离最小的站点标记为已访问
            visitedSet.add(allStations[smIndex]);
            unvisitedSet.remove(allStations[smIndex]);
            allStations[smIndex].visit();

            // 松弛操作：更新邻居的距离
            for (int n = 0; n < N; n++) {
                if (unvisitedSet.contains(allStations[n])) {
                    double newDist = dists[smIndex] + matrix[smIndex][n];
                    if (newDist < dists[n]) {
                        dists[n] = newDist;
                        prevs[n] = allStations[smIndex];
                    }
                }
            }
        }

        // 第四步：回溯构建路径
        return buildPath(prevs, dest, N);
    }

    // 构建权重矩阵（邻接矩阵，存欧几里得距离）
    private static double[][] buildWeightMatrix(Station[] allStations, int N) {
        double[][] matrix = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    matrix[i][j] = 0.0;  // 自己到自己距离为 0
                } else {
                    matrix[i][j] = Double.MAX_VALUE;  // 默认无穷
                }
            }
        }

        // 遍历每个站点的所有线路，找出直接连接的邻居
        for (int i = 0; i < N; i++) {
            Station curr = allStations[i];
            // 遍历 A~E 五条线路
            for (int line = 0; line < 5; line++) {
                char lineLetter = (char) (line + 65);  // 索引转字母 A=65
                Station next = curr.getNext(lineLetter);
                // 如果有 next 邻居，计算欧几里得距离
                if (next != null) {
                    int j = next.getID();
                    double dist = euclideanDistance(curr, next);
                    matrix[i][j] = dist;
                    matrix[j][i] = dist;  // 对称
                }
            }
        }

        return matrix;
    }

    // 计算两点之间的欧几里得距离
    private static double euclideanDistance(Station a, Station b) {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    // 根据 prevs 数组回溯构建最短路径数组
    private static Station[] buildPath(Station[] prevs, Station dest, int N) {
        // 用临时大数组存反序路径（从终点回溯到起点）
        Station[] path = new Station[N];
        int p = 0;
        Station prev = dest;
        path[p++] = prev;

        // 从终点向前回溯，直到起点
        while (prev != null) {
            int idx = prev.getID();
            prev = prevs[idx];
            if (prev != null) {
                path[p++] = prev;
            }
        }

        // 反转路径，得到从起点到终点的正序
        Station[] shortPath = new Station[p];
        for (int i = 0; i < p; i++) {
            shortPath[i] = path[p - 1 - i];
        }

        return shortPath;
    }

    // 将路径数组转为字符串，如 "32 55 72 19 45 15 77 "
    public static String getPathString(Station[] stnArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stnArray.length; i++) {
            if (stnArray[i] != null) {
                sb.append(stnArray[i].getStnNo()).append(" ");
            }
        }
        return sb.toString();
    }
}
