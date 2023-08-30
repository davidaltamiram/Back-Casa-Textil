package com.proyecto_integrador.casa_textil.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, unique = true)
	private Integer id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(name="phone_number", unique = true)
	private String phoneNumber;
	
	@Column(nullable=false, unique = true)
	private String email;
	
	private String address;
	
	private Integer discount;
	
	@Column(name="new_costumer", nullable = false)
	private Boolean newCostumer;
	
	@Column(nullable=false)
	private String password;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "cart_idcart")
	private Cart cartIdCart;

	//Constructores
	public Usuario(String name, String phoneNumber, String email, String address, Integer discount, Boolean newCostumer,
			String password) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.discount = discount;
		this.newCostumer = newCostumer;
		this.password = password;
	}
	
	public Usuario() {
	}

	//Getters y setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Boolean getNewCostumer() {
		return newCostumer;
	}

	public void setNewCostumer(Boolean newCostumer) {
		this.newCostumer = newCostumer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Integer id) {
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}

	public Cart getCartIdCart() {
		return cartIdCart;
	}

	public void setCartIdCart(Cart cartIdCart) {
		this.cartIdCart = cartIdCart;
	}
	
}
