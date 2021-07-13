package co.com.sofka.operador.condicionales;

import co.com.sofka.ReactorApplication;
import co.com.sofka.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

// PERMITR CONDICIONAR EVENTOS Y MOSTRARLO
public class Condicionales {
    private static final Logger Log = LoggerFactory.getLogger(ReactorApplication.class);

    public void defaultIfEmpty() {
        //  defaultIfEmpty(): Proporcione un valor único predeterminado si esta secuencia se completa sin ningún dato.
        //  Es decir, permite enviar una respuesta por defecto en ves de una respuesta vacia
        Mono.empty()
                .defaultIfEmpty(new Persona(0, "xxx", 99))
                .subscribe(x -> Log.info(x.toString()));
    }

    public void takeUntil() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        // takeUntil(): Retransmitir valores desde este Flux hasta los Predicate partidos indicados. Esto incluye los
        // datos coincidentes Es decir, emite flujos haste que aparesca un valor que cumpla la exprecion pero enviara
        // solo uno mas despues de la condicion
        Flux.fromIterable(personas)
                .takeUntil( p -> p.getEdad() > 30)
                .subscribe(x -> Log.info(x.toString()));
    }

    public void timeOut() throws InterruptedException {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        //delayElements(): permite agregar un pequeño retrazo
        // timeout(): Propague una TimeoutException tan pronto como no se emita ningún elemento dentro de la Duración
        // dada desde la emisión anterior (o la suscripción para el primer elemento).Es decir, lanzara una execcion si
        // la lectura se demora
        Flux.fromIterable(personas)
                .delayElements(Duration.ofSeconds(3))
                .timeout(Duration.ofSeconds(2))
                .subscribe(x -> Log.info(x.toString()));

        Thread.sleep(10000);
    }
}
