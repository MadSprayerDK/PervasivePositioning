package org.pp.navigation;

import java.lang.reflect.Constructor;

public class NavigationElement {
	private String name;
	private Class<?> element;
	
	public NavigationElement(Class<?> classType, String name){
		this.name = name;
		this.element = classType;
	}
	
	public String getName(){
		return name;
	}
	public Class<?> getElement(){
		return element;
	}
}
