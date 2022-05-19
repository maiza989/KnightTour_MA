/**
 * 
 * This class performs the Knight's tour with a heuristic, moving to the space that can be 
 * accessed by the least number of other spaces
 * 
 * @author Maitham Alghamgham
 * @version 9/9/19
 * 
 * Programming Project 1
 * Fall 2019
 *
 */


import java.util.*;

public class Heuristic {
	
	// instance fields
	int[][] chessboard = new int[8][8];//	this is the chessboard for the tour that keeps track of former moves
	int[][] HMap = new int[8][8];//			this keeps track of the availibility heuristic for every space
	Boolean endcondition = false;//			this boolean determines when the tour ends
	Boolean turnMade = false;//				this boolean determines when the turn ends
	Boolean KTWin = false;//				this boolean determines if there is a  win
	int xPos;//								this determines the x position
	int yPos;//								this determines the y position
	int sxPos;//							this determines the starting x position
	int syPos;//							this determines the starting y position
	int turnNo;//							this determines the number of moves made
	
	
	/**
	 * This is the empty argument constructor for the heuristic. It fills the chessboard with 0s, but 
	 * also sets up HMap with the availibility heuristic, which will determine the movement
	 */
	public Heuristic() {
		
		for(int j=0; j<chessboard.length; j++) {
			for(int i=0; i<chessboard.length; i++) {
				chessboard[i][j]=0;
			}
		}// end double for loop to fill chessboard with all 0
		
		// Availibility 2 on the Heuristic Map
		HMap[0][0]=2;HMap[7][0]=2;HMap[0][7]=2;HMap[7][7]=2;
		//3
		HMap[1][0]=2;HMap[0][1]=3;HMap[6][0]=3;HMap[0][6]=3;HMap[1][7]=3;HMap[7][1]=3;HMap[6][7]=3;HMap[7][6]=3;
		//4
		HMap[1][1]=4;HMap[6][1]=4;HMap[6][6]=4;HMap[1][6]=4;
		int count = 2;
		do {
			HMap[count][0]=4;
			HMap[count][7]=4;
			HMap[0][count]=4;
			HMap[7][count]=4;
		}while(count!=6);
		//6
		int counter=2;
		do {
			HMap[count][1]=6;
			HMap[count][6]=6;
			HMap[1][count]=6;
			HMap[6][count]=6;
		}while(counter!=6);
		//8
		for(int i=2; i<6; i++) {
			for(int j = 2; j<6; j++) {
				HMap[i][j]=8;
			}
		}// end nested for to fill HMap
		
		turnNo = 0;
	}
	
	/**
	 * 
	 * This constructor for the heurisitic tour takes in the starting x and y positions
	 * 
	 * @param x - the starting x position
	 * @param y - the starting y position
	 */
	public Heuristic(int x, int y) {
		
		setSxPos(x);// calls the setters for bounds checking
		setSyPos(y);
		
	}// end constructor
	
	/**
	 * This method changes the availibility number for each space depending on the x position
	 * and y position
	 * 
	 * @param xPos - the x position
	 * @param yPos - the y position
	 */
	public void ModifyHMap(int xPos, int yPos) {
		
		if( ((xPos+1)>=0 && (xPos+1)<=7) && ((yPos+2)>=0 && (yPos+2)<=7) && (chessboard[xPos+1][yPos+2]==0)) 
			HMap[xPos+1][yPos+2]-=1;
		if( ((xPos+1>=0 && xPos+1<=7) && (yPos-2>=0 && yPos-2<=7) && chessboard[xPos+1][yPos-2]==0 )) 
			HMap[xPos+1][yPos-2]-=1;
		if((xPos-1>=0 && xPos-1<=7) && (yPos+2>=0 && yPos+2<=7) && chessboard[xPos-1][yPos+2]==0 ) 
			HMap[xPos-1][yPos+2]-=1;
		if((xPos-1>=0 && xPos-1<=7) && (yPos-2>=0 && yPos-2<=7) && chessboard[xPos-1][yPos-2]==0 ) 
			HMap[xPos-1][yPos-2]-=1;
		if((xPos+2>=0 && xPos+2<=7) && (yPos+1>=0 && yPos+1<=7) && chessboard[xPos+2][yPos+1]==0 ) 
			HMap[xPos+2][yPos+1]-=1;
		if((xPos+2>=0 && xPos+2<=7) && (yPos-1>=0 && yPos-1<=7) && chessboard[xPos+2][yPos-1]==0 ) 
			HMap[xPos+2][yPos-1]-=1;
		if((xPos-2>=0 && xPos-2<=7) && (yPos+1>=0 && yPos+1<=7) && chessboard[xPos-2][yPos+1]==0 ) 
			HMap[xPos-2][yPos+1]-=1;
		if((xPos-2>=0 && xPos-2<=7) && (yPos-1>=0 && yPos-1<=7) && chessboard[xPos-2][yPos-1]==0 ) 
			HMap[xPos-2][yPos-1]-=1;
		
	}// end ModifyHMap
	
	/**
	 * This is the most important method for the heursitic movement. It keeps all of the possible moves
	 * as a number 0 - 7 so that it can be passed into the switch statement from NoHeuristic. It also
	 * compares all of the availibility numbers for the possible moves and randomly chooses from
	 * the availible moves if there is a tie in which space is least accessible.
	 * 
	 * @param xPos - current x position
	 * @param yPos - current y position
	 * @return the int of which move is being made into the switch statement, which actually moves the piece
	 */
	public int CompareHMap(int xPos, int yPos) {
		
		int compare = 0;// determines if there is a tie
		int movement = 0;// contains the position in the avail array of the move with the lowest availiblity numebr
		int[] avail = new int[8];// records the HMap number for each of the 8 possible moves
		ArrayList<Integer> possible = new ArrayList<Integer>();// this contains the position of the possible moves if there is a tie in the lowest accessibility number
		
		for(int i=0; i<8; i++) {
			avail[i]=50;// keeps the array at 50 at first in case moves can't be made. This will make out of bounds moves impossible
		}// will keep spaces for non moves too high to be picked
		
		// this checks to see if a move is possible, and assigns the availibility number if it is. If not,
		// this will continue to be 50, and obviously not the lowest number
		if( ((xPos+1)>=0 && (xPos+1)<=7) && ((yPos+2)>=0 && (yPos+2)<=7) && (chessboard[xPos+1][yPos+2]==0)) 
			avail[0]=HMap[xPos+1][yPos+2];
		if( ((xPos+1>=0 && xPos+1<=7) && (yPos-2>=0 && yPos-2<=7) && chessboard[xPos+1][yPos-2]==0 )) 
			avail[1]=HMap[xPos+1][yPos-2];
		if((xPos-1>=0 && xPos-1<=7) && (yPos+2>=0 && yPos+2<=7) && chessboard[xPos-1][yPos+2]==0 ) 
			avail[2]=HMap[xPos-1][yPos+2];
		if((xPos-1>=0 && xPos-1<=7) && (yPos-2>=0 && yPos-2<=7) && chessboard[xPos-1][yPos-2]==0 ) 
			avail[3]=HMap[xPos-1][yPos-2];
		if((xPos+2>=0 && xPos+2<=7) && (yPos+1>=0 && yPos+1<=7) && chessboard[xPos+2][yPos+1]==0 ) 
			avail[4]=HMap[xPos+2][yPos+1];
		if((xPos+2>=0 && xPos+2<=7) && (yPos-1>=0 && yPos-1<=7) && chessboard[xPos+2][yPos-1]==0 ) 
			avail[5]=HMap[xPos+2][yPos-1];
		if((xPos-2>=0 && xPos-2<=7) && (yPos+1>=0 && yPos+1<=7) && chessboard[xPos-2][yPos+1]==0 ) 
			avail[6]=HMap[xPos-2][yPos+1];;
		if((xPos-2>=0 && xPos-2<=7) && (yPos-1>=0 && yPos-1<=7) && chessboard[xPos-2][yPos-1]==0 ) 
			avail[7]=HMap[xPos-2][yPos-1];
		
		
		possible.add(0);// sets up 0 to be the first space for comparison
		for(int i = 1; i<8; i++) {
			if(avail[i]<avail[movement]) {
				movement = i;
				possible.clear();// this sets up the for loop for a smaller number for comparison
				compare = 0;
			}
			else if(avail[i]==avail[movement]) {
				compare++;
				possible.add(i);// this prepares to account for a tie
			}
		}// end for
		
		if(compare==0) {
			return movement;// if there is no tie
		}
		else {// chooses randomly between the few
			
			Random rr = new Random();
			int randMov = rr.nextInt(possible.size());// randomly chooses from the possible moves on a tie
			movement = possible.remove(randMov);
			
			return movement;// returns the number for the movement
		}
		
	}// end CompareHMap
	
	/**
	 * This is where movement actually occurs with the use of ModifyHMap and CompareHMap
	 * 
	 * @return a string with the starting and ending position, as well as the number of moves
	 */
	public String KnightsTour() {
		
		xPos = sxPos;// makes the x and y positions the starting positions for the tour
		yPos = syPos;
		String win = "";
		String startPos = "[ " + sxPos + " , " + syPos + " ]";
		chessboard[sxPos][syPos]=100;// makes sure that the starting position will not be moved to
		
		do {// for the tour itself
			
			ModifyHMap(xPos, yPos);// enacts ModifyHMap once the position is established
			
			do {// for the turns
				
				turnMade = false;
				
				switch (CompareHMap(xPos,yPos)) {// uses CompareHMap to determine which move to make
				
				case 0 :// x+1 y+2
					
					if( ((xPos+1)>=0 && (xPos+1)<=7) && ((yPos+2)>=0 && (yPos+2)<=7) && (chessboard[xPos+1][yPos+2]==0) ) {
					
						turnMade = true;
						turnNo++;
					
						xPos = xPos + 1;
						yPos = yPos + 2;
					
						chessboard[xPos][yPos]=turnNo;
						
						
					
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
				
				
				// this checks for the ending of the tour if no moves can be made
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
					endcondition=false;
					
				}
					else {
						
						// this checks for a win if the ending is determined
						if( ((xPos+1 == sxPos && yPos+2 == syPos && chessboard[xPos+1][yPos+2]==100)||
								(xPos+1 == sxPos && yPos-2 == syPos && chessboard[xPos+1][yPos-2]==100)||
								(xPos-1 == sxPos && yPos+2 == syPos && chessboard[xPos-1][yPos+2]==100)||
								(xPos-1 == sxPos && yPos-2 == syPos && chessboard[xPos-1][yPos-2]==100)||
								(xPos+2 == sxPos && yPos+1 == syPos && chessboard[xPos+2][yPos+1]==100)||
								(xPos+2 == sxPos && yPos-1 == syPos && chessboard[xPos+2][yPos-1]==100)||
								(xPos-2 == sxPos && yPos+1 == syPos && chessboard[xPos-2][yPos+1]==100)||
								(xPos-2 == sxPos && yPos-1 == syPos && chessboard[xPos-2][yPos-1]==100)) && turnNo>60 ) {
								
							
								turnNo++;
								xPos = sxPos;
								yPos = syPos;
								
								endcondition = true;
								KTWin = true;// denotes a win
								turnMade = true;
								
							}// end if to check for win
						
						turnNo++;
						endcondition = true;// denotes a loss
						turnMade = true;
						
					}// end else for loss
				
				
			}// end do for the turn to take place
			while(turnMade==false);
			
		}// end do for the tour movement
		while(endcondition == false);
		
		String result = startPos + ", " + turnNo +  ", [ " + xPos + " , " + yPos + " ]" + win;
		
		if(KTWin == true) {
			win = "*";
		}// end if
		
		return result;
	
	}// end KnightsTour

	
	public int getSxPos() {
		return sxPos;
	}

	public void setSxPos(int sxPos) {// bounds checks the starting x position
		if(sxPos<8 && sxPos>-1)
			this.sxPos = sxPos;
		else
			this.sxPos = 0;
	}

	public int getSyPos() {
		return syPos;
	}

	public void setSyPos(int syPos) {// bounds checks the starting y position
		if(syPos<8 && syPos>-1)
			this.syPos = syPos;
		else
			this.syPos=0;
	}


		
}// end NoHeuristic





