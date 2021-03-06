/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebPage.writer.controlador;

import com.WebPage.writer.modelo.audiolibrosModelo;
import com.WebPage.writer.repositorio.audiolibroRepositorio;
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

@RequestMapping("/api/audiolibro")
public class audiolibrosControlador {
    
    List<audiolibrosModelo> listaAudios = new ArrayList<>();
    
    /// Variable de interfaz de Modelo
    @Autowired private audiolibroRepositorio aud;
    @Autowired private MongoTemplate m;
    
    /// Procedimiento guardar un solo producto
    @PostMapping("/guardarAudio")
    public audiolibrosModelo guardarAudiolibro(@Validated @RequestBody audiolibrosModelo audioLibro){
        return aud.insert(audioLibro);
    }
    
    /// Procedimiento guardar una lista de productos
    @PostMapping("/guardarAudios")
    public List<audiolibrosModelo> guardarAudiolibros(@Validated @RequestBody List<audiolibrosModelo> audioLibros){
        audioLibros.stream().forEach(audiolibrosModelo -> {
        listaAudios.add(audiolibrosModelo);
        });
        return aud.insert(audioLibros);
    }
    
    ///Procedimiento consulta general
    @GetMapping("/consultar")
    public List<audiolibrosModelo> consultarAudiolibro(){
        return aud.findAll();
    }
    
    ///Procedimiento consulta individual
    @GetMapping("/consultar/{id}")
    public Optional<audiolibrosModelo> consultarAudiolibroID(@PathVariable String id){
        return aud.findById(id);
    }
    
    ///Procedimiento consulta por codigo
    @GetMapping("/consultarCod/{cod}")
    public List<audiolibrosModelo> consultarAudiolibroCod(@PathVariable (value="cod") String cod){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        return m.find(q, audiolibrosModelo.class);
    }    

    ///Procedimiento consulta por nombre
    @GetMapping("/consultarNombre/{nombre}")
    public List<audiolibrosModelo> consultarAudiolibroNombre(@PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, audiolibrosModelo.class);
    }
    
    ///Procedimiento consulta por varios parametros
    @GetMapping("/consultarParametros/{cod}/{nombre}")
    public List<audiolibrosModelo> consultarAudiolibroParametros(@PathVariable (value="cod")String cod, @PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, audiolibrosModelo.class);
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public audiolibrosModelo actualizarAudiolibro(@PathVariable String id, @Validated @RequestBody audiolibrosModelo varE){
        aud.deleteById(id);
        return aud.save(varE);      
    }
    
    /// Procedimiento eliminar audiolibro
    @DeleteMapping("/eliminar/{id}")
    public void eliminarAudiolibro(@PathVariable String id){
        aud.deleteById(id);
    }
}
