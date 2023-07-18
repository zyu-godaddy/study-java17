

## Java 17 features (since Java 8)


### Language

Java 9 Diamond operator for anonymous classes
- TypeReference

Java 9 Try with resources

Java 9 Private methods in interface

Java 10 Local Variable Type Inference
- https://developer.oracle.com/learn/technical-articles/jdk-10-local-variable-type-inference

### API

Java 9 Collection factory methods




### Library

Java 9 java.net.http.HttpClient

### Optimizations

**Java 9 Compact String** -
`byte[]` instead of `char[]` in `String`,
saving 50% memory for Latin1 strings.

**Java 9 Indify String Concatenation** -
do string concat with `str+str` instead of with StringBuilder;
don't worry about performance.

### more


### CLI

Java 9 
`jshell`

Java 11
`java HelloWorld.java`

Java 14
`jpackage` - create native installer

### others

Java 9 Optional Class Improvements

Java 9 Stream API Improvements

Java 9 StackWalking API

Java 10 List.copyOf()

Java 11 Flight Recorder - profiler for prod

Java 11 Predicate.not(p)

Java 12 CompactNumberFormat. 1234 -> "1.2K"


## References

https://docs.oracle.com/javase/specs/jls/se17/html/index.html

https://openjdk.org/projects/amber/design-notes/records-and-sealed-classes

