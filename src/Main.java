import java.util.List;

public class Main {

    public static void main(String[] args) {
        int bound = 5000;
        char dataA = 'A';
        char dataB = 'B';

        long t1 = System.currentTimeMillis();
        List<Object[]> objects = DataUtil.mergeJoin_1(DataUtil.randomData(bound, dataA), DataUtil.randomData(bound, dataB));
        long t2 = System.currentTimeMillis();
        System.out.println("-----------------------------方法1-花费时间：" + (t2 - t1) + " ms-----------------------------");
        System.out.println("结果集大小：" + objects.size());
//        DataUtil.printData(objects);

        t1 = System.currentTimeMillis();
        objects = DataUtil.mergeJoin_2(DataUtil.randomData(bound, dataA), DataUtil.randomData(bound, dataB));
        t2 = System.currentTimeMillis();
        System.out.println("-----------------------------方法2-花费时间：" + (t2 - t1) + " ms-----------------------------");
        System.out.println("结果集大小：" + objects.size());
//        DataUtil.printData(objects);

        t1 = System.currentTimeMillis();
        objects = DataUtil.mergeJoin_3(DataUtil.randomData(bound, dataA), DataUtil.randomData(bound, dataB));
        t2 = System.currentTimeMillis();
        System.out.println("-----------------------------方法3-花费时间：" + (t2 - t1) + " ms-----------------------------");
        System.out.println("结果集大小：" + objects.size());
//        DataUtil.printData(objects);
    }

}
