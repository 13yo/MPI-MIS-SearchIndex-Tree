package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.transaction.annotation.Transactional;

public abstract class Tree<T> extends IndexNode<T> {

    @RelatedTo(type = "SET", elementClass = DBSet.class, direction = Direction.OUTGOING)
    private Set<DBSet> sets;

    public abstract Set<? extends Tree<T>> getChildren();

    @Transactional
    public Set<DBSet> getSets() {
	return this.sets;
    }

    public void addSet(Long id) {

    }

}
