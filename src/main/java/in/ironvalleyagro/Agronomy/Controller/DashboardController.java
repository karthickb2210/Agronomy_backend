package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Services.DashBoardDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @Autowired
    private DashBoardDetails dashBoardDetails;

    @GetMapping("/getDashDetails/{email}")
    public Response getDashDetails(@PathVariable String email){
        return dashBoardDetails.getDashBoardDetails(email);
    }

}
