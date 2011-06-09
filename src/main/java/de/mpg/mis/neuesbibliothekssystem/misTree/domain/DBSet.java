package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.neo4j.annotation.Indexed;
import org.springframework.data.graph.annotation.GraphId;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.data.graph.core.NodeBacked;
import org.springframework.transaction.annotation.Transactional;

@NodeEntity
public class DBSet extends Leaf<Long> {

    public DBSet(Long value) {
	this.setValue(value);
    }

    public DBSet() {
    }

    @GraphId
    private Long graphId;

    public Long getId() {
	return this.graphId;
    }

    public static final String INDEX_NAME = "indexValue";

    // @GraphProperty(index = true)
    // @Indexed
    private Long value;
    @Indexed
    private String indexValue;

    @RelatedTo(type = "CHILD", elementClass = NodeBacked.class, direction = Direction.INCOMING)
    private Set<Tree<?>> parentNodes;

    @Override
    public Long getValue() {
	return this.value;
    }

    @Override
    public void setValue(Long value) {
	this.value = value;
	this.indexValue = DBSet.getIndexString(value);
    }

    public static String getIndexString(Long value) {
	return "DBSet:" + value;
    }

    @Override
    @Transactional
    public Set<? extends Tree> getParents() {
	return this.parentNodes;
    }

}
