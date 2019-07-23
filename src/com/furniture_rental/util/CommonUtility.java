package com.furniture_rental.util;

public class CommonUtility {

	public String getRadomValue(){
		String value="";
		double a;
		for(int i=1; i<=16; i++){
			a=Math.random()*10;
			value=value+((int)a);
		}
		return value;
	}

	public String hiddenPass(String password){
		int beginIndex=0;
		int endIndex=1;

		StringBuilder stringBuilder=new StringBuilder("***************");
		String hiddenPass=stringBuilder.replace(beginIndex, endIndex, password.substring(beginIndex, endIndex)).toString();
		return hiddenPass;
	}
}
