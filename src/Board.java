public class Board {
		
		public Character[][] rows;
		
		private static Character[][] blankGrid(){
			Character [][] grid = new Character [3][3];
			for(int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					grid[i][j] = '-';
			return grid;
		}
		
		public Board(Character [][] rows) {
			this.rows = rows;
		}
				
		public Board() {
			 this.rows = blankGrid();
		}
		
		public boolean isEmpty(int [] pos) {
			return (getMark(pos) == '-');
		}
		
		public Character getMark(int [] pos) {
			return rows[pos[0]][pos[1]];
		}
		
		public void setMark(int [] pos, Character mark) {
			if(!isEmpty(pos))
				throw new IllegalArgumentException("Non-empty cell, mark is already there!");
			int row = pos[0], col = pos[1];
			rows[row][col] = mark;
		}
		
		private Character [][] rowsClone(){
			Character [][] newRows = new Character[3][3];
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					newRows[i][j] = rows[i][j];
			return newRows;
		}
		
		public Board deepClone() {
			Character [][] rows = rowsClone();
			Board board = new Board(rows);
			return board;
		}
		
	    public Character checkRowWinner(){
	       for(int i = 0; i < 3; i++){
	         if(rows[i][0] == rows[i][1] && rows[i][1] == rows[i][2] && !isEmpty(new int [] {i, 0}))
	        	 return rows[i][0];
	       }
	        return '-';
	    }

	    public Character checkColWinner(){
	       for(int i = 0; i < 3; i++){
	         if(rows[0][i] == rows[1][i] && rows[1][i] == rows[2][i] && !isEmpty(new int [] {0, i}))
	        	 return rows[0][i];
	        }
	        return '-';
	    }

	   public Character checkDiagWinner(){
	       boolean leftDiagWinner = (rows[1][1] == rows[0][0] && rows[1][1] == rows[2][2]);
	       boolean rightDiagWinner = (rows[1][1] == rows[0][2] && rows[1][1] == rows[2][0]);
	       int [] centerPos = new int[] {1, 1};
	       
	       if (!isEmpty(centerPos)) {
	         if (leftDiagWinner || rightDiagWinner)
	           return rows[1][1];   // the winner's mark
	       }
	       return '-';
	   }

	   public Character winner(){
	       Character rowWinner = checkRowWinner(), colWinner = checkColWinner(), diagWinner = checkDiagWinner();
	       if (rowWinner != '-')
	         return rowWinner;
	       else if (colWinner != '-')
	         return colWinner;
	       else if (diagWinner != '-')
	         return diagWinner;
	       
	       return '-';
	   }
	   
	   public boolean isTied(){
	       if (isWon()) 
	        return false;
	       
	       // no empty cell
	       for(int i = 0; i < 3; i++){
	           for(int j = 0; j < 3; j++){ 
	               if (isEmpty(new int[] {i, j}))
	            	   return false;
	           }
	       }
	       return true;
	   }

	   public boolean isWon(){
	       return !(winner() == '-');
	   }

	   public boolean isOver(){
	      return (isWon() || isTied());
	   }
	
//	public static void main(String[] args) {
//		Board brd = new Board();
//		int [] pos1 = {0, 1};
//		System.out.println(brd.isEmpty(0, 1));
//		System.out.println(brd.getMark(0, 1));
//		brd.setMark(0, 1, 'X');
//		brd.setMark(0, 0, 'o');
//		brd.setMark(0, 2, 'X');
//		
//		brd.setMark(1, 1, 'o');
//		brd.setMark(1, 0, 'x');
//		brd.setMark(1, 2, 'o');
//		brd.setMark(2, 1, 'X');
//		brd.setMark(2, 0, 'o');
//		brd.setMark(2, 2, 'X');
//		System.out.println(brd.isOver());
//		System.out.println(brd.winner());
//	}

}
