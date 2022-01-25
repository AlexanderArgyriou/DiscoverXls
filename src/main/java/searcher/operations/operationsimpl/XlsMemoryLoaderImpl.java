package searcher.operations.operationsimpl;

import searcher.instantiations.StaticFactory;
import searcher.operations.operationsif.XlsMemoryLoaderIf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import java.io.File;
import java.io.FileInputStream;
import java.util.Set;

public class XlsMemoryLoaderImpl implements XlsMemoryLoaderIf {
    private static final Logger LOGGER = LogManager.getLogger(XlsMemoryLoaderImpl.class);
    private Set<String> xlsStrings;

    public XlsMemoryLoaderImpl() {
        this.xlsStrings = StaticFactory.createHashSet();
    }

    public Set<String> getXlsStrings() {
        return xlsStrings;
    }

    public void setXlsStrings(Set<String> xlsStrings) {
        this.xlsStrings = xlsStrings;
    }

    @Override
    public void loadToMemory(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             HSSFWorkbook xlsFile = new HSSFWorkbook(fileInputStream)) {
            iterateThroughWorkBook(xlsFile); // WorkBook -> Sheet -> Row -> Cell.
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Set<String> getLoadedStringSetFromXls() {
        return getXlsStrings();
    }

    private void iterateThroughWorkBook(HSSFWorkbook xlsFile) {
        for (int i = 0; i < xlsFile.getNumberOfSheets(); i++) {
            HSSFSheet currSheet = xlsFile.getSheetAt(i);
            for (Row row : currSheet) {
                for (Cell cell : row) {
                    loadCellToStringFormat(cell);
                }
            }
        }
    }

    private void loadCellToStringFormat(Cell cell) {
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            xlsStrings.add(String.valueOf(cell.getNumericCellValue()));
            return;
        }
        if (cell.getCellTypeEnum() == CellType.STRING) {
            xlsStrings.add(cell.getStringCellValue());
            return;
        }
        if (cell.getCellTypeEnum() == CellType.FORMULA) {
            if (cell.getCachedFormulaResultTypeEnum() == CellType.NUMERIC) {
                xlsStrings.add(String.valueOf(cell.getNumericCellValue()));
                return;
            }
            if (cell.getCachedFormulaResultTypeEnum() == CellType.STRING) {
                xlsStrings.add(cell.getStringCellValue());
            }
        }
    }
}
