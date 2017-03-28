import java.util.Scanner;
public class TTT
{
    
    Scanner scan = new Scanner(System.in);
    //allows board to be set to flexible size later on
    int size = 0;
    String [][] tic;
    boolean gameOn = true;
    int turns = 0;
    
    public TTT(int size)
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
        printMuffin();
    }
    
    public void printMuffin() {
        //prints out a muffin picture (because DunderMuffin)
        
        System.out.println("Thanks for playing! Here's a muffin for your enjoyment");
        System.out.println("   .-\"\"\"`\"-.");
        System.out.println("  /.\'.\'.\'`.\'\\");
        System.out.println(" ('.'.'.'.`'.)");
        System.out.println("~|||||||||||||~");
        System.out.println("~|||||||||||||~");
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
        //if no one has won, enters the while statement to place an X
        while (gameOn == true) {
            System.out.println();
            int col;
            int row;
            String play;
            //scans in a row and column position of the players choice, flexible with the size
            System.out.println("Choose a column Player X (0-" + (size - 1) + "): ");
            col = scan.nextInt();
            System.out.println("Choose a row Player X (0-" + (size - 1) + "): ");
            row = scan.nextInt();
            if (tic[row][col] != "-"){
                //if a space is already full, it requests a different input, recalls the method
                System.out.println("Please choose an unoccupied space");
                setLocationX();
            }
            else {
                //fills selected array space with X
                play = "X";
                tic[row][col] = play;
                //prints updated board with most recent piece
                makeBoard();
                //checks that no one has won before calling the O plqyer to make a move
                checkWinner(row, col);
                setLocationO();
            }
        }   
    }
    
    public void setLocationO(){
        //see setLocationX() above, same code except for "O"
        while (gameOn == true) {
            System.out.println();
            int col;
            int row;
            String play;
            System.out.println("Choose a column Player O (0-" + (size - 1) + "): ");
            col = scan.nextInt();
            System.out.println("Choose a row Player O (0-" + (size - 1) + "): ");
            row = scan.nextInt();
            if (tic[row][col] != "-"){
                System.out.println("Please choose an unoccupied space");
                setLocationO();
            }
            else {
                play = "O";
                tic[row][col] = play;
                makeBoard();
                checkWinner(row, col);
                setLocationX();
            }
        }
    }
    
    public void checkWinner(int r, int c) {
        //takes place of last played piece as parameters and checks for a winner->
        //(diagonal - both ways horizontal, or vertical)
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
        //sets either X or O current in the spot to a placeholder so the method will work for either
        String placeHolder = tic[row][col];
        int count = 1;
        //while loop ensures that only checks array spots within the (flexible) bounds
        while ((row - 1) >= 0 && tic[row-1][col] == placeHolder){
            //checks up rows, count increases until either the array ends, or the next one ->
            //is different from the placeholder
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
            //if there is an unbroken string of the same character that is as long as the ->
            //initial size, gameOn becomes false and a new setLocation method will not be ->
            //entered and the game part will end
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
            //checks up diagonally to the left, counting the number of placeholders present in ->
            //an unbroken line
            count ++;
            row--;
            col--;
        }
        row=r;
        col=c;
        while ((row + 1) <= (size - 1) && (col + 1) <= (size-1) && tic[row+1][col+1] == placeHolder) 
        {
            //checks diagonally down to the right
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
            //checks diagonally up to the right
            row--;
            col++;
            count++;
        }
        row=r;
        col=c;
        while((col - 1) >= 0 && (row + 1) <= (size - 1) && tic[row+1][col-1] == placeHolder) {
            //checks diagonally down to the left
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
            //checks spots to left
            count ++;
            col--;
        }
        col=c;
        while ((col + 1) <= (size - 1) && tic[row][col+1] == placeHolder) {
            //checks spots to right
            count ++;
            col++;
        }
        if (count == size) {
            System.out.println("Congrats! Player " + tic[row][col] + " won!");
            gameOn = false;
        }
    }
}
