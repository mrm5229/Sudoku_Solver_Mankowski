import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

class Sudoku_Solver_question {
    public static int type = 0;
    public static int height = 0;
    public static char[][] orig;
    // Node exploration holds the sudoku board in every iteration
    private static class Exploration {
        char[][] board;
        int curCell = 0;
        // constructor method
        public Exploration(char[][] board, int curCell) {
            this.board = cloneBoard(board);
            this.curCell = curCell;
        }
        // clones the board for every state    
        private char[][] cloneBoard(char[][] board) {
            char[][] newBoard = new char[board.length][board[0].length];
            copyIntoBoard(board, newBoard);
            return newBoard;
        }
    }

    public static boolean BFS(char[][] board) {
        // TODO write BFS search algorithm 
        //for (int i = 0; i < board.length * board.length; i++) {
        //    print_matrix_color(board, orig, i);
        //    sleep(300);
        //}
        
        
        // now this is still wrong because it's a 'char' array, and 1 is an integer.. 
        //but '1' is a char. Actually funnily enough 1 is a char too but it's wrong
        
        // check if the top/left number is valid.
        for (int num = 1; num <= 9; num++) {
            board[0][0] = intToChar(num); // assign a new value to the [0][0] spot of the 2d array
            if (isValidSquare(board, 0) == true && isValidColumn(board, 0) == true && isValidRow(board, 0) == true) {
                System.out.println("ayyy valid!");
            } else {
                System.out.println("not valid");
            }
            sleep(600);
        }

        return false;

    }

    /**
     * Converts a number eg 1 or 9 to a char eg '1', '9'
     * @param num number to convert
     * @return the char
     */
    public static char intToChar(int num) {
        return (char)(48+num);
    }

    public static boolean DFS(char[][] board) {
        // TODO write DFS search algorithm
        return false;

    }

    /**
     * Wraps the Thread.sleep to include a try/catch for convenience
     * parameter: sleep time in miliseconds
     */
    private static void sleep(int ms) { 
        try {
            Thread.sleep(ms);
        } catch (Exception e) {

        }
    }
    /**
     * parameter: index
     * return: index
     * Returns the index of a row based on type(6x6 / 9x9) of the board
     */
    private static int getRowForIndex(int idx) {
        return idx / type;
    }

    /**
     * parameter: index
     * return: index
     * Returns the index of a column based on type(6x6 / 9x9) of the board
     */
    private static int getColumnForIndex(int idx) {
        return idx % type;
    }

    /**
     * Parameters: source board & destination board
     * return: null
     * Copies the source board to destination board in every state
     */
    protected static void copyIntoBoard(char[][] src, char[][] dst) {
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dst[i], 0, src[i].length);
        }
    }

    /**
     * Parameters: Board & box index number
     * returns: true / false
     * Validates the box, returns true if there is no duplicate number in the box else false
     */
    private static boolean isValidSquare(char[][] board, int s) {
        boolean[] isPresent = new boolean[board.length+1];
        for (int r = (s / 3) * height; r < ((s / 3) + 1) * height; r++) {
            for (int c = (s % 3) * height; c < ((s % 3) + 1) * height; c++) {
                print_matrix_color(board, orig, r, c); // remove me later
                sleep(150); // remove me later
                char value = board[r][c];
                if (value == '.') {
                    continue;
                }
                int intVal = Character.getNumericValue(value);
                if (isPresent[intVal]) {
                    return false;
                }
                isPresent[intVal] = true;
            }
        }
        return true;
    }

    /**
     * Parameters: Board & column index number
     * returns: true / false
     * Validates the box, returns true if there is no duplicate number in the column else false
     */
    private static boolean isValidColumn(char[][] board, int c) {
        boolean[] isPresent = new boolean[board.length+1];
        for (int i = 0; i < board.length; i++) {
            print_matrix_color(board, orig, i, c); // remove me later
            sleep(150); // remove me later
            char value = board[i][c];
            if (value == '.') {
                continue;
            }
            int intVal = Character.getNumericValue(value);
            if (isPresent[intVal]) {
                return false;
            }
            isPresent[intVal] = true;
        }
        return true;
    }

    /**
     * Parameters: Board & row index number
     * returns: true / false
     * Validates the box, returns true if there is no duplicate number in the row else false
     */
    private static boolean isValidRow(char[][] board, int r) {
        boolean[] isPresent = new boolean[board.length+1];
        for (int i = 0; i < board.length; i++) {
            print_matrix_color(board, orig, r, i); // remove me later
            sleep(150); // remove me later
            char value = board[r][i];
            if (value == '.') {
                continue;
            }
            int intVal = Character.getNumericValue(value);
            if (isPresent[intVal]) {
                return false;
            }
            isPresent[intVal] = true;
        }
        return true;
    }

    /**
     * parameter: board
     * return: null
     * prints the board/matrix in the console
     */
    public static void print_matrix(char[][] board){
        int type = board.length;
        for(int i = 0; i<type;i++){
            if (i % (type/3) == 0 && i > 0) 
                System.out.println("");
            for(int j=0;j<type;j++){
                if (j % 3 == 0 && j > 0) 
                    System.out.print("  ");
                System.out.print(board[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    public static void print_matrix_color(char[][] board, char[][] orig, int index){
        int type = board.length;
        System.out.print(ansi().eraseScreen());
        for(int i = 0; i<type;i++){
            if (i % (type/3) == 0 && i > 0) 
                System.out.print("\n");
            for(int j=0;j<type;j++){
                if (j % 3 == 0 && j > 0) 
                    System.out.print("  ");
                Color bgColor = Color.DEFAULT;
                if (i == getRowForIndex(index) && j == getColumnForIndex(index)) {
                    bgColor = YELLOW;
                }
                if (orig[i][j] == '.') {
                    System.out.print(ansi().fg(RED).bg(bgColor).a(board[i][j]).bgDefault().fgDefault().a(" "));
                } else {
                    System.out.print(ansi().fgDefault().bg(bgColor).a(board[i][j]).bgDefault().fgDefault().a(" "));
                }
            }
            System.out.print("\n");
        }
    }
    public static void print_matrix_color(char[][] board, char[][] orig, int row, int col){
        int type = board.length;
        System.out.print(ansi().eraseScreen());
        for(int i = 0; i<type;i++){
            if (i % (type/3) == 0 && i > 0) 
                System.out.print("\n");
            for(int j=0;j<type;j++){
                if (j % 3 == 0 && j > 0) 
                    System.out.print("  ");
                Color bgColor = Color.DEFAULT;
                if (i == row && j == col) {
                    bgColor = Color.CYAN;
                }
                if (orig[i][j] == '.') {
                    System.out.print(ansi().fg(RED).bg(bgColor).a(board[i][j]).bgDefault().fgDefault().a(" "));
                } else {
                    System.out.print(ansi().fgDefault().bg(bgColor).a(board[i][j]).bgDefault().fgDefault().a(" "));
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Start of the program main function
     */
    public static void main(String args[]){
        AnsiConsole.systemInstall();
        // 6x6 sudoku board
        char[][] board_6x6 = {
                {'.','.','.', '.','4','.'},
                {'5','6','.', '.','.','.'},

                {'3','.','2', '6','5','4'},
                {'.','4','.', '2','.','3'},

                {'4','.','.', '.','6','5'},
                {'1','5','6', '.','.','.'}};
        orig = new char[board_6x6.length][board_6x6[0].length];
        copyIntoBoard(board_6x6, orig);
        // type holds the length of the board
        type = board_6x6.length;
        // height holds the height of each box in the board
        height = board_6x6.length/3;
        // prints the problem board
        System.out.println("Problem board is: ");
        print_matrix(board_6x6);
        // Solve the problem board using BFS
        //boolean status = BFS(board_6x6); // todo: uncomment
        boolean status = false;
        // based on the status print the solution
        if(status){
            System.out.println("\n BFS - Solution board is: ");
            print_matrix(board_6x6);
        }else{
            System.out.println("\n BFS - No Solution found");
        }

        // solve the problem using DFS
        status = DFS(board_6x6);
        // based on the status print the solution
        if(status){
            System.out.println("\n DFS - Solution board is: ");
            print_matrix(board_6x6);
        }else{
            System.out.println("\n DFS - No Solution found");
        }



        char[][] board_9x9 = {
            {'.','.','.', '8','4','.', '6','5','.'},
            {'.','8','.', '.','.','.', '.','.','9'},
            {'.','.','.', '.','.','5', '2','.','1'},

            {'.','3','4', '.','7','.', '5','.','6'},
            {'.','6','.', '2','5','1', '.','3','.'},
            {'5','.','9', '.','6','.', '7','2','.'},
            
            {'1','.','8', '5','.','.', '.','.','.'},
            {'6','.','.', '.','.','.', '.','4','.'},
            {'.','5','2', '.','8','6', '.','.','.'}};

        // type holds the length of the board
        type = board_9x9.length;
        // height holds the height of each box in the board
        height = board_9x9.length/3;
        orig = new char[board_9x9.length][board_9x9[0].length];
        copyIntoBoard(board_9x9, orig);
        
        // prints the problem board
        System.out.println("Problem board is: ");
        print_matrix(board_9x9);
        // Solve the problem board using BFS
        boolean status1 = BFS(board_9x9);
        // based on the status print the solution
        if(status1){
            System.out.println("\n BFS - Solution board is: ");
            print_matrix(board_9x9);
        }else{
            System.out.println("\n BFS - No Solution found");
        }
        // Solve the problem board using DFS
        status1 = DFS(board_9x9);
        // based on the status print the solution
        if(status1){
            System.out.println("\n DFS - Solution board is: ");
            print_matrix(board_9x9);
        }else{
            System.out.println("\n DFS - No Solution found");
        }


    }
} 