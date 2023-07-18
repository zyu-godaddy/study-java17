package co.poynt.study_java17.scratches;

import java.io.Closeable;
import java.util.HashMap;
import java.util.List;


public class VarForNonDenotableTypes {

    // some types are created and understood by javac,
    // but programmers cannot reference them by name in java source

    // Anonymous class
    void test1() throws Exception {

        {
            Object obj = new Object(){
                int z=0;
                int add(int x, int y){ return x+y; }
            };
            // does not compile: `obj` static type is Object
            //   obj.z++;
            //   obj.add(1,1);
        }

        // work around: define a named local class
        {
            class MyObject{
                int z=0;
                int add(int x, int y){ return x+y; }
            }
            MyObject obj = new MyObject();
            obj.z++;
            obj.add(1,1);
        }

        // with 'var'
        {
            var obj = new Object(){
                int z=0;
                int add(int x, int y){ return x+y; }
            };
            // works! `obj` static type is inferred as the (internally named) anonymous class
            obj.z++;
            obj.add(1,1);
        }


        // Use case: container of mutables
        {
            var stat = new Object(){
                int count = 0;
                int sum = 0;
                double avg(){ return 1.0*sum/count; }
            };
            List.of(1, 2, 3, 4).forEach(x->{
                stat.count++;
                stat.sum += x;
            });
            System.out.println(stat.avg());
        }


        // Use case: define local functions
        {
            var s2i = new HashMap<String,Integer>();

            var F = new Object(){
                void register(String s, int i, boolean override) {
                    if (override || !s2i.containsKey(s)) {
                        s2i.put(s, i);
                    }
                }
                void printIndex(String key) {
                    System.out.printf("%s", s2i.getOrDefault(key, -1));
                }
            };

            var keys = List.of("a", "b", "a");
            for (int i=0; i<keys.size(); i++) {
                F.register(keys.get(i), i, i%2==0);
            }

            s2i.keySet().forEach(F::printIndex);
        }
        // for local static function, just use record/interface with static methods.
        {
            record F(){
                static int add(int x, int y) { return x+y; }
            }

            interface G{
                static int add(int x, int y) { return x+y; }
            }

            int z1 = F.add(1, 2);
            int z2 = G.add(1, 2);
        }

    }

    // Intersection type
    void test2(Object item) throws Exception {

        // suppose we are certain `item` must be both Runnable and Closeable

        {
            Runnable r = (Runnable)item;
            Closeable c = (Closeable)item;
            r.run();
            c.close();
        }

        {
            var rc = (Runnable & Closeable)item;
            rc.run();
            rc.close();
        }

    }

    /* https://developer.oracle.com/learn/technical-articles/jdk-10-local-variable-type-inference
        Not all non-denotable types can be used with var - anonymous classes and intersection types are supported.
        Wildcard captured types are not inferred so as to avoid even more cryptic wildcard related error messages
        being exposed to Java programmers
     */
}
