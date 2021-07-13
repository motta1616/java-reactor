package co.com.sofka.operador.filtrado;

import co.com.sofka.ReactorApplication;
import co.com.sofka.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Filtrado {
    private static final Logger Log = LoggerFactory.getLogger(ReactorApplication.class);

    public void filter() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(2, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        // filter: Evalúe cada valor de fuente contra el predicado dado. Si la prueba de predicado tiene éxito, se emite
        // el valor que cumpla con el predicado. Si la prueba de predicado falla, el valor se ignora y se realiza una solicitud de 1 en sentido ascendente.
        Flux.fromIterable(person)
                .filter(p -> p.getEdad() > 32)
                .subscribe(id -> Log.info("personas: " + id));
    }

    // distinct: Indica los elementos que no se dupliquen, para identificarlos debemos colocar en el pojo el equal y el
    // hascode en este ejercicio solo se tomo id cuando son objetos
    public void distinct() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(1, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        Flux.fromIterable(person)
                .distinct()
                .subscribe(id -> Log.info("personas: " + id));
    }

    public void take() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(2, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        //take(): Retransmitir valores de este flujo hasta que transcurra la duración especificada. Si la duración es
        // cero, el Flujo resultante se completa tan pronto como este Flujo señale su primer valor (que no se transmite,
        // sin embargo). Tambien se le puede especificar la cantidad de elementos a trasmitir.
        Flux.fromIterable(person)
                .take(2)
                .subscribe(id -> Log.info("personas: " + id));
    }

    public void takeLast() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(2, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        // takeLast(): Emite los últimos N valores emitidos por este Flujo antes de su finalización.
        Flux.fromIterable(person)
                .takeLast(1)
                .subscribe(id -> Log.info("personas: " + id));
    }

    public void skip() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(2, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        // skip(): Omitir elementos de este Flux emitidos dentro de la duración inicial especificada. Es decir no
        // emitira el primer elemento, los demas si
        Flux.fromIterable(person)
                .skip(1)
                .subscribe(id -> Log.info("personas: " + id));
    }

    public void skipLast() {
        List<Persona> person = new ArrayList<>();
        person.add(new Persona(1, "Leo", 31));
        person.add(new Persona(2, "Motta", 32));
        person.add(new Persona(3, "vargas", 33));

        // skipLast(): Omita un número específico de elementos al final de esta Flux secuencia. Es decir no tomara el
        // ultimo y los demas si
        Flux.fromIterable(person)
                .skipLast(1)
                .subscribe(id -> Log.info("personas: " + id));
    }
}
