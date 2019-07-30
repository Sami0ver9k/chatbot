/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.chatbot;


/**
 *
 * @author majaa
 */
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalyzer {

	public TweetWithSentiment findSentiment(String line) {

		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		int mainSentiment = 0;
		if (line != null && line.length() > 0) {
			int longest = 0;
			Annotation annotation = pipeline.process(line);
			int avgRating = 0;
			int count = 0;
			for (CoreMap sentence : annotation
					.get(CoreAnnotations.SentencesAnnotation.class)) {
				Tree tree = sentence
						.get(SentimentCoreAnnotations.AnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				//System.out.println("Sentiment value: " + sentiment);
				count++;
				avgRating += sentiment;
				/*
				 * String partText = sentence.toString(); if (partText.length()
				 * > longest) { mainSentiment = sentiment; longest =
				 * partText.length(); }
				 */

			}
			mainSentiment = (avgRating / count);
			//System.out.println("avgRating" + mainSentiment);
		}
		/*
		 * if (mainSentiment == 2 || mainSentiment > 4 || mainSentiment < 0) {
		 * return null; }
		 */
		TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(line,
				toCss(mainSentiment));
		return tweetWithSentiment;

	}

	private String toCss(int sentiment) {
		switch (sentiment) {
		case 0:
			return "Very Negative";
		case 1:
			return "Negative";
		case 2:
			return "Neutral";
		case 3:
			return "Positive";
		case 4:
			return "Very Positive";
		default:
			return "";
		}
	}

	public TweetWithSentiment calculateSentimentForGivenString(String stringToCalculate) {
		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		TweetWithSentiment tweetWithSentiment = sentimentAnalyzer
				.findSentiment(stringToCalculate);
		return tweetWithSentiment;
	}

	public static void main(String[] args) {
		SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
		TweetWithSentiment tweetWithSentiment = sentimentAnalyzer
				.findSentiment("For the first time, all the clubs from AUST have done something together. In sha Allah in future, we'll do something even bigger and better together. <3 ");
                //I'm a newbie with Java and Stanford NLP toolkit and trying to use them for a project. Specifically, I'm trying to use Stanford Corenlp toolkit to annotate a text (with Netbeans and not command line) and I tried to use the code provided on http://nlp.stanford.edu/software/corenlp.shtml#Usage (Using the Stanford CoreNLP API).. question is: can anybody tell me how I can get the output in a file so that I can further process it?
		System.out.println(tweetWithSentiment);
	}
}