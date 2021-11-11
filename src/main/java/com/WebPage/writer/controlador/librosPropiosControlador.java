/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebPage.writer.controlador;

import com.WebPage.writer.modelo.librosPropiosModelo;
import com.WebPage.writer.repositorio.librosPropiosRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

@RequestMapping("/api/librosPropios")
public class librosPropiosControlador {
    
    List<librosPropiosModelo> listaLibrosPropios = new ArrayList<>();
    
    /// Variable de interfaz de Modelo
    @Autowired
    private librosPropiosRepositorio libP;
    
    /// Procedimiento guardar un solo producto
    @PostMapping("/guardarLibroPropio")
    public librosPropiosModelo guardarLibroPropio(@Validated @RequestBody librosPropiosModelo libro){
        return libP.insert(libro);
    }
    
    /// Procedimiento guardar una lista de productos
    @PostMapping("/guardarLibrosPropios")
    public List<librosPropiosModelo> guardarLibrosPropios(@Validated @RequestBody List<librosPropiosModelo> libros){
        libros.stream().forEach(librosPropiosModelo -> {
        listaLibrosPropios.add(librosPropiosModelo);
        });
        return libP.insert(libros);
    }
    
    ///Procedimiento consulta general
    @GetMapping("/consultar")
    public List<librosPropiosModelo> consultarLibrosPropios(){
        return libP.findAll();
    }
    
    ///Procedimiento consulta individual
    @GetMapping("/consultar/{id}")
    public Optional<librosPropiosModelo> consultarLibrosPropiosID(@PathVariable String id){
        return libP.findById(id);
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public librosPropiosModelo actualizarLibroPropios(@PathVariable String id, @Validated @RequestBody librosPropiosModelo varLP){
        return libP.save(varLP);
    }
    
    /// Procedimiento eliminar libro propio
    @DeleteMapping("/eliminar/{id}")
    public void eliminarLibroPropio(@PathVariable String id){
        libP.deleteById(id);
    }
}
