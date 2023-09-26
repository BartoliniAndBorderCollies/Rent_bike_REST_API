package com.klodnicki.bike.rest.API.Bike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Napisz aplikację w Spring Boot, która będzie pozwalać na wypożyczanie rowerów miejskich za pomocą mobilnej aplikacji
// na smartphone (Coś jak hulajnogi elektryczne bolta, na przykład).
//		W mieście mamy stacje rowerowe, które służą do odstawiania tam rowerów i wypożyczania ich stamtąd.
//		Każda stacja ma przyjazną dla użytkownika nazwę, którą łatwo zidentyfikować i rozpoznać oraz ograniczoną ilość
//		miejsc dla rowerów. Nie można zostawić przy takiej stacji roweru, jeśli wszystkie miejsca są zajęte.
//		Każdy rower ma również swoją nazwę lub numer, który go identyfikuje. Np. H0123 - tak, aby użytkownik również
//		miał łatwość rozpoznania roweru, który chce wypożyczyć z aplikacji.
//		Rowery obecnie dostępne to rowery elektryczne i tradycyjne.

@SpringBootApplication
public class RentABikeRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentABikeRestApiApplication.class, args);
	}

}
