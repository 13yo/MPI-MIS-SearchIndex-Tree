package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import org.springframework.data.graph.annotation.GraphId;
import org.springframework.data.graph.annotation.NodeEntity;

public abstract class IndexNode<T> {

    @GraphId
    private Long graphId;

    public Long getId() {
	return this.graphId;
    }

    public abstract T getValue();

    public abstract void setValue(T value);

    static final String NAME_INDEX = "value";

}
