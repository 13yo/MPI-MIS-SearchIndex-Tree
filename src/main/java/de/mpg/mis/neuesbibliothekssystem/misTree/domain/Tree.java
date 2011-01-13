package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;


public abstract class Tree<T> extends IndexNode<T> {

	public abstract Set<? extends Tree<T>> getChildren();
	public abstract Set<DBSet> getSets();
	
	public void addSet(Long id){
		
	}
	
}
