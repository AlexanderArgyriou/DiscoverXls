package searcher.operations.operationsif;

import java.io.File;
import java.util.Set;

public interface XlsMemoryLoaderIf {
    void loadToMemory(File file);

    Set<String> getLoadedStringSetFromXls();
}
