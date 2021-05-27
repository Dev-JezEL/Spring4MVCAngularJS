package com.proyecto.springmvc.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.proyecto.springmvc.model.RequestListaPersonas;
import com.proyecto.springmvc.model.PersonaFisica;
import com.proyecto.springmvc.model.ResponseMasRepetido;
import com.proyecto.springmvc.model.User;
import com.proyecto.springmvc.service.UserService;

@RestController
public class HelloWorldRestController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/user/masRepetido2", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String masRepetido2(@RequestBody String json, HttpServletRequest request) throws Exception {
		System.out.println("Lista de personas");
		Gson gson = new Gson();
		System.out.println("json=> " + json);

//		RequestListaPersonas per = new RequestListaPersonas();
//		List<PersonaFisica> lista = new ArrayList<>();
//		PersonaFisica pf = new PersonaFisica();
//		pf.setId(1);
//		pf.setNombre("jesus");
//		pf.setEntidad("tol");
//		pf.setMunicipio("vgro");
//		lista.add(pf);
//		
//		per.setLista(lista);
//		
//		String jsonRequest = gson.toJson(per, RequestListaPersonas.class);
//		System.out.println(jsonRequest);

		// mapeo de json a objeto para obtenmer lista de personas fisicas
		RequestListaPersonas personas = gson.fromJson(json, RequestListaPersonas.class);
//		convertimos la lista en un arreglo
		PersonaFisica[] itemsArray = new PersonaFisica[personas.getListaPersonas().size()];
		itemsArray = personas.getListaPersonas().toArray(itemsArray);
		String response = obtenerModa(itemsArray);
		return response;
	}

	@RequestMapping(value = "/user/masRepetido", method = RequestMethod.GET,
			// consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String masRepetido() throws Exception {
		System.out.println("Lista de personas");
//		System.out.println(json);
		Gson gson = new Gson();

		// obtenemos nuestra lista de todos los usuarios almacenados
		List<User> users = userService.findAllUsers();

//		convertimos la lista en un arreglo
		User[] itemsArray = new User[users.size()];
		itemsArray = users.toArray(itemsArray);

		for (User s : itemsArray)
			System.out.println(s);

		System.out.printf("El arreglo es: %s\n", Arrays.toString(itemsArray));
		HashMap<String, Integer> mapa = new HashMap<>();

		for (int x = 0; x < itemsArray.length; x++) {
			User repe = itemsArray[x];

			if (mapa.containsKey(repe.getNombre().toLowerCase())) {
				mapa.put(repe.getNombre().toLowerCase(), mapa.get(repe.getNombre().toLowerCase()) + 1);
			} else {
				mapa.put(repe.getNombre().toLowerCase(), 1);
			}

		}

		String moda = "";
		int mayor = 0;

		for (HashMap.Entry<String, Integer> entry : mapa.entrySet()) {
			if (entry.getValue() > mayor) {
				mayor = entry.getValue();
				moda = entry.getKey();
			}
		}
		System.out.printf("La moda es %s porque se repite %d veces", moda, mayor);
		ResponseMasRepetido resp = new ResponseMasRepetido();
		resp.setNombre(moda);
		resp.setCantidad(mayor);

		String response = gson.toJson(resp, ResponseMasRepetido.class);
//		String response = gson.toJson("{ 'nombre' : '"+moda+"' }");

		return response;
	}

	// -------------------Obtener Todos
	// Uuarios--------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		System.out.println("Obtniendo Todos los usuarios");
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Obtener unico
	// Usuario--------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		System.out.println("Obteniendo usuario con id " + id);
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("Usuario con " + id + " no encontrdo");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create un
	// Usuario--------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando usuario " + user.getNombre());

//		if (userService.isUserExist(user)) {
//			System.out.println("El usuario " + user.getNombre() + " ya existe");
//			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Actualizar un Usuario
	// --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		System.out.println("Actualizando usuario " + id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			System.out.println("Usuario con " + id + " no encontrado");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setNombre(user.getNombre());
		currentUser.setEntidad(user.getEntidad());
		currentUser.setMunicipio(user.getMunicipio());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Eliminar un Usuario
	// --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		System.out.println("Eliminando usuario con id " + id);

		User user = userService.findById(id);
		if (user == null) {
			System.out.println("No se puede eliminar usuario con " + id + " no encontrado");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Eliminar Todos los usuarios
	// --------------------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		System.out.println("Eliminar todos los usuarios");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	private String obtenerModa(PersonaFisica[] itemsArray) {
		Gson gson = new Gson();
		HashMap<String, Integer> mapa = new HashMap<>();
		for (int x = 0; x < itemsArray.length; x++) {
			PersonaFisica repe = itemsArray[x];

			if (mapa.containsKey(repe.getNombre().toLowerCase())) {
				mapa.put(repe.getNombre().toLowerCase(), mapa.get(repe.getNombre().toLowerCase()) + 1);
			} else {
				mapa.put(repe.getNombre().toLowerCase(), 1);
			}

		}

		String moda = "";
		int mayor = 0;

		for (HashMap.Entry<String, Integer> entry : mapa.entrySet()) {
			if (entry.getValue() > mayor) {
				mayor = entry.getValue();
				moda = entry.getKey();
			}
		}
		System.out.printf("La moda es %s porque se repite %d veces", moda, mayor);
		ResponseMasRepetido resp = new ResponseMasRepetido();
		resp.setNombre(moda);
		resp.setCantidad(mayor);

		String response = gson.toJson(resp, ResponseMasRepetido.class);
		return response;
	}

}