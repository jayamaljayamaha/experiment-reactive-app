package com.experiment.reactive.configuration;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.EventLoopTaskQueueFactory;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;;
import reactor.netty.resources.LoopResources;


@Configuration
public class EventLoopConfig {

    @Bean
    public EventLoopGroup eventLoopGroup() {
        EventLoopTaskQueueFactory eventLoopTaskQueueFactory = i -> new MpscArrayQueue<Runnable>(i);
        return new NioEventLoopGroup(6, new DefaultThreadFactory("reactor-loop-group"));
    }

    @Bean
    public LoopResources loopResources() {
        return LoopResources.create("loop", 12, 6, true);
    }

    @Bean
    public ReactiveWebServerFactory reactiveWebServerFactory() {
        NettyReactiveWebServerFactory factory = new NettyReactiveWebServerFactory();
        factory.addServerCustomizers(builder -> builder.runOn(eventLoopGroup()).runOn(loopResources()));
        return factory;
    }

}
