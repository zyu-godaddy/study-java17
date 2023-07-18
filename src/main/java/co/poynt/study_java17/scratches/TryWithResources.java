package co.poynt.study_java17.scratches;

import java.io.FileReader;
import java.io.IOException;

public class TryWithResources {

    void test() throws IOException  {

        // java-8: try(VarType varName = varInit)
        try(FileReader reader = new FileReader("/tmp/test.txt")) {
            while( -1 != reader.read() ){}
        }

        // sometimes, the resource is already initialized before try(){}

        // java-9: try(varName)
        FileReader reader = new FileReader("/tmp/test.txt");
        try(reader) {
            while( -1 != reader.read() ){}
        }

    }

}
