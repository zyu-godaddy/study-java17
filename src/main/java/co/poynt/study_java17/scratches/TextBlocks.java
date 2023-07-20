package co.poynt.study_java17.scratches;

// https://docs.oracle.com/en/java/javase/17/text-blocks/index.html
// https://docs.oracle.com/javase/specs/jls/se17/html/jls-3.html#jls-3.10.6
public class TextBlocks {

    public static void main(String[] args) {

        var s1 = """
                line 1
                line 2
                """;

        // no other chars after opening """ other than spaces. No // comments!

        // empty string
        var s2 = """
                """;

        // escape sequences
        // \ must be escaped. other chars usually don't need to be.

        var s3 = """
                the "double quote" needs no escape. \s\s
                  typical \t \\ escapes work too \" \077 \u003F \r
                line 3 \
                line 3 continued
                """
                //.stripIndent()
                //.translateEscapes()
                ;
        // text block content to string
        // 0. \ u hhhh are translated at the first step of compilation, before any other lexical processing.
        // 1. CRLF, CR, LF => LF. Notice on line 2, \r is needed to generate the eventual CRLF (\r\n)
        // 2. algo stripIndent() is applied.
        //    - trailing spaces are removed as well. on line 1, we used \s\s to keep trailing spaces.
        //    - escapes like \s and \r are preserved at this step.
        // 3. algo translateEscapes() is applied.
        //    - \<line-terminator> is translated to empty, joining line 3

        // escape triple DQ : \"""
        // double DQ is fine:  { "comment": "" }

        // control indentation
        var s4 = """
                indent of 2
                  indent of 4
              """
                .stripTrailing()
                .indent(4);
        // move closing """ to the left.
        // however, the string ends with LF. may fix it with stripTrailing()
        // can add indent with indent(n)

        // no point for moving closing """ to the right
        String s5 = """ 
                    line 1
                    line 2
                       """;
        // spaces on line 3 are removed (for being trailing spaces)


        // a trick
        String s7 = """
                this string ends with SPACE but not LF \
                """;

    }

}
