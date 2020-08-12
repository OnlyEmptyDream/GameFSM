package com.kong.hotfix.impl;

import com.kong.hotfix.copy.IHello;
import com.kong.hotfix.copy.IScript;

public class HelloScript implements IHello {
    @Override
    public void getWord() {
        System.out.println("hello world now 42141");
    }
}
