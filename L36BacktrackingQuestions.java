public class L36BacktrackingQuestions {
    public static void main(String[] args) {
        // int n=88;
        // boolean [][]board=new boolean[n][n];
        // System.out.println(Nqueens(board, 0));

        int[][] board = new int[][]{
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
    };

    if (sudoko(board)) {
        display(board);
    } else {
        System.out.println("Cannot solve");
    }

}
        
    

    static boolean sudoko(int [][] board){
        int n=board.length;
        int row=-1;
        int col=-1;
        boolean emptyLeft = true;

        // this is how we are replacing the r,c from arguments
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break;
                }
            }
            // if you found some empty element in row, then break
            if (emptyLeft == false) {
                break;
            }
        }

        if (emptyLeft == true) {
            return true;
            // soduko is solved
        }

        // backtrack
        for (int number = 1; number <= 9; number++) {
            if (isSafe(board, row, col, number)) {
                board[row][col] = number;
                if (sudoko(board)) {
                    // found the answer
                    return true;
                } else {
                    // backtrack
                    board[row][col] = 0;
                }
            }
        }
        return false;

    }

    private static void display(int[][] board) {
        for(int[] row : board) {
            for(int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }


    static boolean isSafe(int[][] board, int row, int col, int num) {
        // check the row
        for (int i = 0; i < board.length; i++) {
            // check if the number is in the row
            if (board[row][i] == num) {
                return false;
            }
        }

        // check the col
        for (int[] nums : board) {
            // check if the number is in the col
            if (nums[col] == num) {
                return false;
            }
        }

        int sqrt = (int)(Math.sqrt(board.length));
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;

        for (int r = rowStart; r < rowStart + sqrt; r++) {
            for (int c = colStart; c < colStart + sqrt; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    static int Nqueens(boolean [][] board,int row){
        if(row==board.length){
            display(board);
            System.out.println();
            return 1;
        }
        int count= 0;
        for(int col=0;col<board.length;col++){
            if(isSafe(board,row,col)){
                board[row][col]=true;
                count+=Nqueens(board, row+1);
                board[row][col]=false;
            }
        }
        return count;
    }
    static boolean isSafe(boolean[][]board,int row,int col){
        //check vertical
        for(int i=0;i<row;i++){
            if(board[i][col]){
                return false;
            }
        }
        //diagonal left
        int maxleft=Math.min(row, col);
        for(int i=1;i<=maxleft;i++){
            if(board[row-i][col-i]){
                return false;
            }
        }

        //diagonal right
        int maxright=Math.min(row, board.length-col-1);
        for(int i=1;i<=maxright;i++){
            if(board[row-i][col+i]){
                return false;
            }
        }
        return true;
        
    }
    static void display(boolean [][]board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==true){
                    System.out.print(" Q ");
                }else{
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
    
}
