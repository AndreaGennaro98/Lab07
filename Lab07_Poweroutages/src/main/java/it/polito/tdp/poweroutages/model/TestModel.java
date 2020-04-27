package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		List<Nerc> l=new ArrayList<>(model.getNercList());
		System.out.println(l);

	}

}
