package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/8/2
 **/
public class DijkstraSolution {
    /**
     * 有 n 个网络节点，标记为1到 n。
     * <p>
     * 给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。
     * <p>
     * 现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。
     * 例子：输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
     * 输出：2
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            // src : {dist, weight}
            g[x].add(new int[] {y, t[2]});
        }
        // 记录当前节点到每个边的最短记录记录
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        // 初始化base case
        dist[k - 1] = 0;
        // 利用优先队列获取两节点最短的边
        PriorityQueue<int[]> pq =
            new PriorityQueue<int[]>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        // 插入源点 距离为0
        pq.offer(new int[] {0, k - 1});
        while (!pq.isEmpty()) {
            // 每次从优先队列中取出顶点事实上是这一轮最短路径权值确定的点
            int[] p = pq.poll();
            int time = p[0], src = p[1];
            // 如果到该边的距离小于
            if (dist[src] < time) {
                continue;
            }
            // 遍历所有的边
            for (int[] e : g[src]) {
                int y = e[0], d = dist[src] + e[1];
                if (d < dist[y]) {
                    dist[y] = d;
                    pq.offer(new int[] {d, y});
                }
            }
        }

        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;
    }
}
