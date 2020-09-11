package com.mindtree.phnoyaml.main;

import java.util.HashSet;
import java.util.Set;

import com.mifmif.common.regex.Generex;

public class GenerateNumbers 
{
	public Set<String> numbers(String country, String prefix, String format, Long amount)
	{
		
		Set<String> gennumbers= new HashSet<String>();
		Generex gen=new Generex(format);
		for(long i=1;i<=amount;)
		{
		String str=gen.random();
		String myno=str.replace("^","");
		myno=myno.replace("$","");
		if(gennumbers.add(prefix+"-"+myno))
		i++;
		}
		return gennumbers;
	}
}
