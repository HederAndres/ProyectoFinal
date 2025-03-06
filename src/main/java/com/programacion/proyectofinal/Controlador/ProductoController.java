package com.programacion.proyectofinal.Controlador;

import com.programacion.proyectofinal.Model.Producto;
import com.programacion.proyectofinal.Service.ProductoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controlador REST para gestionar productos.
 * Proporciona endpoints para crear, obtener, actualizar y eliminar productos
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService ProductoService;

    public ProductoController(ProductoService ProductoService) {
        this.ProductoService = ProductoService;
    }

    /**
     * Crea un nuevo producto.
     */
    @PostMapping
    public Mono<Producto> AddProduct(@RequestBody Producto producto) {
        return ProductoService.createProduct(producto);
    }

    /**
     * Obtiene la lista de todos los productos.
     */
    @GetMapping
    public Flux<Producto> GetProducts(@RequestParam(name = "lang", defaultValue = "en") String lang) {
        return ProductoService.getAllProducts(lang);
    }

    /**
     * Obtiene un producto por su ID.
     */
    @GetMapping("/{id}")
    public Mono<Producto> GetProductByID(@PathVariable String id, @RequestParam(name = "lang", defaultValue = "en") String lang) {
        return ProductoService.getProductById(id, lang);
    }

    /**
     * Actualiza un producto existente.
     */
    @PutMapping("/{id}")
    public Mono<Producto> UpdateProduct(@PathVariable String id, @RequestBody Producto producto) {
        return ProductoService.updateProduct(id, producto);
    }

    /**
     * Elimina un producto por su ID.
     */
    @DeleteMapping("/{id}")
    public Mono<Void> DeleteProduct(@PathVariable String id) {
        return ProductoService.deleteProduct(id);
    }
}

