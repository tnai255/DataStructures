package nz.ac.auckland.se281.a4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nz.ac.auckland.se281.a4.ds.Graph;
import nz.ac.auckland.se281.a4.ds.Node;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************
public class TweetGraph extends Graph {

	protected List<Tweet> tweets;
	// Change this to map
	protected Map<TwitterHandle, List<Tweet>> nodeTweets;

	public TweetGraph(List<String> edges, List<Tweet> tweets, Map<TwitterHandle, List<Tweet>> map) throws Exception {
		super(edges);
		this.tweets = tweets;
		// changed to LinkedHashMap for fixed order
		this.nodeTweets = new LinkedHashMap<>();
		nodeTweets = map;
	}

	public List<Tweet> getTweets(Node n) {
		return nodeTweets.get(n);
	}

	public List<String> getTweetsTexts(TwitterHandle n) {
		List<String> texts = new ArrayList<>(); // Only allowed to use ArrayList HERE !!!
		for (Tweet t : getTweets(n)) {
			texts.add(t.getTextString());
		}
		return texts;
	}

	// search for a keyword in a tweet starting from a given node
	public String searchTweet(TwitterHandle user, String tweetKeyword) {

		// loop through all the nodes in dfs order
		for (Node<String> node : depthFirstSearch(user, true)) {
			// gets handle by calling helper method
			TwitterHandle handle = getHandle(node);
			// loops through all the tweets of the input user
			for (Tweet tweet : getTweets(node)) {
				// checks if the string contains the input keyword
				if (tweet.getTextString().contains(tweetKeyword)) {
					// returns the text, the user, and the key word
					return "The tweet string found is: " + tweet.getTextString() + "\nUser " + handle.getName() + " {"
							+ handle.getID() + "} tweeted " + tweetKeyword;
				}
			}
		}

		// if no text with the keyword was found return following string
		return "No successor of " + user.toString() + " tweeted " + tweetKeyword;
	}

	/**
	 * Get the TwitterHandle for a given node by going through key set
	 * 
	 * @param node (node with user id as its value)
	 * @return twitterhandle (handle of user)
	 */
	public TwitterHandle getHandle(Node<String> node) {

		// loops through the key set of the map node tweets
		for (TwitterHandle handle : nodeTweets.keySet()) {
			// if the value of the node and the id of the handle equal then return the
			// handle
			if (handle.getID().equals(node.getValue())) {
				return handle;
			}
		}

		// if no handle was found return null
		return null;

	}
}
