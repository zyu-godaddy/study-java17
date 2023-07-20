package co.poynt.study_java17.scratches;


// https://docs.oracle.com/javase/specs/jls/se17/html/jls-8.html#jls-8.10

// https://openjdk.org/projects/amber/design-notes/records-and-sealed-classes


import java.util.Objects;

public class Records {

    void basics() {

        // "components"
        record Item(String name, int size){}

        Item item = new Item("a", 1);

        // "accessor methods"
        item.name();
        item.size();
        // component name can't be a method in Object, e.g. hashCode, notify

        // default impls
        item.hashCode();
        item.toString();
        item.equals(item);
    }

    // encourage ad-hoc tuple definitions
    // programmers are hesitant to define new classes
    //   - fear of too many classes
    //   - getters/setters/equals... boilerplate
    // language designer encourage to define records whenever needed

    // implicitly 'final static'

    // local records
    //   sometimes we need tuples in method body implementation

    // method parameter and return type
    //    if it's only relevant to this method, no need to define it as a top-level type
    //    can be defined in the same place as the method.
    //    ideally there should be "anonymous" record for that:  public (String name, int value) someFunction()
    //
    public record NV(String name, int value){}
    public NV process(NV input){
        return input;
    }

    // in the big picture, record is to support algebraic datatypes
    // it's transparent on purpose. in particular, for deconstruction:  switch(){ case NV(name,value) ->

    // extra methods in record class
    public record Item(String name) {

        public String nameUp(){
            return this.name().toUpperCase();
        }

        public static Item ofId(int id) {
            return new Item("x-"+id);
        }

        // compact canonical constructor
        public Item{
            // check constructor arguments
            if (name.length()<2)
                throw new IllegalArgumentException("name too short");
            // compiler will take care of the rest
            // this.name = name;
        }
    }

    // constructors
    public record ItemB(String name, int value) {

        // normal, non-compact, explicit canonical constructor
        public ItemB(String name, int value){
            this.name = name;
            this.value = value;
        }

        // other constructors. confusing. factor methods are probably better.
        public ItemB(String name){
            // non-canonical constructor must delegate to another constructor
            // (i.e. eventually to the canonical constructor)
            this(name, 0);
        }

    }


    // ======================================================================
    // bad practices that are allowed... but should be avoided

    // mutable components
    // in particular, varargs
    void varargs() {
        record Item(String name, int... sizes){}
        Item item = new Item("a", 1, 2, 3);
        item.sizes()[0] = 100;
    }

    // you can make records very complicated; but it defeats the spirit

    // override accessor; transform component value
    record ItemX(String name, int value){
        // this is probably fine
        public String name(){
            System.out.println("debug");
            return this.name;
        }
        // this would bring confusion
        public int value() {
            return value*2;
        }
        // note that default hashCode/equals/toString() impls
        // read this.value, not this.value()

        // override hashCode/equals/toString
        public int hashCode() {
            return 0;
        }

        public ItemX{
            // this brings confusion
            name = name.toLowerCase();
        }

        // this is ok
        public static ItemX norm(String name, int value) {
            name = name.toLowerCase();
            return new ItemX(name, value);
        }
    }

}
