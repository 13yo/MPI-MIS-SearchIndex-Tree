package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.GraphProperty;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.transaction.annotation.Transactional;

import org.neo4j.graphdb.Node;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipTypes;

@NodeEntity
public class Char extends Tree<Character> {

    public Char(Character value) {
	this.value = value;
    }

    public Char() {

    }

    // @GraphProperty(index = true)
    private Character value;

    @RelatedTo(type = "CHILD", elementClass = Char.class, direction = Direction.OUTGOING)
    private Set<Char> childChars;

    @RelatedTo(type = "SET", elementClass = DBSet.class, direction = Direction.OUTGOING)
    private Set<DBSet> sets;

    @RelatedTo(type = "DOMAIN_OBJECT", elementClass = DomainObject.class, direction = Direction.OUTGOING)
    private Set<Char> domainObjects;

    @RelatedTo(type = "POSITION", elementClass = Position.class, direction = Direction.OUTGOING)
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
    public Position addPosition(Integer... positions) {

	for (int i = 0; i < positions.length; i++) {

	}
    }

    @Transactional
    public Char getDirectChildChar(Character c) {
	for (Char child : this.childChars) {
	    if (child.getValue().equals(c))
		return child;
	}
	return null;
    }

    @Transactional
    public Char addChar(Character c) {
	Char child = this.getDirectChildChar(c);
	if (child == null) {
	    child = new Char(c);
	    this.getUnderlyingState().createRelationshipTo(
		    child.getUnderlyingState(), RelationshipTypes.CHILD);
	}
	return child;
    }

}
