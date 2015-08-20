package parc.mlj.dao;

import parc.mlj.beans.TypeTicket;
import parc.mlj.dao.config.DAOException;

public interface TypeTicketDAO {

	TypeTicket trouver( long id ) throws DAOException;
	
}
