package searcher.operations.operationsif;

import java.nio.file.Path;

public interface FileLoaderPathIf {
    void setPathToXlsFilesFromString(String pathToXlsFiles);

    Path getPathToXlsFiles();
}
