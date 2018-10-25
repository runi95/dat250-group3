package jms;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.google.gson.JsonObject;
import utils.MessageToBuyer;

//@MessageDriven(mappedName = "jms/dat250/Topic", activationConfig = {
//        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
//        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "topicUser = 'dweet'") })
//public class DweetListener implements MessageListener {

//    @Override
//    public void onMessage(Message message) {
//
//        try {
//            MessageToBuyer m = message.getBody(MessageToBuyer.class);
//            JsonObject json = new JsonObject();
//            json.addProperty("bid", m.getWinningBid().getAmount());
//            json.addProperty("buyer", m.getBuyer().getUserName());
//            json.addProperty("seller", m.getSeller().getUserName());
//            json.addProperty("Message", m.getMessage());
//            try {
//                DweetConnection dc = new DweetConnection();
//                dc.publish(json);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (JMSException e1) {
//            e1.printStackTrace();
//        }
//
//    }

//}
