package com.pujiang.trdschd.sdk;

import com.pujiang.trdschd.sdk.producer.TrdschdProducer;

public class Trdschd {


  private Config config;


  public TrdschdProducer producer(String producerId) {
    this.config.setProducerId(producerId);
    return new TrdschdProducer(this.config);
  }



}
