import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PythonInterpreter {

    public static void interpret(String file) throws Exception {
        List<String> args = new ArrayList<>();
        args.add("python3");
        args.add(file);

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
