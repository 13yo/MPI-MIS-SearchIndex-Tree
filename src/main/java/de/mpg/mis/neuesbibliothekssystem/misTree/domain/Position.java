package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.neo4j.annotation.Indexed;
import org.springframework.data.graph.annotation.GraphId;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.data.graph.core.NodeBacked;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.stereotypes.DomainObjectAware;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.DomainObjectType;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.PositionType;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipType;

@NodeEntity
public class Position extends Tree<Integer> implements DomainObjectAware {

    public Position(Integer value, PositionType type) {
	this.value = value;
	this.type = type;
    }

    public Position() {
    }

    @GraphId
    private Long graphId;

    public Long getId() {
	return this.graphId;
    }

    // @GraphProperty(index = true)
    @Indexed
    private Integer value;

    // @GraphProperty(index = true)
    @Indexed
    private PositionType type;

    @RelatedTo(type = "DOMAIN_OBJECT", elementClass = DomainObject.class, direction = Direction.OUTGOING)
    private Set<DomainObject> domainObjects;

    @RelatedTo(type = "CHILD", elementClass = Position.class, direction = Direction.OUTGOING)
    private Set<Position> childPositions;

    @RelatedTo(type = "SET", elementClass = DBSet.class, direction = Direction.OUTGOING)
    private Set<DBSet> sets;

    @Transactional
    public Set<DBSet> getSets() {
	return this.sets;
    }

    @Override
    @Transactional
    public Set<Position> getChildren() {
	return this.childPositions;
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

    @Override
    @Transactional
    public Set<DomainObject> getDomainObjects() {
	return this.domainObjects;
    }

    @Override
    @Transactional
    public DomainObject addDomainObjects(Long... domainObjects) {
	DomainObject o = null;
	NodeBacked tree = (NodeBacked) this;
	for (int i = 0; i < domainObjects.length; i++) {
	    o = new DomainObject(domainObjects[i], DomainObjectType.values()[i])
		    .persist();
	    tree.relateTo(o, "DOMAIN_OBJECT");
	    tree = o;
	}
	return o;
    }

}
