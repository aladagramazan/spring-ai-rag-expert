package com.rem.springairagexpert.bootstrap;

import com.rem.springairagexpert.config.VectorStoreProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoadVectorStore implements CommandLineRunner {

    private final VectorStore vectorStore;
    private final VectorStoreProperties vectorStoreProperties;


    @Override
    public void run(String... args) {
        if (Objects.requireNonNull(vectorStore.similaritySearch("Sportsman")).isEmpty()) {
            log.info("Loading documents into vector store");

            vectorStoreProperties.getDocumentsToLoad().forEach(document -> {
                System.out.println("Loading document: " + document.getFilename());

                TikaDocumentReader documentReader = new TikaDocumentReader(document);
                List<Document> documents = documentReader.get();

                TextSplitter textSplitter = new TokenTextSplitter();

                List<Document> splitDocuments = textSplitter.apply(documents);
                splitDocuments.getFirst().getMetadata();
                vectorStore.add(splitDocuments);
            });
        } else {
            log.info("Vector store already loaded");
        }

        log.info("Vector store loaded");
    }
}
