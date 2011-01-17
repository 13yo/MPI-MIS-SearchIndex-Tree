package de.mpg.mis.neuesbibliothekssystem.misTree.helper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Position;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Root;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Tree;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.stereotypes.CharAware;

@Service
public class TreeBuilderImpl implements TreeBuilder {

    @Override
    public Root getRoot() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    @Transactional
    public CharAware addWordToTree(String word, Root tree) {
	CharAware c = tree;
	for (int i = 0; i < word.length(); i++) {
	    c = c.addChar(word.charAt(i));
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
