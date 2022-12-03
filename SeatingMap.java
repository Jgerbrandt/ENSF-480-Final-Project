public class SeatingMap{
    private int[][] seats;
    private boolean tenPercentSold = false; 
    private boolean soldOut = false;
    private static double price = 10;

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
    public static double getPrice(){ return price; }

    public boolean checkSoldOut(){
        int count = 0;
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                if(seats[i][j] == 1){
                    count++;
                }
            }
        }
        if (count == 100){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkTenPercent(){
        int count = 0;
        for(int i = 0; i < seats.length; i++){
            for(int j = 0; j < seats[i].length; j++){
                if(seats[i][j] == 1){
                    count++;
                }
            }
        }
        if (count >= 10){
            return true;
        }
        else {
            return false;
        }
    }
}