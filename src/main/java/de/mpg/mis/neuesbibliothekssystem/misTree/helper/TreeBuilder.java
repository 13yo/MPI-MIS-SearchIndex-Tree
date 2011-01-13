package de.mpg.mis.neuesbibliothekssystem.misTree.helper;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Position;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Root;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Tree;

public interface TreeBuilder {

    public Root getRoot();

    public Char addWordToTree(String word, Root tree);

    public void addDBSetToNodes(Long setId, Tree... nodes);

    public Position addPositionToChar(Char character, Integer... positions);

}
