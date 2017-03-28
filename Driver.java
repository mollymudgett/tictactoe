import java.util.Scanner; 
    /**
 * Write a description of class Driver here.
 *  
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Driver
{
    // instance variables - replace the example below with your own
    public static void main() {
       Scanner scan = new Scanner(System.in);
       int size = 0;
       String decision = "";
       System.out.println("Play with another player or with computer?");
       decision = scan.nextLine();
       System.out.println("What size board?");
       size = scan.nextInt();
      
       if (decision.equals("player")){
          TTT jeff = new TTT(size);
          jeff.main();
        }
      else {
          Computer tic = new Computer(size);
          tic.main();
        }
   }
}

