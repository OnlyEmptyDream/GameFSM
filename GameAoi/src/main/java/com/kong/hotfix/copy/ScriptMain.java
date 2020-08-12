package com.kong.hotfix.copy;

public class ScriptMain {
    public static String demoPath = "E:\\project\\oldServer\\GameFSM\\GameAoi\\src\\main\\resources\\";

    public static void main(String[] args) {
        ScriptEngine.init("com.kong.hotfix.impl");
        IHello helloScript = ScriptEngine.getScript(IHello.class);
        helloScript.getWord();


        demoPath = demoPath + "GameAoi.jar";
        ScriptEngine.init(demoPath, "com.kong.hotfix.impl");
        IHello helloScript2 = ScriptEngine.getScript(IHello.class);
        helloScript2.getWord();


    }
}
