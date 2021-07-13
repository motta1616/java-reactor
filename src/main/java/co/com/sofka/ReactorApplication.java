package co.com.sofka;

import co.com.sofka.model.Persona;
import co.com.sofka.operador.combinacion.Combinacion;
import co.com.sofka.operador.condicionales.Condicionales;
import co.com.sofka.operador.controlDeErroes.ControlDeErrores;
import co.com.sofka.operador.creacion.Creacion;
import co.com.sofka.operador.filtrado.Filtrado;
import co.com.sofka.operador.matematicos.Matematicos;
import co.com.sofka.operador.transformacion.Transformacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReactorApplication implements CommandLineRunner { // CommandLineRunner como es una aplicacion por consola para sobreescribir el metodo run

	private static final Logger Log = LoggerFactory.getLogger(ReactorApplication.class); // para revizar a modo de debug

	//FLUJO DE DATOS
	public void subscribe() {
		// just():  es un operador que crea un elemento nuevo Mono que emita el elemento
		// especificado, que se captura en el momento de la instanciación.
		Mono.just(new Persona(1, "Leo", 31))
				.subscribe(p -> Log.info("Persona: " + p)); // subscribe() : permite sucribirse para recibir un flujo
		// de datos
	}

	public void doOnNext() {
		// doOnNext(): permite hacer procesos de depuracion o mecanismo aparte mientra llegan datos. Agregue un
		// comportamiento que se desencadena cuando Mono emite datos con éxito.
		Mono.just(new Persona(1, "Leo", 31))
				.doOnNext(p -> {
					// las llaves son para implementar logica adicional
					Log.info("Persona: " + p);
				})
				.subscribe(p -> Log.info("Persona: " + p));
	}

	// Mono representa un flujo de dato de tipo asincrono de un solo valor
	public void mono() {
		Mono.just(new Persona(1, "Leonardo", 31))
				.subscribe(p -> Log.info("Persona: " + p));
	}

	// Flux representa un flujo de dato de tipo asincrono y representa mas de un solo elemneto
	public void flux() {
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Leo", 31));
		personas.add(new Persona(2, "Motta", 32));
		personas.add(new Persona(3, "vargas", 33));


		//fromIterable(): Cree un Fluxque emita los elementos contenidos en el proporcionado Iterable
		Flux.fromIterable(personas).subscribe(p -> Log.info("Persona: " + p));
	}

	// pasar de un flux a un mono
	public void fluxMono() {
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1, "Leo", 31));
		personas.add(new Persona(2, "Motta", 32));
		personas.add(new Persona(3, "vargas", 33));

		// collectList: Reúna todos los elementos emitidos por esto Flux en un Lista que es emitido por el resultado
		// Mono cuando se completa esta secuencia.
		Flux<Persona> fx = Flux.fromIterable(personas);
		fx.collectList().subscribe(lista -> Log.info("Persona: " + lista));
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactorApplication.class, args);
	}

	@Override
	// me permite ejecutar la logica que quiero implementar
	public void run(String... args) throws Exception {
		//subscribe();
		//doOnNext();
		//mono();
		//flux();
		//fluxMono();
		Creacion app = new Creacion();
		app.empty();
		//app.range();
		//app.repeat();
		//Transformacion app2 = new Transformacion();
		//app2.map();
		//app2.flatMap();
		//app2.groupBy();
        //Filtrado app3 = new Filtrado();
        //app3.filter();
		//app3.distinct();
		//app3.take();
		//app3.takeLast();
		//app3.skip();
		//app3.skipLast();
		//Combinacion app4 = new Combinacion();
		//app4.merge();
		//app4.zip();
		//app4.zipWith();
		//ControlDeErrores app5 = new ControlDeErrores();
		//app5.retry();
		//app5.errorReturn();
		//app5.errorResume();
		//app5.errorMap();
		//Condicionales app6 = new Condicionales();
		//app6.defaultIfEmpty();
		//app6.takeUntil();
		//app6.timeOut();
		Matematicos app7 = new Matematicos();
		//app7.average();
		//app7.count();
		//app7.min();
		//app7.sum();
		app7.sumMarizing();
	}


}
