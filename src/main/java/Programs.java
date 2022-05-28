import java.util.HashMap;
import java.util.Map;

public class Programs {

    private static final Map<String, Integer> javaDevCourse = new HashMap<>();
    private static final Map<String, Integer> aqeCourse = new HashMap<>();

    public static Map<String, Integer> getJavaDevCourse() {
        javaDevCourse.put("Java", 16);
        javaDevCourse.put("JDBC", 24);
        javaDevCourse.put("Spring", 16);
        return javaDevCourse;
    }

    public static Map<String, Integer> getAqeCourse() {
        aqeCourse.put("Test design", 10);
        aqeCourse.put("Page Object", 16);
        aqeCourse.put("Selenium", 16);
        return aqeCourse;
    }

    public static int programDuration(Map<String, Integer> program) {
        return program.values().stream().mapToInt(Integer::intValue).sum();
    }
}
