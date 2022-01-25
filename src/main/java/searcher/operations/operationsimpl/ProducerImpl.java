package searcher.operations.operationsimpl;

import searcher.instantiations.StaticFactory;
import searcher.operations.operationsif.ProducerIf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProducerImpl implements ProducerIf {
    private static final Logger LOGGER = LogManager.getLogger(ProducerImpl.class);
    private static final String FILENAME = "search_results.log";
    private Path path;
    private List<String> result;

    public ProducerImpl() {
        this.path = Paths.get("");
        this.result = StaticFactory.createArrayList();
    }

    public ProducerImpl(Path path, List<String> result) {
        this.path = path;
        this.result = result;
    }

    @Override
    public void produce() {
        File file = StaticFactory.createFile(path, FILENAME);
        try (FileWriter fileWriter = new FileWriter(file);) {
            LOGGER.info("Starting to produce result file!");
            fileWriter.write("Results:\n");
            for (String sentence : result) {
                fileWriter.write(sentence + "\n");
            }
            LOGGER.info("Result file produced in: {}", file);
        } catch (Exception e) {
            LOGGER.error("Could not produce result file: ", e);
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
