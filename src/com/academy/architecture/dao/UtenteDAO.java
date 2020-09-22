package com.academy.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import com.academy.businesscomponent.model.Utente;

@Named
@RequestScoped
public class UtenteDAO implements DAOConstants {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public UtenteDAO() throws SQLException, ClassNotFoundException, NamingException {
		conn = DBAccess.getConnection();
		stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		rs = stmt.executeQuery(SELECT_UTENTE);
	}

	public String create(Utente utente) throws SQLException {
		if(utente != null) {
			rs.moveToInsertRow();
			rs.updateString(2, utente.getNome());
			rs.updateString(3, utente.getCognome());
			rs.updateString(4, utente.getUsername());
			rs.insertRow();
		} 
		return "report?faces-redirect=true";
	}

	public ArrayList<Utente> getUtenti() throws SQLException {
		ArrayList<Utente> lista = new ArrayList<Utente>();
		rs.beforeFirst();
		while (rs.next()) {
			Utente utente = new Utente();
			utente.setId(rs.getLong(1));
			utente.setNome(rs.getString(2));
			utente.setCognome(rs.getString(3));
			utente.setUsername(rs.getString(4));
			lista.add(utente);
		}
		return lista;
	}

	public void update(Utente utente) throws SQLException {
		if (utente != null) {
			PreparedStatement stmt = conn.prepareStatement(UPDATE_UTENTE);
			stmt.setString(1, utente.getNome());
			stmt.setString(2, utente.getCognome());
			stmt.setString(3, utente.getUsername());
			stmt.setLong(4, utente.getId());
			stmt.execute();
		}
	}

	public void delete(String username) throws SQLException {
		if (username != null) {
			PreparedStatement stmt = conn.prepareStatement(DELETE_UTENTE);
			stmt.setString(1, username);
			stmt.execute();
		}
	}

	public Utente findByPK(String username) throws SQLException {
		if (username != null) {
			PreparedStatement stmt = conn.prepareStatement(SELECT_BYPK);
			stmt.setString(1, username);
			Utente utente = new Utente();
			ResultSet rs = stmt.executeQuery();
			rs.next();
			utente.setNome(rs.getString(2));
			utente.setCognome(rs.getString(3));
			utente.setUsername(rs.getString(4));
			return utente;
		}
		return null;
	}
}
