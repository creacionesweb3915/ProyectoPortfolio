/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolioweb.bkd.Controller;

import com.portfolioweb.bkd.Dto.dtoEducacion;
import com.portfolioweb.bkd.Entity.Educacion;
import com.portfolioweb.bkd.Security.Controller.Mensaje;
import com.portfolioweb.bkd.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educac")
@CrossOrigin(origins = "https://portfolioweb-e776b.web.app")
public class CEducacion {
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);         
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!sEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")   
    public ResponseEntity<?> delete(@PathVariable("id") int id){
         //Validamos si existe el ID
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Datos eliminados"), HttpStatus.OK);        
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoedu){
        if(StringUtils.isBlank(dtoedu.getNombreD()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sEducacion.existsByNombreD(dtoedu.getNombreD()))
            return new ResponseEntity(new Mensaje("Esa información existe"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = new Educacion(dtoedu.getNombreD(), dtoedu.getDescripcionD());
        sEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("Información agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoedu){
        //Validamos si existe el ID
        if(!sEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        // Compara nombre de educaciones
        if(sEducacion.existsByNombreD(dtoedu.getNombreD()) && sEducacion.getByNombreD(dtoedu.getNombreD()).get().getId() !=id)
            return new ResponseEntity(new Mensaje("Esa información ya existe"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio
        if(StringUtils.isBlank(dtoedu.getNombreD()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreD(dtoedu.getNombreD());
        educacion.setDescripcionD((dtoedu.getDescripcionD()));
        
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Información actualizada"), HttpStatus.OK);        
    }
       
    
    
}
