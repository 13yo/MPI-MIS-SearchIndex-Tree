package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.GraphId;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.data.graph.core.NodeBacked;
import org.springframework.data.annotation.Indexed;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.stereotypes.CharAware;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.stereotypes.DomainObjectAware;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.DomainObjectType;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.PositionType;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipType;

@NodeEntity
public class Char extends Tree<Character> implements CharAware,
	DomainObjectAware {

    public Char(Character value) {
	this.value = value;
	this.wordIndex = value.toString();
    }

    public Char(Character value, String wordIndex) {
	this.value = value;
	this.wordIndex = wordIndex;
    }

    public Char() {

    }

    @GraphId
    private Long graphId;

    public Long getId() {
	return this.graphId;
    }

    // @GraphProperty(index = true)
    private Character value;

    @Indexed
    private String wordIndex;

    @RelatedTo(type = "CHILD", elementClass = Char.class, direction = Direction.OUTGOING)
    private Set<Char> childChars;

    @RelatedTo(type = "POSITION", elementClass = Position.class, direction = Direction.OUTGOING)
    private Set<Position> positions;

    @RelatedTo(type = "DOMAIN_OBJECT", elementClass = DomainObject.class, direction = Direction.OUTGOING)
    private Set<DomainObject> domainObjects;

    @RelatedTo(type = "SET", elementClass = DBSet.class, direction = Direction.OUTGOING)
    private Set<DBSet> sets;

    @Transactional
    public Set<DBSet> getSets() {
	return this.sets;
    }

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

    @Transactional
    public Position addPosition(Integer... positions) {
	Position p = null;
	NodeBacked tree = (NodeBacked) this;
	for (int i = 0; i < positions.length; i++) {
	    p = new Position(positions[i], PositionType.values()[i]).persist();
	    tree.relateTo(p, "POSITION");
	    tree = p;
	}

	return p;
    }

    @Transactional
    public Char getDirectChildChar(Character c) {
	for (Char child : this.childChars) {
	    // System.out.println(child.getValue());
	    if (child.getValue().equals(c))
		return child;
	}
	return null;
    }

    @Transactional
    public Char addChar(Character c) {
	Char child = this.getDirectChildChar(c);
	if (child == null) {
	    child = this.addCharSimple(c);
	}
	return child;
    }

    @Transactional
    public Char addCharSimple(Character c) {
	Char child = new Char(c, this.wordIndex + c).persist();
	this.relateTo(child, "CHILD");
	return child;
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
	    tree = (NodeBacked) o;
	}

	return o;
    }

}
