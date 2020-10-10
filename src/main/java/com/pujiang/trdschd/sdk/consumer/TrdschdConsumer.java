package com.pujiang.trdschd.sdk.consumer;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.pujiang.trdschd.sdk.Config;

public class TrdschdConsumer {

  private Config config;
  private SerializeConfig serializeConfig;

  public TrdschdConsumer(Config config) {
    this.config = config;
    this.serializeConfig = new SerializeConfig();
    this.serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
  }



}
