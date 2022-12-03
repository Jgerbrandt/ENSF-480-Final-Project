/*
 * All classes in Model package are more or less copy pasted from classes you guys have built
 * Changes from Git: Added Buy Seats method which takes an ordered pair of seats and reserves them in the map
 */

package apiPackage.model;

public class SeatingMap{
    private int[][] seats;
    private boolean tenPercentSold = false; 
    private boolean soldOut = false;

    public SeatingMap(){
        this.seats = new int[10][10];
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                seats[i][j] = 0;
            }
        }
    }

    public int[][] getSeats() { return this.seats; }
    public boolean getTenPercent() {return this.tenPercentSold; }
    public boolean getSoldOut() { return this.soldOut; }
    
    
    //NEW FUNCTION:
    public void buySeats(int[] seatPair) {
    	this.seats[seatPair[0]][seatPair[1]] = 1;
    	checkSoldOut();
    	checkTenPercent();
    }

    public void checkSoldOut(){
        int count = 0;
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                if(seats[i][j] == 1){
                    count++;
                }
            }
        }
        if (count == 100){
            soldOut = true;
        }
        else {
            soldOut = false;
        }
    }

    public void checkTenPercent(){
        int count = 0;
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                if(seats[i][j] == 1){
                    count++;
                }
            }
        }
        if (count >= 10){
            tenPercentSold = true;
        }
        else {
            tenPercentSold = false;
        }
    }

    
}