package org.jianyi.deeplearning;

import java.io.IOException;
import java.util.Collection;

import org.canova.api.util.ClassPathResource;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordTool {
	
	private static Logger log = LoggerFactory.getLogger(WordTool.class);

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filePath = new ClassPathResource("041.txt").getFile().getAbsolutePath();

        log.info("Load & Vectorize Sentences....");
        // Strip white space before and after for each line
        SentenceIterator iter = new BasicLineIterator(filePath);
        // Split on white spaces in the line to get words
        TokenizerFactory t = new MyTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();

        log.info("Fitting Word2Vec model....");
        vec.fit();

        Collection<String> lst = vec.wordsNearest("瓜子", 20);
        
        log.info("Closest words to '瓜子' on 1st run: " + lst);

        /*
            at this momen we're supposed to have model built, and it can be saved for future use.
         */
        //WordVectorSerializer.writeFullModel(vec, "pathToSaveModel.txt");
        WordVectorSerializer.writeWordVectors(vec, "pathToWriteto.txt");
        
        //WordVectorSerializer.
	}

}
