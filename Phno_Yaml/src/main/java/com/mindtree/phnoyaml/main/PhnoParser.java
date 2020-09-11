package com.mindtree.phnoyaml.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class PhnoParser 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
    	
    	Constructor constructor = new Constructor(Country.class);
		Yaml yaml = new Yaml( constructor );
		Scanner scan= new Scanner(System.in);
		PrintWriter pw=null;
		
		InputStream input = null;
		try {
			input = new FileInputStream(new File("./src/main/java/PhoneNumbers.yml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Country data = yaml.loadAs( input, Country.class );
		int n=data.getPhonenumbers().size();
		
		System.out.println("Enter the country name:");
		String cname=scan.nextLine();
		int i=1;
		boolean flag=false;
		while(i<=n)
		{
			
			if(data.getPhonenumbers().get("country"+i).getName().equalsIgnoreCase(cname))
			{
			flag=true;
			String countryname=yaml.dump(data.getPhonenumbers().get("country"+i).getName());
			String prefix=data.getPhonenumbers().get("country"+i).getPrefix();
			String format=yaml.dump(data.getPhonenumbers().get("country"+i).getFormat());
			System.out.println("...Country Available");
			System.out.println("Enter the number of phone numbers to generate:");
			Long amount=scan.nextLong();
			StringBuilder builder= new StringBuilder();
			String columnname="Sl no.,Phone no.";
			builder.append(columnname +"\n");
			scan.close();
			Set<String> numbers= new HashSet<String>();
			GenerateNumbers generate= new GenerateNumbers();
			numbers=generate.numbers(countryname, prefix, format, amount);
			long k=1;
			pw= new PrintWriter(new File("NewData.csv"));
			for(String num: numbers)
			{
				
				System.out.println(num);
				builder.append(k+++",'"+num+"\n");	
			}
			pw.write(builder.toString());
			pw.close();
			break;
		}
		i++;
		}
		if(!flag)
		{
			System.out.println("...Country not available");
		}
	}
}
