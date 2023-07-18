package co.poynt.study_java17.scratches;

import java.nio.file.Files;
import java.nio.file.Paths;

public class NewApi_File {

    void test() throws Exception {

        var path1 = Paths.get("/tmp/test1.txt");
        var path2 = Paths.get("/tmp/test2.txt");

        // Java 11

        String fileContent = Files.readString(path1);

        Files.writeString(path2, "the password is password");


        // Java 12

        // compare file contents
        long first_position_of_diff = Files.mismatch(path1, path2);

    }

}
