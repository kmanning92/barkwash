package com.aca.barkwash.dao;

import java.util.List;

import com.aca.barkwash.model.BreedSize;
import com.aca.barkwash.model.Resource;

public interface ResourceDao {

	public List<Resource> getResources();
	public List<Resource> getResourcesByBreedSize(BreedSize breedSize);
	public List<Resource> getResourcesByPrice(Integer price);
	public List<Resource> getResourcesByTitle(String title);
	public List<Resource> getResourcesById(Integer resourceId);
	public Resource createResource(Resource resource);
	public Resource updateResource(Resource resource);
	public Resource deleteResource(Integer resourceId);
}
