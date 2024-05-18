package opv.proyecto.opvproy.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class Servicio {

    public int getAno() {
        return LocalDate.now().getYear();
    }
}