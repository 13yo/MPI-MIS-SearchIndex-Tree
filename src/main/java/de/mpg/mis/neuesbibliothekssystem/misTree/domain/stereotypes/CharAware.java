package de.mpg.mis.neuesbibliothekssystem.misTree.domain.stereotypes;

import java.util.Set;

import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;

public interface CharAware {

    public Char addChar(Character c);

    public Char getDirectChildChar(Character c);

    public Set<Char> getChildren();

}
