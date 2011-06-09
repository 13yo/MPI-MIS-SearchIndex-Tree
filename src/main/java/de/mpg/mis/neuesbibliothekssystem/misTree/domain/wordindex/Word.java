package de.mpg.mis.neuesbibliothekssystem.misTree.domain.wordindex;

import org.springframework.data.graph.annotation.GraphId;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.neo4j.annotation.Indexed;

@NodeEntity
public class Word {

    @GraphId
    private Long graphId;

    public Long getId() {
	return this.graphId;
    }

    @Indexed(indexName = "word-index", fulltext = true)
    private String word;

}
