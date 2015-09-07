package org.pp.empirical_fp_nn;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.pi4.locutil.GeoPosition;
import org.pi4.locutil.MACAddress;
import org.pi4.locutil.trace.SignalStrengthSamples;
import org.pi4.locutil.trace.TraceEntry;
import org.pp.collect.TraceParser;
import org.pp.file.FileController;
import org.pp.model.TraceEntryQueue;

public class EmpiricalController {
	
	
	public EmpiricalController(TraceParser parser){		
		FileController controller = new FileController("empirical", "positions.json");
		JSONArray array = new JSONArray();
		try {
			//Todo: Performance update
			//Change into sections
			for(TraceEntry onlineEntry: parser.getOnlineTraces()){
				SignalStrengthSamples samples = onlineEntry.getSignalStrengthSamples();
				GeoPosition estimatedPosition = findCalculatedGPSPosition(parser.getOfflineTraces(), samples);
				if(estimatedPosition != null)
				createNewFileEntry(onlineEntry.getGeoPosition().toString(), estimatedPosition.toString(), array, controller);
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private double closeSkalaOnActualPoint(Set<String> macSet, LinkedList<MACAddress> sortedOfflineMacs, SignalStrengthSamples offlineSignalStrengthSample, SignalStrengthSamples onlineSample){
		double totalProcentageDiff = 1;
		int count = 0;
		for(MACAddress mac: sortedOfflineMacs){
			if(macSet.contains(mac.toString())){
				count++;
				//All is leafs
				double currentSignalStrength = offlineSignalStrengthSample.getFirstSignalStrength(mac);
				//This command is a 1 and can therefore be computed everytime without losing performance
				double macsActualSignal = onlineSample.getFirstSignalStrength(mac);
				double procentageDifference = currentSignalStrength < macsActualSignal ? currentSignalStrength/ macsActualSignal : macsActualSignal / currentSignalStrength;
				totalProcentageDiff *= procentageDifference;
			}
		}
		if(count > 0 && count > macSet.size() -1)
		return totalProcentageDiff;
		else return -1;
	}
	
	private GeoPosition findCalculatedGPSPosition(List<TraceEntry> offlineEntries, SignalStrengthSamples onlineSamples){
		Set<String> macSet = retrieveEmptyHashMapWithKeys(onlineSamples);
		TraceEntryQueue<TraceEntry> entryWithSignals = new TraceEntryQueue<>(4);
		for(TraceEntry offlineEntry: offlineEntries){
			SignalStrengthSamples offlineSignalStrengthSample = offlineEntry.getSignalStrengthSamples();
			LinkedList<MACAddress> sortedOfflineMacs = offlineSignalStrengthSample.getSortedAccessPoints();
			//If neither the strongest nor the second-strongest of the offline data is equal to the strongest signal in the online data, the chance of those two traces true position being close is very small 
			double totalProcentageDiff = closeSkalaOnActualPoint(macSet, sortedOfflineMacs, offlineSignalStrengthSample, onlineSamples);
			if(totalProcentageDiff >= 1)
			entryWithSignals.enqueue(offlineEntry, totalProcentageDiff);
		}
		
		int count = 0, totalX =0, totalY = 0;
		Iterator<TraceEntry> iterator = entryWithSignals.getIterator();
		while(iterator.hasNext()){
			TraceEntry sample = iterator.next();
				count++;
				totalX += sample.getGeoPosition().getX();
				totalY += sample.getGeoPosition().getY();
		}
		if(count != 0){
		GeoPosition estimatedPosition = new GeoPosition(totalX/count, totalY / count, 0);
		return estimatedPosition;
		}
		return null;
	}
	
	private Set<String> retrieveEmptyHashMapWithKeys(SignalStrengthSamples onlineSamples){
		LinkedList<MACAddress> onlineOrderedSamples = onlineSamples.getSortedAccessPoints();
		double acceptedNoise = recieveAcceptedNoiseBasedOnNumberOfSamples(onlineOrderedSamples.size());
		double acceptedSignalStrength = recieveAcceptedSignalStrengthBasedOnNumberOfSamples(onlineOrderedSamples.size());
		HashSet<String> closestEntry = new HashSet<>();
		for(MACAddress mac: onlineSamples.getSortedAccessPoints()){
			if(onlineSamples.getFirstNoiseValue(mac) < acceptedNoise && onlineSamples.getFirstSignalStrength(mac) < acceptedSignalStrength){
				closestEntry.add(mac.toString());
				if(closestEntry.size() >= 6) break;
			}
		}
		return closestEntry;
	}
	
	//Has to be calculated based on data evaluation
	private double recieveAcceptedNoiseBasedOnNumberOfSamples(int numberOfSamples){
		return -10.0;
	}
	
	//Has to be calculated based on data evaluation
	private double recieveAcceptedSignalStrengthBasedOnNumberOfSamples(int numberOfSamples){
		return -10.0;
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
