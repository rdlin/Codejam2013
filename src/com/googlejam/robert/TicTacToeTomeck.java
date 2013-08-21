package com.googlejam.robert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeTomeck {
	
	public static void main(String[] args) {
		List<String> inputString = getInputStrings("C:\\Users\\Robert\\codejam\\A-large.in");
		int iterations = Integer.parseInt(inputString.get(0));
		List<Object> objectList = ticTacToeTomeckParser(inputString, iterations);
		int counter = 1;
		for (Object i: objectList){
			String output="";
			output+="Case #" + counter + ": ";
			char[][] grid = (char[][])i;
			boolean winX = checkForWin('X', grid);
			boolean winO = checkForWin('O', grid);
			boolean hasEmpty = checkForEmpty(grid);
			if(winX){
				output+="X won";	
			}
			else if(winO){
				output+="O won";
			}
			else if(!winX && !winO && hasEmpty){
				output+="Game has not completed";
			}
			else{
				output+="Draw";
			}
			System.out.println(output);
			counter++;

			try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Robert\\codejam\\A-large.out", true)));
			    out.println(output);
			    out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
		//System.out.println("done");	
	}
	
	private static boolean checkForEmpty(char[][] grid) {
		for(int a = 0; a<grid.length; a++){
			for(int b = 0; b<grid.length; b++){
				if(grid[a][b] == '.')
					return true;
			}
		}
		return false;
	}

	private static boolean checkForWin(char winChar, char[][] grid) {
		for(int a = 0; a<grid.length; a++){
			if(winning(grid[0][a], winChar) && winning(grid[1][a], winChar)
					&& winning(grid[2][a], winChar) && winning(grid[3][a], winChar)){
				return true;
			}
		}
		for(int a = 0; a<grid.length; a++){
			if(winning(grid[a][0], winChar) && winning(grid[a][1], winChar)
					&& winning(grid[a][2], winChar) && winning(grid[a][3], winChar)){
				return true;
			}
		}
		//diagonal left to right
		if(winning(grid[0][0], winChar) && winning(grid[1][1], winChar)
				&& winning(grid[2][2], winChar) && winning(grid[3][3], winChar)){
			return true;
		}
		//diagonal right to left
		if(winning(grid[0][3], winChar) && winning(grid[1][2], winChar)
				&& winning(grid[2][1], winChar) && winning(grid[3][0], winChar)){
			return true;
		}
		return false;

	}
	
	private static boolean winning(char c, char winChar) {
		if(c==winChar || c=='T'){
			return true;
		}
		return false;
	}

	private static List<Object> ticTacToeTomeckParser(List<String> inputString,
			int iterations) {
		List<Object> objectList = new ArrayList<Object>();
		for(int i = 0; i<iterations; i++){
			//remove blank line
			inputString.remove(0);
			char[][] grid = new char[4][4];
			
			String[] rowOne = inputString.get(0).split("(?!^)");
			for (int a = 0; a < 4; a++){
				grid[0][a] = rowOne[a].charAt(0);
			}
			inputString.remove(0);
			
			String[] rowTwo = inputString.get(0).split("(?!^)");
			for (int a = 0; a < 4; a++){
				grid[1][a] = rowTwo[a].charAt(0);
			}
			inputString.remove(0);
			
			String[] rowThree = inputString.get(0).split("(?!^)");
			for (int a = 0; a < 4; a++){
				grid[2][a] = rowThree[a].charAt(0);
			}
			inputString.remove(0);
			
			String[] rowFour = inputString.get(0).split("(?!^)");
			for (int a = 0; a < 4; a++){
				grid[3][a] = rowFour[a].charAt(0);
			}
			inputString.remove(0);
			
			objectList.add(grid);
		}
		return objectList;
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
