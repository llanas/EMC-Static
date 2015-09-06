package parc.mlj.dao;

import java.util.List;

import parc.mlj.beans.Address;
import parc.mlj.dao.config.DAOException;

public interface AddressDAO {

	Address trouver( long id ) throws DAOException;

	List<Address> lister();
	
}
