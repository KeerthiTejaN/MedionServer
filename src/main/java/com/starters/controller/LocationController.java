//package com.starters.controller;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.starters.model.User;
//import com.starters.model.Coordinates;
//import com.starters.service.AddUserService;
//import com.starters.service.CalculateMedianService;
//
//
//@Path("/location")
//public class LocationController {
//	
//	@GET
//	@Path("/localverify")
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response verifyRESTService(InputStream incomingData) {
//		String result = "Medion location started..";
// 
//		// return HTTP response 200 in case of success
//		return Response.status(200).entity(result).build();
//	}
//	
//	@POST
//	@Path("/getMedian")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public @ResponseBody String calculateMedian(@RequestBody ArrayList<Coordinates> coordinateList){
//			
//		
//		
//		Coordinates c = CalculateMedianService.getMedian(coordinateList);
//		System.out.println(c.getLatitude());
//		return "";
//		
//	}
//	
//}
