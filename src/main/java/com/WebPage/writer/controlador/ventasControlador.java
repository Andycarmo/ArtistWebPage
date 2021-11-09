/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebPage.writer.controlador;

import com.WebPage.writer.modelo.ventasModelo;
import com.WebPage.writer.repositorio.ventasRepositorio;
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
 * @author Writer
 */

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api/ventas")
public class ventasControlador {
    
    /// Variable de interfaz de Modelo
    @Autowired
    private ventasRepositorio ven;
    
    /// Procedimiento guardar
    @PostMapping("/guardar")
    public ventasModelo guardarVenta(@Validated @RequestBody ventasModelo varV){
        return ven.insert(varV);
    }
    
    ///Procedimiento consulta general
    @GetMapping("/consultar")
    public List<ventasModelo> consultarVentas(){
        return ven.findAll();
    }
    
    ///Procedimiento consulta individual
    @GetMapping("/consultar/{id}")
    public Optional<ventasModelo> consultarVentasID(@PathVariable String id){
        return ven.findById(id);
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public ventasModelo actualizarVentas(@PathVariable String id, @Validated @RequestBody ventasModelo varV){
        return ven.save(varV);
    }
    
    /// Procedimiento eliminar ventas
    @DeleteMapping("/eliminar/{id}")
    public void eliminarVentas(@PathVariable String id){
    }
}
