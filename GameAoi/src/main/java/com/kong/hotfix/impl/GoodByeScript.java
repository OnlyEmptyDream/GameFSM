package com.kong.hotfix.impl;

import com.kong.hotfix.copy.IGoodbye;
import com.kong.hotfix.copy.IHello;
import com.kong.hotfix.copy.IScript;

public class GoodByeScript implements IGoodbye {
    @Override
    public void getWord() {
        System.out.println("goodbye world");
    }
}
