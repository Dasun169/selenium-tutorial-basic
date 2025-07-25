package runners;

import org.testng.TestNG;
import tests.LoginTest;
import tests.SignupTest;

import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<Class<?>> testClasses = new ArrayList<>();

        testClasses.add(SignupTest.class);
        testClasses.add(LoginTest.class);

        testNG.setTestClasses(testClasses.toArray(new Class[0]));
        testNG.run();
    }
}
