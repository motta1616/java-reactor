package co.com.sofka.operador.matematicos;

import co.com.sofka.ReactorApplication;
import co.com.sofka.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Matematicos {
    private static final Logger Log = LoggerFactory.getLogger(ReactorApplication.class);

    public void average() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        // averagingInt(): Permite optener el promedio de un conjunto de datos
        // collect(): permite recolectar la informacion de una lista
        Flux.fromIterable(personas)
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(x -> Log.info(x.toString()));

    }

    public void count() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        // count(): Cuenta el número de valores en este Flux. El recuento se emitirá cuando se observe onComplete. es
        // decir, Permite contar los elemento que exite en un flujo determinado
        Flux.fromIterable(personas)
                .count()
                .subscribe(x -> Log.info("Cantidad: " + x));
    }

    public void min() {

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        //minBy(): Permite encontrar el valor minimo a traves de un flujo de datos
        // comparing(): un comparator me devuelve un optional
        Flux.fromIterable(personas)
                .collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
                .subscribe(x -> Log.info(x.get().toString()));

    }

    public void sum() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        // summingInt(): Me permite sumar ciertos valores
        Flux.fromIterable(personas)
                .collect(Collectors.summingInt(Persona::getEdad))
                .subscribe(x -> Log.info("suma: " + x));
    }

    public void sumMarizing() {

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        // es el resumen de unas operaiones aritmeticas como promedio mayor la suma
        Flux.fromIterable(personas)
                .collect(Collectors.summarizingInt(Persona::getEdad))
                .subscribe(x -> Log.info("Resumen: " + x));
    }

}
