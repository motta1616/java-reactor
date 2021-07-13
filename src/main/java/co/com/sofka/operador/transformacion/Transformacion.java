package co.com.sofka.operador.transformacion;

import co.com.sofka.ReactorApplication;
import co.com.sofka.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformacion {
    private static final Logger Log = LoggerFactory.getLogger(ReactorApplication.class);

    // map(): Transforme los elementos emitidos por este Flux aplicando una función síncrona a cada elemento.
    public void map() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(2, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        Flux.fromIterable(person)
                .map( p -> {
                    p.setEdad(p.getEdad() + 10);
                    return p;
                })
                .subscribe(lista -> Log.info("Persona: " + lista));

        Flux<Integer> fx = Flux.range(0,10);
        Flux<Integer> fx2 = fx.map(x -> x + 10);
        fx2.subscribe(x -> Log.info("numero: " + x));
    }

    // flatmap(): te pide que tengas como retorno otro flujo
    public void flatMap() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(2, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        Flux.fromIterable(person)
                .flatMap(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return Mono.just(p);
                })
                .subscribe(x -> Log.info("numero: " + x));
    }

    // groupBy(): Divide this sequence into dynamically created Flux (or groups) for each unique key, as produced by the provided
    // keyMapper Function. Note that groupBy works best with a low cardinality of groups, so chose your keyMapper
    // function accordingly. es decir agrupar por medio de una llava, es este caso id en diferentes flux
    public void groupBy() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(1, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        Flux.fromIterable(person)
                .groupBy(Persona::getId)
                .flatMap(idFlux -> idFlux.collectList()) // permite recolectar en una lista
                .subscribe(id -> Log.info("personas: " + id));
    }
}
