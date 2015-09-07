package org.pp.collect;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.pi4.locutil.MACAddress;
import org.pi4.locutil.io.TraceGenerator;
import org.pi4.locutil.trace.Parser;
import org.pi4.locutil.trace.TraceEntry;

public class TraceParser {
	
	private List<TraceEntry> offlineTrace, onlineTrace;

	public TraceParser()
	{
		String offlinePath = "data/MU.1.5meters.offline.trace", onlinePath = "data/MU.1.5meters.online.trace";
		
		//Construct parsers
		File offlineFile = new File(offlinePath);
		Parser offlineParser = new Parser(offlineFile);
		
		File onlineFile = new File(onlinePath);
		Parser onlineParser = new Parser(onlineFile);
		
		//Construct trace generator
		try {
			int offlineSize = 25;
			int onlineSize = 5;
			TraceGenerator tg = new TraceGenerator(offlineParser, onlineParser,offlineSize,onlineSize);
			
			//Generate traces from parsed files
			tg.generate();
			
			//Iterate the trace generated from the offline file
			offlineTrace = tg.getOffline();	
			
			//Iterate the trace generated from the online file
			onlineTrace = tg.getOnline();			

			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<TraceEntry> getOfflineTraces(){
		return offlineTrace;
	}
	
	public List<TraceEntry> getOnlineTraces(){
		return onlineTrace;
	}
	
}
