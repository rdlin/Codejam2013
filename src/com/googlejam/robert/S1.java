package com.googlejam.robert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class S1 {
	public static void main(String[] args) {
		List<String> inputString = getInputStrings("C:\\Users\\Robert\\codejam\\A-small-attempt3.in");
		int iterations = Integer.parseInt(inputString.get(0));
		inputString.remove(0);
		List<Object> objectList = S1Parser(inputString, iterations);
		int counter = 1;
		for (Object i: objectList){
			String output="";
			output+="Case #" + counter + ": ";	
			output+=S1Result(i);
			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Robert\\codejam\\A-small-attempt3.out", true)));
			    out.println(output);
			    out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(output);
			counter++;
		}
		
	}
	private static List<Object> S1Parser(List<String> inputString,
			int iterations) {
		List<Object> list = new ArrayList<Object>();
		boolean second = false;
		String line = "";
		for(String i: inputString){			
			if(!second){
				line+=i.split(" ")[0];
				second = true;
			}
			else{
				line+=" "+i;
				list.add(line);
				line="";
				second = false;
			}
		}
		return list;
	}
	private static String S1Result(Object i) {
		String input = (String)i;
		List<Integer> list = new ArrayList<Integer>();
		for(String a: input.split(" ")){
			list.add(Integer.parseInt(a));
		}
		int armin = list.get(0);
		list.remove(0);
		Collections.sort(list);
		int movecount = 0;
		List<Integer> problemIndex = findProblems(list, armin);
		boolean notDone = true;
		int index = 0;
		boolean allOne = true;
		/*for(int a: list){
			if(a!=1){
				allOne = false;
			}
		}
		if(allOne){
			
		}*/
		if(armin==1){
			return String.valueOf(list.size());
		}
		return String.valueOf(solve(armin, list, problemIndex,movecount, true));
	}
	
	
	
	private static int solve(int armin, List<Integer> list,
			List<Integer> problemIndex, int movecount, boolean adding) {
		problemIndex= findProblems(list, armin);
		if(list.size()==0 || problemIndex.size()==0){
			if(!adding){
				movecount=movecount+1;
			}
			return movecount;
		}
		else if (findProblemNumber(list, armin)==0){
			if(!adding){
				movecount=movecount+1;
			}
			return movecount;
		}
		else{
			List<Integer>addList = new ArrayList<Integer>();
			List<Integer>removeList = new ArrayList<Integer>();
			for(int i: list){
				removeList.add(i);
			}
			addList = addBiggest(list, problemIndex.get(0), armin);
			problemIndex= findProblems(addList, armin);
			int addListNum = solve(armin, addList,
					problemIndex, movecount+1, true);
			problemIndex= findProblems(removeList, armin);
			removeList.remove((int)problemIndex.get(0));
			int removeListNum = solve(armin, removeList,
					problemIndex, movecount+1, false);
			if(addListNum>removeListNum)
				return removeListNum;
			else
				return addListNum;
			
		}		
	}
	private static List<Integer> addBiggest(List<Integer> list, Integer index, Integer armin) {
		List<Integer>newList = new ArrayList<Integer>();
		for(int i: list){
			newList.add(i);
		}
		int sum = armin;
		for(int i=0;i<index;i++){
			sum+=list.get(i);
		}
		newList.add(index,sum-1);
		Collections.sort(list);
		return newList;
	}
	
	private static List<Integer> findProblems(List<Integer> list, int armin) {
		List<Integer> problemIndex = new ArrayList<Integer>();
		int counter = 0;
		int previous = armin;
		for(int a: list){
			if(previous<=a){
				problemIndex.add(counter);
			}
			previous+=a;
			counter++;
		}
		return problemIndex;
	}
	private static int findProblemNumber (List<Integer> list, int armin) {
		List<Integer> problemIndex = new ArrayList<Integer>();
		int counter = 0;
		int previous = armin;
		for(int a: list){
			if(previous>a){
				problemIndex.add(counter);
			}
			previous+=a;
			counter++;
		}
		return problemIndex.size();
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
