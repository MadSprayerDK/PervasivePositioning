package pp.navigation;

import java.util.List;

import pp.collect.TraceParser;

public class NavigationController {

	private List<NavigationElement> objects;
	private final String standardInformation = "Select a program by choosen the programs corrisponding number:\n";
	private final String defaultErrorMessage = "No such element exists, try again! \n";
	
	
	public NavigationController(List<NavigationElement> navigationElements){
		this.objects = navigationElements;

		
	}
	
	public String returnDefaultWelcomingString(){
		StringBuilder builder = new StringBuilder();
		builder.append(standardInformation);
		for(int a = 0; a<objects.size(); a++){
			NavigationElement element = objects.get(a);
			builder.append(a + " " + element.getName());
			if(a != objects.size() - 1) builder.append("\n");
		}
		return builder.toString();
	}
	
	
	public NavigationElement retrieveNavigationElementByIndex(int a){
		if(a >= 0 && a < objects.size()){
			return objects.get(a);
		}
		return null;
	}
	
	public String errorMessage(){
		return defaultErrorMessage;
	}
	
}
