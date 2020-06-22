package com.lgeratech.demo.controllers;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.lgeratech.demo.models.User;
@RestController
public class UserController {
	ArrayList<User> users = new ArrayList<User>();
	@PostMapping(value = "/createUser")
	public ResponseEntity<String> createUser(@RequestBody User user){
		int userId = users.size() > 0 ? users.get(users.size() - 1).getId() + 1 : 1;
		user.setId(userId);
		users.add(user);
		return new ResponseEntity<String>("User has been added succesfully", HttpStatus.OK);
	}
	@GetMapping(value = "/retrieveUsers")
	public ResponseEntity<ArrayList<User>> retrieveUserList(){
		return new ResponseEntity<ArrayList<User>>(users, HttpStatus.OK);
	}
	@PutMapping(value = "/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user){
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getId() == user.getId()) {
				users.set(i, user);
				break;
			}
		}
		return new ResponseEntity<String>("User has been updated succesfully", HttpStatus.OK);
	}
	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id){
		users.removeIf(user -> user.getId() == id);
		return new ResponseEntity<String>("User has been deleted succesfully", HttpStatus.OK);
	}
}