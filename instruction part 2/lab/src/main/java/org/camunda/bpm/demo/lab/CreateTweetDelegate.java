package org.camunda.bpm.demo.lab;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CreateTweetDelegate implements JavaDelegate {
	  public void execute(DelegateExecution execution) throws Exception {      
	    String content = "I like the camunda BPM Online Training :-) Cheers MUHAMMAD TAUSEEF";

	    AccessToken accessToken = new AccessToken("220324559-jet1dkzhSOeDWdaclI48z5txJRFLCnLOK45qStvo", "B28Ze8VDucBdiE38aVQqTxOyPc7eHunxBVv7XgGim4say");
	    Twitter twitter = new TwitterFactory().getInstance();
	    twitter.setOAuthConsumer("lRhS80iIXXQtm6LM03awjvrvk", "gabtxwW8lnSL9yQUNdzAfgBOgIMSRqh7MegQs79GlKVWF36qLS");
	    twitter.setOAuthAccessToken(accessToken);
	 
	    twitter.updateStatus(content);
	  }
	} 
