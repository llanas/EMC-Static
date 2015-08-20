package parc.mlj.dao;

import parc.mlj.beans.Address;
import parc.mlj.dao.config.DAOException;

public interface AddressDAO {

	Address trouver( long id ) throws DAOException;
	
}
