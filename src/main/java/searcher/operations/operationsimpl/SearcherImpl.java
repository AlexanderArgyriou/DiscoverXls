package searcher.operations.operationsimpl;

import searcher.instantiations.StaticFactory;
import searcher.operations.operationsif.SearcherIf;

import java.util.Set;

public class SearcherImpl implements SearcherIf {
    private Set<String> xlsStrings;
    private Set<String> keywords;

    public SearcherImpl() {
    }

    public SearcherImpl(Set<String> xlsStrings,
                        Set<String> keywords) {
        this.keywords = keywords;
        this.xlsStrings = xlsStrings;
    }

    public Set<String> getKeywords() {
        if (keywords == null) {
            keywords = StaticFactory.createHashSet();
        }
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public Set<String> getXlsStrings() {
        if (xlsStrings == null) {
            xlsStrings = StaticFactory.createHashSet();
        }
        return xlsStrings;
    }

    public void setXlsStrings(Set<String> xlsStrings) {
        this.xlsStrings = xlsStrings;
    }

    @Override
    public Set<String> search() {
        Set<String> found = StaticFactory.createHashSet();
        for (String keyword : this.keywords) {
            for (String xlsWord : this.xlsStrings) {
                if (xlsWord.contains(keyword)) {
                    found.add(keyword);
                }
            }
        }
        return found;
    }

    @Override
    public void assignStringSetToSearch(Set<String> xlsStrings) {
        setXlsStrings(xlsStrings);
    }

    @Override
    public void assignKeywords(Set<String> keywords) {
        setKeywords(keywords);
    }
}
