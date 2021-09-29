package com.nitrek.feedbackApp;

import io.vertx.core.Vertx;

public class RunApp {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new MainVerticle());
  }
}
