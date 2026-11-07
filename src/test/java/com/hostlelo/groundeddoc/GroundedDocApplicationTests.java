package com.hostlelo.groundeddoc;

import org.junit.jupiter.api.Test;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GroundedDocApplicationTests {

	@Autowired(required = false)
	private EmbeddingModel embeddingModel;

	@Autowired(required = false)
	private VectorStore vectorStore;

	@Test
	void contextLoads() {
		assertThat(embeddingModel).isNotNull();
		assertThat(vectorStore).isNotNull();
		assertThat(embeddingModel.dimensions()).isEqualTo(768);
	}

}
