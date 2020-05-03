package com.manish.springbootsecurityjpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manish.springbootsecurityjpa.model.MyUserDetails;
import com.manish.springbootsecurityjpa.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//this is for validating by hardcoded data
		//return new MyUserDetails(s);
		Optional<User> user = userRepository.findByUserName(userName);
		user.orElseThrow(()->new UsernameNotFoundException("Not Found : "+userName));
		return new MyUserDetails(user.get()) ;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}
