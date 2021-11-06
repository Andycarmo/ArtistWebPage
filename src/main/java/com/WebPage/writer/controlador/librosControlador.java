/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebPage.writer.controlador;

import com.WebPage.writer.modelo.librosModelo;
import com.WebPage.writer.repositorio.librosRepositorio;
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

@RequestMapping("/api/libros")
public class librosControlador {
    
    /// Variable de interfaz de Modelo
    @Autowired
    private librosRepositorio lib;
    
    /// Procedimiento guardar
    @PostMapping("/guardar")
    public librosModelo guardarLibro(@Validated @RequestBody librosModelo varL){
        return lib.insert(varL);
    }
    
    ///Procedimiento consulta general
    @GetMapping("/consultar")
    public List<librosModelo> consultarLibros(){
        return lib.findAll();
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public librosModelo actualizarLibro(@PathVariable String id, @Validated @RequestBody librosModelo varL){
        return lib.save(varL);
    }
    
    /// Procedimiento eliminar usuario
    @DeleteMapping("/eliminar/{id}")
    public void eliminarLibro(@PathVariable String id){
    }
}
