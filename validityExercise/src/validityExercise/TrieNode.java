package validityExercise;

import java.util.ArrayList;

/**
 * Class for a node structure used in a trie
 * Has a varying number of children based on type of node
 * Each node corresponds to a specific char
 * 
 * @author Dan Roche <roched1@wit.edu>
 *
 */
public class TrieNode {
	
	final int ALPHA_CHILDREN = 26;			// # children for alpha trieNode
	final int NUMERIC_CHILDREN = 10;		// # children for numeric trieNode
		
	TrieNode parent;
	char data;
	nodeType type;
	ArrayList<ContactInfo> ref;		// reference to contactInfo which end at that node
	TrieNode [] children;
	
	/**
	 * Constructor for root node
	 * @param t	type of node (alpha, numeric, both)
	 * @param p	reference to parent node
	 */
	public TrieNode(nodeType t, TrieNode p) {
		this.parent = p;
		this.type = t;
		ref = new ArrayList<>();
		createChildren();
	}
	
	/**
	 * Constructor for trieNode
	 * @param t	type of node (alpha, numeric, both)
	 * @param p	reference to parent node
	 * @param c	character representation of this node
	 */
	public TrieNode(nodeType t, TrieNode p, char c) {
		this(t, p);
		this.data = c;
	}
		
	/**
	 * Helper method to initialize the number of children for that node based on type
	 * Number children = 26 for alpha, 10 for numeric, 36 for both 
	 */
	private void createChildren() {
		if(this.type == nodeType.ALPHA) {
			children = new TrieNode [ALPHA_CHILDREN];
		}
		else if(this.type == nodeType.BOTH) {
			children = new TrieNode [ALPHA_CHILDREN + NUMERIC_CHILDREN];
		}
		else
			children = new TrieNode [NUMERIC_CHILDREN];
		}
	/**
	 * Adds a contactInfo object to the reference list for this node
	 * @param row String [] used to construct the contactInfo object
	 */
	public void addRef(String [] row) {
		ref.add(new ContactInfo(row));
	}
		
	/**
	 * Getter for the type of node
	 * @return enum type
	 */
	public nodeType getType() { return this.type; }
		
}
