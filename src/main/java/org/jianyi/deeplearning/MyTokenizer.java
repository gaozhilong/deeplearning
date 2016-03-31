package org.jianyi.deeplearning;

import java.util.List;

import org.ansj.app.crf.SplitWord;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;

public class MyTokenizer implements Tokenizer {
	
	private TokenPreProcess tokenPreProcess;
	private String tokens;
	private List<String> values;
	private int current = 0;
	private SplitWord splitWord;
	
	public MyTokenizer(String tokens, SplitWord splitWord) {
		super();
		// TODO Auto-generated constructor stub
		this.tokens = tokens;
		this.splitWord = splitWord;
		values = splitWord.cut(tokens);
	}

	public boolean hasMoreTokens() {
		// TODO Auto-generated method stub
		if (values != null && !values.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public int countTokens() {
		// TODO Auto-generated method stub
		if (values != null && !values.isEmpty()) {
			return values.size();
		} else {
			return 0;
		}
	}

	public String nextToken() {
		// TODO Auto-generated method stub
		//if (current < values.size()) {
			String base =  values.get(current);
			current++;
	        if(tokenPreProcess != null)
	            base = tokenPreProcess.preProcess(base);
	        return base;
		//}
		
	}

	public List<String> getTokens() {
		// TODO Auto-generated method stub
		return values;
	}

	public void setTokenPreProcessor(TokenPreProcess tokenPreProcessor) {
		// TODO Auto-generated method stub
		this.tokenPreProcess = tokenPreProcessor;
	}

}
