package in.ironvalleyagro.Agronomy.Services;

import in.ironvalleyagro.Agronomy.Constant.ResponseCode;
import in.ironvalleyagro.Agronomy.Entity.CurrentStockDetections;
import in.ironvalleyagro.Agronomy.Entity.StockData;
import in.ironvalleyagro.Agronomy.Model.Response;
import in.ironvalleyagro.Agronomy.Repository.StockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockDataRepository stockDataRepository;

    @Transactional
    public Response adminStockUpdate(StockData stockData){
        Response res = new Response();
        try{
            Optional<StockData> oldStockData= stockDataRepository.findById("0");
            StockData newStockData = new StockData();
            newStockData.setItemId("0");
            newStockData.setLettuceQuantity(stockData.getLettuceQuantity());
            newStockData.setKaleQuantity(stockData.getKaleQuantity());
            newStockData.setBabySpinachQuantity(stockData.getBabySpinachQuantity());
            newStockData.setBasilQuantity(stockData.getBasilQuantity());
            newStockData.setPakChoiQuantity(stockData.getPakChoiQuantity());
            stockDataRepository.save(newStockData);
            res.setFlag(true);
            res.setStatusCode(ResponseCode.CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }


    @Transactional
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
            StockData[] stockData1 = new StockData[1];
            stockData.ifPresent(data -> stockData1[0] = data);
            res.setData(stockData1);
        }catch (Exception e){
            res.setFlag(false);
        }
        return res;
    }
 }
