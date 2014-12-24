package de.plushnikov.xjc.lombok;

import com.sun.codemodel.*;

import static org.junit.Assert.assertThat;

public class XjcLombokPluginTest {

    private final XjcLombokPlugin plugin = new XjcLombokPlugin();
    private final JCodeModel aModel = new JCodeModel();
    private final JPackage aPackage;
    private final JDefinedClass aClass;

    private final JMethod aSetter;
    private final JFieldVar aField;
    private final JFieldVar anotherField;
    private final JFieldVar aStaticField;
    private final JMethod aGetter;
    private final JDefinedClass aSuperClass;
    private final JFieldVar aSuperClassField;

    public XjcLombokPluginTest() throws Exception {
        aPackage = aModel._package("test");
        aClass = aPackage._class("AClass");

        aSetter = aClass.method(JMod.PUBLIC, aModel.VOID, "setField");

        aField = aClass.field(JMod.PRIVATE, aModel.INT, "field");
        anotherField = aClass.field(JMod.PRIVATE, aModel.BOOLEAN, "anotherField");
        aStaticField = aClass.field(JMod.STATIC|JMod.PUBLIC,aModel.SHORT,"staticField");
        aGetter = aClass.method(JMod.PUBLIC, aModel.INT, "getField");
        aGetter.body()._return(aField);
        final JVar setterParam = aSetter.param(aModel.INT, "field");
        aSetter.body().assign(aField, setterParam);

        aSuperClass = aPackage._class("ASuperClass");
        aClass._extends(aSuperClass);
        aSuperClassField = aSuperClass.field(JMod.PRIVATE, aModel.DOUBLE, "superClassField");
    }

//    @Test
//    public void testGenerateToString() {
//        plugin.generateToStringMethod(aModel,aClass);
//        final JMethod generatedMethod = aClass.getMethod("toString",new JType[0]);
//        assertThat(generatedMethod, not(nullValue()));
//        assertThat(generatedMethod.type().fullName(), equalTo(String.class.getName()));
//    }


}