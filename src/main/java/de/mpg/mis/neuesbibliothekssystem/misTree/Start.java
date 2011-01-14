package de.mpg.mis.neuesbibliothekssystem.misTree;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.core.NodeBacked;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Root;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipType;
import de.mpg.mis.neuesbibliothekssystem.misTree.helper.TreeHelper;

public class Start {

    @Autowired
    private TreeHelper treeHelper;

    @Transactional
    public void demo() {
	Root r = new Root();

	r.addChar('a').addChar('b').addPosition(1, 1)
		.addDomainObjects(1l, 1l, 1l, 1l, 1l).addSet(1l);
	r.addChar('b');
	r.addChar('a').addChar('b').addChar('c');

	System.out.println(r.getChildren().size());
	for (Char c : r.getChildren()) {
	    System.out.println(c.getValue());
	}

	for (Path p : treeHelper.buildTraversal().traverse(
		r.getUnderlyingState())) {
	    Node n = p.endNode();
	    if (n.hasProperty("value"))
		System.out.println(n.getProperty("value"));
	    for (Relationship rel : n.getRelationships()) {
		System.out.println(rel.getType());
	    }
	}
    }

}
