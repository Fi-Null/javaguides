package com.nivelle.guide.java2e.proxy;

public class DynamicProxy {

    public static void main(String args[]){
        MyProxy myproxy = new MyProxy();
        HoseeDynamicimpl dynamicimpl = new HoseeDynamicimpl();
        HoseeDynamic proxy = (HoseeDynamic)myproxy.bind(dynamicimpl);
        System.out.println(proxy.sayhi());
    }
}
