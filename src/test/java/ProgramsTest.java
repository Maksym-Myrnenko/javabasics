import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;
import static org.testng.Assert.*;

public class ProgramsTest extends Programs {

    @Test
    public void testProgramDuration() {
        Map<String, Integer> aqeCourse = new HashMap<String, Integer>() {{
            put("Java", 10);
            put("JDBC", 20);
            put("Spring", 10);
        }};

        Assert.assertEquals(programDuration(aqeCourse), 40, "Program duration isn't correct");
    }
}