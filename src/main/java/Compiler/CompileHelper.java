package Compiler;


import vk.core.api.*;

import java.util.ArrayList;

/**
 * Created by burba on 30.06.2016.
 */
public class CompileHelper {

    boolean _changed = true;
    private CompilationUnit _testUnit;
    private ArrayList<CompilationUnit> _sourceUnits = new ArrayList<CompilationUnit>(1);
    private JavaStringCompiler _compiler;

    public CompileHelper() {
    }

    public void AddSourceClass(String className, String testClassSource) {
        RemoveSourceClass(testClassSource);
        _sourceUnits.add(new CompilationUnit(className, testClassSource, false));
        _changed = true;
    }

    public boolean RemoveSourceClass(String className) {
        int i = 0;
        for (CompilationUnit unit : _sourceUnits) {
            if (unit.getClassName().equalsIgnoreCase(className)) {
                _sourceUnits.remove(0);
                _changed = true;
                return true;
            }

            ++i;
        }

        return false;
    }

    public String GetTestClassName() {
        return _testUnit.getClassName();
    }

    public String[] SourceClassNames() {
        String[] result = new String[_sourceUnits.size()];

        for (int i = 0; i < result.length; ++i)
            result[i] = _sourceUnits.get(i).getClassName();

        return result;
    }

    public void SetTest(String testClassName, String testClassSource) {
        _testUnit = new CompilationUnit(testClassName, testClassSource, true);
        _changed = true;
    }


    public void CompileAndTest() {
        if (_changed) {
            _sourceUnits.trimToSize();

            ArrayList<CompilationUnit> units = (ArrayList<CompilationUnit>) _sourceUnits.clone();
            units.add(_testUnit);
            CompilationUnit[] unitArray = units.toArray(new CompilationUnit[0]);
            _compiler = CompilerFactory.getCompiler(unitArray);
            _changed = false;
        }

        _compiler.compileAndRunTests();
    }

    public TestResult GetTestResult() {
        return _compiler.getTestResult();
    }

    public CompilerResult GetCompilerResult() {
        return _compiler.getCompilerResult();
    }
}
