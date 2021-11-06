/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.WebPage.writer.controlador;

import com.WebPage.writer.modelo.usuariosModelo;
import com.WebPage.writer.repositorio.usuariosRepositorio;
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

@RequestMapping("/api/usuarios")
public class usuariosControlador {
    
    /// Variable de interfaza de Modelo
    @Autowired
    private usuariosRepositorio usu;
    
    /// Procedimiento guardar
    @PostMapping("/guardar")
    public usuariosModelo guardarUsuario(@Validated @RequestBody usuariosModelo varU){
        return usu.insert(varU);
    }
    
    ///Procedimiento consulta general
    @GetMapping("/consultar")
    public List<usuariosModelo> consultarUsuarios(){
        return usu.findAll();
    }
    
    /// Procedimiento actualizar
    @PutMapping("/actualizar/{id}")
    public usuariosModelo actualizarUsuario(@PathVariable String id, @Validated @RequestBody usuariosModelo varU){
        return usu.save(varU);
    }
    
    /// Procedimiento eliminar usuario
    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable String id){
    }
}
