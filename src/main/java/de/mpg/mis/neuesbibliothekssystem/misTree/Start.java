package de.mpg.mis.neuesbibliothekssystem.misTree;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Direction;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Root;
import de.mpg.mis.neuesbibliothekssystem.misTree.helper.Permutation;
import de.mpg.mis.neuesbibliothekssystem.misTree.helper.TreeHelper;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.repos.CharRepository;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;

public class Start {

    @Autowired
    private TreeHelper treeHelper;

    @Autowired
    private GraphDatabaseService graphDbService;

    @Autowired
    private Permutation permutation;

    @Autowired
    private CharRepository charRepository;

    // @Transactional
    public void demo(String s) {
	long t = System.currentTimeMillis();
	Root r = this.generateRoot();

	// String s = "abcdefghijkl";
	// String s = "abcde";
	// String s = "abcdef";
	// String s = "abcdefg";
	// String s = "abcdefgh";
	// String s = "abcdefghi";
	permutation.perm1(s, r);

	System.out.println("Aktion beendet nach "
		+ (System.currentTimeMillis() - t) + "ms");

	// r.addChar('a').addChar('b').addPosition(1, 1)
	// .addDomainObjects(1l, 1l, 1l, 1l, 1l).addSet(1l);
	// r.addChar('b').addSet(1l);
	// r.addChar('a').addChar('b').addChar('c');
	//
	// Iterable<DBSet> result = finderFactory.getFinderForClass(DBSet.class)
	// .findAllByPropertyValue(DBSet.INDEX_NAME,
	// DBSet.getIndexString(1l));
	//
	// for (DBSet s : result) {
	// System.out.println(s.getId() + ":" + s.getValue());
	// }
	//
	// for (Path p : treeHelper.buildSetTraversal().traverse(
	// r.getUnderlyingState())) {
	// Node n = p.endNode();
	//
	// for (String key : n.getPropertyKeys()) {
	// System.out.print(key + ":" + n.getProperty(key) + " +++");
	// }
	// System.out.println();
	// for (Relationship rel : n.getRelationships(Direction.INCOMING)) {
	// System.out.println(rel.getType());
	// }
	//
	// System.out.println("-----------");
	// System.out.println();
	// }
	searchTree("abcde", r);
	searchTree("abdce", r);
	searchTree("edcba", r);
	searchTree("aaaaaa", r);
    }

    public void searchTree(String s, Root r) {
	long t = System.currentTimeMillis();
	Iterable<Char> it = charRepository.findAllByTraversal(r,
		treeHelper.demo(s));
	for (Char c : it) {
	    System.out.println(c.getWordIndex());
	}
	System.out.println("Aktion beendet nach "
		+ (System.currentTimeMillis() - t) + "ms");
    }

    public void fillTree(Root tree) {

    }

    @Transactional
    public Root generateRoot() {
	return new Root().persist();
    }

}
