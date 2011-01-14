package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.GraphProperty;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.data.graph.core.NodeBacked;
import org.springframework.transaction.annotation.Transactional;

import org.neo4j.graphdb.Node;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.PositionType;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipType;

@NodeEntity
public class Char extends DomainObjectAwareTree<Character> {

    public Char(Character value) {
	this.value = value;
    }

    public Char() {

    }

    // @GraphProperty(index = true)
    private Character value;

    @RelatedTo(type = "CHILD", elementClass = Char.class, direction = Direction.OUTGOING)
    private Set<Char> childChars;

    @RelatedTo(type = "POSITION", elementClass = Position.class, direction = Direction.OUTGOING)
    private Set<Position> positions;

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
	    p = new Position(positions[i], PositionType.values()[i]);
	    tree.getUnderlyingState().createRelationshipTo(
		    p.getUnderlyingState(), RelationshipType.CHILD);
	    tree = p;
	}

	return p;
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
		    child.getUnderlyingState(), RelationshipType.CHILD);
	}
	return child;
    }

}
