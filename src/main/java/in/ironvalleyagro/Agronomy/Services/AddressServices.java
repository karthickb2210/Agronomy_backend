package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.DTO.AddressDto;
import in.ironvalleyagro.Agronomy.Entity.Address;
import in.ironvalleyagro.Agronomy.Entity.Order;
import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.AddressRepository;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressServices {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SequenceGeneratorService generatorService;

    @Autowired
    private UserRepository userRepository;

    public Response getAddress(String email){
        Response res = new Response();
        try {
            User user =userRepository.findByMail(email);
            List<Address> addressList = addressRepository.findAllByUserId(user.getId());
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
            res.setData(addressList);
        }catch (Exception e){
            res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
            e.printStackTrace();
        }
        return res;
    }

    public Response addAddress(AddressDto addressDto){
        Response res =  new Response();
        try{
            Address address = new Address();
            User user = userRepository.findByMail(addressDto.getEmail());
            long newId = generatorService.generateSequence(Address.SEQUENCE_NAME);
            address.setAddressId(newId);
            address.setUserId(user.getId());
            address.setName(addressDto.getName());
            address.setCity(addressDto.getCity());
            address.setState(addressDto.getState());
            address.setHouse(addressDto.getHouse());
            address.setZip(addressDto.getZip());
            address.setMobileNumber(addressDto.getMobileNumber());
            address.setAddress(addressDto.getAddress());
            address.setStreet(addressDto.getStreet());
            address.setCreatedAt(LocalDateTime.now());
            Address createdAddress = addressRepository.save(address);
            res.setData(createdAddress);
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
        }catch (Exception e){
            res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
            e.printStackTrace();
        }
        return res;
    }
}
