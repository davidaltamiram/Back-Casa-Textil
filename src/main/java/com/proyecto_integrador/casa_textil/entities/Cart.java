package com.proyecto_integrador.casa_textil.entities;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cart")
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcart",nullable=false, unique = true)
	private Integer id;

	public Cart() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}
	
	
}
