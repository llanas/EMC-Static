package parc.mlj.dao;

import java.util.List;

import parc.mlj.beans.Location;
import parc.mlj.dao.config.DAOException;

public interface LocationDAO {

	Location trouver( long id ) throws DAOException;

	List<Location> lister();
	
}
