package written_examination_questions.meituan;

/**
 * Description:
 * 小美当上了会计。她现在拿到了n个开支数据 a[1],a[2],…,a[n]，现在她想稍微对这些数据做一些统计。
 * <p>
 * 小美有三种想统计的信息。第一种是她选择一个区间[L,R]，希望知道a[L] + a[L+1] + … + a[R]等于多少，第二种是她选择一个区间[L,R]，希望知道a[L],a[L+1],…,a[R]这些数据的有效值是多少。第三种是她选择一个区间[L,R]，希望知道a[L],a[L+1],…,a[R]的最大值是多少。
 * <p>
 * 一组数据b[l],b[2],…,b[r]的有效值，定义为
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 输入描述
 * 第一行一个整数n，表示一共有n个数。
 * <p>
 * 第二行n个整数,a[1],a[2],…,a[n]，表示小美拿到的数据分别是哪些。
 * <p>
 * 第三行一个整数m，表示一共有m个询问。
 * <p>
 * 接下来m行，每行三个整数，opt, L, R，当opt=1时表示是第一种询问， opt=2时表示第二种询问，opt=3时表示第三种询问。（1 <= opt <= 3，1 <= L <= R <= n）
 * <p>
 * 数据保证每个收支数据a[i]满足 -1000 <= a[i] <= 1000。
 * <p>
 * 统计次数m满足1 <= m <= 500。
 * <p>
 * 其中，对于60%的数据n满足1 <= n <= 1,000。
 * <p>
 * 对于100%的数据n满足1 <= n <= 100, 000。
 * <p>
 * 输出描述
 * 输出m行，每行一个数，表示该行对应询问的答案。
 * <p>
 * <p>
 * 样例输入
 * 4
 * 1 1 2 3
 * 3
 * 1 1 3
 * 2 2 4
 * 3 1 4
 * 样例输出
 * 4
 * 50
 * 3
 * <p>
 * 提示
 * 第一个操作1+1+2=4
 * <p>
 * 第二个操作（6-1)2+(6-2)2+(6-3)2=50
 * <p>
 * 第三个操作其中最大值为a[4]，等于3
 *
 * @author:edgarding
 * @mail:edgarding97@gmail.com
 * @date:2021/8/22
 **/
public class Test4 {
}