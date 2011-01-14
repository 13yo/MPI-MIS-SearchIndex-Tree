package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.annotation.GraphProperty;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.DomainObjectType;

@NodeEntity
public class DomainObject extends Tree<Long> {

    public DomainObject(Long value, DomainObjectType type) {
	this.value = value;
	this.type = type;
    }

    public DomainObject() {
    }

    // @GraphProperty(index = true)
    @Indexed
    private Long value;

    // @GraphProperty(index = true)
    @Indexed
    private DomainObjectType type;

    @RelatedTo(type = "CHILD", elementClass = DomainObject.class, direction = Direction.OUTGOING)
    private Set<DomainObject> childObjects;

    @RelatedTo(type = "SET", elementClass = DBSet.class, direction = Direction.OUTGOING)
    private Set<DBSet> sets;

    @Override
    @Transactional
    public Set<DomainObject> getChildren() {
	return this.childObjects;
    }

    @Override
    @Transactional
    public Set<DBSet> getSets() {
	return this.sets;
    }

    @Override
    public Long getValue() {
	return this.value;
    }

    @Override
    public void setValue(Long value) {
	this.value = value;
    }

    public DomainObjectType getType() {
	return type;
    }

    public void setType(DomainObjectType type) {
	this.type = type;
    }

}
