package parc.mlj.dao;

import java.util.List;

import parc.mlj.beans.Partner;
import parc.mlj.beans.User;
import parc.mlj.dao.config.DAOException;

public interface PartnerDAO {
	
	Partner trouver( long id ) throws DAOException;
	
}
