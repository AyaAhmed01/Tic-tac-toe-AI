public class Board {
		
		Character[][] rows;
		
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
		
		boolean isEmpty(int [] pos) {
			return (getMark(pos) == '-');
		}
		
		Character getMark(int [] pos) {
			return rows[pos[0]][pos[1]];
		}
		
		void setMark(int [] pos, Character mark) {
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
		
		Board deepClone() {
			Character [][] rows = rowsClone();
			Board board = new Board(rows);
			return board;
		}
		
	    private Character checkRowWinner(){
	       for(int i = 0; i < 3; i++){
	         if(rows[i][0].equals(rows[i][1]) && rows[i][1].equals(rows[i][2]) && !isEmpty(new int [] {i, 0}))
	        	 return rows[i][0];
	       }
	        return '-';
	    }

	    private Character checkColWinner(){
	       for(int i = 0; i < 3; i++){
	         if(rows[0][i].equals(rows[1][i]) && rows[1][i].equals(rows[2][i]) && !isEmpty(new int [] {0, i}))
	        	 return rows[0][i];
	        }
	        return '-';
	    }

	   private Character checkDiagWinner(){
	       boolean leftDiagWinner = (rows[1][1].equals(rows[0][0]) && rows[1][1].equals(rows[2][2]));
	       boolean rightDiagWinner = (rows[1][1].equals(rows[0][2]) && rows[1][1].equals(rows[2][0]));
	       int [] centerPos = new int[] {1, 1};
	       
	       if (!isEmpty(centerPos)) {
	         if (leftDiagWinner || rightDiagWinner)
	           return rows[1][1];   // the winner's mark
	       }
	       return '-';
	   }

	   Character winner(){
	       Character rowWinner = checkRowWinner(), colWinner = checkColWinner(), diagWinner = checkDiagWinner();
	       if (rowWinner != '-')
	         return rowWinner;
	       else if (colWinner != '-')
	         return colWinner;
	       else if (diagWinner != '-')
	         return diagWinner;
	       
	       return '-';
	   }
	   
	   boolean isTied(){
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

	   boolean isWon(){
	       return !(winner() == '-');
	   }

	   boolean isOver(){
	      return (isWon() || isTied());
	   }

}
