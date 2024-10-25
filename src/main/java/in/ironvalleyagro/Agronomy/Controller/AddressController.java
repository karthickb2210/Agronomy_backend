package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.DTO.AddressDto;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Services.AddressServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {

    @Autowired
    private AddressServices addressServices;

    @PostMapping("/addAddress")
    public Response addAddress(@RequestBody AddressDto addressDto){
        return addressServices.addAddress(addressDto);
    }

    @PostMapping("/updateAddress/{id}")
    public Response updateAddress(@RequestBody AddressDto addressDto,@PathVariable long id){
        return addressServices.updateAddress(addressDto,id);
    }

    @GetMapping("/getAllAddress/{email}")
    public Response getAddress(@PathVariable String email){
        return addressServices.getAddress(email);
    }
}
