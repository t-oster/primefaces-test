package org.primefaces.test;

import java.io.Serializable;
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
     
    private final List<ExampleData> repo = List.of(
            new ExampleData("changeme1"),
            new ExampleData("changeme2")
    );
    
    @Override
    public List<ExampleData> load(int i, int i1, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
        setRowCount(repo.size());
        setPageSize(2);
        return repo;
    }
}
