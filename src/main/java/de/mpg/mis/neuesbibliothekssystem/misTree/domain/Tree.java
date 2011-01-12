package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;


public abstract class Tree<T> extends IndexNode<T> {

	public abstract Set<IndexNode<?>> getChildren();
	
	public void addSet(Long id){
		
	}
	
}
