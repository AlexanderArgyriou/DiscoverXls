package searcher.operations.operationsif;

import java.util.Set;

public interface SearcherIf {
    Set<String> search();

    void assignStringSetToSearch(Set<String> xlsStrings);

    void assignKeywords(Set<String> keywords);
}
