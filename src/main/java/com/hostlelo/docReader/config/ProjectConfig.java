
package com.hostlelo.docReader.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProjectConfig {

	@Bean
	public ChatClient chatClient(ChatClient.Builder builder, ChatMemory memory) {

		log.info("ChatMemory class name: {}", memory.getClass().getName());

		return builder
				.defaultSystem("""
                        You are DocReader, an intelligent, versatile, and friendly AI document intelligence assistant.

                        Your Capabilities:

                        1. Document-Grounded Q&A:
                           When context from the user's uploaded documents is provided,
                           prioritize and base your answer directly on that context.
                           Cite document names and page numbers when available.

                        2. General Knowledge & Conversation:
                           If the user engages in general conversation, greetings,
                           programming questions, mathematics, explanations, summaries,
                           or general knowledge, answer helpfully, accurately, and naturally.

                        3. Hybrid Synthesis:
                           If the document context partially covers a topic,
                           synthesize the document facts with your broader knowledge
                           to provide a complete, high-quality answer.

                        4. Tone & Format:
                           Always be warm, professional, clear, and structured.
                           Use Markdown headings, bullet points, bold text,
                           and code blocks where appropriate.

                        Document Context:

                        {doc_context}

                        Produce Markdown output.
                        """)
				.defaultAdvisors(
						new SimpleLoggerAdvisor(),
						MessageChatMemoryAdvisor.builder(memory).build()
				)
				.build();
	}

	@Bean
	public OpenAPI openAPI() {

		String securitySchemeName = "bearerAuth";

		return new OpenAPI()
				.info(
						new Info()
								.title("DocReader — AI Document Intelligence & RAG Backend")
								.description(
										"REST API for DocReader: Multi-format document ingestion, " +
												"vector embeddings with PostgresSQL pgvector, and " +
												"conversational Q&A powered by Google Gemini."
								)
								.version("1.0.0")
								.contact(
										new Contact()
												.name("Fareed khan")
												.email("fareedfk4747@gmail.com")
												.url("https://portfolio-fareed-khan.vercel.app")
								)
				)
				.components(
						new Components()
								.addSecuritySchemes(
										securitySchemeName,
										new SecurityScheme()
												.name(securitySchemeName)
												.type(SecurityScheme.Type.HTTP)
												.scheme("bearer")
												.bearerFormat("JWT")
								)
				)
				.addSecurityItem(
						new SecurityRequirement()
								.addList(securitySchemeName)
				);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
