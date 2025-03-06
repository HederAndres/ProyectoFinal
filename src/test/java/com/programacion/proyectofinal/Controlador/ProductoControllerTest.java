package com.programacion.proyectofinal.Controlador;

import com.programacion.proyectofinal.Model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerTest {
    @Autowired
    private ProductoController productoController;

    @Test
    public void testListaProductos() {
        Flux<Producto> productos = productoController.GetProducts("es");
        StepVerifier.create(productos)
                .expectNextMatches(p -> p.getNombre().equals("Portátil"))
                .expectNextMatches(p -> p.getNombre().equals("Ratón"))
                .expectNextMatches(p -> p.getNombre().equals("Teclado"))
                .verifyComplete();
    }

    @Test
    public void testCrearProducto() {
        Producto newProducto = new Producto("5", "test producto", 2890.0);
        Mono<Producto> producto = productoController.AddProduct(newProducto);
        StepVerifier.create(producto)
                .expectNextMatches(p -> p.getNombre().equals("test producto"))
                .verifyComplete();
    }

    @Test
    public void testBuscarProductoFrances() {
        Mono<Producto> producto = productoController.GetProductByID("1", "fr");
        StepVerifier.create(producto)
                .expectNextMatches(p -> p.getNombre().equals("Ordinateur portable"))
                .verifyComplete();
    }

    @Test
    public void testActualizarProducto() {
        Producto newProducto = new Producto("1", "Laptop Gaming", 3890.0);
        Mono<Producto> producto = productoController.UpdateProduct("1", newProducto);
        StepVerifier.create(producto)
                .expectNextMatches(p -> p.getNombre().equals("Laptop Gaming") && p.getPrecio().equals(3890.0))
                .verifyComplete();
    }
}
