package co.poynt.study_java17.scratches;

// Java 16
// https://docs.oracle.com/javase/specs/jls/se17/html/jls-15.html#jls-15.20.2
// https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.30
// https://bugs.openjdk.org/browse/JDK-8250623

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public class PatternMatchingForInstanceof {

    // instanceof was frowned upon.
    // so why even add new features to it, make it easier to use?
    // for algebraic datatypes.

    void basicUsage(Object obj) {
        if (obj instanceof String s) {
            s.toLowerCase();
        } else if (obj instanceof Integer i) {
            i.intValue();
        }

    }

    void instanceofSupertype(Object obj) {

        // this is allowed, as previously.
        // it could be false only if obj==null
        if (obj instanceof Object) {
            obj.getClass();
        }

        // this is *not* allowed
        //   if (obj instanceof Object o)
        // probably because it is most likely a programming mistake,
        // therefore rejected in a new language construct.

    }

    void instanceofParameterizedType(List<Integer> list) {

        // previously the type must be reifiable
        if (list instanceof ArrayList<?>){}

        // now, this is allowed.
        if (list instanceof ArrayList<Integer>){}

        if (list instanceof ArrayList<Integer> arrayList){}

    }

    void scopeOfPatternVariable(Object obj) throws Exception {

        // jls > The scope of a pattern variable declaration ... is the part of the program that might be executed after the matching

        if (obj instanceof String str && str.length()>0){}

        if (obj instanceof Runnable r && obj instanceof Callable c) {
            r.run();
            c.call();
        }

        if (!(obj instanceof String str) || str.length()>0){}

        {
            if(!(obj instanceof Runnable r) || !(obj instanceof Callable c)) {
                // r and c not in scope
            } else {
                r.run();
                c.call();
            }
        }

        {
            var v1 = (obj instanceof String str)
                    && str.length()>10
                    && str.startsWith("abc");

            var v2 = !(obj instanceof String str)
                    || str.length()>10
                    && str.startsWith("abc");
        }

        {
            var v1 = (obj instanceof String str) ? str.toLowerCase() : "na";

            var v2 = !(obj instanceof String str) ? "na" : str.toLowerCase();

            // simulate pattern matching for switch expressions
            var v4 = Boolean.FALSE ? null
                    : (obj instanceof String s)
                    ? s.toLowerCase()
                    : (obj instanceof Integer i && i>0)
                    ? "x".repeat(i)
                    : "na";

            var v5 = obj==null ? null
                    : !(obj instanceof List<?> list) ? ""
                    : !(obj instanceof Set<?> set) ? ""+list.get(0)
                    : ""+list.get(0)+","+set.contains("a");
        }



        {
            if (obj instanceof String str) {
                str.toLowerCase();
            }

            // out of scope
            //   str.toLowerCase();
        }

        {
            if (!(obj instanceof String str)) {
                if (Math.tan(1)>1)
                    return;
                else
                    throw new Error();
            }
            // str is introduced; obj must be a non-null String from here on.
            str.toLowerCase();
        }

        {
            if (!(obj instanceof Runnable r)) {
                ;
            } else {
                r.run();
            }

            if (!(obj instanceof Runnable r)) {
                ;
            } else if (!(obj instanceof Callable c)) {
                r.run();
            } else {
                r.run();
                c.call();
            }
        }

        {
            while (obj instanceof String str) {
                obj = str.toLowerCase();
            }

            while (!(obj instanceof String str)) {
                obj = String.valueOf(obj);
            }
            // str is introduced
            str.toLowerCase();
        }
        {
            do {
                obj = String.valueOf(obj);
            }while (!(obj instanceof String str));
            // str is introduced
            str.toLowerCase();
        }

        {
            for (int i=0; obj instanceof String str; str.toLowerCase() ) {
                str.toUpperCase();
            }

            for ( ; !(obj instanceof String str) ; ) {
                ;
            }
            // str is introduced
            str.toLowerCase();
        }




    }

}
