/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author ersin
 */
public class Validator {

    public List<String> check(Object obj) {

        List<String> result = new LinkedList<>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field fld : fields) {
            for (Annotation ann : fld.getAnnotations()) {
                try {

                    String name = fld.getName();
                    String methodName = "get" + name.substring(0, 1).toUpperCase(Locale.UK)+ name.substring(1);
                    Method m = obj.getClass().getMethod(methodName, new Class[0]);
                    Object val = m.invoke(obj, new Object[0]);

                    notNullCheck(ann, val, result, name);

                    notEmptyCheck(ann, val, result, name);
                    
                    patternCheck(ann, val, result, name);

                    lengthCheck(ann, val, result, name);
                } catch (Exception e) {
                    result.add(e.getMessage());
                }

            }
        }

        return result;

    }

    protected void lengthCheck(Annotation ann, Object val, List<String> result, String name) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (ann instanceof Size) {

            Size annS = (Size) ann;

            if (val == null) {

                result.add(name + ", null olamaz!");

            } else {

                int size = -1;

                if (val instanceof Collection) {
                    size = ((Collection) val).size();
                }

                if (val instanceof String) {
                    size = ((String) val).length();

                }

                if (val.getClass().isArray()) {
                    size = Array.getLength(val);
                }

                if (annS.max() < size) {
                    result.add(annS.max() + " max degeri aşıldı :" + size);
                }

                if (annS.min() > size) {
                    result.add(name + ", alanı için " + annS.min() + " min degeri sağlanamadı :" + size);
                }

            }
        }
    }

    protected void notNullCheck(Annotation ann, Object val, List<String> result, String name) {
        if (ann instanceof NotNull && val == null) {
            result.add(name + ", null olamaz! ");
        }
    }

    private void notEmptyCheck(Annotation ann, Object val, List<String> result, String name) {
        if (ann instanceof NotEmpty) {

            if (val == null) {
                result.add(name + ", null olamaz! ");
            } else if (String.valueOf(val).equals("")) {
                result.add(name + ", empty olamaz! ");
            }
        }
    }

    private void patternCheck(Annotation ann, Object val, List<String> result, String name) {
       if (ann instanceof Pattern) {

            if (val == null) {
                result.add(name + ", null olamaz! ");
            } else if (!String.valueOf(val).matches(((Pattern)ann).regexp())) {
                result.add(name + ", eşleşmedi!: "+((Pattern)ann).regexp() );
            }
        }
    }

}
