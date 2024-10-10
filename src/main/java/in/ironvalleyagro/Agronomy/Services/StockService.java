package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Entity.CurrentStockDetections;
import in.ironvalleyagro.Agronomy.Entity.StockData;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.StockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockDataRepository stockDataRepository;
    public Response updateStocks(CurrentStockDetections currentStockDetections){
        Response res = new Response();
        try{
            Optional<StockData> stockData= stockDataRepository.findById("0");
            StockData stocks = new StockData();
            if(stockData.isPresent()){
                stocks = stockData.get();
            }
            stocks.setItemId("0");
            stocks.setPakChoiQuantity(stocks.getPakChoiQuantity()-currentStockDetections.getPakChoiQuantityDetections());
            stocks.setLettuceQuantity(stocks.getLettuceQuantity()-currentStockDetections.getLettuceQuantityDetections());
            stocks.setBabySpinachQuantity(stocks.getBabySpinachQuantity()-currentStockDetections.getBabySpinachQuantityDetections());
            stocks.setKaleQuantity(stocks.getKaleQuantity()-currentStockDetections.getKaleQuantityDetections());
            stocks.setBasilQuantity(stocks.getBasilQuantity()-currentStockDetections.getBasilQuantityDetections());
            stockDataRepository.save(stocks);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public Response getStocks(){
        Response res = new Response();
        try{
            Optional<StockData> stockData= stockDataRepository.findById("0");
            if(stockData.isPresent()) {
                res.setData(stockData.get());
                res.setFlag(true);
            }else{
                res.setFlag(false);
            }
        }catch (Exception e){
            res.setFlag(false);
        }
        return res;
    }
 }
