package com.pujiang.trdschd.sdk.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.pujiang.trdschd.sdk.Config;
import com.pujiang.trdschd.sdk.types.Event;
import com.pujiang.trdschd.sdk.types.Topic;
import com.pujiang.trdschd.sdk.types.thr.TrdschdException;
import com.pujiang.trdschd.sdk.types.trdschd.TrdschdResp;
import io.enoa.http.Http;
import io.enoa.http.protocol.HttpMethod;
import io.enoa.http.protocol.HttpResponse;
import io.enoa.typebuilder.TypeBuilder;

import java.lang.reflect.Type;

public class TrdschdProducer {


  private Config config;
  private SerializeConfig serializeConfig;

  public TrdschdProducer(Config config) {
    this.config = config;
    this.serializeConfig = new SerializeConfig();
    this.serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
  }

  private <T> TrdschdResp<T> call(Http http, Type type) throws TrdschdException {
    String body;
    try {
      HttpResponse response = http.emit();
      body = response.body().string();
    } catch (Exception e) {
      throw new TrdschdException(e.getMessage(), e);
    }
    return JSON.parseObject(body, type);
  }

  public TrdschdProducer removeTopic() throws TrdschdException {
    Http http = this.config.http("/api/topic/remove-all")
      .method(HttpMethod.POST)
      .para("producer", this.config.getProducerId());
    TrdschdResp<Void> resp = this.call(http, TypeBuilder.with(TrdschdResp.class).type(Void.class).build());
    if (resp.getErr() == 0)
      return this;
    throw new TrdschdException(resp.getMsg());
  }

  public TrdschdProducer addTopic(Topic topic) throws TrdschdException {
    topic.setProducerId(this.config.getProducerId());
    String data = JSON.toJSONString(topic, this.serializeConfig);
    Http http = this.config.http("/api/topic/modify")
      .method(HttpMethod.POST)
      .raw(data, "application/json");
    TrdschdResp<Void> resp = this.call(http, TypeBuilder.with(TrdschdResp.class).type(Void.class).build());
    if (resp.getErr() == 0)
      return this;
    throw new TrdschdException(resp.getMsg());
  }

  public TrdschdProducer sendEvent(Event event) throws TrdschdException {
    event.setProducerId(this.config.getProducerId());
    String data = JSON.toJSONString(event, this.serializeConfig);
    Http http = this.config.http("/api/event/publish")
      .method(HttpMethod.POST)
      .raw(data, "application/json");
    TrdschdResp<Void> resp = this.call(http, TypeBuilder.with(TrdschdResp.class).type(Void.class).build());
    if (resp.getErr() == 0)
      return this;
    throw new TrdschdException(resp.getMsg());
  }


}
