package org.pp.empirical_fp_nn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.pp.FileController;
import org.pp.collect.TraceParser;

public class EmpiricalController {
	
	public EmpiricalController(TraceParser parser){
		FileController controller = new FileController("empirical", "positions.json");
		JSONArray array = convertStringToJSON(controller.returnInformationFromFile());
		try {
			createNewFileEntry("test", "test", array, controller);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createNewFileEntry(String truePosition, String estimatedPosition, JSONArray array, FileController controller) throws JSONException{
		JSONObject entry = new JSONObject();
		entry.put("truePosition", truePosition);
		entry.put("estimatedPosition", estimatedPosition);
		array.put(entry);
		controller.saveContentToFile(array.toString());
	}
	
	public JSONArray convertStringToJSON(String text){
		try {
			JSONArray array = new JSONArray(text);
			return array;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JSONArray();
	}
	
}
