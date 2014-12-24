package de.plushnikov.xjc.lombok;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JVar;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class XjcLombokPluginTest {

    private final XjcLombokPlugin plugin = new XjcLombokPlugin();
    private final JDefinedClass aClass;

    public XjcLombokPluginTest() throws Exception {
        JCodeModel aModel = new JCodeModel();
        JPackage aPackage = aModel._package("test");
        aClass = aPackage._class("AClass");

        JMethod aSetter = aClass.method(JMod.PUBLIC, aModel.VOID, "setField");

        JFieldVar aField = aClass.field(JMod.PRIVATE, aModel.INT, "field");
        aClass.field(JMod.PRIVATE, aModel.BOOLEAN, "anotherField");
        aClass.field(JMod.STATIC | JMod.PUBLIC, aModel.SHORT, "staticField");
        JMethod aGetter = aClass.method(JMod.PUBLIC, aModel.INT, "getField");
        aGetter.body()._return(aField);
        final JVar setterParam = aSetter.param(aModel.INT, "field");
        aSetter.body().assign(aField, setterParam);

        JDefinedClass aSuperClass = aPackage._class("ASuperClass");
        aClass._extends(aSuperClass);
        aSuperClass.field(JMod.PRIVATE, aModel.DOUBLE, "superClassField");
    }

    @Test
    public void testGenerateToString() {
        plugin.generateLombokAnnotations(aClass);
        assertTrue(true);
    }
}