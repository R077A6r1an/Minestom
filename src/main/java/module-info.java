/**
 * The Minestom server library module. This module contains the Minestom
 * server library implementation for Minecraft server-side handling.
 * 
 * <br> The main purpose is to manage the worlds (instances) from this server,
 * publishing it to multiplayers, and handle Minecraft client connections.
 * 
 * <br> <b>NOTE</b> that this module is not meant to be used as standalone
 * Minecraft Server software as like Bukkit or Spigot. Instead, this is a
 * Minecraft Server library that must be used by an user, configuring the
 * server to it's needs.
 * 
 * <br> Now, the minimal Java source to start the server would be like this:
 * 
 * <blockquote><pre>
 * public static void main(String[] args) {
 *   MinecraftServer server = MinecraftServer.init();
 * 
 *   InstanceContainer instance = server.getInstanceManager().createInstanceContainer();
 *   instanceContainer.setChunkGenerator( new ChunkGenerator() {
 * 
 *     @Override
 *     public void generate(GenerationUnit unit) {
 *       unit.modifier().fillHeight(0, 10, Block.STONE);
 *     }
 *   });
 * 
 *   MinecraftServer.getGlobalEventHandler().addEventCallback(PlayerLoginEvent.class, event -> {
 * 
 *     event.setSpawningInstance(instance);
 *     event.getPlayer().setRespawnPoint(new Position(0, 11, 0));
 *   });
 * 
 *   server.start("0.0.0.0", 25565);
 * }
 * </pre></blockquote>
 * 
 * <br> Now with this basic implementation, it basicly just creats a Minecraft server available
 * for players on the IP of the computer running this programm. By logging in, the player will
 * appear in a stone desert on height 11 on position (0, 11, 0). By login, there is no need to
 * specify the port of the server since Minecraft Servers run by default on port 25565. By changing
 * the port number, the server address must be specified with {@code {@literal <computer-IP>:<port> }}
 */
module net.minestom.server {
  // All exported packages
  exports net.minestom.server;
  exports net.minestom.server.snapshot;
  exports net.minestom.server.crypto;
  exports net.minestom.server.ping;
  exports net.minestom.server.listener;
  exports net.minestom.server.listener.manager;
  exports net.minestom.server.listener.common;
  exports net.minestom.server.listener.preplay;
  exports net.minestom.server.attribute;
  exports net.minestom.server.entity;
  exports net.minestom.server.entity.vehicle;
  exports net.minestom.server.entity.pathfinding;
  exports net.minestom.server.entity.ai;
  exports net.minestom.server.entity.ai.target;
  exports net.minestom.server.entity.ai.goal;
  exports net.minestom.server.entity.metadata;
  exports net.minestom.server.entity.metadata.water;
  exports net.minestom.server.entity.metadata.water.fish;
  exports net.minestom.server.entity.metadata.other;
  exports net.minestom.server.entity.metadata.flying;
  exports net.minestom.server.entity.metadata.ambient;
  exports net.minestom.server.entity.metadata.projectile;
  exports net.minestom.server.entity.metadata.item;
  exports net.minestom.server.entity.metadata.minecart;
  exports net.minestom.server.entity.metadata.animal;
  exports net.minestom.server.entity.metadata.animal.tameable;
  exports net.minestom.server.entity.metadata.display;
  exports net.minestom.server.entity.metadata.golem;
  exports net.minestom.server.entity.metadata.villager;
  exports net.minestom.server.entity.metadata.monster;
  exports net.minestom.server.entity.metadata.monster.zombie;
  exports net.minestom.server.entity.metadata.monster.skeleton;
  exports net.minestom.server.entity.metadata.monster.raider;
  exports net.minestom.server.entity.damage;
  exports net.minestom.server.recipe;
  exports net.minestom.server.color;
  exports net.minestom.server.network;
  exports net.minestom.server.network.packet.server;
  exports net.minestom.server.network.packet.server.configuration;
  exports net.minestom.server.network.packet.server.status;
  exports net.minestom.server.network.packet.server.common;
  exports net.minestom.server.network.packet.server.play;
  exports net.minestom.server.network.packet.server.play.data;
  exports net.minestom.server.network.packet.server.login;
  exports net.minestom.server.network.packet.client;
  exports net.minestom.server.network.packet.client.configuration;
  exports net.minestom.server.network.packet.client.handshake;
  exports net.minestom.server.network.packet.client.status;
  exports net.minestom.server.network.packet.client.common;
  exports net.minestom.server.network.packet.client.play;
  exports net.minestom.server.network.packet.client.login;
  exports net.minestom.server.network.socket;
  exports net.minestom.server.network.player;
  exports net.minestom.server.timer;
  exports net.minestom.server.advancements;
  exports net.minestom.server.advancements.notifications;
  exports net.minestom.server.terminal;
  exports net.minestom.server.world;
  exports net.minestom.server.world.biomes;
  exports net.minestom.server.message;
  exports net.minestom.server.utils;
  exports net.minestom.server.utils.crypto;
  exports net.minestom.server.utils.validate;
  exports net.minestom.server.utils.collection;
  exports net.minestom.server.utils.chunk;
  exports net.minestom.server.utils.identity;
  exports net.minestom.server.utils.entity;
  exports net.minestom.server.utils.location;
  exports net.minestom.server.utils.position;
  exports net.minestom.server.utils.async;
  exports net.minestom.server.utils.function;
  exports net.minestom.server.utils.math;
  exports net.minestom.server.utils.url;
  exports net.minestom.server.utils.time;
  exports net.minestom.server.utils.inventory;
  exports net.minestom.server.utils.instance;
  exports net.minestom.server.utils.mojang;
  exports net.minestom.server.utils.binary;
  exports net.minestom.server.utils.callback;
  exports net.minestom.server.utils.block;
  exports net.minestom.server.utils.debug;
  exports net.minestom.server.utils.player;
  exports net.minestom.server.adventure;
  exports net.minestom.server.adventure.serializer.nbt;
  exports net.minestom.server.adventure.provider;
  exports net.minestom.server.adventure.bossbar;
  exports net.minestom.server.adventure.audience;
  exports net.minestom.server.extensions;
  exports net.minestom.server.map;
  exports net.minestom.server.map.framebuffers;
  exports net.minestom.server.scoreboard;
  exports net.minestom.server.item;
  exports net.minestom.server.item.attribute;
  exports net.minestom.server.item.rule;
  exports net.minestom.server.item.armor;
  exports net.minestom.server.item.firework;
  exports net.minestom.server.item.metadata;
  exports net.minestom.server.inventory;
  exports net.minestom.server.inventory.click;
  exports net.minestom.server.inventory.type;
  exports net.minestom.server.inventory.condition;
  exports net.minestom.server.gamedata.tags;
  exports net.minestom.server.instance.generator;
  exports net.minestom.server.instance.batch;
  exports net.minestom.server.instance.light;
  exports net.minestom.server.instance.palette;
  exports net.minestom.server.instance.block;
  exports net.minestom.server.instance.block.rule;
  exports net.minestom.server.registry;
  exports net.minestom.server.command;
  exports net.minestom.server.command.builder;
  exports net.minestom.server.command.builder.suggestion;
  exports net.minestom.server.command.builder.parser;
  exports net.minestom.server.command.builder.condition;
  exports net.minestom.server.command.builder.exception;
  exports net.minestom.server.command.builder.arguments;
  exports net.minestom.server.command.builder.arguments.relative;
  exports net.minestom.server.command.builder.arguments.number;
  exports net.minestom.server.command.builder.arguments.minecraft;
  exports net.minestom.server.command.builder.arguments.minecraft.registry;
  exports net.minestom.server.statistic;
  exports net.minestom.server.monitoring;
  exports net.minestom.server.resourcepack;
  exports net.minestom.server.coordinate;
  exports net.minestom.server.effects;
  exports net.minestom.server.permission;
  exports net.minestom.server.sound;
  exports net.minestom.server.particle;
  exports net.minestom.server.particle.data;
  exports net.minestom.server.thread;
  exports net.minestom.server.exception;
  exports net.minestom.server.event;
  exports net.minestom.server.event.entity;
  exports net.minestom.server.event.entity.projectile;
  exports net.minestom.server.event.server;
  exports net.minestom.server.event.item;
  exports net.minestom.server.event.inventory;
  exports net.minestom.server.event.trait;
  exports net.minestom.server.event.book;
  exports net.minestom.server.event.instance;
  exports net.minestom.server.event.player;
  exports net.minestom.server.tag;
  exports net.minestom.server.potion;
  exports net.minestom.server.collision;
  exports net.minestom.server.extras;
  exports net.minestom.server.extras.bungee;
  exports net.minestom.server.extras.velocity;
  exports net.minestom.server.extras.lan;
  exports net.minestom.server.extras.mojangAuth;
  exports net.minestom.server.extras.query;
  exports net.minestom.server.extras.query.response;
  exports net.minestom.server.extras.query.event;

  // All opened packages
  opens net.minestom.server;
  opens net.minestom.server.snapshot;
  opens net.minestom.server.crypto;
  opens net.minestom.server.ping;
  opens net.minestom.server.listener;
  opens net.minestom.server.listener.manager;
  opens net.minestom.server.listener.common;
  opens net.minestom.server.listener.preplay;
  opens net.minestom.server.attribute;
  opens net.minestom.server.entity;
  opens net.minestom.server.entity.vehicle;
  opens net.minestom.server.entity.pathfinding;
  opens net.minestom.server.entity.ai;
  opens net.minestom.server.entity.ai.target;
  opens net.minestom.server.entity.ai.goal;
  opens net.minestom.server.entity.metadata;
  opens net.minestom.server.entity.metadata.water;
  opens net.minestom.server.entity.metadata.water.fish;
  opens net.minestom.server.entity.metadata.other;
  opens net.minestom.server.entity.metadata.flying;
  opens net.minestom.server.entity.metadata.ambient;
  opens net.minestom.server.entity.metadata.projectile;
  opens net.minestom.server.entity.metadata.item;
  opens net.minestom.server.entity.metadata.minecart;
  opens net.minestom.server.entity.metadata.animal;
  opens net.minestom.server.entity.metadata.animal.tameable;
  opens net.minestom.server.entity.metadata.display;
  opens net.minestom.server.entity.metadata.golem;
  opens net.minestom.server.entity.metadata.villager;
  opens net.minestom.server.entity.metadata.monster;
  opens net.minestom.server.entity.metadata.monster.zombie;
  opens net.minestom.server.entity.metadata.monster.skeleton;
  opens net.minestom.server.entity.metadata.monster.raider;
  opens net.minestom.server.entity.damage;
  opens net.minestom.server.recipe;
  opens net.minestom.server.color;
  opens net.minestom.server.network;
  opens net.minestom.server.network.packet;
  opens net.minestom.server.network.packet.server;
  opens net.minestom.server.network.packet.server.configuration;
  opens net.minestom.server.network.packet.server.status;
  opens net.minestom.server.network.packet.server.common;
  opens net.minestom.server.network.packet.server.play;
  opens net.minestom.server.network.packet.server.play.data;
  opens net.minestom.server.network.packet.server.login;
  opens net.minestom.server.network.packet.client;
  opens net.minestom.server.network.packet.client.configuration;
  opens net.minestom.server.network.packet.client.handshake;
  opens net.minestom.server.network.packet.client.status;
  opens net.minestom.server.network.packet.client.common;
  opens net.minestom.server.network.packet.client.play;
  opens net.minestom.server.network.packet.client.login;
  opens net.minestom.server.network.socket;
  opens net.minestom.server.network.player;
  opens net.minestom.server.timer;
  opens net.minestom.server.advancements;
  opens net.minestom.server.advancements.notifications;
  opens net.minestom.server.terminal;
  opens net.minestom.server.world;
  opens net.minestom.server.world.biomes;
  opens net.minestom.server.message;
  opens net.minestom.server.utils;
  opens net.minestom.server.utils.crypto;
  opens net.minestom.server.utils.validate;
  opens net.minestom.server.utils.collection;
  opens net.minestom.server.utils.chunk;
  opens net.minestom.server.utils.identity;
  opens net.minestom.server.utils.entity;
  opens net.minestom.server.utils.location;
  opens net.minestom.server.utils.position;
  opens net.minestom.server.utils.async;
  opens net.minestom.server.utils.function;
  opens net.minestom.server.utils.math;
  opens net.minestom.server.utils.url;
  opens net.minestom.server.utils.time;
  opens net.minestom.server.utils.inventory;
  opens net.minestom.server.utils.instance;
  opens net.minestom.server.utils.mojang;
  opens net.minestom.server.utils.binary;
  opens net.minestom.server.utils.callback;
  opens net.minestom.server.utils.block;
  opens net.minestom.server.utils.debug;
  opens net.minestom.server.utils.player;
  opens net.minestom.server.adventure;
  opens net.minestom.server.adventure.serializer;
  opens net.minestom.server.adventure.serializer.nbt;
  opens net.minestom.server.adventure.provider;
  opens net.minestom.server.adventure.bossbar;
  opens net.minestom.server.adventure.audience;
  opens net.minestom.server.extensions;
  opens net.minestom.server.map;
  opens net.minestom.server.map.framebuffers;
  opens net.minestom.server.scoreboard;
  opens net.minestom.server.item;
  opens net.minestom.server.item.attribute;
  opens net.minestom.server.item.rule;
  opens net.minestom.server.item.armor;
  opens net.minestom.server.item.firework;
  opens net.minestom.server.item.metadata;
  opens net.minestom.server.inventory;
  opens net.minestom.server.inventory.click;
  opens net.minestom.server.inventory.type;
  opens net.minestom.server.inventory.condition;
  opens net.minestom.server.gamedata;
  opens net.minestom.server.gamedata.tags;
  opens net.minestom.server.instance;
  opens net.minestom.server.instance.generator;
  opens net.minestom.server.instance.batch;
  opens net.minestom.server.instance.light;
  opens net.minestom.server.instance.palette;
  opens net.minestom.server.instance.block;
  opens net.minestom.server.instance.block.rule;
  opens net.minestom.server.registry;
  opens net.minestom.server.command;
  opens net.minestom.server.command.builder;
  opens net.minestom.server.command.builder.suggestion;
  opens net.minestom.server.command.builder.parser;
  opens net.minestom.server.command.builder.condition;
  opens net.minestom.server.command.builder.exception;
  opens net.minestom.server.command.builder.arguments;
  opens net.minestom.server.command.builder.arguments.relative;
  opens net.minestom.server.command.builder.arguments.number;
  opens net.minestom.server.command.builder.arguments.minecraft;
  opens net.minestom.server.command.builder.arguments.minecraft.registry;
  opens net.minestom.server.statistic;
  opens net.minestom.server.monitoring;
  opens net.minestom.server.resourcepack;
  opens net.minestom.server.coordinate;
  opens net.minestom.server.effects;
  opens net.minestom.server.permission;
  opens net.minestom.server.sound;
  opens net.minestom.server.particle;
  opens net.minestom.server.particle.data;
  opens net.minestom.server.thread;
  opens net.minestom.server.exception;
  opens net.minestom.server.event;
  opens net.minestom.server.event.entity;
  opens net.minestom.server.event.entity.projectile;
  opens net.minestom.server.event.server;
  opens net.minestom.server.event.item;
  opens net.minestom.server.event.inventory;
  opens net.minestom.server.event.trait;
  opens net.minestom.server.event.book;
  opens net.minestom.server.event.instance;
  opens net.minestom.server.event.player;
  opens net.minestom.server.tag;
  opens net.minestom.server.potion;
  opens net.minestom.server.collision;
  opens net.minestom.server.extras;
  opens net.minestom.server.extras.bungee;
  opens net.minestom.server.extras.velocity;
  opens net.minestom.server.extras.lan;
  opens net.minestom.server.extras.mojangAuth;
  opens net.minestom.server.extras.query;
  opens net.minestom.server.extras.query.response;
  opens net.minestom.server.extras.query.event;

  // used services
  uses javax.annotation.processing.Processor;

  // Provided services
  provides net.kyori.adventure.text.logger.slf4j.ComponentLoggerProvider with net.minestom.server.adventure.provider.MinestomComponentLoggerProvider;
  provides net.kyori.adventure.text.serializer.gson.GsonComponentSerializer.Provider with net.minestom.server.adventure.provider.MinestomGsonComponentSerializerProvider;
  provides net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.Provider with net.minestom.server.adventure.provider.MinestomLegacyComponentSerializerProvider;
  provides net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.Provider with net.minestom.server.adventure.provider.MinestomPlainTextComponentSerializerProvider;

  // static requirements
  requires static java.compiler;
  requires static jdk.compiler;

  // requirements
  requires org.tinylog.api;
  requires org.tinylog.impl;
  requires org.tinylog.api.slf4j;
  requires ch.qos.logback.core;
  requires ch.qos.logback.classic;
  requires org.jline;
  //requires org.jline.terminal;
  //requires org.jline.terminal.jansi;
  requires org.jetbrains.annotations;
  requires net.kyori.adventure;
  requires net.kyori.adventure.key;
  requires net.kyori.adventure.text.serializer.gson;
  requires net.kyori.adventure.text.serializer.legacy;
  requires net.kyori.adventure.text.serializer.plain;
  requires net.kyori.adventure.text.serializer.json;
  requires net.kyori.adventure.text.logger.slf4j;
  requires com.github.benmanes.caffeine;
  requires space.vectrix.flare;
  requires space.vectrix.flare.fastutil;
  requires com.google.gson;
  requires java.desktop;
  requires java.net.http;
  requires java.management;
  requires org.slf4j;

  // Kotlin modules
  requires kotlin.reflect;
  requires kotlin.stdlib;

  // unnamed modules: WARNING is unstable
  requires hydrazine.path.finding;
  requires data.structures;
  requires common;
  requires gson;
  requires jctools.core;
  requires data;
  requires it.unimi.dsi.fastutil;
}