package de.mpg.mis.neuesbibliothekssystem.misTree.domain.types;

import org.neo4j.graphdb.RelationshipType;

public enum RelationshipTypes implements RelationshipType {

    CHILD, PARENT, POSITION, DOMAIN_OBJECT, SET

}
