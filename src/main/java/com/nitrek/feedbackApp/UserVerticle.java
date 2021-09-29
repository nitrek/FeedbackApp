package com.nitrek.feedbackApp;

import java.util.UUID;

import io.vertx.core.AbstractVerticle;

public class UserVerticle extends AbstractVerticle {

  String verticleId = UUID.randomUUID().toString();

  public static final String GET_USER_DETAILS_ADDR = "com.nitrek.feedbackApp.get_user";

  @Override
  public void start() {

    vertx.eventBus().consumer(GET_USER_DETAILS_ADDR, msg -> {

      msg.reply("hello from UserVerticle" + verticleId);
    });

    // vertx.eventBus().consumer("hello.named.addr", msg-> {
    // String name = (String)msg.body();
    // msg.reply(String.format("hello %s from %s",name,verticleId));
    // });
  }

}