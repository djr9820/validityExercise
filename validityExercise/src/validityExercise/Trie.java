package validityExercise;

import java.util.ArrayList;

/**
 * Class for a trie data structure
 * Tries can handle Strings comprised of letters, numbers, or both
 * Tries are comprised of the smaller TrieNode structures
 * 
 * @author Dan Roche	<roched1@wit.edu>
 */
public class Trie {

	final int ALPHA_OFFSET = 97;			// ascii index offset for letters
	final int NUMERIC_OFFSET = 48;			// 					  for decimal
	
	TrieNode root;				
	
	/**
	 * Constructor for Trie
	 * @param t enum type of trie to create
	 */
	public Trie(nodeType t){
		root = new TrieNode(t, null);
	}
	
	/**
	 * Removes any non-alphanumeric characters from a String and converts it to lowercase for comparisons
	 * @param s	String to clean
	 * @return	lowercase alphanumeric version of the String
	 */
	private String cleanString(String s) {
		String newS = "";
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(Character.isLetter(c) || Character.isDigit(c)) {
				if(Character.isLetter(c) && Character.isUpperCase(c))
					c = Character.toLowerCase(c);
				newS += c;
			}
		}
		return newS;
		
	}
	
	/**
	 * Recursive method to print contactInfo objects stored in trie
	 * Useful for debugging/developing Trie.java
	 * @param cur trieNode to print the contactInfo objects from
	 */
	private void printUniqueValues(TrieNode cur) {
		if(!cur.ref.isEmpty()) {
			for(ContactInfo i : cur.ref) {
				System.out.println(i);
			}
		}
		
		for(int i = 0; i < cur.children.length; i++) {
			if(cur.children[i] != null)
				printUniqueValues(cur.children[i]);
		}
	}
	
	/**
	 * Wrapper method to identify potential duplicates based on a given number of substitutions
	 * @param subs	number of substitutions
	 * @param rec	Reference of all contactInfo objects that were read in from file
	 * @return		ArrayList<contactInfo> of potentially duplicate contacts
	 */
	public ArrayList<ContactInfo> identifyDuplicates(int subs, ArrayList<ContactInfo> rec) {
		ArrayList<ContactInfo> duplicates = new ArrayList<ContactInfo>();
		for(ContactInfo test : rec) {
			_identifyDuplicates(cleanString(test.lName), subs, this.root, test, duplicates);
		}
		return duplicates;
	}
	
	/**
	 * Driving recursive algorithm to identify potentially duplicate data
	 * @param word 			String to travel throughout the trie with
	 * @param subs 			number of allowed substitutions from this node
	 * @param cur 		    current trieNode in the trie
	 * @param test			reference to test equality with	
	 * @param duplicates	running tally of identified duplicates
	 */
	public void _identifyDuplicates(String word, int subs, TrieNode cur, ContactInfo test, ArrayList<ContactInfo> duplicates) {
		if(word.length() != 0) {
			for(TrieNode t : cur.children) {	
				if(t != null) {
					if(t.data == word.charAt(0)) {
						// recursive call if the character is correct
						_identifyDuplicates(word.substring(1), subs, t, test, duplicates);	
					}
					else if(subs > 0) {
						// recursive call if the character is not correct, but there are still substitutions allowed
						_identifyDuplicates(word.substring(1), subs-1, t, test, duplicates);
					}
				}
			}
		}
		if(word.length() == 0 && !cur.ref.isEmpty()) {
			for(ContactInfo c : cur.ref) {
				if(!c.equals(test) && !duplicates.contains(c))
					duplicates.add(test);
			}
		}
	}
	
	/**
	 * Creates a path of trieNodes in the trie based on a given String
	 * @param s	String to add a branch based on
	 * @return	the leaf node of the branch
	 */
	public TrieNode addToTrie(String s) {
		
		TrieNode cur = root;
		s = cleanString(s);
		
		for(int i = 0; i < s.length(); i++) {
			
			int index = (int)(s.charAt(i));			// index children based on char
			
			if(root.getType() == nodeType.ALPHA)		// get correct index based on type
				index -= ALPHA_OFFSET;
			else if(root.getType() == nodeType.NUMERIC)
				index -= NUMERIC_OFFSET;
			else {
				if(index > 96)
					index = index - ALPHA_OFFSET + root.NUMERIC_CHILDREN;
				else
					index -= NUMERIC_OFFSET;
			}
			
			//System.out.println(index);
			
			if(cur.children[index] == null) {							// create node at index if null
				cur.children[index] = new TrieNode(cur.getType(), cur, s.charAt(i));
			}
			
			cur = cur.children[index];
		}
		
		return cur;
	}
	
	/**
	 * Simple enum to keep track of the type of node
	 */
	
}

enum nodeType{
	ALPHA, NUMERIC, BOTH
}

