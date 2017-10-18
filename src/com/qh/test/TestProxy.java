package com.qh.test;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


interface ISend{
	public void send();
}

class Send implements ISend{

	@Override
	public void send() {
		System.out.println("�Ҷ���");
		
	}
	
}

public class TestProxy implements InvocationHandler {
	private Object target;
	
	public void eat() {
		System.out.println("���˾ͳ԰�");
	}
	public void clean() {
		System.out.println("�����˾���ʰһ�°�");
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	     this.eat();
	     Object resuleBack = null;
	     resuleBack = method.invoke(target, args);
	     this.clean();
		return resuleBack;
	}
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
		
	}
	
	public static void main(String[] args) {
		ISend tp = (ISend) new TestProxy().bind(new Send());
		tp.send();
		
	}

}
