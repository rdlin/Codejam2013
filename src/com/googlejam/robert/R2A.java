package com.googlejam.robert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class R2A {
	public static void main(String[] args) {
		List<String> inputString = getInputStrings("C:\\Users\\Robert\\codejam\\B-small-attempt0.in");
		int iterations = Integer.parseInt(inputString.get(0));
		inputString.remove(0);
		List<Object> objectList = R2AParser(inputString, iterations);
		int counter = 1;
		for (Object i: objectList){
			String output="";
			output+="Case #" + counter + ": ";	
			output+=R2AResult(i);
			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Robert\\codejam\\B-small-attempt0.out", true)));
			    out.println(output);
			    out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(output);
			counter++;
		}
		
	}
	private static String R2AResult(Object i) {
		List<Integer> numberList = (List<Integer>)i;
		int max = numberList.get(0);
		int regen = numberList.get(1);
		numberList.remove(0);
		numberList.remove(0);
		//System.out.println(numberList);
		List<Integer> times = new ArrayList<Integer>();
		List<Integer> biggestNumber = new ArrayList<Integer>();
		for(int k = 0; k<numberList.size(); k++){
			int num = 1;
			for (int j = 0; j<numberList.size(); j++){
				if(j!=k){
					if (numberList.get(j)> numberList.get(k)){
						num++;
					}
				}
			}
			biggestNumber.add(num);
		}
		List<Integer> minimumBalance = new ArrayList<Integer>();
		for(int k: biggestNumber){
			minimumBalance.add(-1);
		}
		Object[] minBalArray = minimumBalance.toArray();
		if(numberList.size()!=1){
			for(int k = 1; k!=numberList.size()+1; k++){
				for(int j = 0; j<numberList.size(); j++){
					int tempMax = max-regen;
					if(biggestNumber.get(j)==k && j != 0){
						for (int l = j-1; l!=-1; l--){
							if((int)(minBalArray[l])==-1){
								minBalArray[l]=tempMax;
								tempMax = tempMax-regen;
								if(tempMax < 0){
									break;
								}
							}
							else{
								break;
							}
						}
					}
				}
			}
		}
		int counter = 0;
		int currentEnergy=max;
		int gained = 0;
		for(int k: numberList){
			if((int)(minBalArray[counter])==-1){
				minBalArray[counter]=0;
			}
			if(counter == minBalArray.length){
				minBalArray[counter]=0;
			}
			int hours = currentEnergy - (int)minBalArray[counter];
			currentEnergy = currentEnergy-hours;
			gained+=hours*k;
			currentEnergy+=regen;
			counter++;
		}
		return String.valueOf(gained);
	}
	private static List<Object> R2AParser(List<String> inputString,
			int iterations) {
		List<Object> objects = new ArrayList<Object>();
		boolean putIn = false;
		List<Integer> info = new ArrayList<Integer>();
		for (String i: inputString){
			if(putIn == false){
				String[]test = i.split(" ");
				for(String x: test){
					info.add(Integer.parseInt(x));
				}
				info.remove(2);
				putIn = true;
				continue;
			}
			else{
				String[]test = i.split(" ");
				for(String x: test){
					info.add(Integer.parseInt(x));
				}
				//System.out.println(info);
				List<Integer> infoTemp = new ArrayList<Integer>(info);
				objects.add(infoTemp);
				info.clear();
				putIn = false;
				continue;
			}
		}
		return objects;
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
