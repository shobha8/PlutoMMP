package org.iitworkforce.selenium.mmppluto;

import java.util.HashSet;
import java.util.Set;

public class Calculator {

	public static void main(String[] args) {
		String[] names= {"john","shobha","john","nancy","tom"};
		Set<String> store=new HashSet<String>();
		for(String name:names) {
		if( store.add(name)==false) {
			System.out.println("Duplicate Names :"+name);
		}
	}
	}
}
