package com.googlejam.robert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FairAndSquare {
	public static void main(String[] args) {
		List<String> inputString = getInputStrings("C:\\Users\\Robert\\codejam\\C-large-1.in");
		System.out.println(inputString);
		int iterations = Integer.parseInt(inputString.get(0));
		inputString.remove(0);
		List<Object> objectList = fairAndSquareParser(inputString, iterations);
		System.out.println(isPalindrome("4"));
		int counter = 1;
		for (Object i: objectList){
			String output="";
			output+="Case #" + counter + ": ";
			int numberOfFairAndSquare = 0;
			String[] range = (String[])i;
			BigDecimal lower = new BigDecimal(range[0]);
			//System.out.println(lower);
			BigDecimal upper = new BigDecimal(range[1]);
			upper = upper.add(new BigDecimal(1));
			//System.out.println(upper);
			while(lower.compareTo(upper)!=0){
				//System.out.println(lower+":");
				if(isPalindrome(parseBigDecimal(lower)) && isSquare(parseBigDecimal(getSquare(lower))) && isPalindrome(parseBigDecimal(getSquare(lower)))){
					numberOfFairAndSquare++;
				}
				lower = lower.add(new BigDecimal(1));
				
			}
			output+=numberOfFairAndSquare;
			System.out.println(output);
			counter++;
			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Robert\\codejam\\C-large-1.out", true)));
			    out.println(output);
			    out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static boolean isSquare(String decimalString) {
		boolean decimal=false;;
		for (int i = 0; i < decimalString.length(); i++){
			if(decimalString.substring(i, i+1)=="."){
				decimal = true;
			}
			if(decimal && decimalString.substring(i, 1)!="0"){
				return false;
			}
		}
		return true;
	}

	private static String parseBigDecimal(BigDecimal num) {
		String numString = num.toString();
		/*int lengthBefore = numString.length();
		for (int i = 0; i < lengthBefore; i++){
			if(numString.substring(numString.length()-i-1, 1)=="0"){
				numString=numString.substring(0,numString.length()-1);
			}
		}*/
		//return numString;
		return numString.replaceAll("\\.0*$", "");
	}

	private static List<Object> fairAndSquareParser(List<String> inputString,
			int iterations) {
		List<Object> ranges = new ArrayList<Object>();
		for(int i = 0; i < iterations; i++){
			String[] range=inputString.get(i).split(" ");
			ranges.add(range);
		}
		return ranges;
	}
	
	private static boolean isPalindrome(String s){
		//System.out.println("-"+s+"-");
		int n = s.length();
		for (int i=0;i<(n / 2) + 1;++i) {
			if (s.charAt(i) != s.charAt(n - i - 1)) {
				//System.out.println("Palindrome:"+false);
				return false;
		    }
		}
		//System.out.println("Palindrome:"+true);
		return true;
	}
	
	private static BigDecimal getSquare(BigDecimal num){
		BigDecimal g = BigDecimal.valueOf(1);
		BigDecimal n = num;		
		g = ((n.divide(g, 10, BigDecimal.ROUND_HALF_UP).add(g))).divide(BigDecimal.valueOf(2), 10, BigDecimal.ROUND_HALF_UP);
		BigDecimal f = g;
		//System.out.println(g);
		for(int i = 0; i < 100; i++){
			f = g;
			g = ((n.divide(g, 10, BigDecimal.ROUND_HALF_UP).add(g))).divide(BigDecimal.valueOf(2), 10, BigDecimal.ROUND_HALF_UP);
			if(f==g){
				return f;
			}
		}
		
		return g;
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
