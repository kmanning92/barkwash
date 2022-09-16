package com.aca.barkwash.model;

public enum BreedSize {
	 Small, Medium, Large, XLarge, Irrelevant;
	
	public static BreedSize convertStringToBreedSize(String value) {
		BreedSize myBreedSize = null;
		for(BreedSize breedSize : BreedSize.values()) {
			if(breedSize.toString().equalsIgnoreCase(value)) {
				myBreedSize = breedSize;
			}
		}
		return myBreedSize;
	}

}

