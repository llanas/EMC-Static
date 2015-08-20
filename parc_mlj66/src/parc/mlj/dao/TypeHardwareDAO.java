package parc.mlj.dao;

import parc.mlj.beans.TypeHardware;
import parc.mlj.dao.config.DAOException;

public interface TypeHardwareDAO {

	TypeHardware trouver( long id ) throws DAOException;
	
}
