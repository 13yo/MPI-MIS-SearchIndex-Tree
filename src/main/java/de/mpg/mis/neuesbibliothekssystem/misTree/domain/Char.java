package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.GraphProperty;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.transaction.annotation.Transactional;

public class Char extends Tree<Character>{
	
	public Char(Character value) {
		this.value = value;
	}

	@GraphProperty(index = true)
	private Character value;
	
	@RelatedTo(type="CHILD", elementClass=Char.class, direction=Direction.OUTGOING )
	private Set<Char> childChars;
	
	@RelatedTo(type="SET", elementClass=DBSet.class, direction=Direction.OUTGOING )
	private Set<DBSet> sets;
	
	@RelatedTo(type="DOMAIN_OBJECT", elementClass=DomainObject.class, direction=Direction.OUTGOING )
	private Set<Char> domainObjects;
	
	@RelatedTo(type="POSITION", elementClass=Position.class, direction=Direction.OUTGOING )
	private Set<DBSet> positions;

	@Override
	public Character getValue() {
		return this.value; 
	}

	@Override
	public void setValue(Character value) {
		this.value = value;
	}

	@Override
	@Transactional
	public Set<Char> getChildren() {
		return this.childChars;
	}

	@Override
	@Transactional
	public Set<DBSet> getSets() {
		return this.sets;
	}
	
	@Transactional
	public Position addPosition(Integer... positions){
		//TODO muss noch gefüllt werden
		return null;
	}

}
