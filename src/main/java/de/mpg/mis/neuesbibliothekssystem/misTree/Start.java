package de.mpg.mis.neuesbibliothekssystem.misTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.core.NodeBacked;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Root;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.types.RelationshipTypes;
import de.mpg.mis.neuesbibliothekssystem.misTree.helper.TreeHelper;

public class Start {

    // @Autowired
    // private TreeHelper treeHelper;

    @Transactional
    public void demo() {
	Root r = new Root();

	newBuchstabe(r, 'a');

	System.out.println(r.getChildren().size());
	for (Char c : r.getChildren()) {
	    System.out.println(c.getValue());
	}
    }

    @Transactional
    public Char newBuchstabe(NodeBacked root, Character c) {
	Char b = new Char(c);

	// b.getUnderlyingState().createRelationshipTo(root.getUnderlyingState(),
	// BezTyp.VATER);
	// System.out.println(b.getUnderlyingState());
	// System.out.println(root.getUnderlyingState());
	root.getUnderlyingState().createRelationshipTo(b.getUnderlyingState(),
		RelationshipTypes.CHILD);

	return b;
    }

}
