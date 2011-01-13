package de.mpg.mis.neuesbibliothekssystem.misTree.helper;

import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.core.NodeBacked;
import org.springframework.data.graph.neo4j.finder.FinderFactory;
import org.springframework.stereotype.Service;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Tree;

@Service
public class TreeHelperImpl implements TreeHelper {

    @Autowired
    private FinderFactory finderFactory;

    @Override
    public <T extends Object> Tree<T> findDirectChild(T o, Tree tree) {
	for(Object)
	return null;
    }

    private <T extends NodeBacked> T createEntityFromState(Node node,
	    Class<T> type) {
	return finderFactory.getFinderForClass(type).findById(node.getId());
    }

}
