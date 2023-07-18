package co.poynt.study_java17.scratches;


// java-9
public interface InterfaceWithPrivateMethods {

    public String firstName();
    public String lastName();

    // default method (public interface method with default implementation)
    default
    public String fullName() {
        return firstName() + " " + lastName();
    }

    // but for more sophisticated default implementation,
    // we may want to invoke private utility methods
    default String fullName2() {
        return concat( norm(firstName()), norm(lastName()) );
    }

    // private static|instance methods ---------

    private
    static
    String norm(String s) {
        return s.trim().toUpperCase();
    }

    private
    String concat(String s1, String s2) {
        return s1+" "+s2;
    }

}
