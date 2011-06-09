package de.mpg.mis.neuesbibliothekssystem.misTree.domain.repos;

import org.springframework.data.graph.neo4j.repository.GraphRepository;
import de.mpg.mis.neuesbibliothekssystem.misTree.domain.Char;

public interface CharRepository extends GraphRepository<Char> {
}
