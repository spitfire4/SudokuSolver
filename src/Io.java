/* Suduko Solver 
 * Recursive solution to Suduko Puzzles on 9x9 grids (with 3x3 subgrids)
 * by Liam MacLeod
 * lmacl649@mtroyal.ca
 * 
 * design notes - this program uses recursion to brute-force sudoku puzzles
 * 
 * limitations - input must be placed in 'input.txt' in same directory
 * 			   - input must be 9x9 with blanks or zeroes indicating empty spaces	
 */               


/* io class
 * static class for handling file input/output, also a parse input function which prepares an int[][] array for the 
 * gameboard. */

import java.io.*;
import java.util.*;


public class Io{
	
	private static final int MAX_ROW = 9;   
	private static final int MAX_COL = 9;
	
	/*attempts to open a file, meaningful error messages displayed otherwise*/
	public static int[][] openFile(){
		int[][] data = new int[MAX_ROW][MAX_COL];
		try{
			Scanner in = new Scanner(new File("input.txt"));
			return parseInput(in);
		}
		catch(FileNotFoundException e){
			System.out.println("Error: File Not Found");
			System.exit(0);
		}
		catch(Exception f){
			System.out.println(f);
			System.exit(0);
		}
		
		return data;
	}
	
	
	/*parses input one string at a time until 9x9 int array is complete 
	 * also ensures input is valid from 0-9 (or a blank) and each line is immediately followed by newline
	 */
	
	private static int[][] parseInput(Scanner in)throws Exception{
			int[][] data = new int[MAX_ROW][MAX_COL];
			String [] rows = new String[MAX_ROW];
			for(int y = 0; y < MAX_ROW; y++){
					rows[y] = in.nextLine();
					for(int x = 0; x < MAX_COL; x++){
						char numberIn = rows[y].charAt(x);
						if(numberIn  == ' ')
							numberIn = '0';
						if(numberIn - '0' < 0 || numberIn - '0' > 9 || rows[y].length() > 10)
								throw new Exception("Error with Input File");
						data[y][x] = numberIn - '0';
					}
			}
			return data;
	}
	
	
	/* writes a string to an output file "output.txt" */
	
	public static void writeString(String toWrite){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
			out.write(toWrite);
			out.close();
		}
		catch (IOException e){}
	}
}
