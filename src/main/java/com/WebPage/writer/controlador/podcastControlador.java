/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebPage.writer.controlador;

import com.WebPage.writer.modelo.podcastModelo;
import com.WebPage.writer.repositorio.podcastRepositorio;
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

@RequestMapping("/api/podcast")
public class podcastControlador {
    
    List<podcastModelo> listaPodcasts = new ArrayList<>();
    
    /// Variable de interfaz de Modelo
    @Autowired private podcastRepositorio pod;
    @Autowired private MongoTemplate m;
    
    /// Procedimiento guardar un solo producto
    @PostMapping("/guardarPodcast")
    public podcastModelo guardarPodcast(@Validated @RequestBody podcastModelo podcast){
        return pod.insert(podcast);
    }
    
    /// Procedimiento guardar una lista de productos
    @PostMapping("/guardarPodcasts")
    public List<podcastModelo> guardarPodcasts(@Validated @RequestBody List<podcastModelo> podcasts){
        podcasts.stream().forEach(podcastModelo -> {
        listaPodcasts.add(podcastModelo);
        });
        return pod.insert(podcasts);
    }
    
    ///Procedimiento consulta general
    @GetMapping("/consultar")
    public List<podcastModelo> consultarPodcasts(){
        return pod.findAll();
    }
    
    ///Procedimiento consulta individual
    @GetMapping("/consultar/{id}")
    public Optional<podcastModelo> consultarPodcastsID(@PathVariable String id){
        return pod.findById(id);
    }
    
        ///Procedimiento consulta por codigo
    @GetMapping("/consultarCod/{cod}")
    public List<podcastModelo> consultarPodcastsCod(@PathVariable (value="cod") String cod){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        return m.find(q, podcastModelo.class);
    }    

    ///Procedimiento consulta por nombre
    @GetMapping("/consultarNombre/{nombre}")
    public List<podcastModelo> consultarLibrosPropiosNombre(@PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, podcastModelo.class);
    }
    
    ///Procedimiento consulta por varios parametros
    @GetMapping("/consultarParametros/{cod}/{nombre}")
    public List<podcastModelo> consultarLibrosPropiosParametros(@PathVariable (value="cod")String cod, @PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, podcastModelo.class);
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public podcastModelo actualizarPodcast(@PathVariable String id, @Validated @RequestBody podcastModelo varPC){
        pod.deleteById(id);
        return pod.save(varPC);
    }
    
    /// Procedimiento eliminar podcast
    @DeleteMapping("/eliminar/{id}")
    public void eliminarPodcast(@PathVariable String id){
        pod.deleteById(id);
    }
    
}
