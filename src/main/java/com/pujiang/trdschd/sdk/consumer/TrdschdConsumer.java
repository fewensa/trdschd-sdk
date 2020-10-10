package com.pujiang.trdschd.sdk.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.pujiang.trdschd.sdk.Config;
import com.pujiang.trdschd.sdk.types.Event;
import com.pujiang.trdschd.sdk.types.thr.TrdschdException;
import com.pujiang.trdschd.sdk.types.trdschd.TrdschdResp;
import io.enoa.http.Http;
import io.enoa.http.protocol.HttpMethod;
import io.enoa.http.protocol.HttpResponse;
import io.enoa.typebuilder.TypeBuilder;

import java.lang.reflect.Type;
import java.util.Optional;

public class TrdschdConsumer {

  private Config config;
  private SerializeConfig serializeConfig;

  public TrdschdConsumer(Config config) {
    this.config = config;
    this.serializeConfig = new SerializeConfig();
    this.serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
  }


  private <T> TrdschdResp<T> call(Http http, Type type) throws TrdschdException {
    try {
      HttpResponse response = http.emit();
      String body = response.body().string();
      return JSON.parseObject(body, type);
    } catch (Exception e) {
      throw new TrdschdException(e.getMessage(), e);
    }
  }

  public Optional<Event> pop() throws TrdschdException {
    Http http = this.config.http("/api/event/pop")
      .method(HttpMethod.GET)
      .para("consumer", this.config.getConsumerId());
    TrdschdResp<Event> resp = this.call(http, TypeBuilder.with(TrdschdResp.class).type(Void.class).build());
    if (resp.getErr() == 0)
      return Optional.ofNullable(resp.getData());
    throw new TrdschdException(resp.getMsg());
  }

  public boolean confirm(String eventId) throws TrdschdException {
    Http http = this.config.http("/api/event/confirm")
      .method(HttpMethod.POST)
      .para("consumer", this.config.getConsumerId())
      .para("event", eventId);
    TrdschdResp<Event> resp = this.call(http, TypeBuilder.with(TrdschdResp.class).type(Void.class).build());
    if (resp.getErr() == 0)
      return true;
    throw new TrdschdException(resp.getMsg());
  }


}
