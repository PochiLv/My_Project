package com.pochi.utils;

public class DaoFactory {
	private static final DaoFactory fa=new DaoFactory();
	private DaoFactory(){}
	public static DaoFactory getDaoFactory(){
		return fa;
	}
	
	public <T> T getDao(String className,Class<T> clazz){
		try{
			T t=(T) Class.forName(className).newInstance();
			return t;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
