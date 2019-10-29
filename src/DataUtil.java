import java.util.*;

public class DataUtil {

    /**
     * 合并数据，原始版本，双重循环，没有扩展
     * @param listA 集合A
     * @param listB 集合B
     * @return 合并后的数据
     */
    public static List<Object[]> mergeJoin_1(List<Object[]> listA, List<Object[]> listB) {
        //结果集
        List<Object[]> res = new ArrayList<>();

        for(Object[] objsA: listA) {
            //校验listA的id类型
            if(!(objsA[0] instanceof Integer)) {
                continue;
            }
            int idA = (int) objsA[0];
            //标记，记录listB中是否有id等于idA的数据
            boolean flag = false;
            for(Object[] objsB: listB) {
                //校验listB的id的类型
                if(!(objsB[0] instanceof Integer)) {
                    continue;
                }
                int idB = (int) objsB[0];
                //判定id是否相等
                if(idA != idB) {
                    continue;
                }
                Object[] mergeArr = mergeArr(objsA, objsB);
                res.add(mergeArr);
                flag = true;
            }
            //如果listB中没有匹配的
            if(!flag) {
                Object[] mergeArr = mergeArr(objsA, null);
                res.add(mergeArr);
            }
        }

        return res;
    }

    /**
     * 合并数据
     * @param listA 集合A
     * @param listB 集合B
     * @return 合并后的数据
     */
    public static List<Object[]> mergeJoin_2(List<Object[]> listA, List<Object[]> listB) {
        //结果集
        List<Object[]> res = new ArrayList<>();

        //先把listA的数据全存进去
        for(Object[] objsA: listA) {
            if(!(objsA[0] instanceof Integer)) {
                continue;
            }
            Object[] arr = new Object[5];
            arr[0] = objsA[0];
            arr[1] = objsA[1];
            arr[2] = objsA[2];
            res.add(arr);
        }

        //遍历listB
        for(Object[] objsB: listB) {
            if(!(objsB[0] instanceof Integer)) {
                continue;
            }
            //标记，记录res中是否存在id相等的数据
            boolean flag = false;
            int idB = (int) objsB[0];
            //遍历res数据
            for(Object[] objs: res) {
                int id = (int) objs[0];
                if(id == idB) {
                    objs[3] = objsB[1];
                    objs[4] = objsB[2];
                    flag = true;
                }
            }
            //如果res中不存在id相等的数据，则把listB的加进去
            if(!flag) {
                Object[] arr = new Object[5];
                arr[0] = objsB[0];
                arr[3] = objsB[1];
                arr[4] = objsB[2];
                res.add(arr);
            }
        }

        return res;
    }

    /**
     * 合并数据，使用Map
     * @param listA 集合A
     * @param listB 集合B
     * @return 合并后的数据
     */
    public static List<Object[]> mergeJoin_3(List<Object[]> listA, List<Object[]> listB) {
        //结果集
        Map<Integer, Object[]> res = new HashMap<>();

        for(Object[] objs: listA) {
            //校验id类型
            if(!(objs[0] instanceof Integer)) {
                continue;
            }
            int id = (int) objs[0];
            Object[] value = res.get(id);
            if(value == null) {
                value = new Object[5];
                value[0] = id;
            }
            value[1] = objs[1];
            value[2] = objs[2];
            res.put(id, value);
        }

        for(Object[] objs: listB) {
            //校验id类型
            if(!(objs[0] instanceof Integer)) {
                continue;
            }
            int id = (int) objs[0];
            Object[] value = res.get(id);
            if(value == null) {
                value = new Object[5];
                value[0] = id;
            }
            value[3] = objs[1];
            value[4] = objs[2];
            res.put(id, value);
        }

        return new ArrayList<>(res.values());
    }

    /**
     * 合并数组，调用这个方法时，保证至少有一个参数不为空
     * @return 合并后的数组
     */
    private static Object[] mergeArr(Object[] arr1, Object[] arr2) {
        //参数校验
        if(arr1==null && arr2==null) {
            throw new IllegalArgumentException("需要至少一个非空参数！");
        }
        if(arr1!=null && arr1.length!=3 || arr2!=null && arr2.length!=3) {
            throw new IllegalArgumentException("数组参数长度有误！");
        }

        Object[] res = new Object[5];
        //设置id
        res[0] = (arr1 != null) ? arr1[0] : arr2[0];

        //合并数组
        for(int i=1; i<3; i++) {
            if(arr1 != null) {
                res[i] = arr1[i];
            }
            if(arr2 != null) {
                res[i+2] = arr2[i];
            }
        }
        return res;
    }

    /**
     * 随机构造数据
     * @param bound 结果集大小
     * @param data 存储的数据
     * @return 随机数据，结构：[id, data, data]
     */
    public static List<Object[]> randomData(int bound, char data) {
        //结果集
        List<Object[]> res = new ArrayList<>();
        Random random = new Random();
        //取不重复的id
        Set<Integer> idSet = new HashSet<>();
        while (idSet.size() < bound) {
            idSet.add(random.nextInt(bound + 500));
        }
        //存入结果集
        idSet.forEach(id -> {
            Object[] objs = new Object[3];
            objs[0] = id;
            objs[1] = data;
            objs[2] = data;
            res.add(objs);
        });
        return res;
    }

    /**
     * 打印数据
     * @param data 数据
     */
    public static void printData(List<Object[]> data) {
        for(Object[] objs: data) {
            System.out.printf("[id = %d] [%c, %c, %c, %c]\n", objs[0], objs[1], objs[2], objs[3], objs[4]);
        }
    }
}
