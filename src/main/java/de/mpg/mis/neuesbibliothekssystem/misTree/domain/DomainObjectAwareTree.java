package de.mpg.mis.neuesbibliothekssystem.misTree.domain;

import java.util.Set;

import org.springframework.data.graph.annotation.RelatedTo;
import org.springframework.data.graph.core.Direction;
import org.springframework.data.graph.core.NodeBacked;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.stereotypes.DomainObjectAware;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.DomainObjectType;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipType;

public abstract class DomainObjectAwareTree<T> extends Tree<T> implements
	DomainObjectAware {

    public abstract Set<DomainObject> getDomainObjects();

    @Transactional
    public DomainObject addDomainObjects(Long... domainObjects) {
	DomainObject o = null;
	NodeBacked tree = (NodeBacked) this;
	for (int i = 0; i < domainObjects.length; i++) {
	    o = new DomainObject(domainObjects[i], DomainObjectType.values()[i]);
	    tree.getUnderlyingState().createRelationshipTo(
		    o.getUnderlyingState(), RelationshipType.DOMAIN_OBJECT);
	    tree = (NodeBacked) o;
	}

	return o;
    }

}
