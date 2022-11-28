class SeatingMap{
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

    
}