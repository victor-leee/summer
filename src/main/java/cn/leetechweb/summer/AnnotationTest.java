package cn.leetechweb.summer;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/**
 * Project Name: summer
 * Create Time: 2020/11/5 0:37
 *
 * @author junyu lee
 **/
public class AnnotationTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        File file = new File("src/main/java");
        Set<File> files = new HashSet<>();
        read(file, files);
        for (File classFile : files) {
            if (classFile.getName().endsWith("java") && classFile.isFile()) {
//                Class<?> clazz = Class.forName(classFile.getName());
//                System.out.println(clazz);
                String before = classFile.getPath().
                        replaceAll("\\\\", ".").replaceAll(".java", "");
                String after = before.replaceAll("src.main.", "");
                System.out.println(after);
                Class<?> clazz = Class.forName(after);
                if ("cn.leetechweb.summer.GoodMorning".equals(after)) {
                    Object inst = clazz.getConstructors()[0].newInstance("你好");
                    System.out.println(inst);
                }
            }
        }
    }

    static void read(File file, Set<File> subFiles) {
        if (file.isFile()) {
            subFiles.add(file);
        }else {
            for (File sub : file.listFiles()) {
                read(sub, subFiles);
            }
        }
    }
}
