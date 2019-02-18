package com.finleap.template.resp;

import java.util.List;
import java.util.concurrent.CompletionException;
import com.fasterxml.jackson.databind.JsonNode;
import com.finleap.template.util.Json;

/**
 * The type Response to client.
 *
 * @author Kalidass Mahalingam
 */
public class ResponseToClient {

	/**
	 * Instantiates a new Response to client.
	 */
	public ResponseToClient() {
    }

	/**
	 * Object to client json node.
	 *
	 * @param obj the obj
	 * @return the json node
	 */
	public static JsonNode objectToClient(Object obj) {
		Response<Object> response = new Response<>();
		response.setStatus(new Info(Info.InfoType.INFO_OK));
		response.setPayLoad(obj);
		return Json.toJson(response);
	}

	/**
	 * List to client json node.
	 *
	 * @param list the list
	 * @return the json node
	 */
	public static JsonNode listToClient(List<? extends Object> list) {
		try {
			Response<Object> response = new Response<>();
			response.setStatus(new Info(Info.InfoType.INFO_OK));
			response.setPayLoad(list);
			return Json.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CompletionException(e);
		}
	}

	/**
	 * Object to client json node.
	 *
	 * @param obj    the obj
	 * @param status the status
	 * @return the json node
	 */
	public static JsonNode objectToClient(Object obj, Status status) {
		try {
			Response<Object> response = new Response<>();
			response.setStatus(status);
			response.setPayLoad(obj);
			return Json.toJson(response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CompletionException(e);
		}
	}



}
