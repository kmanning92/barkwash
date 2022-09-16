package com.aca.barkwash.model;

public class Resource {

		private String title;
		private Integer price;
		private BreedSize breedSize;
		private Integer id;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		
		}
		
		public BreedSize getBreedSize() {
			return breedSize;
		}
		public void setBreedsize(BreedSize breedSize) {
			this.breedSize = breedSize;
		}
		public Integer getPrice() {
			return price;
		}
		public void setPrice(Integer price) {
			this.price = price;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
	
		@Override 
		public String toString() {
			return "Resource [ " + id + ", " + title + ", " + price + ", " + breedSize + " ]";
		}
		
}
