package configuration.handler;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor
@Data
public class DataHandler {


    private static Logger log = LoggerFactory.getLogger("DataCollect.class");
    private String shippingMethodName;
    private String paymentMethod;
    private String orderReference;
    private double totalAmount;
    private String address;

    public DataHandler(String shippingMethodName) {
        this.shippingMethodName = shippingMethodName;
    }

}

