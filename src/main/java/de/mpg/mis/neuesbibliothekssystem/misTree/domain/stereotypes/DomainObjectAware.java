package de.mpg.mis.neuesbibliothekssystem.misTree.domain.stereotypes;

import java.util.Set;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.DomainObject;

public interface DomainObjectAware {

    public Set<DomainObject> getDomainObjects();

    public DomainObject addDomainObjects(Long... domainObjects);

}
