package co.com.sofka.operador.controlDeErroes;

import co.com.sofka.ReactorApplication;
import co.com.sofka.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ControlDeErrores {
    private static final Logger Log = LoggerFactory.getLogger(ReactorApplication.class);

    public void retry() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        // concatWith(): Permite simular un error
        // Flux.error() crea el error
        // RuntimeException(): es un tipo de error
        // retry(): el Flux vuelve a suscribir a esta secuencia si la señala da algún error, de forma definida.
        // Es decir, ejecuta nuevamente las veces que desees. Para este ejemplo corrio los flujo y encontro error, por
        // lo que corre nuevamente los flujos hasta la cantidad de veces que determinemos
        Flux.fromIterable(personas)
            .concatWith(Flux.error(new RuntimeException("ESTO ES UN ERROR")))
                .retry(2)
                .doOnNext(x -> Log.info(x.toString()))
                .subscribe();
    }

    public void errorReturn() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("ESTO ES UN ERROR")))
                // Simplemente emita un valor de reserva capturado cuando se observe un error del tipo especificado en
                // este Flux. Es decir, permite resolver de una forma predeterminada, que es un bloque por defecto cuando
                // se presenta un error
                .onErrorReturn(new Persona(0, "xxx", 99))
                .subscribe(x -> Log.info(x.toString()));
    }

    public void errorResume() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("ESTO ES UN ERROR")))
                // onErrorResume(): Suscríbase a un editor de respaldo devuelto cuando se produzca un error, utilizando
                // una función para elegir el respaldo según el error.requiere de una funcion para manejar los temas del exeption
                .onErrorResume(e -> Mono.just(new Persona(0, "xxx", 99)))
                .subscribe(x -> Log.info(x.toString()));
    }

    public void errorMap() {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Leo", 31));
        personas.add(new Persona(2, "Motta", 32));
        personas.add(new Persona(3, "vargas", 33));

        // onErrorMap: Transforma cualquier error emitido por este Fluxaplicándole de forma síncrona una función, es
        // decir, me permite capturar el mensaje que se trasmite en el error
        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("ESTO ES UN ERROR")))
                .onErrorMap(e -> new InterruptedException(e.getMessage()))
                .subscribe(x -> Log.info(x.toString()));
    }
}
