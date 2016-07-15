package TDDT.Compiler;


import vk.core.api.*;

import java.util.Collection;

/**
 * Created by burba on 30.06.2016.
 */
public class CompileHelper {

    private CompilationUnit _testUnit;
    private CompilationUnit _sourceUnit;
    private JavaStringCompiler _compiler;
    private CompilationUnit _featureTestUnit;

    public CompileHelper() {
    }

    public void AddSourceClass(String className, String classSource) {
        _sourceUnit = new CompilationUnit(className, classSource, false);
    }

    public void SetTest(String testClassName, String testClassSource) {
        _testUnit = new CompilationUnit(testClassName, testClassSource, true);
    }

    public void SetFeatureTest(String feauterTestClassName, String feautretestClassSource) {
        _featureTestUnit = new CompilationUnit(feauterTestClassName, feautretestClassSource, true);
    }


    public void CompileAndTest() {

        if (_featureTestUnit != null && _sourceUnit != null && _testUnit != null)
            _compiler = CompilerFactory.getCompiler(_sourceUnit, _testUnit, _featureTestUnit);
        else if (_sourceUnit != null && _testUnit != null)
            _compiler = CompilerFactory.getCompiler(_sourceUnit, _testUnit);
        else if (_sourceUnit != null && _featureTestUnit != null)
            _compiler = CompilerFactory.getCompiler(_sourceUnit, _featureTestUnit);
        else if (_testUnit != null && _featureTestUnit != null)
            _compiler = CompilerFactory.getCompiler(_featureTestUnit, _testUnit);
        else if (_featureTestUnit != null)
            _compiler = CompilerFactory.getCompiler(_featureTestUnit);
        else if (_testUnit != null)
            _compiler = CompilerFactory.getCompiler(_testUnit);
        else if (_sourceUnit != null)
            _compiler = CompilerFactory.getCompiler(_sourceUnit);
        
        _compiler.compileAndRunTests();
    }

    public TestResult GetTestResult() {
        return _compiler.getTestResult();
    }

    public CompilerResult GetCompilerResult() {
        return _compiler.getCompilerResult();
    }

    public boolean HasCompilerErrors() {
        return _compiler.getCompilerResult().hasCompileErrors();
    }

    public String GetSourceClassCompilerError() {
        return GetCompileError(_sourceUnit);
    }

    public String GetTestClassCompilerError() {
        return GetCompileError(_testUnit);
    }

    public String GetFeatureTestClassClassCompilerError() {
        return GetCompileError(_featureTestUnit);
    }

    private String GetCompileError(CompilationUnit cu) {
        if (cu == null)
            return "";

        Collection<CompileError> errors = _compiler.getCompilerResult().getCompilerErrorsForCompilationUnit(cu);
        if (errors == null)
            return "";

        String result = "";

        for (CompileError error : errors) {
            result += error.toString() + "\n";
        }

        return result;
    }

    public String GetCompilerErros() {
        String result = GetSourceClassCompilerError();
        result += "\n" + GetTestClassCompilerError();
        result += "\n" + GetFeatureTestClassClassCompilerError();
        return result;
    }

    public int NumberOfFailedTests() {
        return CountFailedFeatureTest(_testUnit);
    }

    public int NumberOfSucceddfulTests() {
        return _compiler.getTestResult().getNumberOfSuccessfulTests();
    }

    public String GetTestFaillures() {
        Collection<TestFailure> failures = _compiler.getTestResult().getTestFailures();

        String result = "";

        for (TestFailure failure : failures) {
            result += failure.getTestClassName() + ": " + failure.getMethodName() + "\n";
            result += failure.getMessage();
            //result += failure.getExceptionStackTrace();
            result += "\n\n";
        }

        return result;
    }

    public int NumberOfFailedFeatureTest(){
        return CountFailedFeatureTest(_featureTestUnit);
    }

    private int CountFailedFeatureTest(CompilationUnit cu){
        if(cu == null || _compiler.getTestResult().getNumberOfFailedTests() == 0)
            return 0;

        int result = 0;

        Collection<TestFailure> failures = _compiler.getTestResult().getTestFailures();

        for (TestFailure failure : failures)
            if(failure.getTestClassName().equalsIgnoreCase(cu.getClassName()))
                ++result;

        return result;
    }
}
