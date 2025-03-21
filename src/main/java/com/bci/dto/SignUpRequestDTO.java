package com.bci.dto;

import com.bci.model.Phone;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class SignUpRequestDTO {

    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Formato de correo inválido")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=(.*\\d){2})[a-zA-Z\\d]{8,12}$", message = "Formato de contraseña inválido")
    private String password;

    private List<Phone> phones;
}
