package com.dsm.kdr_backend.global.jwt.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailsService implements UserDetailsService {

	@Override
	public Details loadUserByUsername(String username) throws UsernameNotFoundException {
		return new Details();
	}

}
