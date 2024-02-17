package statistics.collectors;

import org.jfree.data.general.AbstractDataset;

public interface DatasetCollector<T extends AbstractDataset> {

    T getDataset();

}
