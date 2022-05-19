/**
 * 
 * This class runs the knight's tour with a heuristic from every space on the chessboard and prints the
 * output to the txt file Houtput.txt
 * 
 * @author Maitham Alghamgham
 * @version 9/9/19
 * 
 * Programming Project 1
 * Fall 2019
 *
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HeuristicApplication {

	public static void main(String[] args) throws IOException {
		
		
		 FileWriter fileWriter = new FileWriter("Houtput.txt");
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    
		    
		   for(int i = 0; i<8; i++) {
			   for(int j = 0; j<8; j++) {
				   Heuristic results = new Heuristic(i, j);
				   printWriter.print(results.KnightsTour());
				   printWriter.println();
			   }
			   
			   
		   }// end loop to print results to output.txt
		   
		    printWriter.close();
		
	}// end main

}// end class
