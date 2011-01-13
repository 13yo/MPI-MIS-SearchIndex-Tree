package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.GraphProperty;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.transaction.annotation.Transactional;

public class DBSet extends Leaf<Long> {
	
	public DBSet(Long value) {
		this.value = value;
	}

	@GraphProperty(index = true)
	private Long value;
	
	@RelatedTo(type="CHILD", elementClass=Tree.class, direction=Direction.INCOMING )
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
