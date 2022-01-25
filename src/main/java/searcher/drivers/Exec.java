package searcher.drivers;

import searcher.instantiations.Setup;
import searcher.processes.processesif.SearchProcessIf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Exec {
    private static final Logger LOGGER = LogManager.getLogger(Exec.class);

    public static void main(String[] args) {
        try {
            LOGGER.info("Building Process object...");
            SearchProcessIf searchProcessIf = Setup.searchProcessFactoryMethod(args);
            LOGGER.info("Successful setup!");
            LOGGER.info("Running...");
            searchProcessIf.run();
            LOGGER.info("Successful run!");
            LOGGER.info("Start result presentation:");
            searchProcessIf.presentResult();
            LOGGER.info("End result presentation:");
            searchProcessIf.produceResults();
            LOGGER.info("Application was executed successfully!");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
