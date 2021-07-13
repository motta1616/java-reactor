package co.com.sofka.operador.creacion;

import co.com.sofka.ReactorApplication;
import co.com.sofka.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creacion {
    private static final Logger Log = LoggerFactory.getLogger(ReactorApplication.class);

    // permite crear un flujo
    public void justFrom() {
        Mono.just(new Persona(1, "Leonardo", 31));
        //Flux.fromIterable(personas)
    }

    // empty(): es un metodo que expresa flujos vacios
    public void empty() {
        Mono.empty();
        Flux.empty();
    }

    // range(): permite crear un flujo de datos a partir de un rango de numeros
    public void range() {
        Flux.range(0, 3) // EN UN RANGO DE MAYOR O IGUAL A 0 HASTA MENOR QUE 3
        .doOnNext(i -> Log.info("i: " + i))
                .subscribe();
    }

    public void repeat() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(2, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        Flux.fromIterable(person)
                .repeat(2)
                .subscribe(lista -> Log.info("Persona: " + lista));

        Mono.just(new Persona(1, "Leo", 40))
                .repeat(2)
                .subscribe(lista -> Log.info("Persona: " + lista));

    }
}
