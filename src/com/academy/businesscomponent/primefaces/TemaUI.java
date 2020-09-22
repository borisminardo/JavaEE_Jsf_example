package com.academy.businesscomponent.primefaces;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
//serviva per per le jsf
@ApplicationScoped
@Named
public class TemaUI {
	public String getTema() {
		return "nova-light"; 
	}
}
