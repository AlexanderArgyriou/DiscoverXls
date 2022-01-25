package searcher.instantiations;

import searcher.exceptions.ArgException;
import searcher.processes.processesif.SearchProcessIf;
import searcher.processes.processesimpl.SearchProcessImpl;

import java.util.Arrays;
import java.util.Set;

public class Setup {
    private Setup() {
    }

    public static SearchProcessIf searchProcessFactoryMethod(String[] arguments)
            throws ArgException {
        if (arguments.length < 1) {
            throw new ArgException("You have to insert at least 1 argument " +
                    "which is the absolute path to .xls file(s). Arguments 2..N are keywords");
        }
        String path = arguments[0];
        Set<String> keywords = StaticFactory.createHashSet();
        keywords.addAll(Arrays
                .asList(arguments)
                .subList(1, arguments.length));
        return new SearchProcessImpl(StaticFactory.createFileLoaderPath(path), keywords);
    }
}
