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
 * @author Writer
 */

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})

@RequestMapping("/api/ventas")
public class ventasControlador {
    
    /// Variable de interfaz de Modelo
    @Autowired private ventasRepositorio ven;
    @Autowired private MongoTemplate m;
    
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
    
    ///Procedimiento consulta por codigo
    @GetMapping("/consultarCod/{cod}")
    public List<ventasModelo> consultarVentasCod(@PathVariable (value="cod") String cod){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        return m.find(q, ventasModelo.class);
    }    

    ///Procedimiento consulta por nombre
    @GetMapping("/consultarNombre/{nombre}")
    public List<ventasModelo> consultarVentasNombre(@PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, ventasModelo.class);
    }
    
    ///Procedimiento consulta por varios parametros
    @GetMapping("/consultarParametros/{cod}/{nombre}")
    public List<ventasModelo> consultarVentasParametros(@PathVariable (value="cod")String cod, @PathVariable (value="nombre") String nombre){
        Query q = new Query();
        q.addCriteria(Criteria.where("cod").is(cod));
        q.addCriteria(Criteria.where("nombre").is(nombre));
        return m.find(q, ventasModelo.class);
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public ventasModelo actualizarVentas(@PathVariable String id, @Validated @RequestBody ventasModelo varV){
        ven.deleteById(id);
        return ven.save(varV);
    }
    
    /// Procedimiento eliminar ventas
    @DeleteMapping("/eliminar/{id}")
    public void eliminarVentas(@PathVariable String id){
        ven.deleteById(id);
    }
}
