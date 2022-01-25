package searcher.instantiations;

import searcher.operations.operationsif.FileLoaderPathIf;
import searcher.operations.operationsif.ProducerIf;
import searcher.operations.operationsif.SearcherIf;
import searcher.operations.operationsif.XlsMemoryLoaderIf;
import searcher.operations.operationsimpl.FileLoaderPathImpl;
import searcher.operations.operationsimpl.ProducerImpl;
import searcher.operations.operationsimpl.SearcherImpl;
import searcher.operations.operationsimpl.XlsMemoryLoaderImpl;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaticFactory {
    private StaticFactory() {
    }

    public static List<String> createArrayList() { return new ArrayList<>(); }

    public static Set<String> createHashSet() {
        return new HashSet<>();
    }

    public static XlsMemoryLoaderIf createXlsMemoryLoader() {
        return new XlsMemoryLoaderImpl();
    }

    public static SearcherIf createSearcher() {
        return new SearcherImpl();
    }

    public static FileLoaderPathIf createFileLoaderPath(String path) {
        return new FileLoaderPathImpl(path);
    }

    public static ProducerIf createProducer(Path path, List<String> result) {
        return new ProducerImpl(path, result);
    }

    public static File createFile(Path path, String fileName) {
        return new File(path.toString(), fileName);
    }
}
