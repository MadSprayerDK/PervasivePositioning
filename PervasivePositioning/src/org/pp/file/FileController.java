package org.pp.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileController {
	private final String defaultBase = "data/";
	private String directoryName, filename;
	private File createdFile;
	
	public FileController(String directoryName, String filename){
		this.directoryName = directoryName;
		this.filename = filename;
		createdFile = create();
		
	}
	
	public void saveContentToFile(String content){
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(createdFile, false)));
			out.println(content);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File create(){
		boolean folderCreated = ensureFolderIsCreated(directoryName);
		if(folderCreated){
		String completePath = defaultBase + directoryName + "/" + filename;
		System.out.println(completePath);
		File file = new File(completePath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
		}
		return null;
	}
	
	private boolean ensureFolderIsCreated(String directoryName){
		File file = new File("data/" + directoryName);
		if (!file.exists()) {
			return file.mkdir();
		}
		return true;
	}
	
	public String returnInformationFromFile(){
		BufferedReader br = null;
	    try {
	    	 br = new BufferedReader(new FileReader(createdFile));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
	        	if(br != null)
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return null;
	}
}
