package com.aca.barkwash.controller;

import java.util.List;

import com.aca.barkwash.model.BreedSize;
import com.aca.barkwash.model.Resource;
import com.aca.barkwash.service.ResourceService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

//sets the url to get all resources
@Path("/resources")
public class ResourceController {
	private ResourceService service = new ResourceService();
	
	@DELETE
	@Path("/{resourceIdValue}")
	public Resource deleteResource(@PathParam("resourceIdValue") Integer resourceId) {
		System.out.println("resourceId:" + resourceId);
		return service.deleteResource(resourceId);
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Resource updateResource(Resource resource) {
		System.out.println("Resource title : " + resource.toString());
		return service.updateResource(resource);
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Resource createResource(Resource resource) {
		System.out.println("Resource title : " + resource.toString());
		return service.createMovie(resource);
	}

	@GET
	public List<Resource> getResources() {
		return service.getResources();
	}

	@GET
	@Path("/breedsize/{breedSizeValue}")
	public List<Resource> getResourcesByBreedSize(@PathParam("breedSizeValue") BreedSize breedSize) {
		System.out.println("Breed Size:" + breedSize);
		return service.getResourcesByBreedSize(breedSize);
	}

	@GET
	@Path("/price/{priceValue}")
	public List<Resource> getResourcesByPrice(@PathParam("priceValue") Integer price) {
		System.out.println("price:" + price);
		return service.getResourcesByPrice(price);
	}

	@GET
	@Path("/title/{titleValue}")
	public List<Resource> getResourcesByTitle(@PathParam("titleValue") String title) {
		System.out.println("title: " + title);
		return service.getResourcesByTitle(title);
	}

	@GET
	@Path("/{resourceIdValue}")
	public List<Resource> getResourcesById(@PathParam("resourceIdValue") Integer id) {
		System.out.println("Resource ID:  " + id);
		return service.getResourcesById(id);
	}
}