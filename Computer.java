import java.util.Scanner;
import java.util.Random;
public class Computer
{
    Random generator = new Random();
    Scanner scan = new Scanner(System.in);
    //allows board to be set to flexible size later on
    int size = 0;
    String [][] tic;
    boolean gameOn = true;
    
    /**
     * Constructor for objects of class TTT
     */
    public Computer(int size)
    {
       // initialise instance variables, sets arry to size "size"
       this.size = size;
       tic  = new String[size][size];
    }
    
     public void main() {
        //main method
        newBoard();
        makeBoard();
        setLocationX();
    }
    
    public void emptyRedSpace() {
        //prints 3 empty lines
        System.err.println(" ");
        System.err.println(" ");
        System.err.println(" ");
    }
    
    public void newBoard() {
        //fills an empty array with "-" strings
        for (int i = 0; i < tic.length; i++) {
            for (int j = 0; j < tic[0].length; j++){ 
                tic[i][j] = "-";
            }
        }
    }
    
    public void makeBoard() {
        //dividing line of correct length
        for (int i = 0; i < size; i++) {
             System.err.print("-----");
        }
        System.err.println();
        
        //prints the current game board, most recent
        for (int i = 0; i < tic.length; i++) {
            for (int j = 0; j < tic[0].length; j++){ 
                System.err.print("| " + tic[i][j] + " |");
            }
            System.err.println("");
            for (int j = 0; j< size; j ++){
                System.err.print("-----");
            }
            System.err.println();
        }
        System.err.println();
    }
    
    public void setLocationX() {
        while (gameOn == true) {
            System.out.println();
            int col;
            int row;
            String play;
            System.out.println("Choose a column Player X (0-" + (size - 1) + "): ");
            col = scan.nextInt();
            System.out.println("Choose a row Player X (0-" + (size - 1) + "): ");
            row = scan.nextInt();
            if (tic[row][col] != "-"){
                System.out.println("Please choose an unoccupied space");
                setLocationX();
            }
            else {
                play = "X";
                tic[row][col] = play;
                makeBoard();
                checkWinner(row, col);
                setComputerLocation();
            }
        }   
        System.out.println("Game over");
    }
    
    public void setComputerLocation(){
        while (gameOn == true) {
            //uses random generator to set a random position on the board for the computer
            System.out.println();
            int col;
            int row;
            String play;
            col = generator.nextInt(3);
            row = generator.nextInt(3);
            if (tic[row][col] != "-"){
                setComputerLocation();
            }
            else {
                play = "O";
                tic[row][col] = play;
                makeBoard();
                checkWinner(row, col);
                setLocationX();
            }
        }
        System.out.println("Game over");
    }
    
    public void checkWinner(int r, int c) {
        int row = r;
        int col = c;
        verticalCheck(row, col);
        diagonalUpCheck(row, col);
        diagonalDownCheck(row, col);
        horizontalCheck(row, col);
    }
    
     public void verticalCheck(int r, int c) {
        int row = r;
        int col = c;
        String placeHolder = tic[row][col];
        int count = 1;
        while ((row - 1) >= 0 && tic[row-1][col] == placeHolder){
            //checks up rows
            count ++;
            row--;
        }
        row=r;
        while ((row + 1) <= (size - 1) && tic[row+1][col] == placeHolder) {
            //checks down rows
            count ++;
            row++;
        }
        if (count == size) {
            System.out.println("Congrats! Player " + tic[row][col] + " won!");
            gameOn = false;
        }
    }
    
     public void diagonalUpCheck(int r, int c) {
        int row = r;
        int col = c;
        String placeHolder = tic[row][col];
        int count = 1;
        while ((row - 1) >= 0 && (col - 1) >= 0 && tic[row-1][col-1] == placeHolder){
            count ++;
            row--;
            col--;
        }
        row=r;
        col=c;
        while ((row + 1) <= (size - 1) && (col + 1) <= (size-1) && tic[row+1][col+1] == placeHolder) 
        {
            count ++;
            row++;
            col++;
        }
        if (count == size) {
            System.out.println("Congrats! Player " + tic[row][col] + " won!");
            gameOn = false;
        }
    }
    
    public void diagonalDownCheck(int r, int c) {
        int row = r;
        int col = c;
        String placeHolder = tic[row][col];
        int count = 1;
        while((row - 1) >= 0 && (col + 1) <= (size - 1) && tic[row-1][col+1] == placeHolder) {
            row--;
            col++;
            count++;
        }
        row=r;
        col=c;
        while((col - 1) >= 0 && (row + 1) <= (size - 1) && tic[row+1][col-1] == placeHolder) {
            row++;
            col--;
            count++;
        }
        if (count == size) {
            System.out.println("Congrats! Player " + tic[row][col] + " won!");
            gameOn = false;
        }
    }
    
     public void horizontalCheck(int r, int c) {
        int row = r;
        int col = c;
        String placeHolder = tic[row][col];
        int count = 1;
        while ((col- 1) >= 0 && tic[row][col-1] == placeHolder){
            count ++;
            col--;
        }
        col=c;
        while ((col + 1) <= (size - 1) && tic[row][col+1] == placeHolder) {
            count ++;
            col++;
        }
        if (count == size) {
            System.out.println("Congrats! Player " + tic[row][col] + " won!");
            gameOn = false;
        }
    }
}
