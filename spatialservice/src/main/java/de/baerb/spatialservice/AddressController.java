package de.baerb;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

	@Autowired
	private AddressRepository repo;
	

    @GetMapping("/address")
	public List<Address> allAddresses() { 
		return repo.findAll();
    }
    		

}



