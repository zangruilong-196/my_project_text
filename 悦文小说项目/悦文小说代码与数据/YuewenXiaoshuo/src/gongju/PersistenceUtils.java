package gongju;
import java.io.*;

public class PersistenceUtils {

    /**
     * 数据持久化方法
     *
     * @param filePath 文件路径
     * @param o        持久化对象
     * @throws IOException
     */
    public static void dataPersistence(String filePath, Object o)  {
        File testFile = new File(filePath);//创建一个File类
        File fileParent = testFile.getParentFile();//返回的是File类型,可以调用exsit()等方法
        if (!fileParent.exists()) {//如果目录不存在，则创建目录
            fileParent.mkdirs();// 能创建多级目录
        }
        if (!testFile.exists()) {
            try {
                testFile.createNewFile();//有路径才能创建文件
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件创建失败");
            }
        }
        //序列化对象
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(testFile));
            out.writeObject(o);    //写入customer对象
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("序列化失败");
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 读取数据源 反序列化
     *
     * @param filePath 文件路径
     * @throws IOException
     */
    public static Object dataDeserialization(String filePath) {
        //反序列化对象
        ObjectInputStream in = null;//"e:\\test\\objectFile.obj"
        Object o=null;
        try {
            in = new ObjectInputStream(new FileInputStream(filePath));
            o = in.readObject();//读取customer对象
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("反序列化失败");
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
}

