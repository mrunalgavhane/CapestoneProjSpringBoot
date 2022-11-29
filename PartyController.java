package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Booking;
import com.example.demo.model.Payment;
import com.example.demo.model.User;
import com.example.demo.repository.BookingRepo;
import com.example.demo.repository.PaymentRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.SequenceGeneratorService;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin("http://192.168.0.106:8080")
public class PartyController {
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private BookingRepo bookingrepo;
	
	@Autowired
	private SequenceGeneratorService service;
	
	@Autowired
	private PaymentRepo paymentrepo;
	
	
    @PostMapping("/user")
    public String newuser(@RequestBody User newUser) {
        userrepo.save(newUser);
        return "User Registered";
    }
          
    @GetMapping("/users/{mail}")
    public User users(@PathVariable String mail) {
    	return userservice.users(mail);
    }
    
    @PostMapping("/saveBook")
	public Booking save(@RequestBody Booking book){
		//generate sequence
		book.setId(service.getSequenceNumber(Booking.SEQUENCE_NAME));
		return bookingrepo.save(book);
	}
    
    @PostMapping("/savePay")
	public Payment save(@RequestBody Payment pay){
		//generate sequence
		pay.setId(service.getSequenceNumber(Payment.SEQUENCE_NAME));
		return paymentrepo.save(pay);
	}
    
    
    
//    @PutMapping("/updateUser/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User newuser) {
//        User updateUser = userrepo.findById(id).get();
////        updateUser.setId(newuser.getId());
//        updateUser.setName(newuser.getName());
//        updateUser.setCity(newuser.getCity());
//        updateUser.setPhone(newuser.getPhone());
//        updateUser.setMail(newuser.getMail());
//        userrepo.save(updateUser);
//        return ResponseEntity.ok(updateUser);
//        }
    
    
//    @DeleteMapping("/delete/{id}")
//    public String deleteDepartmentById(@PathVariable int id)
//    {
//        userservice.deleteDepartmentById(id);
//        return "Deleted Successfully";
//    }

}