package de.mpg.mis.neuesbibliothekssystem.misTree.helper;

import org.neo4j.graphdb.traversal.TraversalDescription;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Tree;

public interface TreeHelper {

    public <T extends Object> Tree<T> findDirectChild(T o, Tree<T> tree);

    public TraversalDescription buildTraversal();

}
