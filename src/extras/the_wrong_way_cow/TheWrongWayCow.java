//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

import java.util.ArrayList;

public class TheWrongWayCow {

	static ArrayList<location> rightCows = new ArrayList<location>();
	static ArrayList<location> downCows = new ArrayList<location>();
	static ArrayList<location> leftCows = new ArrayList<location>();
	static ArrayList<location> upCows = new ArrayList<location>();
	
	static char [] currentChar = {'c','o','w'};
	
	static int rightCowCount = 0;
	static int leftCowCount = 0;
	static int upCowCount = 0;
	static int downCowCount = 0;
	static int letterCount = 0;
	
    public static int[] findWrongWayCow(final char[][] field) {
        // Fill in the code to return the x,y coordinate position of the
        // head (letter 'c') of the wrong way cow!

    	// for each cell in the field
    	// check all 4 directions for the next letter in "cow"
    	// Keep track of the starting point
    	// Increment a count for each cow completed for each direction
    	// when we're all done, see which count = 1. Return the starting point or that direction
    	
    	
    	// alternative solution
    	// For each direction, add to an Arraylist int[][] to hold the starting point of each cow in this direction
    	// Check length of each arraylist for 1. Return that starting location
    	
    	rightCowCount = 0;
    	rightCows.clear();
    	//System.out.println("field[0].length = " + field[0].length);
        for(int row=0; row<field.length; row++) {
        	letterCount = 0;
        	searchCowRight(field, row, 0);	
        	
       	}
        System.out.println("Number of cows facing right: " + rightCowCount);
        if(rightCowCount>0)
        	System.out.println("Last location of right facing cow: " + rightCows.get(rightCows.size()-1).coordinates[0] + ", " + rightCows.get(rightCows.size()-1).coordinates[1]);
        
        leftCowCount = 0;
        leftCows.clear();
        for(int row=0; row<field.length; row++) {
        	letterCount = 0;
        	searchCowLeft(field, row, field[0].length-1);	
       	}
       
        System.out.println("Number of cows facing left: " + leftCowCount);
        if(leftCowCount>0)
        	System.out.println("Last location of left facing cow: " + leftCows.get(leftCows.size()-1).coordinates[0] + ", " + leftCows.get(leftCows.size()-1).coordinates[1]);

    	downCowCount = 0;
    	downCows.clear();
        for(int index=0; index<field[0].length; index++) {
        	letterCount = 0;
        	searchCowDown(field, 0, index);	
       	}
        System.out.println("Number of cows facing down: " + downCowCount);
        if(downCowCount>0)
        	System.out.println("Last location of down facing cow: " + downCows.get(downCows.size()-1).coordinates[0] + ", " + downCows.get(downCows.size()-1).coordinates[1]);

    	upCowCount = 0;
    	upCows.clear();
        for(int index=0; index<field[0].length; index++) {
        	letterCount = 0;
        	searchCowUp(field, field.length-1, index);	
       	}
        System.out.println("Number of cows facing up: " + upCowCount);
        if(upCowCount>0)
        	System.out.println("Last location of up facing cow: " + upCows.get(upCows.size()-1).coordinates[0] + ", " + upCows.get(upCows.size()-1).coordinates[1]);

        
        if(rightCowCount == 1)
        	return rightCows.get(0).coordinates;
        else if(leftCowCount == 1)
        	return leftCows.get(0).coordinates;
        else if(downCowCount == 1)
        	return downCows.get(0).coordinates;
        else if(upCowCount == 1)
        	return upCows.get(0).coordinates;
        else
        	return new int[] {0,0};
    }
    
    static void searchCowRight(final char[][] field, int row, int index){
    	//need a static letter count that increases until w is reached, or reset to zero
    		if(field[row][index] == currentChar[letterCount])
    		{
    			if(letterCount == 2) {
    				rightCowCount++; //we matched 3 letters, increment cow count for this direction
    				rightCows.add(new location(index-2, row)); //save the starting point of this 'c' location in (x,y) coordinates (not row, index)
    				letterCount = 0; //we reached 'w', reset to zero to keep searching for cow in the same row
    			}
    			else //we did not reach 'w', so check for the next letter in the row 
    			{
    				letterCount++; //check for matching next letter
    			}
    		}
    		else if((letterCount>0) && (field[row][index] == 'c')) {   //if expected 'o' or 'w'. but got a 'c', set appropriate count
    			letterCount = 1;
    		}
    		else
    		{
    			//letter does not match, reset the letterCount - did not get a 'c' + 'o' + 'w' in order 
    			letterCount = 0;
    		}
    		
    		//decision point to recurse if we did not reach the end of the row
			if(index < field[0].length-1) //did we reach the end of the row?
			{
				//recurse next letter
				searchCowRight(field, row, index+1);
			}
			else
				letterCount = 0; //reached the end of the row, reset to look starting with 'c'
				return;
    		}
    
static void searchCowLeft(final char[][] field, int row, int index){
	//need a static? letter count that increases until w is reached, or reset to zero
		if(field[row][index] == currentChar[letterCount])
		{
			if(letterCount == 2) {
				leftCowCount++; //we matched 3 letters, increment cow count for this direction
				leftCows.add(new location(index+2, row)); //save the starting point of this 'c' location in (x,y) coordinates (not row,index)
				letterCount = 0; //we reached 'w', reset to zero to keep searching for cow in the same row
			} 
			else //we did not reach 'w', so check for the next letter in the row 
			{
				letterCount++; //check for matching next letter
			}
		} 
		else if((letterCount>0) && (field[row][index] == 'c'))    //if expected 'o' or 'w'. but got a 'c', set appropriate count
		{
			letterCount = 1;
		}
		else 
		{
			//letter does not match, reset the letterCount - did not get a 'c' + 'o' + 'w' in order 
			letterCount = 0;
		}
		
		//decision point to recurse if we did not reach the left end of the row
		if(index > 0) //did we reach the left end of the row?
		{
			//recurse next letter
			searchCowLeft(field, row, index-1);
		}
		else
			letterCount = 0; //reached the left end of the row, reset to look starting with 'c'
			return;
		}
	
	static void searchCowDown(final char[][] field, int row, int index){
	//need a static? letter count that increases until w is reached, or reset to zero
		if(field[row][index] == currentChar[letterCount])
		{
			if(letterCount == 2) {
				downCowCount++; //we matched 3 letters, increment cow count for this direction
				downCows.add(new location(index, row-2)); //save the starting point of this 'c' location as (x,y), not (row, index)
				letterCount = 0; //we reached 'w', reset to zero to keep searching for cow in the same row
			} 
			else //we did not reach 'w', so check for the next letter in the row 
			{
				letterCount++; //check for matching next letter
			}
		}
		else if((letterCount>0) && (field[row][index] == 'c'))    //if expected 'o' or 'w'. but got a 'c', set appropriate count
		{
			letterCount = 1;
		}
		else 
		{
			//letter does not match, reset the letterCount - did not get a 'c' + 'o' + 'w' in order 
			letterCount = 0;
		}
		
		//decision point to recurse if we did not reach the end of the row
		if(row < field.length-1) //did we reach the end of the row?
		{
			//recurse next letter
			searchCowDown(field, row+1, index);
		}
		else
			letterCount = 0; //reached the end of the row, reset to look starting with 'c'
			return;
		}

	static void searchCowUp(final char[][] field, int row, int index){
	//need a static? letter count that increases until w is reached, or reset to zero
		if(field[row][index] == currentChar[letterCount])
		{
			if(letterCount == 2) {
				upCowCount++; //we matched 3 letters, increment cow count for this direction
				upCows.add(new location(index, row+2)); //save the starting point of this 'c' location in (x,y) and not (row, index) format
				letterCount = 0; //we reached 'w', reset to zero to keep searching for cow in the same row
			} 
			else //we did not reach 'w', so check for the next letter in the row 
			{
				letterCount++; //check for matching next letter
			}
		}
		else if((letterCount>0) && (field[row][index] == 'c'))    //if expected 'o' or 'w'. but got a 'c', set appropriate count
		{
			letterCount = 1;
		}
		else 
		{
			//letter does not match, reset the letterCount - did not get a 'c' + 'o' + 'w' in order 
			letterCount = 0;
		}
		
		//decision point to recurse if we did not reach the end of the row
		if(row > 0) //did we reach the end of the row?
		{
			//recurse next letter
			searchCowUp(field, row-1, index);
		}
		else
			letterCount = 0; //reached the end of the row, reset to look starting with 'c'
			return;
		}
}

