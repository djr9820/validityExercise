# Validity Duplicate Data Detection Exercise
Take Home Exercise for Validity Application

### Prerequisites

Jdk 1.8 - (https://www.oracle.com/technetwork/java/javaee/downloads/jdk8-downloads-2133151.html)

## Authors

* **Dan Roche** - *Applicant* - [djr9820](https://github.com/djr9820)

## Usage

duplicateDataDuplication.java "data/normal.csv"

Output:

Potential Duplicates:
	Kale, Gipp, Klein Group, kgipp3@360.cn, 4985 Menomonie Drive, , 94975, Petaluma, California, 707-840-2551
	Barney, Parncutt, "Collier,  Grady and Huels", bparncutta@ezinearticles.com, 07700 Rutledge Court, , 11205, Brooklyn, New York, 718-430-6357

None Duplicates:
	Donalt, Canter, Gottlieb Group, dcanter0@nydailynews.com, 9 Homewood Alley, , 50335, Des Moines, Iowa, 515-601-4495
	Daphene, McArthur, "West,  Schimmel and Rath", dmcarthur1@twitter.com, 43 Grover Parkway, , 30311, Atlanta, Georgia, 770-271-7837
  
## Design Choice

I decided to make a trie data strucutre to compare the data. I made this choice because a trie has a runtime of O(M) where M is the length of a given key rather than other approaches such as the levenshtein distance approach which would require O(n*n) comparisons  
  
## Next Steps
  
This was my approach given the reccomended 4 hour time constraint. I recognize that my approach does have some inherent naivety. Admittedly it only compares last names and instances. However, given more time it could easily be adapted to compare more than just last names. And in fact the trie data strucutre has been built to handle some of the other data fields such as phone numbers and addresses.
  
  
