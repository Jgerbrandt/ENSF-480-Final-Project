package apiPackage.model;
import java.time.LocalDate;

import java.time.LocalDate;

public class SeatingMap{
    private int[][] seats;
    private LocalDate earlyAccess;
    private LocalDate releaseDate;
    private boolean tenPercentSold = false; 
    private boolean soldOut = false;
    private static double price = 10;

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

    public int[][] getSeats() { return this.seats; }
    public boolean getTenPercent() {return this.tenPercentSold; }
    public boolean getSoldOut() { return this.soldOut; }
    public static double getPrice(){ return price; }

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

    public void buySeats(int[] seatPair) {
        this.seats[seatPair[0]][seatPair[1]] = 1;
        if(isEarly()){
            checkTenPercent();
        }
        else{
            checkSoldOut();
        }
    }

    public boolean isEarly(){
        if (LocalDate.now().isAfter(this.earlyAccess) && LocalDate.now().isBefore(this.releaseDate)){
            return true;
        }
        else {
            return false;
        }
    }

    public void cancelSeats(int col, int row) {
        this.seats[col][row] = 0;
    }
}