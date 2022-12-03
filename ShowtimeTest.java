import java.time.format.*;

public class ShowtimeTest {
    public static void main(String[] args){
        Showtime st1 = new Showtime(1, "12-12-2022", "9:30am");

        System.out.println("TEST MEMBERS");
        System.out.print("EXPECTED VALUES:\nCounter: 2\nID: 1\nScreen: 1\nShowDate: December 12, 2022\nTime: 9:30am\n");

        System.out.println("ACTUAL VALUES:");
        System.out.println("Counter: " + Integer.toString(Showtime.getCounter()));
        System.out.println("ID: " + Integer.toString(st1.getID()));
        System.out.println("Screen: " + Integer.toString(st1.getScreen()));
        System.out.println("ShowDate: " + st1.getShowDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println("Time: " + st1.getTime());

        Showtime st2 = new Showtime(1, "24-12-2022", "12:00pm");
        System.out.println("Test ID Logic");
        System.out.print("EXPECTED VALUES:\nCounter: 3\nID: 2\n");

        System.out.println("ACTUAL VALUES:");
        System.out.println("Counter: " + Integer.toString(Showtime.getCounter()));
        System.out.println("ID: " + Integer.toString(st2.getID()));

        System.out.println("Test SeatingMap Logic");
        System.out.println("Expected: 10x10 array of 0");
        System.out.println("Actual: ");
        for(int i = 0; i < st1.getSeats().getSeats().length; i++){
            for(int j = 0; j < st1.getSeats().getSeats()[i].length; j++){
                System.out.print(Integer.toString(st1.getSeats().getSeats()[i][j]) + " ");
            }
            System.out.println();
        }

        System.out.println("Add two seats");
        int[] seat1 = {5,6};
        int[] seat2 = {5,7};
        st1.getSeats().buySeats(seat1);
        st1.getSeats().buySeats(seat2);
        System.out.println("Expected: All zeros but row 6, seats 7 and 8");
        System.out.println("Actual: ");
        for(int i = 0; i < st1.getSeats().getSeats().length; i++){
            for(int j = 0; j < st1.getSeats().getSeats()[i].length; j++){
                System.out.print(Integer.toString(st1.getSeats().getSeats()[i][j]) + " ");
            }
            System.out.println();
        }

        System.out.println("Check 10 percent");
        int[] seat = {5, 0};
        for(int j = 0; j < st1.getSeats().getSeats()[5].length; j++){
            seat[1] = j;
            st1.getSeats().buySeats(seat);
        }

        System.out.println("Expected: Ten Percent Full");
        System.out.println("Actual: ");
        if(st1.getSeats().checkTenPercent()){
            System.out.println("Ten Percent Full");
        }
        else{
            System.out.println("NOt Ten Percent Full");
        }

        System.out.println("Check Sold Out");
        for(int i = 0; i < st1.getSeats().getSeats().length; i++){
            for(int j = 0; j < st1.getSeats().getSeats()[i].length; j++){
                seat[0] = i;
                seat[1] = j;
                if(st1.getSeats().getSeats()[i][j] != 1){
                    st1.getSeats().buySeats(seat);
                }
            }
        }

        System.out.println("Expected: Sold Out");
        System.out.println("Actual: ");
        if(st1.getSeats().checkSoldOut()){
            System.out.println("Sold Out");
        }
        else{
            System.out.println("Not Sold Out");
        }
    }
}
