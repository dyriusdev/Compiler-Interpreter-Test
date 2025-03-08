import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CCompiler {

    public static void compile(String file) throws Exception {
        List<String> args = new ArrayList<>();
        args.add("gcc");
        args.add(file);
        args.add("-o");
        args.add("executable");

        ProcessBuilder builder = new ProcessBuilder(args);
        Process process = builder.start();

        BufferedReader out = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String outLine;
        while ((outLine = out.readLine()) != null) {
            System.out.println(outLine);
        }

        BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((outLine = errors.readLine()) != null) {
            System.err.println(outLine);
        }

        process.waitFor();
    }
}
