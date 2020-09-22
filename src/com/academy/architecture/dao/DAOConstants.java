package com.academy.architecture.dao;


public interface DAOConstants {
	String SELECT_UTENTE = "Select id, nome, cognome, username from utente";
	String UPDATE_UTENTE = "Update utente set nome = ?, cognome = ?, username = ? where id = ?";
	String DELETE_UTENTE = "Delete from utente where username = ?";
	String SELECT_BYPK = "Select * from utente where username = ?";
}