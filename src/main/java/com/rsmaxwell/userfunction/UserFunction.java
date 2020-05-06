package com.rsmaxwell.userfunction;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class UserFunction {

	public static UserFunction load() throws FileNotFoundException {
		String filename = "userfunction.json";
		JsonReader reader = new JsonReader(new FileReader(filename));

		Gson gson = new GsonBuilder().create();
		return gson.fromJson(reader, UserFunction.class);
	}

	public double calculate(double time) throws Exception {
		return 0;
	}
}
