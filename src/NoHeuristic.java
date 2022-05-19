/**
 * 
 * This class performs the Knight's tour without a heuristic, starting at a random position on
 * an 8 by 8 chessboard
 * 
 * @author Maitham Alghamgham
 * @version 9/9/19
 * 
 * Programming Project 1
 * Fall 2019
 *
 */


import java.util.*;

public class NoHeuristic {
	
	// instance fields
	int[][] chessboard = new int[8][8];//	this is the actual chessboard itself, which also keeps track of whether or not spaces have been landed on
	Boolean endcondition = false;//			this is the boolean that determines when the Knight's tour is to end
	Boolean turnMade = false;//				this is the boolean that determines whether or not a turn has been randomly made
	Boolean KTWin = false;//				this is the boolean that determines whether or not the knight's tour was a win
	int xPos;//								this keeps track of the x position on the chessboard
	int yPos;//								this keeps track of the y position on the chessboard
	int sxPos;//							this is the starting x position
	int syPos;//							this is the starting y position
	
	
	
	/**
	 * This is the empty argument constructor for the NoHeuristic, which randomly determines 
	 * sxPos and syPos
	 */
	public NoHeuristic() {
		
		Random r = new Random();
		sxPos = r.nextInt(8);
		syPos = r.nextInt(8);
		
	}// end empty argument constructor
	
	
	/**
	 * 
	 * This class performs the entirety of the knight's tour without a heuristic
	 * 
	 * @return the string containing the results of the knight's tour with the starting and ending positions,
	 * 		   as well as the number of turns made
	 */
	public String KnightsTour() {
		
		for(int j=0; j<chessboard.length; j++) {
			for(int i=0; i<chessboard.length; i++) {
				chessboard[i][j]=0;
			}
		}// end double for loop to fill chessboard with all 0
		
		xPos = sxPos;
		yPos = syPos;// sets the xPos and yPos for the first turn
		
		String win = "";// will be changed based on KTWin
		String startPos = "[ " + sxPos + " , " + syPos + " ]";
		int turnNo =  0;// keeps track of the number of turns made
		chessboard[sxPos][syPos]=100;// will make sure that the loop never moves randomly onto the start
		
		
		do {// for the tour itself
			
			do {// for the turns
				
				Random rr = new Random();
				int randMov = rr.nextInt(8);// this creates a random number 1 through 8, which will be passed
				turnMade = false;			// into the switch statement, randomly performing 1 of 8 possible moves
				
				switch (randMov) {
				
				case 0 :// x+1 y+2
					
					if( ((xPos+1)>=0 && (xPos+1)<=7) && ((yPos+2)>=0 && (yPos+2)<=7) && (chessboard[xPos+1][yPos+2]==0) ) {
					// this bounds checks the random move, making sure that it is on the chessboard
					// and has not been moved to before
						turnMade = true;
						turnNo++;
					
						xPos = xPos + 1;
						yPos = yPos + 2;// changes the actual position of the knight
					
						chessboard[xPos][yPos]=turnNo; // makes sure that the space cannot be accessed anymore
						
						
					
					}// end if for movement
					
					break;
					
					
				case 1 :// x+1 y-2
					
					if( ((xPos+1)>=0 && (xPos+1)<=7) && ((yPos-2)>=0 && (yPos-2)<=7) && chessboard[xPos+1][yPos-2]==0  ) {
					
						turnMade = true;
						turnNo++;
					
						xPos+=1;
						yPos-=2;
					
						chessboard[xPos][yPos]=turnNo;
					
					}// end if for movement
					break;
					
				case 2 :// x-1 y+2
					
					if( ((xPos-1)>=0 && (xPos-1)<=7) && ((yPos+2)>=0 && (yPos+2)<=7) && chessboard[xPos-1][yPos+2]==0 ) {
					
						turnMade = true;
						turnNo++;
					
						xPos-=1;
						yPos+=2;
					
						chessboard[xPos][yPos]=turnNo;
					}// end if for movement
					break;
					
				case 3 :// x-1 y-2
					
					if( ((xPos-1)>=0 && (xPos-1)<=7) && ((yPos-2)>=0 && (yPos-2)<=7) && chessboard[xPos-1][yPos-2]==0  ) {
					
						turnMade = true;
						turnNo++;
					
						xPos-=1;
						yPos-=2;
					
						chessboard[xPos][yPos]=turnNo;
					}// end if for movement
					break;
					
				case 4 :// x+2 y+1
					
					if( ((xPos+2)>=0 && (xPos+2)<=7) && ((yPos+1)>=0 && (yPos+1)<=7) && chessboard[xPos+2][yPos+1]==0 ) {
					
						turnMade = true;
						turnNo++;
					
						xPos+=2;
						yPos+=1;
					
						chessboard[xPos][yPos]=turnNo;
					
					}// end if for movement
					break;
					
				case 5 :// x+2 y-1
					
					if( ((xPos+2)>=0 && (xPos+2)<=7) && ((yPos-1)>=0 && (yPos-1)<=7) && chessboard[xPos+2][yPos-1]==0) {
					
						turnMade = true;
						turnNo++;
					
						xPos+=2;
						yPos-=1;
					
						chessboard[xPos][yPos]=turnNo;
					
					}// end if for movement
					break;
					
				case 6 :// x-2 y+1
					
					if( ((xPos-2)>=0 && (xPos-2)<=7) && ((yPos+1)>=0 && (yPos+1)<=7) && chessboard[xPos-2][yPos+1]==0 ) {
					
						turnMade = true;
						turnNo++;
					
						xPos-=2;
						yPos+=1;
					
						chessboard[xPos][yPos]=turnNo;
					
					}// end if for movement
					break;
					
				case 7 :// x-2 y-1
					
					if( ((xPos-2)>=0 && (xPos-2)<=7) && ((yPos-1)>=0 && (yPos-1)<=7) && chessboard[xPos-2][yPos-1]==0) {
					
						turnMade = true;
						turnNo++;
					
						xPos-=2;
						yPos-=1;
					
						chessboard[xPos][yPos]=turnNo;
					
					}// end if for movement
					
					break;
					default :
				
				}// end swtich for movement
				
				
				// this checks to see that there is a possible move yet. this will not register the origin as a possible move
				if( ((xPos+1>=0 && xPos+1<=7) && (yPos+2>=0 && yPos+2<=7) && chessboard[xPos+1][yPos+2]==0 ) ||
					((xPos+1>=0 && xPos+1<=7) && (yPos-2>=0 && yPos-2<=7) && chessboard[xPos+1][yPos-2]==0 ) ||
					((xPos-1>=0 && xPos-1<=7) && (yPos+2>=0 && yPos+2<=7) && chessboard[xPos-1][yPos+2]==0 ) ||
					((xPos-1>=0 && xPos-1<=7) && (yPos-2>=0 && yPos-2<=7) && chessboard[xPos-1][yPos-2]==0 ) ||
					((xPos+2>=0 && xPos+2<=7) && (yPos+1>=0 && yPos+1<=7) && chessboard[xPos+2][yPos+1]==0 ) ||
					((xPos+2>=0 && xPos+2<=7) && (yPos-1>=0 && yPos-1<=7) && chessboard[xPos+2][yPos-1]==0 ) ||
					((xPos-2>=0 && xPos-2<=7) && (yPos+1>=0 && yPos+1<=7) && chessboard[xPos-2][yPos+1]==0 ) ||
					((xPos-2>=0 && xPos-2<=7) && (yPos-1>=0 && yPos-1<=7) && chessboard[xPos-2][yPos-1]==0 )) 
				
						{
					turnMade=true;
					endcondition=false;// this continues the loop if there are any moves left
					
				}
					else {
						// if there are no moves, this checks to see if the origin is a possible move and the tour is  at the very end
						if( ((xPos+1 == sxPos && yPos+2 == syPos && chessboard[xPos+1][yPos+2]==100)||
								(xPos+1 == sxPos && yPos-2 == syPos && chessboard[xPos+1][yPos-2]==100)||
								(xPos-1 == sxPos && yPos+2 == syPos && chessboard[xPos-1][yPos+2]==100)||
								(xPos-1 == sxPos && yPos-2 == syPos && chessboard[xPos-1][yPos-2]==100)||
								(xPos+2 == sxPos && yPos+1 == syPos && chessboard[xPos+2][yPos+1]==100)||
								(xPos+2 == sxPos && yPos-1 == syPos && chessboard[xPos+2][yPos-1]==100)||
								(xPos-2 == sxPos && yPos+1 == syPos && chessboard[xPos-2][yPos+1]==100)||
								(xPos-2 == sxPos && yPos-1 == syPos && chessboard[xPos-2][yPos-1]==100)) && turnNo>62 ) {
								
								turnNo++;
								xPos = sxPos;
								yPos = syPos;
								
								endcondition = true;
								KTWin = true;// this ends the loop on a win if the tour can be completed
								turnMade = true;
								
							}// end if to check for win
						
						turnNo++;
						endcondition = true;
						turnMade = true;
						
					}// end else for loss
				
				
			}// end do for the turn to take place
			while(turnMade==false);
			
		}// end do for the tour movement
		while(endcondition == false);
		
		String result = startPos + ", " + turnNo +  ", [ " + xPos + " , " + yPos + " ]" + win;
		
		if(KTWin == true) {// this adds the asterisk if the tour was a win
			win = "*";
		}// end if
		
		return result;
	
	}// end KnightsTour


		
}// end NoHeuristic



