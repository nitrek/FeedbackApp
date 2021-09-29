package com.nitrek.feedbackApp;

import com.nitrek.feedbackApp.config.MongoDbConfigProperties;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;

import java.util.UUID;

public class PostgresVerticle extends AbstractVerticle {

  String verticleId = UUID.randomUUID().toString();


  public static final String GET_USER_DETAILS_FROM_DB_ADDR = "com.nitrek.feedbackApp.get_db_client";
//  JsonObject postgreSQLClientConfig = new JsonObject().put("host", "mypostgresqldb.mycompany");
//  SQLClient postgreSQLClient = PostgreSQLClient.createShared(vertx, postgreSQLClientConfig);

  @Override
  public void start() {

    System.setProperty("vertx.disableDnsResolver", "true");
    vertx.eventBus().consumer(GET_USER_DETAILS_FROM_DB_ADDR, msg -> {
//      postgreSQLClient.getConnection(res -> {
//        if (res.succeeded()) {
//
//          SQLConnection connection = res.result();
//
//          msg.reply("hello from DatabaseVerticle which is connected" + verticleId);
//
//        } else {
//          msg.reply("hello from DatabaseVerticle failed to conenct" + verticleId);
//        }
//      });
      msg.reply("hello from DatabaseVerticle which is connected" + verticleId);
    });

    // vertx.eventBus().consumer("hello.named.addr", msg-> {
    // String name = (String)msg.body();
    // msg.reply(String.format("hello %s from %s",name,verticleId));
    // });
  }

}
