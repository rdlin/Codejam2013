package com.googlejam.robert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class R1A {
	public static void main(String[] args) {
		List<String> inputString = getInputStrings("C:\\Users\\Robert\\codejam\\A-large.in");
		int iterations = Integer.parseInt(inputString.get(0));
		inputString.remove(0);
		List<Object> objectList = R1AParser(inputString, iterations);
		int counter = 1;
		for (Object i: objectList){
			String output="";
			output+="Case #" + counter + ": ";	
			output+=R1AResult(i);
			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Robert\\codejam\\A-large.out", true)));
			    out.println(output);
			    out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(output);
			counter++;
		}
		
	}
	private static String R1AResult(Object i) {
		long radius = ((long[])i)[0];
		long paint = ((long[])i)[1];
		//System.out.println("Radius:" + radius);
		//System.out.println("Paint:" + paint);
		long blackRadius = 0L;
		int times = 0;
		long whiteRadius = radius;
		boolean finished = false;
		blackRadius = radius + 1L;
		while(!finished){		
			long paintArea = blackRadius*blackRadius - whiteRadius*whiteRadius;
			//System.out.println("PaintArea:" + paintArea);
			long tempPaint = paint - paintArea;
			//System.out.println("tempPaint:" + paintArea);
			if(paintArea > paint){
				//System.out.println("quit");
				return String.valueOf(times);
			}
			else{
				times++;
				paint = tempPaint;
				//System.out.println("Paint:" + paint);
				whiteRadius = whiteRadius + 2L;
				blackRadius = blackRadius + 2L;;
			}
		}
		return String.valueOf(times);
	}
	private static List<Object> R1AParser(List<String> inputString,
			int iterations) {
		List<Object> circles = new ArrayList<Object>();
		for (String i:inputString){
			String[] temp = i.split(" ");
			long[] circleInfo = {Long.valueOf(temp[0]),(Long.valueOf(temp[1]))};
			circles.add(circleInfo);
		}
		return circles;
	}
	public static List<String> getInputStrings(String filename){
			
			List<String> inputString = new ArrayList<String>();
			BufferedReader br = null;
			 
			try { 
				String sCurrentLine;
		 
				br = new BufferedReader(new FileReader(filename));
		 
				while ((sCurrentLine = br.readLine()) != null) {
					//System.out.println(sCurrentLine);
					inputString.add(sCurrentLine);
				}
		 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return inputString;
	}
}
