package Test;

import TDDT.Compiler.CompileHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by burba on 30.06.2016.
 */
public class CompileHelper_Tests {

    @Test
    public void Test_CompilerAndTestRunner() {

        String sourceClass =
                "public class XMain {\n" +
                        "\n" +
                        "    public static boolean ToTest(){\n" +
                        "        return true;\n" +
                        "    }\n" +
                        "\n" +
                        "}";

        String testClass =
                "import org.junit.Test;\n" +
                        "import static org.junit.Assert.assertEquals;\n" +
                        "\n" +
                        "public class XMainTest {\n" +
                        "\n" +
                        "   @Test\n" +
                        "   public void Test(){\n" +
                        "       assertEquals(true, XMain.ToTest());\n" +
                        "   }\n" +
                        "}";

        CompileHelper compiler = new CompileHelper();
        compiler.AddSourceClass("XMain", sourceClass);
        compiler.SetTest("XMainTest", testClass);

        compiler.CompileAndTest();

        assertEquals(false, compiler.HasCompilerErrors());
        assertEquals(0, compiler.NumberOfFailedTests());
    }


    @Test
    public void Test_FeatureTest() {

        String sourceClass =
                "public class XMain {\n" +
                        "\n" +
                        "    public static boolean ToTest(){\n" +
                        "        return true;\n" +
                        "    }\n" +
                        "\n" +
                        "}";

        String testClass =
                "import org.junit.Test;\n" +
                        "import static org.junit.Assert.assertEquals;\n" +
                        "\n" +
                        "public class XMainTest {\n" +
                        "\n" +
                        "   @Test\n" +
                        "   public void Test(){\n" +
                        "       assertEquals(true, XMain.ToTest());\n" +
                        "   }\n" +
                        "}";

        CompileHelper compiler = new CompileHelper();
        compiler.AddSourceClass("XMain", sourceClass);
        compiler.SetFeatureTest("XMainTest", testClass);

        compiler.CompileAndTest();

        assertEquals(false, compiler.HasCompilerErrors());
        assertEquals(0, compiler.NumberOfFailedFeatureTest());
    }

}
