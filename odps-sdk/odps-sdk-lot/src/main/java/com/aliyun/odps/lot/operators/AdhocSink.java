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

package com.aliyun.odps.lot.operators;

import apsara.odps.lot.AdhocSinkProtos;
import apsara.odps.lot.DataSinkProtos;
import apsara.odps.lot.Lot;

public class AdhocSink extends DataSink {

  public AdhocSink() {
  }

  @Override
  public Lot.LogicalOperator toProtoBuf() {
    assert (getParents().size() == 1);

    Lot.LogicalOperator.Builder builder = Lot.LogicalOperator.newBuilder();

    DataSinkProtos.DataSink.Builder ds = DataSinkProtos.DataSink.newBuilder();
    ds.setId(getId());
    ds.setParentId(getParents().get(0).getId());

    AdhocSinkProtos.AdhocSink.Builder as = AdhocSinkProtos.AdhocSink.newBuilder();
    ds.setAdhocSink(as.build());
    builder.setDataSink(ds.build());
    return builder.build();
  }
}