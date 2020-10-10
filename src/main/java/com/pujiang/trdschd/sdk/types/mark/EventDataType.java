package com.pujiang.trdschd.sdk.types.mark;

import java.util.Optional;
import java.util.stream.Stream;

public enum EventDataType  {

  TEXT,

  JSON,

  FILE,

  BINARY,
  //
  ;

  public String getValue() {
    return this.name();
  }

  public static Optional<EventDataType> of(String value) {
    return Stream.of(EventDataType.values())
      .filter(item -> item.name().equalsIgnoreCase(value))
      .findFirst();
  }

}
