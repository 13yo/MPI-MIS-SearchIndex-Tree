package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.graph.core.NodeBacked;

@NodeEntity
public class DBSet extends Leaf<Long> {

    public DBSet(Long value) {
	this.value = value;
    }

    // @GraphProperty(index = true)
    @Indexed
    private Long value;

    @RelatedTo(type = "CHILD", elementClass = NodeBacked.class, direction = Direction.INCOMING)
    private Set<Tree<?>> parentNodes;

    @Override
    public Long getValue() {
	return this.value;
    }

    @Override
    public void setValue(Long value) {
	this.value = value;
    }

    @Override
    @Transactional
    public Set<? extends Tree> getParents() {
	return this.parentNodes;
    }

}
