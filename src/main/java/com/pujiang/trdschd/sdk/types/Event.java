package com.pujiang.trdschd.sdk.types;

import com.pujiang.trdschd.sdk.types.mark.EventDataType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {


  private String id;
  private String topicId;
  private String producerId;
  private EventDataType dataType;
  private String data;
  private String additional;

}
