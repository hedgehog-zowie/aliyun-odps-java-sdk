/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.aliyun.odps.tunnel;

import java.net.URI;
import java.net.URISyntaxException;

import com.aliyun.odps.Odps;
import com.aliyun.odps.commons.GeneralConfiguration;
import com.aliyun.odps.rest.RestClient;
import com.aliyun.odps.tunnel.io.CompressOption;

/**
 * ODPS Tunnel 配置项
 *
 * <p>
 * 用于保存与ODPS Tunnel服务通讯过程需要的配置信息。
 * </p>
 *
 * @author <a href="mailto:chao.liu@alibaba-inc.com">chao.liu</a>
 */
class Configuration extends GeneralConfiguration {

  private TunnelServerRouter router;

  private CompressOption option = new CompressOption();

  public Configuration(Odps odps) {
    super(odps);
    router = new TunnelServerRouter(this.odps.getRestClient());
  }

  public CompressOption getCompressOption() {
    return option;
  }

  public void setCompressOption(CompressOption option) {
    this.option = option;
  }

  /**
   * 取得指定Project的Tunnel服务入口地址
   *
   * @return ODPS Tunnel服务入口地址
   * @throws TunnelException
   */
  @Override
  public URI getEndpoint(String projectName) throws TunnelException {

    if (endpoint != null) {
      return endpoint;
    }

    String odpsEndpoint = odps.getEndpoint();
    URI u = null;
    try {
      u = new URI(odpsEndpoint);
    } catch (URISyntaxException e) {
      throw new TunnelException(e.getMessage(), e);
    }
    return router.getTunnelServer(projectName, u.getScheme());
  }

  /**
   * 获得Stream upload数据的RESTful资源标识符
   *
   * @param projectName
   * @param tableName
   * @return
   */
  @Deprecated
  public String getStreamUploadResource(String projectName, String tableName, String shardId) {
    StringBuilder sb = new StringBuilder();
    sb.append("/projects/").append(projectName).append("/tables/")
        .append(tableName)
        .append("/shards/")
        .append(shardId);

    return sb.toString();
  }

  RestClient newRestClient(String projectName) throws TunnelException {

    RestClient odpsServiceClient = odps.clone().getRestClient();

    odpsServiceClient.setReadTimeout(getSocketTimeout());
    odpsServiceClient.setConnectTimeout(getSocketConnectTimeout());
    odpsServiceClient.setEndpoint(getEndpoint(projectName).toString());

    return odpsServiceClient;
  }


}
