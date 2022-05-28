import java.time.LocalDate;
import java.util.Scanner;

public class Summary {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Input '1' to get short info. '2' - full info. Input '0' to quit the program:");
            Scanner sc = new Scanner(System.in);
            int infoType = Integer.parseInt(sc.nextLine());
            switch (infoType) {
                case 1:
                    for (StudentsCourses student :
                            StudentsCourses.getStudents()) {
                        System.out.println("Input course starting year: ");
                        int year = Integer.parseInt(sc.nextLine());
                        System.out.println("Input course starting month: ");
                        int month = Integer.parseInt(sc.nextLine());
                        System.out.println("Input course starting day: ");
                        int day = Integer.parseInt(sc.nextLine());

                        student.getShortInfo(LocalDate.of(year, month, day));
                    }
                    break;
                case 2:
                    for (StudentsCourses student :
                            StudentsCourses.getStudents()) {
                        System.out.println("Input course starting year: ");
                        int year = Integer.parseInt(sc.nextLine());
                        System.out.println("Input course starting month: ");
                        int month = Integer.parseInt(sc.nextLine());
                        System.out.println("Input course starting day: ");
                        int day = Integer.parseInt(sc.nextLine());

                        student.getFullInfo(LocalDate.of(year, month, day));
                    }
                    break;
                default:
                    System.exit(1);
            }
        }
    }
}
