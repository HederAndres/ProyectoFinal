package com.programacion.proyectofinal.Service;

import com.programacion.proyectofinal.Model.Producto;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ProductoService {
    private final List<Producto> productos = new ArrayList<>();
    private final MessageSource messageSource;

    public ProductoService(MessageSource messageSource) {
        this.messageSource = messageSource;
        productos.add(new Producto("1", "producto.1", 1200.0));
        productos.add(new Producto("2", "producto.2", 25.0));
        productos.add(new Producto("3", "producto.3", 45.0));
    }

    // Obtener todos los productos con traducción de nombres
    public Flux<Producto> getAllProducts(String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        return Flux.fromIterable(productos)
                .map(producto -> traducirProducto(producto, locale));
    }

    // Método para traducir el nombre del producto
    private Producto traducirProducto(Producto producto, Locale locale) {
        String nombreTraducido = messageSource.getMessage(producto.getNombre(), null, producto.getNombre(), locale);
        return new Producto(producto.getId(), nombreTraducido, producto.getPrecio());
    }

    // Obtener un solo producto por ID con traducción de nombre
    public Mono<Producto> getProductById(String id, String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        return Mono.justOrEmpty(productos.stream()
                        .filter(producto -> producto.getId().equals(id))
                        .findFirst())
                .map(producto -> traducirProducto(producto, locale));
    }

    // Crear un nuevo producto (no tiene traducción automática)
    public Mono<Producto> createProduct(Producto producto) {
        Producto newProducto = new Producto(producto.getId(), producto.getNombre(), producto.getPrecio());
        productos.add(newProducto);
        return Mono.just(newProducto);
    }

    // Actualizar un producto
    public Mono<Producto> updateProduct(String id, Producto newProducto) {
        return Mono.justOrEmpty(productos.stream()
                        .filter(producto -> producto.getId().equals(id))
                        .findFirst())
                .flatMap(existingProducto -> {
                    // Modify the existing product directly in the list
                    existingProducto.setNombre(newProducto.getNombre());
                    existingProducto.setPrecio(newProducto.getPrecio());
                    return Mono.just(existingProducto);
                });
    }

    // Eliminar un producto
    public Mono<Void> deleteProduct(String id) {
        productos.removeIf(producto -> producto.getId().equals(id));
        return Mono.empty();
    }
}


