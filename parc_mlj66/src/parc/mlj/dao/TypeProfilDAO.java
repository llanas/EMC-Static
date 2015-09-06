package parc.mlj.dao;

import parc.mlj.beans.TypeProfil;
import parc.mlj.dao.config.DAOException;

public interface TypeProfilDAO {

	TypeProfil trouver( long id ) throws DAOException;
	
}
