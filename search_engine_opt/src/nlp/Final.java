package nlp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.TregexPattern;
import edu.stanford.nlp.util.CoreMap;

public class Final { 
	private static String question="1";
	private static String answer="";
	public void sentiment(String phrase){
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation annotation = pipeline.process(phrase);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
		  String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
		  answer=answer+"["+"("+sentiment+")" + sentence+"]"+ "\t";}
	}
	
	public List<String> getkeyword(){
		List<String> phrase_list = new ArrayList<String>();
	    Properties props = new Properties();
	    props.put("annotators","tokenize, ssplit, pos, lemma, ner, parse, dcoref, sentiment");
	    StanfordCoreNLP stanford = new StanfordCoreNLP(props);
	    TregexPattern npPattern = TregexPattern.compile("@NP");
	    
    	Scanner input = new Scanner(System.in);
        System.out.print("Question:");
        question = input.nextLine();

        //do{
	    // create an empty Annotation just with the given text
	    Annotation document = new Annotation(question);
	    // run all Annotators on this text
	    stanford.annotate(document);

	    List <CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
	   
	    for (CoreMap sentence : sentences) {

	        Tree sentenceTree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
	        TregexMatcher matcher = npPattern.matcher(sentenceTree);
	        while (matcher.find()) {
	            //this tree should contain "The fitness room" 
	            Tree nounPhraseTree = matcher.getMatch();
	            //Question : how do I find that "dirty" has a relationship to the nounPhraseTree
	            String NP=nounPhraseTree.toString();
	            if(!(NP.charAt(5)=='N'&&NP.charAt(6)=='P')){
	            	NP=NP.replaceAll("CC", "");
	            	NP=NP.replaceAll("CD", "");
	            	NP=NP.replaceAll("DT", "");
	            	NP=NP.replaceAll("EX", "");
	            	NP=NP.replaceAll("FW", "");
	            	NP=NP.replaceAll("IN", "");
	            	NP=NP.replaceAll("JJR", "");
	            	NP=NP.replaceAll("JJS", "");
	            	NP=NP.replaceAll("JJ", "");
	            	NP=NP.replaceAll("MD", "");
	            	NP=NP.replaceAll("NNPS", "");
	            	NP=NP.replaceAll("NNP", "");
	            	NP=NP.replaceAll("NP", "");
	            	NP=NP.replaceAll("NNS", "");
	            	NP=NP.replaceAll("NN", "");
	            	NP=NP.replaceAll("POS", "");
	            	NP=NP.replaceAll("PRP$", "");
	            	NP=NP.replaceAll("PRP", "");
	            	NP=NP.replaceAll("RBR", "");
	            	NP=NP.replaceAll("RBS", "");
	            	NP=NP.replaceAll("RB", "");
	            	NP=NP.replaceAll("RP", "");
	            	NP=NP.replaceAll("TO", "");
	            	NP=NP.replaceAll("UH", "");
	            	NP=NP.replaceAll("VBD", "");
	            	NP=NP.replaceAll("VBG", "");
	            	NP=NP.replaceAll("VBN", "");
	            	NP=NP.replaceAll("VBP", "");
	            	NP=NP.replaceAll("VBZ", "");
	            	NP=NP.replaceAll("VB", "");
	            	NP=NP.replaceAll("WDT", "");
	            	NP=NP.replaceAll("WP$", "");
	            	NP=NP.replaceAll("WP", "");
	            	NP=NP.replaceAll("WRB", "");
	            	NP=NP.replaceAll("ADJP", "");
	            	NP=NP.replaceAll("LS", "");
	            	NP=NP.replace("(", "");
	            	NP=NP.replace(")", ""); 
	            	Document doc = new Document(NP);
	            	String phrase="";
	            	for (Sentence sent : doc.sentences()) {
	            		for (int i=0; i<sent.posTags().size(); i++) {
	            			if ((sent.posTags().get(i).equals("CD")||sent.posTags().get(i).equals("FW")||
	                   			sent.posTags().get(i).equals("JJ")||sent.posTags().get(i).equals("JJR")||
	                   			sent.posTags().get(i).equals("JJS")||sent.posTags().get(i).equals("NN")||
	                   			sent.posTags().get(i).equals("NNP")||sent.posTags().get(i).equals("NNPS")||
	                   			sent.posTags().get(i).equals("NNS")||sent.posTags().get(i).equals("RB")||
	                   			sent.posTags().get(i).equals("RBR")||sent.posTags().get(i).equals("RBS")||
	                   			sent.posTags().get(i).equals("VB")||sent.posTags().get(i).equals("VBD")||
	                   			sent.posTags().get(i).equals("VBG")||sent.posTags().get(i).equals("VBN")||
	                   			sent.posTags().get(i).equals("VBP")||sent.posTags().get(i).equals("VBZ"))
	                   			&&!sent.word(i).equals("is")&&!sent.word(i).equals("are")&&
	                   					!sent.word(i).equals("am")&&!sent.word(i).equals("was")&&
	                   					!sent.word(i).equals("were")&&!sent.word(i).equals("been")
	                   					&&!sent.word(i).equals("be")){
	                   		phrase=phrase+sent.word(i)+" ";
	                   		question=question.replaceAll(sent.word(i),"");
	                   		}
	                   }
	           }  
	            	if(!phrase.equals("")){
	            		phrase_list.add(phrase.substring(0,phrase.length()-1));
	            		Final t = new Final();
	            		t.sentiment(phrase);
	            		}
	           }
	            }
	        }
	    Document docremain = new Document(question);
        
        for (Sentence sent : docremain.sentences()) { 	
        for (int i=0; i<sent.posTags().size(); i++) {
            	if ((sent.posTags().get(i).equals("CD")||sent.posTags().get(i).equals("FW")||
            			sent.posTags().get(i).equals("JJ")||sent.posTags().get(i).equals("JJR")||
            			sent.posTags().get(i).equals("JJS")||sent.posTags().get(i).equals("NN")||
            			sent.posTags().get(i).equals("NNP")||sent.posTags().get(i).equals("NNPS")||
            			sent.posTags().get(i).equals("NNS")||sent.posTags().get(i).equals("RB")||
            			sent.posTags().get(i).equals("RBR")||sent.posTags().get(i).equals("RBS")||
            			sent.posTags().get(i).equals("VB")||sent.posTags().get(i).equals("VBD")||
            			sent.posTags().get(i).equals("VBG")||sent.posTags().get(i).equals("VBN")||
            			sent.posTags().get(i).equals("VBP")||sent.posTags().get(i).equals("VBZ"))
            			&&!sent.word(i).equals("is")&&!sent.word(i).equals("are")&&
            					!sent.word(i).equals("am")&&!sent.word(i).equals("was")&&
            					!sent.word(i).equals("were")&&!sent.word(i).equals("been")
            					&&!sent.word(i).equals("")&&!sent.word(i).equals("be"))
            	{phrase_list.add(sent.word(i));
            		Final t = new Final();
            		t.sentiment(sent.word(i));}
            }
	    }
        System.out.println(answer);
        //answer="";
        //System.out.print("Question:");
        //question = input.nextLine();
	//}
        //while(!question.equals("0"));
        input.close();
        return phrase_list;
}
}
	       


