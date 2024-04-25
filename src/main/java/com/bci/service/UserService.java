package com.bci.service;

import com.bci.dto.UserDTO;
import com.bci.security.GenericException;

public interface UserService {

	UserDTO signup(UserDTO userDto) throws GenericException;
}
