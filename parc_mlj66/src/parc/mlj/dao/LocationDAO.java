package parc.mlj.dao;

import parc.mlj.beans.Location;
import parc.mlj.dao.config.DAOException;

public interface LocationDAO {

	Location trouver( long id ) throws DAOException;
	
}
