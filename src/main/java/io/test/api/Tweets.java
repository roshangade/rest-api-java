package io.test.api;

import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.test.api.models.Tweet;
import io.test.api.models.TweetsDAO;

@Path("/tweets")
@Produces(MediaType.APPLICATION_JSON)
public class Tweets {
	
	@Inject
	private TweetsDAO tweets;
	
	@GET
	public Response getTweets() {
		JsonObjectBuilder res = Json.createObjectBuilder();
		try {
			//TODO: query part
			res.add("tweets", tweets.getTweets());
		} catch(Exception e) {
			throw new InternalServerErrorException();
		}
		return Response
				.status(Response.Status.OK)
				.entity(res.build())
				.build();
	}
	
	@GET
	@Path("/all")
	public Response getTweetsFromHibernate() {
		JsonObjectBuilder res = Json.createObjectBuilder();
		try {
			//System.out.println(tweets.getTweetsFromHibernate());
			//TODO: query part
			res.add("tweets", "[]");
		} catch(Exception e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAA "+e.getMessage());
			throw new InternalServerErrorException();
		}
		return Response
				.status(Response.Status.OK)
				.entity(tweets.getTweetsFromHibernate())
				.build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTweet(JsonObject body) {
		JsonObjectBuilder res = Json.createObjectBuilder().add("message", "Tweet has been added successfully");
		try {
			//TODO: validate body
			tweets.addTweet(body);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new InternalServerErrorException();
		}
		return Response
				.status(Response.Status.CREATED)
				.entity(res.build())
				.build();
	}
}
