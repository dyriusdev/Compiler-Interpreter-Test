public class Program {

    public static void main(String[] args) {
        try {
            JavaCompiler.compile("Test.java");
            PythonInterpreter.interpret("test.py");
            CCompiler.compile("test.c");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
