package com.proyecto_integrador.casa_textil.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto_integrador.casa_textil.entities.Usuario;
import com.proyecto_integrador.casa_textil.service.UserService;
import com.proyecto_integrador.casa_textil.utils.LoginRequest;
import com.proyecto_integrador.casa_textil.utils.UsuarioException;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, 
		RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<String> postUsuario(@RequestBody Usuario usuario) throws SQLIntegrityConstraintViolationException {
		try {
			if(usuario.getPassword().length()>=8) {
				userService.postUsuario(usuario);
				return ResponseEntity.ok().body("Usuario creado exitosamente");
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contraseña de longitud incorrecta");
			}
		}catch(SQLIntegrityConstraintViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
		}
	}
	
	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<String> loginUsuario(@RequestBody LoginRequest loginrequest) {
		try {
			Usuario usuario = userService.validateEmail(loginrequest.getEmail());
			if (usuario.getPassword().equals(loginrequest.getPassword())) {
				Integer userId = usuario.getId();
				return ResponseEntity.ok().body(userId.toString());
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
			}
		} catch (UsuarioException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
		}

	}
	
	@GetMapping("/login/{id}")
	public String getNombreUsuario(@PathVariable Integer id) {
		return userService.readLoggedUser(id).getName();
	}
	
	// Método PUT para modificar un producto
	@PutMapping(path = "{userId}") // path para agregarle el id al endpoint
	public Usuario updateUsuario(@PathVariable("userId") Integer id, @RequestParam(required = false) String name,
			@RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String email,
			@RequestParam(required = false) String address, @RequestParam(required = false) Integer discount,
			@RequestParam(required = false) Boolean newCostumer, @RequestParam(required = false) String password) {

		return userService.actualizarUsuario(id, name, phoneNumber, email, address, discount, newCostumer, password);

	}// updateProducto

	@DeleteMapping(path = "/{userId}")
	public Usuario deleteUsuario(@PathVariable("userId") Integer id) {
		return userService.deleteUsuario(id);
	}

	@GetMapping
	public List<Usuario> getTodosLosUsuarios() {
		return userService.leerTodosLosUsuarios();

	}

}
