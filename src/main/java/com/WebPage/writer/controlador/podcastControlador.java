/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebPage.writer.controlador;

import com.WebPage.writer.modelo.podcastModelo;
import com.WebPage.writer.repositorio.podcastRepositorio;
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

/**
 *
 * @author Andres
 */

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api/podcast")
public class podcastControlador {
    
    /// Variable de interfaz de Modelo
    @Autowired
    private podcastRepositorio pod;
    
    /// Procedimiento guardar
    @PostMapping("/guardar")
    public podcastModelo guardarPodcast(@Validated @RequestBody podcastModelo varPC){
        return pod.insert(varPC);
    }
    
    ///Procedimiento consulta general
    @GetMapping("/consultar")
    public List<podcastModelo> consultarPodcasts(){
        return pod.findAll();
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public podcastModelo actualizarPodcast(@PathVariable String id, @Validated @RequestBody podcastModelo varPC){
        return pod.save(varPC);
    }
    
    /// Procedimiento eliminar podcast
    @DeleteMapping("/eliminar/{id}")
    public void eliminarPodcast(@PathVariable String id){
    }
    
}
