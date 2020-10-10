package com.pujiang.trdschd.sdk;

import com.pujiang.trdschd.sdk.consumer.TrdschdConsumer;
import com.pujiang.trdschd.sdk.producer.TrdschdProducer;

public class Trdschd {


  private final Config config;

  public Trdschd(Config config) {
    this.config = config;
  }

  public static Trdschd host(String host) {
    Config config = Config.builder()
      .host(host)
      .build();
    return new Trdschd(config);
  }

  public TrdschdProducer producer(String producerId) {
    this.config.setProducerId(producerId);
    return new TrdschdProducer(this.config);
  }

  public TrdschdConsumer consumer(String consumerId) {
    this.config.setConsumerId(consumerId);
    return new TrdschdConsumer(this.config);
  }


}
