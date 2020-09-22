package com.academy.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.academy.architecture.dao.UtenteDAO;
import com.academy.businesscomponent.model.Utente;

@Path("/service")
public class UtenteService {
	
	@GET
	@Path("/utenti")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Utente> getUtenti() throws ClassNotFoundException, SQLException, NamingException{
		UtenteDAO uDAO = new UtenteDAO();
		return uDAO.getUtenti();		
	}
	
	@GET
	@Path("/utente/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Utente getUtente(@PathParam("username") String username)
			throws ClassNotFoundException, SQLException, NamingException{
		UtenteDAO uDAO = new UtenteDAO();
		return uDAO.findByPK(username);
	}
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public void createUtente(Utente utente)
			throws ClassNotFoundException, SQLException, 
			NamingException, ParseException {
		UtenteDAO uDAO = new UtenteDAO();
		uDAO.create(utente);
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public void updateUtente(Utente utente)
			throws ClassNotFoundException, SQLException, 
			NamingException, ParseException {
		UtenteDAO uDAO = new UtenteDAO();
		uDAO.update(utente);
	}
	
	@DELETE
	@Path("/delete/{username}")
	@Transactional
	public void deleteUtente(@PathParam("username") String username)
			throws ClassNotFoundException, SQLException, NamingException{
		UtenteDAO uDAO = new UtenteDAO();
		uDAO.delete(username);
	}
}