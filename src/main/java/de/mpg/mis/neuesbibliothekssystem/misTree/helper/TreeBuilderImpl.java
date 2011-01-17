package de.mpg.mis.neuesbibliothekssystem.misTree.helper;

import org.springframework.data.graph.neo4j.finder.FinderFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Position;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Root;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Tree;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.stereotypes.CharAware;

@Service
public class TreeBuilderImpl implements TreeBuilder {

    @Autowired
    private FinderFactory finderFactory;

    @Override
    public Root getRoot() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    @Transactional
    public CharAware addWordToTree(String word, Root tree) {
	CharAware c = null;
	String rest = "";
	for (;;) {
	    c = finderFactory.getFinderForClass(Char.class)
		    .findByPropertyValue("wordIndex", word);
	    if (c != null)
		break;

	    if (word.isEmpty()) {
		c = tree;
		break;
	    } else {
		rest = word.charAt(word.length() - 1) + rest;
		word = word.substring(0, word.length() - 1);
	    }
	}
	// System.out.println("Rest = " + rest);
	for (int i = 0; i < rest.length(); i++) {
	    c = c.addCharSimple(rest.charAt(i));
	}
	return c;
    }

    @Override
    public void addDBSetToNodes(Long setId, Tree... nodes) {
	// TODO Auto-generated method stub

    }

    @Override
    public Position addPositionToChar(Char character, Integer... positions) {
	// TODO Auto-generated method stub
	return null;
    }

}
