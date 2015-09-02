package pp.navigation;

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
	public Object getElement(){
		try {
			return element.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
