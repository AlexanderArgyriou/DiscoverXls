package searcher.operations.operationsimpl;

import searcher.operations.operationsif.FileLoaderPathIf;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLoaderPathImpl implements FileLoaderPathIf {
    private Path pathToXlsFiles;

    public FileLoaderPathImpl() {
    }

    public FileLoaderPathImpl(Path pathToXlsFiles) {
        this.pathToXlsFiles = pathToXlsFiles;
    }

    public FileLoaderPathImpl(String pathToXlsFiles) {
        this.pathToXlsFiles = Paths.get(pathToXlsFiles);
    }

    @Override
    public Path getPathToXlsFiles() {
        return pathToXlsFiles;
    }

    public void setPathToXlsFilesFromString(Path pathToXlsFiles) {
        this.pathToXlsFiles = pathToXlsFiles;
    }

    @Override
    public void setPathToXlsFilesFromString(String pathToXlsFiles) {
        this.pathToXlsFiles = Paths.get(pathToXlsFiles);
    }
}
