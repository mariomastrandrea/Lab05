package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
	
	public void computeAnagramsOf(String word)
	{
		if(this.meaningfulAnagramsByWord.containsKey(word) && this.meaninglessAnagramsByWord.containsKey(word))
			return; //already done
		
		List<String> anagrams = new ArrayList<>();
		//this.recursiveAnagramsComputation(word);
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

	private void recursiveAnagramsComputation(String word)
	{
		return;
	}
	
	public String printMeaningfulAnagramsOf(String word)
	{
		Collection<String> anagrams = this.meaningfulAnagramsByWord.get(word);
		return this.printCollection(anagrams);
	}
	
	public String printMeaninglessAnagramsOf(String word)
	{	
		Collection<String> anagrams = this.meaninglessAnagramsByWord.get(word);
		return this.printCollection(anagrams);
	}
	
	private String printCollection(Collection<String> collection)
	{
		StringBuilder builder = new StringBuilder();
		
		for(String word : collection)
		{
			if(builder.length() > 0)
				builder.append(", ");
			
			builder.append("\"").append(word).append("\"");
		}
		
		return builder.toString();
	}
	
	
}
