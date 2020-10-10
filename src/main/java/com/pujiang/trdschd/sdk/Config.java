package com.pujiang.trdschd.sdk;

import io.enoa.http.EoUrl;
import io.enoa.http.Http;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Config {

  private String host;
  private String producerId;
  private String consumerId;



  public Http http(String... subpaths) {
    String _host = this.host == null || !this.host.startsWith("http") ?
      "https://trdschd-gdzj.zto-korea.com" :
      this.host;
    return Http.request(EoUrl.with(_host).subpath(subpaths));
  }


}
