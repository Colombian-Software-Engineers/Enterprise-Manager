package com.ColombianSoftwareEngineers.ezprise.controllers;

import com.ColombianSoftwareEngineers.ezprise.entities.Empleado;
import com.ColombianSoftwareEngineers.ezprise.entities.User;
import com.ColombianSoftwareEngineers.ezprise.services.EmpleadoServices;
import com.ColombianSoftwareEngineers.ezprise.services.MovimientoServices;
import com.ColombianSoftwareEngineers.ezprise.services.UserServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class EmpleadoController {

    EmpleadoServices service;
    UserServices userServices;
    MovimientoServices movimientoServices;
    public EmpleadoController(EmpleadoServices services, UserServices userServices, MovimientoServices movimientoServices) {
        this.service = services;
        this.userServices = userServices;
        this.movimientoServices = movimientoServices;
    }

    @PostMapping("/empleados")
    public RedirectView PostEmpleado(@ModelAttribute Empleado empleado){
        User user = this.userServices.findByEmailUser(empleado.getCorreoEmpleado());
        if(user != null){
            empleado.setUser(user);
        }
        this.service.createOrUpdateEmpleado(empleado);
        return new RedirectView("/empresas/"+empleado.getEmpresa().getIdEmpresa()+"/empleados");
    }
    @DeleteMapping("/empleados/{id}")
    public RedirectView DeleteEmpleadoById(@PathVariable Long id){
        Empleado empleado = this.service.getEmpleadoById(id);
        empleado.deleteEmpleado(this.service, this.movimientoServices);
        return new RedirectView("/empresas/" + empleado.getEmpresa().getIdEmpresa() + "/empleados");
    }

}
