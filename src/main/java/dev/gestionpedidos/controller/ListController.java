package dev.gestionpedidos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {

    /*
    TOREV: prueba para devolver listado de productos (se podria devolver desde el controlador de productos?
     y asi no crear un controlador a parte, ya que este es de tipo controller y tiene que devolver un hmtl
     y el otro es rest
    */
    @GetMapping(value = "/listado")
    public String showProductsList() {
        return "products";
    }

}
