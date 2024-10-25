package in.ironvalleyagro.Agronomy.Services;

import com.mongodb.client.result.UpdateResult;
import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.DTO.AddressDto;
import in.ironvalleyagro.Agronomy.Entity.Address;
import in.ironvalleyagro.Agronomy.Entity.Order;
import in.ironvalleyagro.Agronomy.Entity.User;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.AddressRepository;
import in.ironvalleyagro.Agronomy.Repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServices {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SequenceGeneratorService generatorService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Response updateAddress(AddressDto addressDto,long id){
        Response res = new Response();
        try{
            if(!addressRepository.existsById(id)){
                res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
                return res;
            }
            Optional<Address> address = addressRepository.findById(id);
            if(address.isPresent()){
                Address newAddress = getNewAddress(addressDto, id, address);
                res.setData(addressRepository.save(newAddress));
                res.setFlag(true);
                res.setStatusCode(ResponseCode.CODE_SUCCESS);
            }else{
                res.setStatusCode(ResponseCode.DATA_NOT_FOUND);
                return res;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    private static @NotNull Address getNewAddress(AddressDto addressDto, long id, Optional<Address> address) {

            Address newAddress = address.get();
            newAddress.setAddressId(id);
            newAddress.setName(addressDto.getName());
            newAddress.setZip(addressDto.getZip());
            newAddress.setHouse(addressDto.getHouse());
            newAddress.setAddress(addressDto.getAddress());
            newAddress.setStreet(addressDto.getStreet());
            newAddress.setMobileNumber(addressDto.getMobileNumber());
            newAddress.setState(addressDto.getState());
            newAddress.setEmail(addressDto.getEmail());
            newAddress.setCity(addressDto.getCity());
            return newAddress;
        }

    public Response getAddress(String email){
        Response res = new Response();
        try {
            User user =userRepository.findByMail(email);
            List<Address> addressList = addressRepository.findAllByEmail(email);
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
            address.setEmail(addressDto.getEmail());
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
