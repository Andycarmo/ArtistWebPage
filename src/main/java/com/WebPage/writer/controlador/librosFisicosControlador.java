/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebPage.writer.controlador;

import com.WebPage.writer.modelo.librosFisicosModelo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.WebPage.writer.repositorio.librosFisicosRepositorio;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author Andres
 */

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api/librosFisicos")
public class librosFisicosControlador {
    
    List<librosFisicosModelo> listaLibrosFisicos = new ArrayList<>();
    
    /// Variable de interfaz de Modelo
    @Autowired private librosFisicosRepositorio lib;
    @Autowired private MongoTemplate m;
    
    /// Procedimiento guardar un solo producto
    @PostMapping("/guardarLibro")
    public librosFisicosModelo guardarLibro(@Validated @RequestBody librosFisicosModelo libro){
        return lib.insert(libro);
    }
    
    /// Procedimiento guardar una lista de productos
    @PostMapping("/guardarLibros")
    public List<librosFisicosModelo> guardarLibros(@Validated @RequestBody List<librosFisicosModelo> libros){
        libros.stream().forEach(librosFisicosModelo -> {
        listaLibrosFisicos.add(librosFisicosModelo);
        });
        return lib.insert(libros);
    }
    
    ///Procedimiento consulta general
    @GetMapping("/consultar")
    public List<librosFisicosModelo> consultarLibros(){
        return lib.findAll();
    }
    
    ///Procedimiento consulta individual
    @GetMapping("/consultar/{id}")
    public Optional<librosFisicosModelo> consultarLibrosID(@PathVariable String id){
        return lib.findById(id);
    }
    
        ///Procedimiento consulta por codigo
    @GetMapping("/consultarCod/{cod}")
    public List<librosFisicosModelo> consultarLibrosPropiosCod(@PathVariable (value="cod") String cod){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        return m.find(q, librosFisicosModelo.class);
    }    

    ///Procedimiento consulta por nombre
    @GetMapping("/consultarNombre/{nombre}")
    public List<librosFisicosModelo> consultarLibrosPropiosNombre(@PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, librosFisicosModelo.class);
    }
    
    ///Procedimiento consulta por varios parametros
    @GetMapping("/consultarParametros/{cod}/{nombre}")
    public List<librosFisicosModelo> consultarLibrosPropiosParametros(@PathVariable (value="cod")String cod, @PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, librosFisicosModelo.class);
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public librosFisicosModelo actualizarLibro(@PathVariable String id, @Validated @RequestBody librosFisicosModelo varL){
        lib.deleteById(id);
        return lib.save(varL);
    }
    
    /// Procedimiento eliminar libro fisico
    @DeleteMapping("/eliminar/{id}")
    public void eliminarLibro(@PathVariable String id){
        lib.deleteById(id);
    }
}
