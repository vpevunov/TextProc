package com.myText;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/*
Given a line of text, verify that the beginning and ending parentheses match up.
1)      The text contains only one type of parentheses, e.g., ()
2)      The text contains more than one type of parentheses, e.g. (), {}, []

*/

public class MyApp {
	
	private static final Map<Character, Character> openToClose;
	private static final Map<Character, Character> closeToOpen;
	
	static {
		openToClose = new HashMap<Character, Character>();
		openToClose.put('(', ')');
		openToClose.put('{', '}');
		openToClose.put('[', ']');
		
		closeToOpen = new HashMap<Character, Character>();
		for(char key: openToClose.keySet()){
			closeToOpen.put(openToClose.get(key), key);
		}
	}
	
	
	public static void main(String [] args){
		System.out.println(validatePrentheses("()a()", new char[] {'(',')'}));
		
		MyApp me = new MyApp();
		
		System.out.println(me.validatePrsText("({}){}"));
		
	}
	
	public static boolean validatePrentheses(String text, char[] pts){
		boolean valid = true;
		
		if (text != null && text.length() > 0){
			int countOpenP = 0;
			int prevIndexClose = 0;
			for (int prevIndex = -1; text.indexOf(pts[0], prevIndex + 1) > -1; prevIndex = text.indexOf(pts[0], prevIndex + 1)){
				
				countOpenP++;
				if (text.indexOf(pts[0], prevIndex + 1) < text.indexOf(pts[1], prevIndexClose + 1)){
					prevIndexClose = text.indexOf(pts[1], prevIndexClose + 1);
					continue;
				}
				valid = false;
			}
			
			int countCloseP = 0;
			for (int prevIndex = -1; text.indexOf(pts[1], prevIndex + 1) > -1; prevIndex = text.indexOf(pts[1], prevIndex + 1)){
				countCloseP++;
			}
			
			if (countOpenP != countCloseP){
				valid = false;
			}
			
		}
		
		return valid;
	}
	
	public boolean validatePrsText(String text){
		boolean valid = true;
		char[] textArr = text.toCharArray();
		
		Deque<Character> openPrsStack = new ArrayDeque<Character>();
		
		for(char currChar: textArr){
			if(openToClose.containsKey(currChar)){
				openPrsStack.push(currChar);				
			} else if(closeToOpen.containsKey(currChar)){
				try{
					if(!closeToOpen.get(currChar).equals(openPrsStack.pop())){
						valid = false;
					}
				} catch(NoSuchElementException e) {
					valid = false;
					break;
				}
			}
		}
		if(!openPrsStack.isEmpty()){
			valid = false;
		}
		
		return valid;
	}
}