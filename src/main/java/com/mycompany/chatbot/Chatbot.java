package com.mycompany.chatbot;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

// To convert text-to-speech
import java.util.Locale; 
import javax.speech.Central; 
import javax.speech.synthesis.Synthesizer; 
import javax.speech.synthesis.SynthesizerModeDesc; 
import javax.speech.synthesis.Voice;

public class Chatbot {
	private static final boolean TRACE_MODE = false;
	static String botName = "super";

	public static void main(String[] args) {
		try {

			String resourcesPath = getResourcesPath();
			System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot("super", resourcesPath);
			Chat chatSession = new Chat(bot);
			bot.brain.nodeStats();
			String textLine = "";
                        
                        	 
			
			// set property as Kevin Dictionary 
			System.setProperty("freetts.voices", 
				"com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory"); 
				
			// Register Engine 
			Central.registerEngineCentral 
				("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral"); 
                        
                        
                        //required.setLocale(Locale.ENGLISH);
                        Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));	
                        
	
			// Allocate synthesizer 
			synthesizer.allocate();	 
                        
                        
                        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
                        
			while(true) {
                                
                                
				System.out.print("Saif : ");
				textLine = IOUtils.readInputTextLine();
				if ((textLine == null) || (textLine.length() < 1))
					textLine = MagicStrings.null_input;
				if (textLine.equals("q")) {
					System.exit(0);
				} else if (textLine.equals("wq")) {
					bot.writeQuit();
					System.exit(0);
				} else {
                                    
                                        TweetWithSentiment tweetWithSentiment = sentimentAnalyzer
                                                        .findSentiment(textLine);
                                        //I'm a newbie with Java and Stanford NLP toolkit and trying to use them for a project. Specifically, I'm trying to use Stanford Corenlp toolkit to annotate a text (with Netbeans and not command line) and I tried to use the code provided on http://nlp.stanford.edu/software/corenlp.shtml#Usage (Using the Stanford CoreNLP API).. question is: can anybody tell me how I can get the output in a file so that I can further process it?
                                        System.out.println("Saif: " +tweetWithSentiment);
                                        
					String request = textLine;
					if (MagicBooleans.trace_mode)
						System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
					String response = chatSession.multisentenceRespond(request);
					while (response.contains("&lt;"))
						response = response.replace("&lt;", "<");
					while (response.contains("&gt;"))
						response = response.replace("&gt;", ">");
                                        
                                        // speaks the given text until queue is empty. 
                                        //textToSpeech(response);
                                        
                                        // Resume Synthesizer 
                                        synthesizer.resume();	 

                                        // speaks the given text until queue is empty. 
                                        
                                        synthesizer.speakPlainText(response, null);	
                                        //synthesizer.pause();
                                        synthesizer.waitEngineState(Synthesizer.QUEUE_NOT_EMPTY); 
                                        // Deallocate the Synthesizer. 
                                        //synthesizer.deallocate();  
                                        
                                        tweetWithSentiment = sentimentAnalyzer
                                                        .findSentiment(response);
                                        //I'm a newbie with Java and Stanford NLP toolkit and trying to use them for a project. Specifically, I'm trying to use Stanford Corenlp toolkit to annotate a text (with Netbeans and not command line) and I tried to use the code provided on http://nlp.stanford.edu/software/corenlp.shtml#Usage (Using the Stanford CoreNLP API).. question is: can anybody tell me how I can get the output in a file so that I can further process it?
                                        
					System.out.println("Robot : " + response);
                                        System.out.println("Robot : " +tweetWithSentiment);
                                        
				}
                            
			
                               
                            
			}
                        
                      
                         
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

        
        //Adddddddddddddddd
        public String getResponse (String msg) {
		try {

			String resourcesPath = getResourcesPath();
			System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot("super", resourcesPath);
			Chat chatSession = new Chat(bot);
			bot.brain.nodeStats();
			String textLine = "";
                        
                        	 
			
			// set property as Kevin Dictionary 
			System.setProperty("freetts.voices", 
				"com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory"); 
				
			// Register Engine 
			Central.registerEngineCentral 
				("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral"); 
                        
                        
                        //required.setLocale(Locale.ENGLISH);
                        Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));	
                        
	
			// Allocate synthesizer 
			synthesizer.allocate();	 
                        
                        
                        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
                        
			while(true) {
                                
                                
				System.out.print("Saif : ");
				textLine = msg;
				if ((textLine == null) || (textLine.length() < 1))
					textLine = MagicStrings.null_input;
				if (textLine.equals("q")) {
					System.exit(0);
				} else if (textLine.equals("wq")) {
					bot.writeQuit();
					System.exit(0);
				} else {
                                    
                                        TweetWithSentiment tweetWithSentiment = sentimentAnalyzer
                                                        .findSentiment(textLine);
                                        //I'm a newbie with Java and Stanford NLP toolkit and trying to use them for a project. Specifically, I'm trying to use Stanford Corenlp toolkit to annotate a text (with Netbeans and not command line) and I tried to use the code provided on http://nlp.stanford.edu/software/corenlp.shtml#Usage (Using the Stanford CoreNLP API).. question is: can anybody tell me how I can get the output in a file so that I can further process it?
                                        System.out.println("Saif: " +tweetWithSentiment);
                                        
					String request = textLine;
					if (MagicBooleans.trace_mode)
						System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
					String response = chatSession.multisentenceRespond(request);
					while (response.contains("&lt;"))
						response = response.replace("&lt;", "<");
					while (response.contains("&gt;"))
						response = response.replace("&gt;", ">");
                                        
                                        // speaks the given text until queue is empty. 
                                        //textToSpeech(response);
                                        
                                        // Resume Synthesizer 
                                        synthesizer.resume();	 

                                        // speaks the given text until queue is empty. 
                                        
                                        synthesizer.speakPlainText(response, null);	
                                        //synthesizer.pause();
                                        synthesizer.waitEngineState(Synthesizer.QUEUE_NOT_EMPTY); 
                                        // Deallocate the Synthesizer. 
                                        //synthesizer.deallocate();  
                                        
                                        tweetWithSentiment = sentimentAnalyzer
                                                        .findSentiment(response);
                                        //I'm a newbie with Java and Stanford NLP toolkit and trying to use them for a project. Specifically, I'm trying to use Stanford Corenlp toolkit to annotate a text (with Netbeans and not command line) and I tried to use the code provided on http://nlp.stanford.edu/software/corenlp.shtml#Usage (Using the Stanford CoreNLP API).. question is: can anybody tell me how I can get the output in a file so that I can further process it?
                                        
					System.out.println("Robot : " + response);
                                        System.out.println("Robot : " +tweetWithSentiment);
                                        return response+" Sentiment: "+tweetWithSentiment;
                                        
				}
                            
			
                               
                            
			}
                        
                      
                         
		} catch (Exception e) {
			e.printStackTrace();
		}
                
                return null;
	}

        
        //????????
        
	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}
        
        private static void textToSpeech (String speech) 
	{ 

		try
		{ 
			// set property as Kevin Dictionary 
			System.setProperty("freetts.voices", 
				"com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory"); 
				
			// Register Engine 
			Central.registerEngineCentral 
				("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral"); 

			// Create a Synthesizer 
			Synthesizer synthesizer =										 
				Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));	 
	
			// Allocate synthesizer 
			synthesizer.allocate();		 
			
			// Resume Synthesizer 
			synthesizer.resume();	 
			
			// speaks the given text until queue is empty. 
			synthesizer.speakPlainText(speech, null);		 
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY); 
			
			// Deallocate the Synthesizer. 
			synthesizer.deallocate();								 
		} 

		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
	} 

}
