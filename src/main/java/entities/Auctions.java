package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(Auction.class)
public class Auctions extends ArrayList<Auction> {	
	private static final long serialVersionUID = 1L;

	public Auctions() {
		super();
	}
			
	public Auctions(Collection<? extends Auction> c) {
		super(c);
	}
			
	@XmlElement(name = "auction")
	public List<Auction> getAuctions() {
		return this;
	}
			
	public void setAuctions(List<Auction> auctions) {
		this.addAll(auctions);
	}


}
