/*
 * Copyright 2014 Michail Plushnikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.plushnikov.xjc.lombok;

import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.xml.sax.ErrorHandler;

import java.io.IOException;

/**
 * <p>Generates hashCode, equals and toString annotations using Lombok.</p>
 *
 * @author Michail Plushnikov
 */
public class XjcLombokPlugin extends Plugin {

    public static final String OPTION_NAME = "Xlombok";

    @Override
    public String getOptionName() {
        return OPTION_NAME;
    }

    @Override
    public String getUsage() {
        return "  -" + OPTION_NAME + "\t:  enable generation of lombok @ToString, @EqualsAndHashCode annotations";
    }

    @Override
    public int parseArgument(Options opt, String[] args, int i) throws BadCommandLineException, IOException {
        return 0;
    }

    @Override
    public boolean run(final Outline outline, final Options options, final ErrorHandler errorHandler) {
        // For each defined class
        for (final ClassOutline classOutline : outline.getClasses()) {
            final JDefinedClass implClass = classOutline.implClass;

            final JAnnotationUse toStringAnnotation = implClass.annotate(ToString.class);
            final JAnnotationUse equalsAndHashCodeAnnotation = implClass.annotate(EqualsAndHashCode.class);
            if (implClass._extends() instanceof JDefinedClass) {
                toStringAnnotation.param("callSuper", true);
                equalsAndHashCodeAnnotation.param("callSuper", true);
            }
        }
        return true;
    }
}