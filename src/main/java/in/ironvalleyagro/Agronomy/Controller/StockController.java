package in.ironvalleyagro.Agronomy.Controller;

import in.ironvalleyagro.Agronomy.Entity.CurrentStockDetections;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    @Autowired
    private StockService stockService;

    @PostMapping("/updateStocks")
    public Response updateStocks(@RequestBody CurrentStockDetections currentStockDetections){
        return stockService.updateStocks(currentStockDetections);
    }

    @GetMapping("/getStocks")
    public Response getStocks(){
        return stockService.getStocks();
    }

}