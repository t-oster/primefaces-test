package org.primefaces.test;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author Thomas Oster <thomas.oster@upstart-it.de>
 */
public class TestLazyModel extends LazyDataModel<ExampleData> implements Serializable {
     
    private final List<ExampleData> repo;
    
    public TestLazyModel() {
        repo = new LinkedList<>();
        repo.add(new ExampleData("changeme1"));
        repo.add(new ExampleData("changeme2"));
    }
    
    public int count(Map map) {
        return repo.size();
    }
    
    @Override
    public List<ExampleData> load(int i, int i1, Map map, Map map1) {
        setRowCount(repo.size());
        setPageSize(2);
        return repo;
    }
}
