package co.com.sofka.operador.combinacion;

import co.com.sofka.ReactorApplication;
import co.com.sofka.model.Persona;
import co.com.sofka.model.Venta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class Combinacion {
    private static final Logger Log = LoggerFactory.getLogger(ReactorApplication.class);

    public void merge() {
        List<Persona> clientes = new ArrayList<>();
        clientes.add(new Persona(1, "Leo", 31));
        clientes.add(new Persona(2, "Motta", 32));
        clientes.add(new Persona(3, "vargas", 33));

        List<Persona> empleados = new ArrayList<>();
        empleados.add(new Persona(1, "lida", 41));
        empleados.add(new Persona(2, "Motta", 42));
        empleados.add(new Persona(3, "vargas", 43));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1));
        ventas.add(new Venta(2));

        Flux<Persona> flux1 = Flux.fromIterable(clientes);
        Flux<Persona> flux2 = Flux.fromIterable(empleados);
        Flux<Venta> flux3 = Flux.fromIterable(ventas);

        // merge(): Fusionar datos de Publisher secuencias contenidas en una matriz / vararg en una secuencia fusionada
        // intercalada. A diferencia concat, las fuentes se suscriben con entusiasmo. Es decir me permite unir
        // diferentes flujs
        Flux.merge(flux1, flux2, flux3)
                .subscribe(id -> Log.info(id.toString()));
    }

    public void zip() {
        List<Persona> clientes = new ArrayList<>();
        clientes.add(new Persona(1, "Leo", 31));
        clientes.add(new Persona(2, "Motta", 32));
        clientes.add(new Persona(3, "vargas", 33));

        List<Persona> empleados = new ArrayList<>();
        empleados.add(new Persona(1, "lida", 41));
        empleados.add(new Persona(2, "Motta", 42));
        empleados.add(new Persona(3, "vargas", 43));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1));

        Flux<Persona> flux1 = Flux.fromIterable(clientes);
        Flux<Persona> flux2 = Flux.fromIterable(empleados);
        Flux<Venta> flux3 = Flux.fromIterable(ventas);

        //zip(): Comprima varias fuentes juntas, es decir, espere a que todas las fuentes emitan un elemento y combine
        // estos elementos una vez en un valor de salida (construido por el combinador proporcionado). El operador
        // continuará haciéndolo hasta que se complete alguna de las fuentes. Los errores se reenviarán inmediatamente.
        // Este procesamiento "Step-Merge" es especialmente útil en escenarios Scatter-Gather. Es decir el zip va
        // esperar que almenos todos los flujos emitan un valor para operar
        // String.format() = permite una impresion es un formato
        Flux.zip(flux1, flux2, (p1, p2) -> String.format("Flux1: %s, Flux2: %s ", p1, p2))
                .subscribe(x -> Log.info(x));
        Flux.zip(flux1, flux2, flux3)
                .subscribe(x -> Log.info(x.toString()));
    }


    public void zipWith() {

        List<Persona> clientes = new ArrayList<>();
        clientes.add(new Persona(1, "Leo", 31));
        clientes.add(new Persona(2, "Motta", 32));
        clientes.add(new Persona(3, "vargas", 33));

        List<Persona> empleados = new ArrayList<>();
        empleados.add(new Persona(1, "lida", 41));
        empleados.add(new Persona(2, "Motta", 42));
        empleados.add(new Persona(3, "vargas", 43));

        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(1));

        Flux<Persona> flux1 = Flux.fromIterable(clientes);
        Flux<Persona> flux2 = Flux.fromIterable(empleados);
        Flux<Venta> flux3 = Flux.fromIterable(ventas);

        // zipWith(): Comprima esto Fluxcon otra Publisherfuente, es decir, espere a que ambos emitan un elemento y
        // combinen estos elementos una vez en un archivo Tuple2. El operador continuará haciéndolo hasta que se complete
        // alguna de las fuentes. Los errores se reenviarán inmediatamente. Este procesamiento "Step-Merge" es
        // especialmente útil en escenarios Scatter-Gather. Es decir, se debe usar con un flujo previo y no directamente de la
        // clase flux
        flux1.zipWith(flux3, (p1, v2) -> String.format("Flux1: %s, Flux2: %s ", p1, v2))
                .subscribe(x -> Log.info(x));
    }
}
