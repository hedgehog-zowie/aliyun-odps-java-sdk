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

/**
 *
 */
package com.aliyun.odps;

/**
 * TableFilter用于查询所有表时根据条件过滤表
 *
 * <p>
 *
 * 注: 表名是指表名的前缀 <br />
 * 例如:<br />
 *
 * <pre>
 * <code>
 * TableFilter filter = new TableFilter();
 * filter.setName("my_table_prefix");
 *
 * for (Table t : odps.tables().iterator(filter)) {
 *     // do somthing on the Table object
 * }
 * </code>
 * </pre>
 * </p>
 *
 * @author shenggong.wang@alibaba-inc.com
 */
public class TableFilter {

  private String name;

  /**
   * 设置表名前缀
   *
   * @param name
   *     表名前缀
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 获得表名前缀
   *
   * @return 表名前缀
   */
  public String getName() {
    return name;
  }
}