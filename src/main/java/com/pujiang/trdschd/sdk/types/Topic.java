package com.pujiang.trdschd.sdk.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Topic implements Serializable {

  private String id;
  private String code;
  private String name;
  private String producerId;
  private String additional;

}
