package com.tamboro.question.one;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HSVColor {
	Set<Set<HSVModel>> pairSet =new HashSet<Set<HSVModel>>();
	Set<HSVModel> hsvComplementaryColorSet;
	public static void main(String[] args) {
		HSVColor hsv=new HSVColor();
		String hsvColor = null;
		
		Set<HSVModel> hsvSet = new HashSet<HSVModel>();
		for (int i = 1; i <= 4; i++) {
			Scanner  scanner = new Scanner(System.in);
			System.out.println("Provide HSVColor like(110,7,10)");
			hsvColor = scanner.nextLine();
			String [] hsvColorArray=hsvColor.split(",");
			HSVModel hsvModel = new HSVModel();
			hsvModel.setHue(Integer.parseInt(hsvColorArray[0]));
			hsvModel.setSaturtion(Integer.parseInt(hsvColorArray[1]));
			hsvModel.setValue(Integer.parseInt(hsvColorArray[2]));
			hsvSet.add(hsvModel);
		}
		
		for (HSVModel hsvModelColor : hsvSet) {
			
			Integer hue = (hsvModelColor.getHue() + 180) > 360 ?(hsvModelColor.getHue() + 180) - 360 :hsvModelColor.getHue()+180; 
			HSVModel oppositColor=new HSVModel(hue,hsvModelColor.getSaturtion(),hsvModelColor.getValue());
			boolean isoppositColorAvailable = hsvSet.contains(oppositColor);
			if(isoppositColorAvailable){
				hsv.hsvComplementaryColorSet = new HashSet<HSVModel>();
				hsv.hsvComplementaryColorSet.add(hsvModelColor);
				hsv.hsvComplementaryColorSet.add(oppositColor);
			}
			hsv.pairSet.add(hsv.hsvComplementaryColorSet);
		}
		int i=0;
		for (Set<HSVModel> set : hsv.pairSet) {
			i=i+1;
			System.out.println(i +". " +set);
			
			
		}
	}
}
