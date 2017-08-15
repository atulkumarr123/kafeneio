

package com.kafeneio.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.kafeneio.enums.ResponseKeyName;



/**
 * This abstract BaseRestController class for rest Controllers that returns standard response object.
 * 
 *
 *
 */
public abstract class BaseRestController {

  /**
   * Creates the success response.
   *
   * @param payloadName
   *          the payload name
   * @param object
   *          the object
   * @return the hash map
   */
  
  protected HashMap<ResponseKeyName, Object> createSuccessResponse(ResponseKeyName payloadName, Object object) {

    HashMap<ResponseKeyName, Object> responseMap = new LinkedHashMap<ResponseKeyName, Object>();
    responseMap.put(payloadName, object);
    return responseMap;

  }

}
