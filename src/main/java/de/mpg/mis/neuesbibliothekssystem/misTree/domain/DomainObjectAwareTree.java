package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.data.graph.core.NodeBacked;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.DomainObjectType;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipType;

public abstract class DomainObjectAwareTree<T> extends Tree<T> {

    @RelatedTo(type = "DOMAIN_OBJECT", elementClass = DomainObject.class, direction = Direction.OUTGOING)
    private Set<Char> domainObjects;

    @Transactional
    public Set<Char> getDomainObjects() {
	return this.domainObjects;
    }

    @Transactional
    public DomainObject addDomainObjects(Long... domainObjects) {
	DomainObject o = null;
	NodeBacked tree = (NodeBacked) this;
	for (int i = 0; i < domainObjects.length; i++) {
	    o = new DomainObject(domainObjects[i], DomainObjectType.values()[i]);
	    tree.getUnderlyingState().createRelationshipTo(
		    o.getUnderlyingState(), RelationshipType.CHILD);
	    tree = o;
	}

	return o;
    }

}
