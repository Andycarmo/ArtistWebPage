/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebPage.writer.controlador;

import com.WebPage.writer.modelo.ebooksModelo;
import com.WebPage.writer.repositorio.ebooksRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

/**
 *
 * @author Andres
 */

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api/ebooks")
public class ebooksControlador {
    
    List<ebooksModelo> listaEbooks = new ArrayList<>();
    
    
     /// Variable de interfaz de Modelo
    @Autowired private ebooksRepositorio ebo;
    @Autowired private MongoTemplate m;
    
    /// Procedimiento guardar un solo producto
    @PostMapping("/guardarEbook")
    public ebooksModelo guardarEbook(@Validated @RequestBody ebooksModelo ebook){
        return ebo.insert(ebook);
    }
    
    /// Procedimiento guardar una lista de productos
    @PostMapping("/guardarEbooks")
    public List<ebooksModelo> guardarEbooks(@Validated @RequestBody List<ebooksModelo> ebooks){
        ebooks.stream().forEach(ebooksModelo -> {
        listaEbooks.add(ebooksModelo);
        });
        return ebo.insert(ebooks);
    }
    
    ///Procedimiento consulta general
    @GetMapping("/consultar")
    public List<ebooksModelo> consultarEbooks(){
        return ebo.findAll();
    }
    
    ///Procedimiento consulta individual
    @GetMapping("/consultar/{id}")
    public Optional<ebooksModelo> consultarEbookID(@PathVariable String id){
        return ebo.findById(id);
    }
    
    ///Procedimiento consulta por codigo
    @GetMapping("/consultarCod/{cod}")
    public List<ebooksModelo> consultarEbookCod(@PathVariable (value="cod") String cod){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        return m.find(q, ebooksModelo.class);
    }    

    ///Procedimiento consulta por nombre
    @GetMapping("/consultarNombre/{nombre}")
    public List<ebooksModelo> consultarEbookNombre(@PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, ebooksModelo.class);
    }
    
    ///Procedimiento consulta por varios parametros
    @GetMapping("/consultarParametros/{cod}/{nombre}")
    public List<ebooksModelo> consultarEbookParametros(@PathVariable (value="cod")String cod, @PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, ebooksModelo.class);
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public ebooksModelo actualizarEbook(@PathVariable String id, @Validated @RequestBody ebooksModelo varE){
        ebo.deleteById(id);
        return ebo.save(varE);
    }
    
    /// Procedimiento eliminar ebook
    @DeleteMapping("/eliminar/{id}")
    public void eliminarEbook(@PathVariable String id){
        ebo.deleteById(id);
    }
}
