package it.polito.tdp.anagrammi.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.anagrammi.dao.AnagrammiDao;

public class AnagrammiModel
{
	private Map<String, Collection<String>> meaningfulAnagramsByWord;
	private Map<String, Collection<String>> meaninglessAnagramsByWord;
	private AnagrammiDao anagrammiDao;
	
	
	public AnagrammiModel()
	{
		this.meaningfulAnagramsByWord = new HashMap<>();
		this.meaninglessAnagramsByWord = new HashMap<>();
		this.anagrammiDao = new AnagrammiDao();
	}
	
	private void computeAnagramsOf(String word)
	{
		Collection<String> anagrams = new HashSet<>();
		StringBuilder partialAnagram = new StringBuilder();
		StringBuilder remainedChars = new StringBuilder(word);
		
		this.recursiveAnagramsOf(partialAnagram, remainedChars, anagrams);		
		
		Collection<String> meaningfulAnagrams = new HashSet<>();
		Collection<String> meaninlessAnagrams = new HashSet<>();

		for(String anagram : anagrams)
		{
			if(anagrammiDao.isMeaningful(anagram))
				meaningfulAnagrams.add(anagram);
			else
				meaninlessAnagrams.add(anagram);
		}
		
		this.meaningfulAnagramsByWord.put(word, meaningfulAnagrams);
		this.meaninglessAnagramsByWord.put(word, meaninlessAnagrams);
	}

	private void recursiveAnagramsOf(
			StringBuilder partialAnagram, StringBuilder remainedChars, Collection<String> anagrams)
	{
		if(remainedChars.length() == 0)
		{	
			String newAnagram = partialAnagram.toString();
			anagrams.add(newAnagram);
			return;
		}		
		
		int loops = remainedChars.length();
		
		Set<Character> insertedChars = new HashSet<>();
		
		for(int i=0; i<loops; i++)
		{		
			char c = remainedChars.charAt(i);
			
			if(insertedChars.contains(c))
				continue;
			else
				insertedChars.add(c);
			
			partialAnagram.append(c);
			remainedChars.deleteCharAt(i);
			
			/* this part is useful to optimise the search
			 * 
			if(partialAnagram.length() % 4 == 0)
			{
				if(this.anagrammiDao.existsPrefix(partialAnagram.toString()))
					recursiveAnagramsOf(partialAnagram, remainedChars, anagrams);
			}
			else
				recursiveAnagramsOf(partialAnagram, remainedChars, anagrams);
			*/
			recursiveAnagramsOf(partialAnagram, remainedChars, anagrams);

				
			//backtracking
			int lastIndex = partialAnagram.length() - 1;
			partialAnagram.deleteCharAt(lastIndex);
			remainedChars.insert(i,c);
		}
		return;
	}
	
	public String printMeaningfulAnagramsOf(String word)
	{
		if(!this.meaningfulAnagramsByWord.containsKey(word))
			this.computeAnagramsOf(word); //not done yet

		Collection<String> anagrams = this.meaningfulAnagramsByWord.get(word);
		return this.printCollection(anagrams);
	}
	
	public String printMeaninglessAnagramsOf(String word)
	{	
		if(!this.meaninglessAnagramsByWord.containsKey(word))
			this.computeAnagramsOf(word); //not done yet
		
		Collection<String> anagrams = this.meaninglessAnagramsByWord.get(word);
		return this.printCollection(anagrams);
	}
	
	private String printCollection(Collection<?> collection)
	{
		StringBuilder builder = new StringBuilder();
		
		for(Object o : collection)
		{
			if(builder.length() > 0)
				builder.append(", ");
			
			//builder.append("\"").append(o.toString()).append("\"");
			builder.append(o.toString());
		}
		return builder.toString();
	}
	
	
}
