/**
 * 
 * This class runs the knight's tour without a heuristic 1000 times, printing all of the outputs to the
 * txt file NHoutput
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

public class NoHeuristicApplication {

	public static void main(String[] args) throws IOException {
		
		
		 FileWriter fileWriter = new FileWriter("NHoutput.txt");
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    
		    
		   for(int i = 0; i<1000; i++) {
			   NoHeuristic results = new NoHeuristic();
			   printWriter.print(results.KnightsTour());
			   printWriter.println();
		   }// end loop to print results to output.txt
		   
		    printWriter.close();
		
	}// end main

}// end class
