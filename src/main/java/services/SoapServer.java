package services;

import entities.Auction;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SoapServer {

    @WebMethod
    Auction getAuctionById(int id);

    @WebMethod
    Auction[] getOpenAuctions();

    @WebMethod
    double bidOnAuction(int id, int userID, int amount);
}
