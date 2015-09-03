package org.pp.test;

import org.pi4.locutil.trace.TraceEntry;
import org.pp.collect.TraceParser;

public class TestElement{

	public TestElement(TraceParser parser){
		for(TraceEntry entry: parser.getOnlineTraces()) {
			//Print out coordinates for the collection point and the number of signal strength samples
			System.out.println(entry.getGeoPosition().toString() + " - " + entry.getSignalStrengthSamples().size());
		}
		for(TraceEntry entry: parser.getOfflineTraces()) {
			//Print out coordinates for the collection point and the number of signal strength samples
			System.out.println(entry.getGeoPosition().toString() + " - " + entry.getSignalStrengthSamples().size());				
		}
	}
	
}
