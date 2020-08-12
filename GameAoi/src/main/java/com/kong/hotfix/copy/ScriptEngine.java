package com.kong.hotfix.copy;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

/**
 * @author Peanut
 * @date 2019/2/21 16:33
 */
public class ScriptEngine {

    private static Map<Class<?>,Object> scriptMap = new HashMap<Class<?>, Object>();
    private static Logger logger = Logger.getLogger("ScriptEngin.class");

    public static void init(String scriptJarFile,String packagePath)  {


        try {
            File jarFile = new File(scriptJarFile);
            URL url = jarFile.toURI().toURL();
            JarFile jar = new JarFile(scriptJarFile);
            ScriptClassLoader loader = new ScriptClassLoader(new URL[]{url}, packagePath, false);
//            URLClassLoader loader = new URLClassLoader(new URL[]{url},Thread.currentThread().getContextClassLoader());

            Map<Class<?>,Object> map = new HashMap<Class<?>, Object>();
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements())
            {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                name = name.replace("/",".");
                if (name.endsWith(".class") && !name.contains("$"))
                {
                    name = name.split(".class")[0];
                    if (name.startsWith(packagePath)) {
                        logger.info("ScriptImpl ClassName :" + name);
                        Class<?> type = loader.loadClass(name);
                        Class<?>[] superTypes = type.getInterfaces();

                        if (Modifier.isAbstract(type.getModifiers())){
                            continue;
                        }
                        //这个是 HelloScript有实现IHelloScript 暂时可以不需要
                        for (Class<?> superType : superTypes) {
                            if (!isVerify(packagePath, superType)) {
                                logger.info("ScriptEngine 文件验证失败");
                                return;
                            }
                            map.put(superType, type.newInstance());
                        }
//                        map.put(type, type.newInstance());
                    }
                }
            }

            if (map.isEmpty())
            {
                logger.info("ScriptEngine 文件验证失败 2");
                return ;
            }

            //文件全部验证完毕后，再加入缓存中
            for (Map.Entry<Class<?>,Object> entry : map.entrySet())
            {
                scriptMap.put(entry.getKey(),entry.getValue());
            }
        }
        catch (Exception e)
        {
            logger.info("ScriptEngine 读取文件失败");
            e.printStackTrace();
            return;
        }
    }

    /**
     * 验证是否继承IScript,不然失败
     * @param type
     * @return
     */
    private static boolean isVerify(String packagePath,Class<?> type)
    {
        for (Class<?> superCls : type.getInterfaces())
        {
//            if (!("com.kong.hotfix.").equals(superCls.getName()))
            if(!superCls.getName().contains("com.kong.hotfix"))
            {
                return false;
            }
        }

        return true;
    }

    public static <T> T getScript(Class<T> type)
    {
        return (T) scriptMap.get(type);
    }

    public static void init(String packageName)
    {
        try {
            String packagePath = packageName.replace(".", "/");
            List<Class<?>> myClassNameList = new ArrayList<Class<?>>();

            ClassLoader loader = ScriptEngine.class.getClassLoader();
            Enumeration enumeration = loader.getResources(packagePath);

            while (enumeration.hasMoreElements())
            {
                URL url = (URL)enumeration.nextElement();
                String type = url.getProtocol();
                if (type.equals("file"))
                {
                    readFile(url.getPath(),true,myClassNameList,packageName);
                }
            }

            for (Class clazz : myClassNameList)
            {
                Class<?>[] superTypes = clazz.getInterfaces();
                if (superTypes != null && !Modifier.isAbstract(clazz.getModifiers()))
                {
                    scriptMap.put(superTypes[0],clazz.newInstance());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void readFile(String filePath,boolean isChild,List<Class<?>> myClassNameList,String packageName) throws ClassNotFoundException {
        File file = new File(filePath);
        File[] childFiles = file.listFiles();

        for (File childFile : childFiles)
        {
            if (childFile.isDirectory())
            {
                if (isChild)
                {
                    readFile(childFile.getPath(),true,myClassNameList,packageName);
                }
            }
            else
            {
                if (childFile.getName().endsWith(".class")) {
                    String name = childFile.getName().split(".class")[0];
                    if (name.contains("$"))
                    {
                        continue;
                    }
                    myClassNameList.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + name));
                }
            }
        }
    }


}
