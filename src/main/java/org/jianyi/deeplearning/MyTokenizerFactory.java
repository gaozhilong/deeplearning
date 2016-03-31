package org.jianyi.deeplearning;

import java.io.InputStream;

import org.ansj.app.crf.SplitWord;
import org.ansj.util.MyStaticValue;
import org.deeplearning4j.text.tokenization.tokenizer.DefaultStreamTokenizer;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

public class MyTokenizerFactory implements TokenizerFactory {
	
	private TokenPreProcess tokenPreProcess;
	
	private SplitWord splitWord = MyStaticValue.getCRFSplitWord();

	public Tokenizer create(String toTokenize) {
		// TODO Auto-generated method stub
		MyTokenizer t =  new MyTokenizer(toTokenize,splitWord);
        t.setTokenPreProcessor(tokenPreProcess);
        return t;
	}

	public Tokenizer create(InputStream toTokenize) {
		// TODO Auto-generated method stub
		Tokenizer t =  new DefaultStreamTokenizer(toTokenize);
        t.setTokenPreProcessor(tokenPreProcess);
        return t;
	}

	public void setTokenPreProcessor(TokenPreProcess preProcessor) {
		// TODO Auto-generated method stub
		this.tokenPreProcess = preProcessor;
	}

}
