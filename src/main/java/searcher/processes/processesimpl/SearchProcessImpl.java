package searcher.processes.processesimpl;

import searcher.instantiations.StaticFactory;
import searcher.operations.operationsif.FileLoaderPathIf;
import searcher.operations.operationsif.ProducerIf;
import searcher.operations.operationsif.SearcherIf;
import searcher.operations.operationsif.XlsMemoryLoaderIf;
import searcher.processes.processesif.SearchProcessIf;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SearchProcessImpl implements SearchProcessIf {
    private FileLoaderPathIf fileLoaderPathIf;
    private List<String> result;
    private Set<String> keyWords;

    public SearchProcessImpl() {
    }

    public SearchProcessImpl(FileLoaderPathIf fileLoaderPathIf,
                             Set<String> keyWords) {
        this.fileLoaderPathIf = fileLoaderPathIf;
        this.keyWords = keyWords;
        this.result = StaticFactory.createArrayList();
    }

    @Override
    public void presentResult() {
        Collections.sort(result);
        for (String sentence : result) {
            System.out.println(sentence);
        }
    }

    @Override
    public void produceResults() {
        ProducerIf producer = StaticFactory.createProducer(Paths.get(System.getProperty("user.dir")), result);
        producer.produce();
    }

    @Override
    public void run() {
        this.runRecursive(fileLoaderPathIf.getPathToXlsFiles()
                .toFile());
    }

    private void runRecursive(File current) {
        File[] files = current.listFiles();
        if (files != null) {
            for (File file : files) {
                if (isXls(file)) { // xls
                    XlsMemoryLoaderIf xlsMemoryLoaderIf = StaticFactory.createXlsMemoryLoader();
                    SearcherIf searcherIf = StaticFactory.createSearcher();
                    prepareSearchForFile(file, xlsMemoryLoaderIf, searcherIf);
                    doSearchForFile(current, file, searcherIf);
                } else { // directory
                    this.runRecursive(file);
                }
            }
        }
    }

    private boolean isXls(File file) {
        return (file.isFile() && getExtension(file).equals("xls"));
    }

    private String getExtension(File file) {
        String fileName = file.getName();
        int lastDotPos = fileName.lastIndexOf('.');

        if (lastDotPos > 0) {
            return fileName.substring(lastDotPos + 1);
        } else {
            return "";
        }
    }

    private void prepareSearchForFile(File file,
                                      XlsMemoryLoaderIf xlsMemoryLoaderIf,
                                      SearcherIf searcherIf) {
        xlsMemoryLoaderIf.loadToMemory(file);
        searcherIf.assignStringSetToSearch(xlsMemoryLoaderIf.getLoadedStringSetFromXls());
        searcherIf.assignKeywords(keyWords);
    }

    private void doSearchForFile(File current, File file,
                                 SearcherIf searcherIf) {
        Set<String> foundKeyWords = searcherIf.search();
        for (String keyword : foundKeyWords) {
            result.add(keyword + " in " + current + "\\" + file.getName());
        }
    }

    public FileLoaderPathIf getFileLoaderPathIf() {
        return fileLoaderPathIf;
    }

    public void setFileLoaderPathIf(FileLoaderPathIf fileLoaderPathIf) {
        this.fileLoaderPathIf = fileLoaderPathIf;
    }

    public List<String> getResult() {
        if (result == null) {
            result = StaticFactory.createArrayList();
        }
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public Set<String> getKeyWords() {
        if (keyWords == null) {
            keyWords = StaticFactory.createHashSet();
        }
        return keyWords;
    }

    public void setKeyWords(Set<String> keyWords) {
        this.keyWords = keyWords;
    }
}
