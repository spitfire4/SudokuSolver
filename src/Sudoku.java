/* Suduko Solver 
 * Recursive solution to Suduko Puzzles on 9x9 grids (with 3x3 subgrids)
 * 
 * written by liam m - email liam345@gmail.com
 * design notes - this program uses recursion to brute-force sudoku puzzles
 * 
 * limitations - input must be placed in 'input.txt' in same directory
 * 			   - input must be 9x9 with blanks or zeroes indicating empty spaces	
 */               

public class Sudoku {
	public static void main(String[] args) {
		int [][] data = Io.openFile();
		
		Board game = new Board(data);
		
		try	{ game.solvePuzzle(0,0); }
		
		catch (Exception victory){
			System.out.println("Success, A solution has been found - results in output.txt");
			String solution = game.toString();
			System.out.println(solution);
			Io.writeString(solution);
			System.exit(0);
		}
		System.out.println("Sorry, No Solution.");
	}
}
