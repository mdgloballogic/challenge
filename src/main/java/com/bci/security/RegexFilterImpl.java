package com.bci.security;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class RegexFilterImpl implements RegexFilter {

	@Override
	public boolean regexPassword(String password) {
		// TODO Auto-generated method stub
        if(Pattern.matches(PASSWORD_REGEX, password)){
            return true;
        }
		return false;
	}

	@Override
	public boolean regexEmail(String email) {
		// TODO Auto-generated method stub
        if(Pattern.matches(EMAIL_REGEX, email)){
            return true;
        }
		return false;
	}

}
