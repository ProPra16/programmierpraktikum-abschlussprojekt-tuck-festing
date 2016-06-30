import org.junit.Test;
import Compiler.*;
import vk.core.api.CompilerResult;
import vk.core.api.TestResult;

/**
 * Created by burba on 30.06.2016.
 */
public class CompileHelper_Tests {

    @Test
    public void Test_CompilerAndTestRunner(){

        String sourceClass =
                "public class Main {\n" +
                "\n" +
                "    public static boolean totest(){\n" +
                "        return true;\n" +
                "    }\n" +
                "\n" +
                "}";

        String testClass =
                "import org.junit.Test;\n" +
                "import static org.junit.Assert.assertEquals;\n" +
                "\n" +
                "public class MainTest {\n" +
                "\n" +
                "   @Test\n" +
                "   public void Test(){\n" +
                        "Main.totest();\n"+
                "       //assertEquals(false, Main.ToTest());\n" +
                "   }\n" +
                "}";

        CompileHelper compiler = new CompileHelper();
        compiler.AddSourceClass("Main", sourceClass);
        compiler.SetTest("MainTest", testClass);

        compiler.CompileAndTest();

        TestResult tResult = compiler.GetTestResult();
        CompilerResult cResult = compiler.GetCompilerResult();

        String tessttt = "   ";

    }

}