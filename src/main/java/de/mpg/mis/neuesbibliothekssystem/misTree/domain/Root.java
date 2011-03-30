package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.GraphId;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.stereotypes.CharAware;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipType;

@NodeEntity
public class Root extends Tree<Character> implements CharAware {

    @GraphId
    private Long graphId;

    public Long getId() {
	return this.graphId;
    }

    // @GraphProperty(index = true)
    private Character value = new Character(' ');

    @RelatedTo(type = "CHILD", elementClass = Char.class, direction = Direction.OUTGOING)
    private Set<Char> children;

    @RelatedTo(type = "SET", elementClass = DBSet.class, direction = Direction.OUTGOING)
    private Set<DBSet> sets;

    @Transactional
    public Set<Char> getChildren() {
	return this.children;
    }

    public Character getValue() {
	return this.value;
    }

    @Override
    @Transactional
    public Set<DBSet> getSets() {
	return this.sets;
    }

    @Override
    public void setValue(Character value) {
	this.value = value;
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
	Char child = new Char(c).persist();
	this.relateTo(child, "CHILD");
	return child;
    }

    @Transactional
    public Char getDirectChildChar(Character c) {
	for (Char child : this.children) {
	    if (child.getValue().equals(c))
		return child;
	}
	return null;
    }

    public String toString() {
	return "-+=ROOT-Node=+-";
    }

}
