package opv.proyecto.opvproy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import opv.proyecto.opvproy.domain.*;
import opv.proyecto.opvproy.services.*;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner initData(JugadorService jugadorService, ClubService clubService, CampoService campoService,
			PartidoService partidoService, LigaService ligaService, UsuarioService usuarioService) {
		return args -> {
			Liga primeraLiga = ligaService.añadir(new Liga(135, "Galicia", "A Coruña"));
			Liga segundaLiga = ligaService.añadir(new Liga(024, "Cataluña", "Barcelona"));

			Club club1 = clubService.añadir(new Club(1284, "Sp Meicende", primeraLiga));
			Club club2 = clubService.añadir(new Club(1029, "Sada CF", segundaLiga));

			jugadorService.añadir(new Jugador("11122233A", "Jacobo Rodriguez", "Zapateira", club1));
			jugadorService.añadir(new Jugador("44455566B", "Gonzalo Aguado", "Icaria", club2));
			
			campoService.añadir(new Campo("Municipal de Meicende", "Arteixo", club1));
			campoService.añadir(new Campo("As Mariñas", "Sada", club2));
			
			partidoService.añadir(new Partido(321, "2-0", club1, club2));
			partidoService.añadir(new Partido(654, "0-1", club2, club1));

			usuarioService.añadir(new Usuario("79346384H", "Oscar", "1234", "oscar@gmail.com", Rol.ADMIN));
			usuarioService.añadir(new Usuario("79346385L", "Aaron", "1234", "aaron@gmail.com", Rol.USER));
		};
	}
}