import java.time.format.*;

public class MovieTest {

    public static void main(String[] args){
        String title = "Morbius";
        String date = "03-12-2022";
        Movie m1 = new Movie(title, date);

        String title2 = "Morbius 2: It's Morbin' Time";
        String date2 = "06-12-2022";
        Movie m2 = new Movie(title2, date2);

        String title3 = "Morbius 3: One Last Morb";
        String date3 = "02-02-2023";
        Movie m3 = new Movie(title3, date3);

        System.out.println();
        System.out.println("TEST MEMBERS");
        System.out.print("EXPECTED VALUES:\nTitle: " + title + "\nRelease: December 3, 2022\nEarly: November 26, 2022\n");

        System.out.println("Actual: ");
        System.out.println("Title: " + m1.getTitle());
        System.out.println("Release: " + m1.getReleaseDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println("Early: " + m1.getEarlyAccess().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));

        System.out.println();
        System.out.println("TEST isReleased() and isEarly()");
        System.out.print("EXPECTED VALUES:\n"+ title + "\nReleased: Yes\nEarly: Yes\n");
        System.out.print(title2 + "\nReleased: No\nEarly: Yes\n");
        System.out.print(title3 + "\nReleased: No\nEarly: No\n");

        System.out.println("Actual: ");
        System.out.println(m1.getTitle());
        if(m1.isReleased()){
            System.out.println("Released: Yes");
        }
        else{ 
            System.out.println("Released: No");
        }
        if(m1.isEarly()){
            System.out.println("Early: Yes");
        }
        else{ 
            System.out.println("Early: No");
        }
        System.out.println(m2.getTitle());
        if(m2.isReleased()){
            System.out.println("Released: Yes");
        }
        else{ 
            System.out.println("Released: No");
        }
        if(m2.isEarly()){
            System.out.println("Early: Yes");
        }
        else{ 
            System.out.println("Early: No");
        }
        System.out.println(m3.getTitle());
        if(m3.isReleased()){
            System.out.println("Released: Yes");
        }
        else{ 
            System.out.println("Released: No");
        }
        if(m3.isEarly()){
            System.out.println("Early: Yes");
        }
        else{ 
            System.out.println("Early: No");
        }

        System.out.println();
        System.out.println("TEST addShowtime");
        m1.addShowtime(1, "03-12-2022", "4:20pm");

        System.out.print("EXPECTED VALUES:\nCounter: 2\nID: 1\nScreen: 1\nShowDate: December 3, 2022\nTime: 4:20pm\n");
        System.out.println("ACTUAL VALUES:");
        System.out.println("Counter: " + Integer.toString(Showtime.getCounter()));
        System.out.println("ID: " + Integer.toString(m1.getShowtime(0).getID()));
        System.out.println("Screen: " + Integer.toString(m1.getShowtime(0).getScreen()));
        System.out.println("ShowDate: " + m1.getShowtime(0).getShowDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println("Time: " + m1.getShowtime(0).getTime());
    }
}
