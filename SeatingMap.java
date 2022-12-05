/**
* Controller class to manage available seats for each showtime
* Manages capacity states and whether booking is available
* Theatre seating map represented by int[][] of 0s (empty seat) or 1s (full seat)
*
*/

import java.time.LocalDate;

public class SeatingMap{
    private int[][] seats;
    private LocalDate earlyAccess;
    private LocalDate releaseDate;
    private boolean tenPercentSold = false; 
    private boolean soldOut = false;
    private static double price = 10;
    
    /**
    * Ctor for all seating maps
    * fills array with matrix of 0s (no tickets booked yet)
    * 
    * @param rd     released date for associated movie to determine
                    whether the 10% full condition applies
    */
    public SeatingMap(LocalDate rd){
        this.releaseDate = rd;
        this.earlyAccess = rd.minusWeeks(1);
        this.seats = new int[10][10];
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                seats[i][j] = 0;
            }
        }
    }
    
    //getters
    public int[][] getSeats() { return this.seats; }
    public boolean getTenPercent() {return this.tenPercentSold; }
    public boolean getSoldOut() { return this.soldOut; }
    public static double getPrice(){ return price; }
    
    /**
    * sets capacity flag if all seats are booked after early access period
    */
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
            this.soldOut = true;
        }
        else {
            this.soldOut = false;
        }
    }
    
    /**
    * sets capacity flag if 10% of seats are booked in early access period
    */
    public void checkTenPercent(){
        int count = 0;
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                if(seats[i][j] == 1){
                    count++;
                }
            }
        }
        if (count >= 10 && isEarly()){
                this.tenPercentSold = true;
        }
        else {
            this.tenPercentSold = false;
        }
    }
    
    /**
    * updates seat map if when user selects seats
    *
    * @param seatPair   value of seat column and row to be booked
    */
    public void buySeats(int[] seatPair) {
        this.seats[seatPair[0]][seatPair[1]] = 1;
        if(isEarly()){
            checkTenPercent();
        }
        else{
            checkSoldOut();
        }
    }
    
    /**
    *   checks if showing movie is in early access period
    *
    * @return whether movie is early
    
    */
    public boolean isEarly(){
        if (LocalDate.now().isAfter(this.earlyAccess) && LocalDate.now().isBefore(this.releaseDate)){
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
    * updates seat map if user cancels order
    *
    * @param col   value of canceled seat column
    * @param row    value of cancelled seat row
    */
    public void cancelSeats(int col, int row) {
        this.seats[col][row] = 0;
    }
}
