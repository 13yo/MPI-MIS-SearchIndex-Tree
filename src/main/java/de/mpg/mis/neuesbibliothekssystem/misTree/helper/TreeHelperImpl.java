package de.mpg.mis.neuesbibliothekssystem.misTree.helper;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.Evaluator;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.helpers.Predicate;
import org.neo4j.kernel.Traversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.core.NodeBacked;
//import org.springframework.data.graph.neo4j.finder.FinderFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Tree;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipType;

@Service
public class TreeHelperImpl implements TreeHelper {

    // @Autowired
    // private FinderFactory finderFactory;

    @Override
    @Transactional
    public <T extends Object> Tree<T> findDirectChild(T o, Tree<T> tree) {
	for (Tree<T> t : tree.getChildren()) {
	    if (t.getValue().equals(o))
		return t;
	}
	return null;
    }

    // private <T extends NodeBacked> T createEntityFromState(Node node,
    // Class<T> type) {
    // return finderFactory.createNodeEntityFinder(type)
    // .findById(node.getId());
    // }

    public TraversalDescription buildSetTraversal() {

	return Traversal.description().depthFirst()
		.filter(new Predicate<Path>() {
		    public boolean accept(Path item) {
			// return (item.length() > 0);
			return (item.length() > 0);
		    }
		});
    }

    public TraversalDescription demo(String w) {
	// ImmutableList<Character> chars = Lists.charactersOf(w);
	// UnmodifiableListIterator<Character> iter = chars.listIterator();

	return Traversal.description().breadthFirst()
		.relationships(RelationshipType.CHILD, Direction.OUTGOING)
		.evaluator(new StringEvaluator(w));
    }

    public class StringEvaluator implements Evaluator {
	// private Iterator<Character> it;
	private String wort;

	// public StringEvaluator(Iterator<Character> it) {
	// this.it = it;
	// }

	public StringEvaluator(String wort) {
	    this.wort = wort;
	}

	@Override
	public Evaluation evaluate(Path path) {
	    int l = path.length();
	    if (l == 0)
		return Evaluation.EXCLUDE_AND_CONTINUE;
	    if (l < wort.length())
		if (path.endNode().getProperty("value")
			.equals(wort.charAt(l - 1)))
		    return Evaluation.EXCLUDE_AND_CONTINUE;
		else
		    return Evaluation.EXCLUDE_AND_PRUNE;
	    else if (path.endNode().getProperty("value")
		    .equals(wort.charAt(l - 1)))
		return Evaluation.INCLUDE_AND_PRUNE;
	    else
		return Evaluation.EXCLUDE_AND_PRUNE;
	}
    }
}
