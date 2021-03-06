/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.WebPage.writer.repositorio;

import com.WebPage.writer.modelo.usuariosModelo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Writer
 */

@Repository
public interface usuariosRepositorio extends MongoRepository<usuariosModelo, String>{
    
}
