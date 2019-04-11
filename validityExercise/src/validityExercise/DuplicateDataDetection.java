package validityExercise;

import java.io.*;
import java.util.ArrayList;

/**
 * Main application to search for duplicate information in the supplied Validity data files
 * 
 * @author Dan Roche <roched1@wit.edu>
 *
 */
public class DuplicateDataDetection {

	/**
	 * Attempts to read data on contact information in from an input file
	 * Reads the data into a list of contactInfo objects as well as the given trie
	 * @param file		data file to attempt to read from
	 * @param lastName	the trie to rad the data into
	 * @return			list of all the data as contactInfo objects
	 */
	public static ArrayList<ContactInfo> readDataIntoTrie(String file, Trie lastName) 
	{ 
		ArrayList<ContactInfo> cList = new ArrayList<>();
        BufferedReader read = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            read = new BufferedReader(new FileReader(file));
            line = read.readLine();
            int id = 0;
            while ((line = read.readLine()) != null) {

                // row into array based on comma
                String[] row = line.split(cvsSplitBy);
                row[0] = id + "";
                TrieNode t = lastName.addToTrie(row[2]);
                t.addRef(row);
                cList.add(new ContactInfo(row));
                id++;
            }
            
            read.close();

        } catch (FileNotFoundException e) {		// if file does not exist
            e.printStackTrace();
        } catch(IOException e) {
        	e.printStackTrace();
        }
        
        return cList;
	} 
	
	/**
	 * main method for identifying duplicate data in a Validity contact information file
	 * @param args	command line arguments; should only contain the String name of the data file
	 */
	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("Usage: duplciateDataDetection inputFile");
			System.exit(0);
		}
		
		Trie lastName = new Trie(nodeType.ALPHA);
		ArrayList<ContactInfo> rec = readDataIntoTrie(args[0], lastName);
		ArrayList<ContactInfo> dup = lastName.identifyDuplicates(1, rec);
		
		System.out.println("Potential Duplicates:");
		
		for(ContactInfo c : dup)
			System.out.println("\t" + c);
		
		System.out.println("\nNone Duplicates:");
		
		for(ContactInfo c: rec) {
			if(!dup.contains(c))
				System.out.println("\t" + c);
		}
	}

}
