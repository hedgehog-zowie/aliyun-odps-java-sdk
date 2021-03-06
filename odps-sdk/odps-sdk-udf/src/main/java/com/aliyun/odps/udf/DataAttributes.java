package com.aliyun.odps.udf;

import com.aliyun.odps.Column;

import java.util.HashSet;
import java.util.Properties;

/**
 * Provides interfaces to access different attributes of the underlying data, including the attributes provided
 * by the user, as well as different (system) properties that govern the underlying data, such as
 * the record columns, resources used, etc.
 */
public abstract class DataAttributes {

  /**
   * Get the attribute value associated with the given key, return null if key not found
   *
   * @return: attribute value
   **/
  public abstract String getValueByKey(String key);


  /**
   * @return Serde properties specified in DDL statement and table information (e.g., columns name/type)
   */
  public abstract Properties getHiveTableProperites();

  /**
   * Getter for the record columns that describe the schema of the underlying data
   *
   * @return: column arrays
   */
  public abstract Column[] getRecordColumns();

  /**
   * Getter for needed indexes, this can be used to skip deserialization of non-neede column(s).
   *
   * @return: the indexes of columns that should be extracted from underlying unstructured raw data.
   **/
  public abstract int[] getNeededIndexes();

  /**
   * Getter for set of resources.
   *
   * @return: resources, each denoted by a formatted string of <project>:<resourceName>
   **/
  public abstract HashSet<String> getResources();
}
