import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StudentsCourses extends Programs {

    private final String studentFullName;
    private final String courseTitle;
    private final Map<String, Integer> courseMap;

    public static StudentsCourses[] getStudents() {
        StudentsCourses[] studentsList = new StudentsCourses[2];
        studentsList[0] = new StudentsCourses("Ivanov Ivan", "Java Developer", getJavaDevCourse());
        studentsList[1] = new StudentsCourses("Sidorov Ivan", "AQE", getAqeCourse());
        return studentsList;
    }

    StudentsCourses(String studentFullName, String courseTitle, Map<String, Integer> courseMap) {
        this.studentFullName = studentFullName;
        this.courseTitle = courseTitle;
        this.courseMap = courseMap;
    }

    public void getShortInfo(LocalDate startDate) {
        int passedDays = calculateBusinessDays(startDate, LocalDate.now());
        int programDuration = programDuration(courseMap);

        generateReportDate();
        if (programDuration < passedDays * 8 + getHoursOfDayPassed()) {
            passedDays = passedDays - programDuration / 8;
            System.out.println(studentFullName + " (" + courseTitle + ") " + "- Training completed. " +
                    passedDays + " day (s) " + getHoursOfDayPassed() + " hour(s)" + " have passed since the end.\n\n");
        } else {
            passedDays = programDuration / 8 - passedDays;
            System.out.println(studentFullName + " (" + courseTitle + ") " + "- Training is not finished. " +
                    passedDays + " day (s) " + getHoursOfDayLeft() + " hour(s)" + " are left until the end.\n\n");
        }
    }

    public void getFullInfo(LocalDate startDate) {
        int passedDays = calculateBusinessDays(startDate, LocalDate.now());
        int programDuration = programDuration(courseMap);

        generateReportDate();
        if (programDuration < passedDays * 8 + getHoursOfDayPassed()) {
            System.out.println("Student name: " + studentFullName + "\nWorking time: from 10 to 18" +
                    "\nProgram name: " + courseTitle + "\nProgram duration: " + programDuration(courseMap) +
                    "\nHours: " + "10:00 - 18:00" +
                    "\nStart date: " + startDate + "\nEnd date: " + getEndCourseDate(startDate) +
                    "\nTime passed until completion: "
                    + calculateBusinessDays(startDate, LocalDate.now()) + " day(s) " + getHoursOfDayPassed() + "hour(s)\n\n");
        } else {
            System.out.println("Student name: " + studentFullName + "\nWorking time: from 10 to 18" +
                    "\nProgram name: " + courseTitle + "\nProgram duration: " + programDuration(courseMap) +
                    "\nHours: " + "10:00 - 18:00" +
                    "\nStart date: " + startDate + "\nEnd date: " + getEndCourseDate(startDate) +
                    "\nTime remains until completion: "
                    + calculateBusinessDays(startDate, LocalDate.now()) + " day(s) " + getHoursOfDayPassed() + "hour(s)\n\n");
        }
    }

    private LocalDate getEndCourseDate(LocalDate startDate) {
        LocalDate result = startDate;
        int addedDays = 0;
        while (addedDays < programDuration(courseMap) / 8) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }

    private int getHoursOfDayLeft() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && calendar.get(Calendar.HOUR_OF_DAY) < 18) {
            return 18 - Calendar.HOUR_OF_DAY;
        } else {
            return 0;
        }
    }

    private int getHoursOfDayPassed() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && calendar.get(Calendar.HOUR_OF_DAY) < 18) {
            return Calendar.HOUR_OF_DAY - 10;
        } else {
            return 0;
        }
    }

    private int calculateBusinessDays(final LocalDate startDate, final LocalDate endDate) {

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Invalid method argument(s) to " +
                    "countBusinessDaysBetween (" + startDate
                    + "," + endDate + ")");
        }

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        return (int) Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(daysBetween)
                .filter(isWeekend.negate()).count();
    }

    private static void generateReportDate() {
        System.out.println("Report Generated On: "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy, E, HH:mm")) + "\n");
    }
}
