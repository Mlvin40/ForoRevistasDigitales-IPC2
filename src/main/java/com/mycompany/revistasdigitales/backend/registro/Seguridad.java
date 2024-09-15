package com.mycompany.revistasdigitales.backend.registro;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Seguridad {

    /**
     * Este metodo encripta la contrasena con BCrypt y la retorna
     * @param contrasena
     * @return
     */
    public String encriptarContrasena(String contrasena) {
        return BCrypt.hashpw(contrasena, BCrypt.gensalt()); // Genera el hash con BCrypt
    }

//    public boolean verificarContrasena(String contrasena, String hash) {
//        return passwordEncoder.matches(contrasena, hash);
//    }

}
