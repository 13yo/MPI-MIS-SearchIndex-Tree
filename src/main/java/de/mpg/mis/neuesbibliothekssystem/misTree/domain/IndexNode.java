package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import org.springframework.data.graph.annotation.NodeEntity;

@NodeEntity
public abstract class IndexNode<T> {

	public abstract T getValue();
	public abstract void setValue(T value);
	
	static final String NAME_INDEX = "value";
	
}
