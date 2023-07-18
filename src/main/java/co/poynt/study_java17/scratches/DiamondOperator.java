package co.poynt.study_java17.scratches;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DiamondOperator {

    void test() {

        // Java-5: introduced Generics
        List<String> a = DiamondOperator.<String>newList();
        // type inference
        List<String> b = DiamondOperator.newList();
        // no type inference for constructor!
        List<String> c = new ArrayList<String>();
        // vexing to type <String> twice.

        // why?
        //   constructor can have two kinds of type parameters
        class Foo<E> {
            <T> Foo(T t){}
        }
        Foo<String> foo1 = new <Integer>Foo<String>(1);
        // type inference worked only on method/constructor type parameters (T), not on E.
        Foo<String> foo2 = new Foo<String>(2);

        {
            // this works... but it means something else (raw type, which is frowned upon)
            List<String> c2 = new ArrayList();

            // use raw type anyway, if there's no choice, or if it's much simpler.
            @SuppressWarnings({"rawtypes", "unchecked"})
            List<String> c3 = new ArrayList();
        }


        // Java-7: type inference on E with <>
        Foo<String> foo3 = new Foo<>(3);
        // common use case
        List<String> d = new ArrayList<>();

        // however, <> did not work on anonymous classes
        Callable<String> call1 = new Callable<String>() {
            public String call() { return "?"; }
        };

        // Java-9: <> works on anonymous classes
        Callable<String> call2 = new Callable<>() {
            public String call() { return "?"; }
        };

        // =======================================================================
        // Use cases:
        //   see JacksonTypeReference
        // =======================================================================

    }

    static <E> List<E> newList() {
        return new ArrayList<E>();
    }


}
