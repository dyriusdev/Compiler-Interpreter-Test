    import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JavaCompiler {

    public static void compile(String file) throws Exception {
        String className = file.substring(file.lastIndexOf("\\") + 1, file.lastIndexOf("."));
        List<String> argsCompilation = new ArrayList<>();
        argsCompilation.add("javac");
        argsCompilation.add(file);

        ProcessBuilder compilationBuilder = new ProcessBuilder(argsCompilation);
        Process compilationProcess = compilationBuilder.start();

        BufferedReader out = new BufferedReader(new InputStreamReader(compilationProcess.getInputStream()));
        String outComp;
        while ((outComp = out.readLine()) != null) {
            System.out.println(outComp);
        }

        BufferedReader errorsCompilation = new BufferedReader(new InputStreamReader(compilationProcess.getErrorStream()));
        while ((outComp = errorsCompilation.readLine()) != null) {
            System.err.println(outComp);
        }

        int code = compilationProcess.waitFor();
        if (code != 0) {
            System.err.println("Compilação falhou.");
            return;
        }

        List<String> argsExec = new ArrayList<>();
        argsExec.add("java");
        argsExec.add(className);

        ProcessBuilder execBuilder = new ProcessBuilder(argsExec);
        Process processExec = execBuilder.start();

        BufferedReader outExec = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        String lineExec;
        while ((lineExec = outExec.readLine()) != null) {
            System.out.println(lineExec);
        }

        BufferedReader errorsExec = new BufferedReader(new InputStreamReader(processExec.getErrorStream()));
        while ((lineExec = errorsExec.readLine()) != null) {
            System.err.println(lineExec);
        }

        processExec.waitFor();
    }
}
