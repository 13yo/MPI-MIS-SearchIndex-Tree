package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.core.NodeBacked;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipType;

public abstract class Tree<T> extends IndexNode<T> {

    public abstract Set<? extends Tree<T>> getChildren();

    public abstract Set<DBSet> getSets();

    @Transactional
    public DBSet getDBSet(Long id) {
	for (DBSet set : this.getSets()) {
	    if (set.getValue().equals(id))
		return set;
	}
	return null;
    }

    @Transactional
    public void addSet(Long... ids) {
	NodeBacked tree = (NodeBacked) this;
	for (int i = 0; i < ids.length; i++) {
	    if (getDBSet(ids[i]) == null) {
		DBSet s = new DBSet(ids[i]);
		tree.getUnderlyingState().createRelationshipTo(
			s.getUnderlyingState(), RelationshipType.SET);
	    }
	}
    }

}
