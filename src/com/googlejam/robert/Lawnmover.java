package com.googlejam.robert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lawnmover {
	public static void main(String[] args) {
		List<String> inputString = getInputStrings("C:\\Users\\Robert\\codejam\\B-large.in");
		int iterations = Integer.parseInt(inputString.get(0));
		inputString.remove(0);
		List<Object> objectList = LawnmowerParser(inputString, iterations);
		int counter = 1;
		for (Object i: objectList){
			String output="";
			output+="Case #" + counter + ": ";			
			String possible = "NO";
			if(isLawnmowed((List<List<Integer>>)i)){
				possible = "YES";
			}
			output+=possible;
			counter++;

			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Robert\\codejam\\B-large.out", true)));
			    out.println(output);
			    out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(output);
		
		}
	}
	
	
	private static boolean isLawnmowed(List<List<Integer>> lawn) {
		boolean rows = checkRows(lawn);
		//boolean columns = checkRows(rotate(lawn));
		return rows;//&&columns;
	}


	private static List<List<Integer>> rotate(List<List<Integer>> lawn) {
		List<List<Integer>> rotated = new ArrayList<List<Integer>>();
		for(int i = 0; i<lawn.get(0).size(); i++){
			List<Integer> tempRow = new ArrayList<Integer>();
			for(int j = 0; j<lawn.size(); j++){
				tempRow.add(lawn.get(j).get(i));
			}
			rotated.add(tempRow);
		}
		return rotated;
	}


	private static boolean checkRows(List<List<Integer>> lawn) {
		int temp1 = 0;
		for(int i = 0; i<lawn.size(); i++){
			int biggest = getBiggest(lawn.get(i));
			for(int j = 0; j<lawn.get(i).size(); j++){
				if(lawn.get(i).get(j)<biggest && !allSame(lawn.get(i))){
					List<Integer> temp = new ArrayList<Integer>();
					for(int y = 0; y<lawn.size(); y++){
						temp.add(lawn.get(y).get(j));
					}
					int num = lawn.get(i).get(j);
					if(getBiggest(temp)!=lawn.get(i).get(j) && !allSame(temp)){
						return false;
					}
				}
			}
		}
		return true;
	}


	private static boolean allSame(List<Integer> temp) {
		int tempNum = temp.get(0);
		for(int i: temp){
			if(i!=tempNum){
				return false;
			}
		}
		return true;
	}


	private static int getBiggest(List<Integer> list) {
		int biggest = -1;
		for(int i: list){
			if (i>biggest){
				biggest = i;
			}
		}
		return biggest;
	}


	private static List<Object> LawnmowerParser(List<String> inputString,
			int iterations) {
		List<Object> lawnList = new ArrayList<Object>();
		for(int i = 0; i<iterations; i++){
			String[] dimensions = inputString.get(0).split(" ");
			int height = Integer.parseInt(dimensions[0]);
			int width = Integer.parseInt(dimensions[1]);
			inputString.remove(0);
			List<List<Integer>> lawn= new ArrayList<List<Integer>>();
			for(int a = 0; a<height; a++){
				String[] lawnRowArray = inputString.get(0).split(" ");
				inputString.remove(0);
				List<String> lawnRow = Arrays.asList(lawnRowArray);
				List<Integer> lawnRowInt = new ArrayList<Integer>();
				for(String r: lawnRow){
					lawnRowInt.add(Integer.parseInt(r));
				}
				lawn.add(lawnRowInt);
			}
			lawnList.add(lawn);
		}
		return lawnList;
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
