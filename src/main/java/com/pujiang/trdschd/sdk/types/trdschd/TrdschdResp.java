package com.pujiang.trdschd.sdk.types.trdschd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TrdschdResp<T> implements Serializable {

  private Integer err;
  private String msg;
  private T data;

}
