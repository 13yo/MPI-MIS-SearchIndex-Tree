package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;


public abstract class Leaf<T> extends IndexNode<T> {
	
	public abstract Set<? extends Tree> getParents();

}
