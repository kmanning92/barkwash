package com.aca.barkwash.service;

import java.util.List;

import com.aca.barkwash.dao.ResourceDao;
import com.aca.barkwash.dao.ResourceDaoImpl;
import com.aca.barkwash.dao.ResourceDaoMock;
import com.aca.barkwash.model.BreedSize;
import com.aca.barkwash.model.RequestError;
import com.aca.barkwash.model.Resource;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ResourceService {

	private ResourceDao resourceDao = new ResourceDaoImpl();

	public List<Resource> getResources() {
		return resourceDao.getResources();
	}

	public List<Resource> getResourcesByBreedSize(BreedSize breedSize) {
		return resourceDao.getResourcesByBreedSize(breedSize);
	}

	public List<Resource> getResourcesByPrice(Integer price) {
		validatePrice(price);
		return resourceDao.getResourcesByPrice(price);
	}

	private void validatePrice(Integer price) {
		if (price < 9 || price > 200) {
			RequestError error = new RequestError(1, "Invalid price value: " + price);
			Response response = Response.status(400).entity(error).build();
			throw new WebApplicationException(response);

		}

	}

	public List<Resource> getResourcesByTitle(String title) {
		return resourceDao.getResourcesByTitle(title);
	}

	public List<Resource> getResourcesById(Integer resourceId) {
		validateResourceId(resourceId);
		return resourceDao.getResourcesById(resourceId);
	}

	private void validateResourceId(Integer resourceId) {
		if (null == resourceId || resourceId < 0) {
			RequestError error = new RequestError(2, "Invalid ID:" + resourceId + ". ID must be > 0 and less than 13");
			Response response = Response.status(400).entity(error).build();
			throw new WebApplicationException(response);

		}
	}
	
	public Resource updateResource(Resource updateResource) {
		//TODO needs validation...
		validatePrice(updateResource.getPrice());
		validateResourceId(updateResource.getId());
		List<Resource> resources = resourceDao.getResourcesById(updateResource.getId());
		if(resources.size() == 1) {
			return resourceDao.updateResource(updateResource);
			
		} else {
			RequestError error = new RequestError(4, "Resource ID nonexistant: " + updateResource.getId() 
			+ ".");
			Response response = Response.status(400).entity(error).build();
			throw new WebApplicationException(response);
		}
		
		
	}
	
	private void validateTitle(String title) {
		if (title.length() < 1 || title.length() > 20) {
			RequestError error = new RequestError(3, "Invalid title: " + title + " must be at least > 1 and <= 20 ");
			Response response = Response.status(400).entity(error).build();
			throw new WebApplicationException(response);

		}
	}

	public Resource createResource(Resource resource) {
		validatePrice(resource.getPrice());
		validateTitle(resource.getTitle());
		return resourceDao.createResource(resource);
	}

	public Resource deleteResource(Integer resourceId) {
		validateResourceId(resourceId);
		List<Resource> resources = resourceDao.getResourcesById(resourceId);
		if(resources.size() == 1) {
			return resourceDao.deleteResource(resourceId);
		} else {
			RequestError error = new RequestError(5, "Resource ID nonexistant: " + resourceId 
			+ ".");
			Response response = Response.status(400).entity(error).build();
			throw new WebApplicationException(response);
		}
		
	}
}