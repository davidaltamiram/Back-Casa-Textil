package com.proyecto_integrador.casa_textil.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto_integrador.casa_textil.entities.Cart;
import com.proyecto_integrador.casa_textil.entities.Usuario;
import com.proyecto_integrador.casa_textil.repositories.UserRepository;
import com.proyecto_integrador.casa_textil.utils.UsuarioException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	private final UserRepository userRepository;
	private Cart cart;
	private EntityManager entityManager;

	@Autowired
	public UserService(UserRepository userRepository, Cart cart, EntityManager entityManager) {
		this.userRepository = userRepository;
		this.cart = cart;
		this.entityManager = entityManager;
	}

	@Transactional
	public Usuario postUsuario(Usuario usuario) throws SQLIntegrityConstraintViolationException {
		try {
			validateEmail(usuario.getEmail());
			throw new SQLIntegrityConstraintViolationException();
		}catch(UsuarioException e) {
			usuario.setId(null);
			Cart mergedCart = entityManager.merge(cart);
			usuario.setCartIdCart(mergedCart);
			return userRepository.save(usuario);
		}
	}

	public Usuario validateEmail(String email) {
		return userRepository.getByEmail(email).orElseThrow(() -> new UsuarioException("Usuario no encontrado"));
	}

	public Usuario readLoggedUser(Integer id) {
		return userRepository.findById(id).get();
	}

//Método para modificar un producto
	public Usuario actualizarUsuario(Integer id, String name, String phoneNumber, String email, String address,
			Integer discount, Boolean newCostumer, String password) {
		Usuario userTemporal = null; // declaro producto temporal ccon valor nulo, para que sirva conmo mi calca
		// Si el producto existe, lo modifico
		if (userRepository.existsById(id)) { // true
			// hago la modificacion de los parámetros
			userTemporal = userRepository.findById(id).get();
			if (name != null)
				userTemporal.setName(name);
			if (phoneNumber != null)
				userTemporal.setPhoneNumber(phoneNumber);
			if (email != null)
				userTemporal.setEmail(email);
			if (address != null)
				userTemporal.setAddress(address);
			if (discount != null)
				userTemporal.setDiscount(discount);
			if (newCostumer != null)
				userTemporal.setNewCostumer(newCostumer);
			if (password != null)
				userTemporal.setPassword(password);
			userRepository.save(userTemporal);
		} else {
			// Si el producto no existe, no lo puedo modificar y mando un mensaje que dice
			// "El producto no existe".
			System.out.println("El usuario que quieres actualizar, no existe");
		}
		return userTemporal; // nulo o el producto modificado
	}// actualizarProducto

	public List<Usuario> leerTodosLosUsuarios() {
		return userRepository.findAll();
	}

	public Usuario deleteUsuario(Integer id) {
		Usuario userTemporal = null;
		if (userRepository.existsById(id)) {
			userTemporal = userRepository.findById(id).get();
			userRepository.deleteById(id);
		}
		return userTemporal;
	}

}
