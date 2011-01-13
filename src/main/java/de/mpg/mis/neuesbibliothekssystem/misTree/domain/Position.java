package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.GraphProperty;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.DomainObjectType;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.PositionType;

public class Position extends Tree<Integer> {

	public Position(Integer value, PositionType type) {
		this.value = value;
		this.type = type;
	}

	@GraphProperty(index = true)
	private Integer value;
	
	@GraphProperty(index = true)
	private PositionType type;
	
	@RelatedTo(type="CHILD", elementClass=Position.class, direction=Direction.OUTGOING )
	private Set<Position> childPositions;

	@RelatedTo(type="CHILD", elementClass=DomainObject.class, direction=Direction.OUTGOING )
	private Set<DomainObject> domainObjects;
	
	@RelatedTo(type="SET", elementClass=DBSet.class, direction=Direction.OUTGOING )
	private Set<DBSet> sets;

	@Override
	@Transactional
	public Set<Position> getChildren() {
		return this.childPositions;
	}
	
	@Transactional
	public Set<DomainObject> getDomainObjects() {
		return this.domainObjects;
	}

	@Override
	@Transactional
	public Set<DBSet> getSets() {
		return this.sets;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public PositionType getType() {
		return type;
	}

	public void setType(PositionType type) {
		this.type = type;
	}

}
