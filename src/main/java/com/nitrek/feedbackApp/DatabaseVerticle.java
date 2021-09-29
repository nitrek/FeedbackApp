package com.nitrek.feedbackApp;

import java.util.UUID;

import com.nitrek.feedbackApp.config.MongoDbConfigProperties;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class DatabaseVerticle extends AbstractVerticle {

  String verticleId = UUID.randomUUID().toString();

  public static final String GET_USER_DETAILS_FROM_DB_ADDR = "com.nitrek.feedbackApp.get_db_client";
  final MongoDbConfigProperties mongoDbConfig = new MongoDbConfigProperties()
    .setConnectionString("mongodb://setConnectionString");
  MongoClient mongoClient = MongoClient.createShared(vertx, mongoDbConfig.getMongoClientConfig());

  @Override
  public void start() {

    vertx.eventBus().consumer(GET_USER_DETAILS_FROM_DB_ADDR, msg -> {
      JsonObject document = new JsonObject()
        .put("title", "The Hobbit");
      mongoClient.save("books", document, res -> {
        if (res.succeeded()) {
          String id = res.result();
          System.out.println("Saved book with id " + id);
        } else {
          res.cause().printStackTrace();
        }
      });
      msg.reply("hello from DatabaseVerticle" + verticleId);
    });

    // vertx.eventBus().consumer("hello.named.addr", msg-> {
    // String name = (String)msg.body();
    // msg.reply(String.format("hello %s from %s",name,verticleId));
    // });
  }

}
