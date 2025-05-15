package distribuida.rs;

import distribuida.service.StringService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/hello")
public class HolaRest {

    //http://127.0.0.1:8080/hello?txt=hola mundo

    @Inject
    StringService stringService;

    @GET
    public String hello(@QueryParam("txt" ) String txt) {
        return stringService.convert(txt);
    }
}
