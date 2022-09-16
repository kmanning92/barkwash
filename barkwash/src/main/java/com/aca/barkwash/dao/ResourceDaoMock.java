package com.aca.barkwash.dao;

import java.util.ArrayList;
import java.util.List;

import com.aca.barkwash.model.BreedSize;
import com.aca.barkwash.model.Resource;

public class ResourceDaoMock implements ResourceDao {

	private static List<Resource> resources = new ArrayList<Resource>();

	private static Integer lastResourceId = 10;

	private static Integer getNextResourceId() {
		return ++lastResourceId;
	}

	static {
		Resource nail = new Resource();
		nail.setTitle("nail trim");
		nail.setBreedsize(BreedSize.Irrelevant);
		nail.setPrice(12);
		nail.setId(1);

		Resource bathL = new Resource();
		bathL.setTitle("Oatmeal Bath (large or xl)");
		bathL.setBreedsize(BreedSize.Large);
		bathL.setPrice(40);
		bathL.setId(2);

		Resource bathM = new Resource();
		bathM.setTitle("Oatmeal Bath (Medium)");
		bathM.setBreedsize(BreedSize.Medium);
		bathM.setPrice(30);
		bathM.setId(3);

		Resource bathS = new Resource();
		bathS.setTitle("Oatmeal Bath (Small)");
		bathS.setBreedsize(BreedSize.Small);
		bathS.setPrice(25);
		bathS.setId(4);

		Resource teethCleaning = new Resource();
		teethCleaning.setTitle("Teeth Cleaning");
		teethCleaning.setBreedsize(BreedSize.Irrelevant);
		teethCleaning.setPrice(19);
		teethCleaning.setId(5);

		Resource fullServiceS = new Resource();
		fullServiceS.setTitle("Full Grooming Service");
		fullServiceS.setBreedsize(BreedSize.Small);
		fullServiceS.setPrice(40);
		fullServiceS.setId(6);

		Resource fullServiceM = new Resource();
		fullServiceM.setTitle("Full Grooming Service");
		fullServiceM.setBreedsize(BreedSize.Medium);
		fullServiceM.setPrice(50);
		fullServiceM.setId(7);

		Resource fullServiceL = new Resource();
		fullServiceL.setTitle("Full Grooming Service");
		fullServiceL.setBreedsize(BreedSize.Large);
		fullServiceL.setPrice(60);
		fullServiceL.setId(8);

		Resource fullServiceXl = new Resource();
		fullServiceXl.setTitle("Full Grooming Service");
		fullServiceXl.setBreedsize(BreedSize.XLarge);
		fullServiceXl.setPrice(70);
		fullServiceXl.setId(9);

		Resource earClean = new Resource();
		earClean.setTitle("Ear Cleaning");
		earClean.setBreedsize(BreedSize.Irrelevant);
		earClean.setPrice(12);
		earClean.setId(10);

		resources.add(nail);
		resources.add(bathL);
		resources.add(bathM);
		resources.add(bathS);
		resources.add(teethCleaning);
		resources.add(fullServiceS);
		resources.add(fullServiceM);
		resources.add(fullServiceL);
		resources.add(fullServiceXl);
		resources.add(earClean);
	}

	@Override
	public List<Resource> getResources() {
		List<Resource> myResources = new ArrayList<Resource>();
		myResources.addAll(resources);
		return myResources;
	}

	@Override
	public List<Resource> getResourcesByBreedSize(BreedSize breedSize) {
		List<Resource> myResources = new ArrayList<Resource>();
		for (Resource resource : resources) {
			if (resource.getBreedSize().equals(breedSize)) {
				myResources.add(resource);
			}
		}

		return myResources;
	}

	@Override
	public List<Resource> getResourcesByPrice(Integer price) {
		List<Resource> myResources = new ArrayList<Resource>();
		for (Resource resource : resources) {
			if (resource.getPrice().intValue() == price.intValue()) {
				myResources.add(resource);
			}

		}
		return myResources;
	}

	@Override
	public List<Resource> getResourcesByTitle(String title) {
		List<Resource> myResources = new ArrayList<Resource>();
		for (Resource resource : resources) {
			if (resource.getTitle().equals(title)) {
				myResources.add(resource);
			}
		}
		return myResources;
	}

	@Override
	public List<Resource> getResourcesById(Integer resourceId) {
		List<Resource> myResources = new ArrayList<Resource>();
		for (Resource resource : resources) {
			if (resource.getId().intValue() == resourceId.intValue()) {
				myResources.add(resource);
			}
		}
		return myResources;
	}

	@Override
	public Resource createResource(Resource newResource) {
		newResource.setId(getNextResourceId());
		resources.add(newResource);
		return newResource;
	}

	@Override
	public Resource updateResource(Resource updateResource) {
		int index = 0;
		for (int i = 0; i < resources.size(); i++) {
			if (resources.get(i).getId().intValue() == updateResource.getId().intValue()) {
				index = i;
				break;
			}
		}
		resources.set(index, updateResource);
		return updateResource;
	}

	@Override
	public Resource deleteResource(Integer resourceId) {
		int index = 0;
		for (int i = 0; i < resources.size(); i++) {
			if (resources.get(i).getId().intValue() == resourceId.intValue()) {
				index = i;
				break;
			}
		}
		Resource resource = resources.get(index);
		resources.remove(index);
		return resource;
	}
}