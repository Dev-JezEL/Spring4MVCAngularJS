package com.proyecto.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.proyecto.springmvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= obtenerUsersDefault();
	}

	public List<User> findAllUsers() {
		return users;
	}
	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getNombre().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getNombre())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> obtenerUsersDefault(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"Gerardo", "CDMX", "MIGUEL H"));
		users.add(new User(counter.incrementAndGet(),"Maria", "CDMX", "AZCAPO"));
		users.add(new User(counter.incrementAndGet(),"Rodrigo", "EDOMX", "TOLUCA"));
		users.add(new User(counter.incrementAndGet(),"Aarón", "EDOMX", "NEZA"));
		users.add(new User(counter.incrementAndGet(),"Maria", "CDMX", "AZCAPO"));
		users.add(new User(counter.incrementAndGet(),"maria", "CDMX", "AZCAPO"));
		users.add(new User(counter.incrementAndGet(),"Gerardo", "CDMX", "MIGUEL H"));
		users.add(new User(counter.incrementAndGet(),"Maria", "CDMX", "AZCAPO"));
		users.add(new User(counter.incrementAndGet(),"Gabriel", "EDOMX", "TOLUCA"));
		users.add(new User(counter.incrementAndGet(),"Maria", "CDMX", "AZCAPO"));
		return users;
	}

}
