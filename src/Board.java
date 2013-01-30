/* Suduko Solver 
 * Recursive solution to Suduko Puzzles on 9x9 grids (with 3x3 subgrids)
 * by Liam M
 * liam345@gmail.com
 * 
 * design notes - this program uses recursion to brute-force sudoku puzzles
 * 
 * limitations - input must be placed in 'input.txt' in same directory
 * 			   - input must be 9x9 with blanks or zeroes indicating empty spaces	
 */               

public class Board {
	private final int MAX_ROW = 9;   
	private final int MAX_COL = 9;
	
	private int [][] gameBoard;
	
	
	/*constructor for board class.
	 * is passed a two dimensional int array for the gameboard. */
	public Board(int[][] data){
		gameBoard = new int[MAX_ROW][MAX_COL];
		for(int y = 0; y < MAX_ROW; y++){
			for(int x = 0; x < MAX_COL; x++){
				gameBoard[y][x] = data[y][x];
			}
		}
	}
	
	/*getter and setter for individual num*/

	public int getNumber(int y, int x){
		return gameBoard[y][x];
	}
	
	public void setNumber(int y, int x, int value){
		gameBoard[y][x] = value;
	}
	
	
	/* isLegal function 
	 * passed x, y coords and a value to place
	 * checks to see if the value being placed is legal according to sudoku rules
	 * (no duplicate in col, row, or 3x3 grid) */
	
	public boolean isLegal(int y, int x, int value) {
		for (int i = 0; i < MAX_COL; i++){      // check valid in row and column
			if (gameBoard[y][i] == value || gameBoard[i][x] == value)
				return false;
		}
			
		int offsetX = (x / 3) * 3;              //gets the starting coordinates of 3x3 grid value being placed is in  
		int offsetY = (y / 3) * 3;
		
		for (int i = 0; i < 3; i++){           // check if valid in 3x3
			for (int j = 0; j < 3; j++){
				if (gameBoard[offsetY+i][offsetX+j] == value)
					return false;
			}
		}
		return true;                            // else, the value is legal 
	}
	
	
	/* recursive function to find a valid number for a cell */
	
	public void solvePuzzle(int y, int x)throws Exception{
		if(y > 8)								/*base cases*/
			throw new Exception("Solution Found");		 //1.puzzle is solved													
											
		
		if(getNumber(y,x) != 0)                     //2.current cell is filled
			next(y,x);
				                                        
		else{										/*general case*/
			for(int i = 1; i < 10; i++){            //iterate through possible move list
				if(isLegal(y,x,i)){
					setNumber(y,x,i);               //set a legal move 
					next(y,x);                      //recurse and try to find solution
				}
			}
		}
		setNumber(y,x,0);                           //no solution found, reset squares to 0 on way out
	}
	
	private void next(int y, int x) throws Exception{
		if(x < 8 )								  //find next empty square
			solvePuzzle(y, x + 1); 
		else		
		   	solvePuzzle(y + 1, 0);     
	}
	
	/* returns the gameboard in string format */
	public String toString() {
		String Puzzle = "";
		for(int y = 0; y < MAX_COL; y++){
			for(int x = 0; x < MAX_ROW; x++){
				int c = getNumber(y,x);
				char toWrite = (char)(c + '0');
				Puzzle += toWrite;
			}
			Puzzle += "\n";
		}
		return Puzzle;
	}
}

	
	
