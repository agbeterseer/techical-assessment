package com.paymentgateway.gpay.client.request;

import com.paymentgateway.gpay.client.enums.EventType;
import lombok.Data;

@Data
public class WebHookNotification {
private EventType event_type;
private String  event_timestamp;
private String  payment_id;
private String  amount;
private String  currency;
private String  status;
private String  customer_id;
private String  customer_email;
private String order_id;
private MetaData metaData;

}

@Data
class MetaData{
private String description;
private String product_name;

}