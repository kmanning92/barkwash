package com.aca.barkwash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.aca.barkwash.model.BreedSize;
import com.aca.barkwash.model.Resource;








public class ResourceDaoImpl implements ResourceDao {

	private static String selectAllResources = 
			"SELECT id, title, price, breedsizeId " + "FROM resources";
	
	private static String selectResourcesByBreedSize = "SELECT id, title, price, breedsizeId "
			 + "FROM resources " + "WHERE breedsizeId = ?";
	
	private static String selectResourcesByPrice = "SELECT id, title, price, breedsizeId "
			 + "FROM resources " + "WHERE price = ?";
	
	private static String selectResourcesByTitle = "SELECT id, title, price, breedsizeId "
			 + "FROM resources" + " WHERE title LIKE ?";

	private static String selectResourcesById = "SELECT id, title, price, breedsizeId "
			+ "FROM resources " + "WHERE id = ? ";
	
	private static String deleteResourceById = "DELETE FROM resources " + "WHERE id = ? ";
	
	private static String updateResourcesById = "UPDATE resources " + "SET title = ?, " + "price = ?, "
			+ "breedsizeId = ? " + "WHERE id = ? ";
	
	private static String createResource = "INSERT INTO resources (title, price, breedsizeId) " + "VALUES " + "(?, ?, ?)";
	
	private static String selectNewResourceId =
			"SELECT LAST_INSERT_ID() AS 'resourceId' ";
	
	private int getNewResourceId(Connection connection) {
		ResultSet rs = null;
		Statement statement = null;
		int newResourceId = 0;
		
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(selectNewResourceId);
			while (rs.next()) {
				newResourceId = rs.getInt("resourceId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return newResourceId;
	}
	
	@Override
	public List<Resource> getResources() {
		List<Resource> myResources = new ArrayList<Resource>();
		ResultSet result = null;
		Statement statement = null;
		
		Connection connection = MariaDbUtil.getConnection();
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(selectAllResources);
			myResources = makeResource(result);
		}   catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					result.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		
	
		return myResources;
	}
	
		private List<Resource> makeResource(ResultSet result) throws SQLException {
			List<Resource> myResources = new ArrayList<Resource>();
			while (result.next()) {
				Resource resource = new Resource();
				resource.setTitle(result.getString("title"));
				resource.setId(result.getInt("id"));
				resource.setPrice(result.getInt("price"));
				String breedSizeString = result.getString("breedsizeId");
				resource.setBreedsize(BreedSize.convertStringToBreedSize(breedSizeString));
				myResources.add(resource);
			

			}
			return myResources;

			
		}

	@Override
	public List<Resource> getResourcesByBreedSize(BreedSize breedSize) {
		List<Resource> myResources = new ArrayList<Resource>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection connection = MariaDbUtil.getConnection();

		try {
			ps = connection.prepareStatement(selectResourcesByBreedSize);
			ps.setString(1, breedSize.toString());
			result = ps.executeQuery();
			myResources = makeResource(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return myResources;
	}

	@Override
	public List<Resource> getResourcesByPrice(Integer price) {
		List<Resource> myResources = new ArrayList<Resource>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection connection = MariaDbUtil.getConnection();

		try {
			ps = connection.prepareStatement(selectResourcesByPrice);
			ps.setInt(1, price);
			result = ps.executeQuery();
			myResources = makeResource(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return myResources;
	}

	@Override
	public List<Resource> getResourcesByTitle(String title) {
		List<Resource> myResources = new ArrayList<Resource>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection connection = MariaDbUtil.getConnection();

		try {
			ps = connection.prepareStatement(selectResourcesByTitle);
			ps.setString(1, "%" + title + "%");
			result = ps.executeQuery();
			myResources = makeResource(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return myResources;
	}

	@Override
	public List<Resource> getResourcesById(Integer resourceId) {
		List<Resource> myResources = new ArrayList<Resource>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection connection = MariaDbUtil.getConnection();

		try {
			ps = connection.prepareStatement(selectResourcesById);
			ps.setInt(1, resourceId);
			result = ps.executeQuery();
			myResources = makeResource(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return myResources;
	}

	@Override
	public Resource createResource(Resource resource) {
		int updateRowCount = 0;
		PreparedStatement ps = null;

		Connection connection = MariaDbUtil.getConnection();
		try {
			ps = connection.prepareStatement(createResource);
			ps.setString(1, resource.getTitle());
			ps.setInt(2, resource.getPrice());
			ps.setString(3, resource.getBreedSize().toString());
			updateRowCount = ps.executeUpdate();
			System.out.println("Rows inserted: " + updateRowCount);
			int resourceId = getNewResourceId(connection);
			resource.setId(resourceId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resource;
	}

	@Override
	public Resource updateResource(Resource updatedResource) {
		List<Resource> resources = this.getResourcesById(updatedResource.getId());

		if (resources.size() > 0) {
			int updateRowCount = 0;
			PreparedStatement ps = null;

			Connection connection = MariaDbUtil.getConnection();
			try {
				ps = connection.prepareStatement(updateResourcesById);
				ps.setString(1, updatedResource.getTitle());
				ps.setInt(2, updatedResource.getPrice());
				ps.setString(3, updatedResource.getBreedSize().toString());
				ps.setInt(4, updatedResource.getId());
				
				updateRowCount = ps.executeUpdate();
				System.out.println("Rows updated: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			

		}
		return updatedResource;
	}

	@Override
	public Resource deleteResource(Integer resourceId) {
		List<Resource> resources = this.getResourcesById(resourceId);
		Resource resourceToDelete = null;

		if (resources.size() > 0) {
			resourceToDelete = resources.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;

			Connection connection = MariaDbUtil.getConnection();
			try {
				ps = connection.prepareStatement(deleteResourceById);
				ps.setInt(1, resourceId);
				updateRowCount = ps.executeUpdate();
				System.out.println("Rows deleted: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					connection.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return resourceToDelete;
		
	}
	

	
}
