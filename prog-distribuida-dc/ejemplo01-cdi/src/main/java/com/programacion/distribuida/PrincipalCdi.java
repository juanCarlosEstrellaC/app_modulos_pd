package com.programacion.distribuida;
import com.programacion.distribuida.service.StringService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;


public class PrincipalCdi {
    //@Inject
    //StringService service;

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            // Aquí va tu código de trabajo con el contenedor.


            StringService service = container.
                    select(StringService.class).
                    get();

            String ret = service.convert("Hola mundo  xd");
            System.out.println(ret);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
