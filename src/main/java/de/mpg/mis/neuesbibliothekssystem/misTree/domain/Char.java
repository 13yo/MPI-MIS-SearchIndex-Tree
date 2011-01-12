package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.GraphProperty;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;


public class Char extends Tree<Character>{
	
	@GraphProperty(index = true)
	private Character value;
	
	@RelatedTo(type="CHILD", elementClass=IndexNode.class, direction=Direction.OUTGOING )
	private Set<IndexNode<?>> children;

	@Override
	public Character getValue() {
		return this.value; 
	}

	@Override
	public void setValue(Character value) {
		this.value = value;
	}

	@Override
	public Set<IndexNode<?>> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

}
