package util;

import java.util.ArrayList;

import searchEngines.QueryResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JSONConverter {
	
	private static JSONConverter instance = new JSONConverter();
	
	private JSONConverter(){};
	
	public static JSONConverter getInstance()
	{
		return instance;
	}
	
	public String convert(ArrayList<QueryResult> pQueryResults) throws JsonProcessingException
	{
		ObjectMapper aMapper = new ObjectMapper();
		aMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return aMapper.writeValueAsString(pQueryResults);
	}

}
