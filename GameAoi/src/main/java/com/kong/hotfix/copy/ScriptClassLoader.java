package com.kong.hotfix.copy;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Peanut
 * @date 2019/5/14 17:50
 */
public class ScriptClassLoader extends URLClassLoader {

    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("ScriptClassLoader.class");
    private String classPackage;
    private ClassLoader defaultClassLoader;
    private boolean dev;

    public static ScriptClassLoader newInstance(URL url, String classPackage, boolean dev) {
        URL[] urls;
        if (dev) {
            urls = ((URLClassLoader)ScriptClassLoader.class.getClassLoader()).getURLs();
        } else {
            urls = new URL[]{url};
        }

        return new ScriptClassLoader(urls, classPackage, dev);
    }

    protected ScriptClassLoader(URL[] urls, String classPackage, boolean dev) {
        super(urls);
        this.classPackage = classPackage;
        this.dev = dev;
        this.defaultClassLoader = ScriptClassLoader.class.getClassLoader();
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (!this.dev && name.startsWith(this.classPackage)) {
            Class<?> c = this.findLoadedClass(name);
            if (c == null) {
                c = this.findClass(name);
                logger.info("加载类[{}]完毕." + name);
            } else {
                logger.info("类[{}]已加载，无需再次加载." + name);
            }

            if (resolve) {
                this.resolveClass(c);
            }

            return c;
        } else {
            return super.loadClass(name, resolve);
        }
    }
}
