package parc.mlj.dao;

import static parc.mlj.dao.config.DAOUtilitaire.fermeturesSilencieuses;
import static parc.mlj.dao.config.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import parc.mlj.beans.TypeProfil;
import parc.mlj.dao.config.DAOException;



public class TypeProfilDaoImpl implements TypeProfilDAO {
	
	private static final String SQL_SELECT_FROM_ID	= "SELECT * FROM type_profil WHERE id_type_profil = ?";

	private DAOFactory daoFactory;
	
	TypeProfilDaoImpl( DAOFactory daoFactory ){
		this.daoFactory = daoFactory;
	}
	
	public TypeProfil trouver( long id ) throws DAOException {
		return trouver( SQL_SELECT_FROM_ID, id );
	}
	
	private TypeProfil trouver( String sql, Object... objects){
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		TypeProfil typeProfil = null;
		
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, sql, false, objects);
			resultSet = preparedStatement.executeQuery();
			if( resultSet.next() ){
				typeProfil = map(resultSet);
			}
		} catch (SQLException e){
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion);
		}
		return typeProfil;		
	}

	private TypeProfil map(ResultSet resultSet) throws SQLException {
		
		TypeProfil typeProfil = new TypeProfil();
		typeProfil.setId( resultSet.getLong( "id_type_profil" ));
		typeProfil.setName( resultSet.getString( "name_type_profil" ));
		typeProfil.setRight( resultSet.getInt( "right_type_profil" ));
		return typeProfil;
	}
	
	
}
