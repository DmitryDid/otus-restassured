package com.otus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.otus.data.OrderStatusData;
import lombok.Getter;

@Getter
public class OrderDTO {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("petId")
    Integer petId;

    @JsonProperty("quantity")
    Integer quantity;

    @JsonProperty("shipDate")
    String shipDate;

    @JsonProperty("status")
    OrderStatusData status;

    @JsonProperty("complete")
    Boolean complete;

}
