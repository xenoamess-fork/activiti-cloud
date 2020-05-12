/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.services.notifications.graphql.events.consumer;

import org.activiti.cloud.services.notifications.graphql.events.model.EngineEvent;
import org.activiti.cloud.services.notifications.graphql.events.transformer.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class EngineEventsConsumerMessageHandler {

    private static Logger logger = LoggerFactory.getLogger(EngineEventsConsumerMessageHandler.class);

    private final FluxSink<Message<List<EngineEvent>>> processorSink;
    private final Transformer transformer;

    public EngineEventsConsumerMessageHandler(Transformer transformer,
                                              FluxSink<Message<List<EngineEvent>>> engineEventsSink)
    {
        this.processorSink = engineEventsSink;
        this.transformer = transformer;
    }

    @Bean
    public Consumer<Flux<Message<List<Map<String, Object>>>>> graphQLEngineEventsConsumerSource() {
        return item -> item.doOnNext( message -> {
            System.out.println("Message size: " + message.getPayload().size());
        }).subscribe();
    }


    /*@Bean("graphQLEngineEventsConsumerSource")
    public Consumer<Flux<Message<List<Map<String,Object>>>>> graphQLEngineEventsConsumerSource() {

        return input -> {
            input.flatMapSequential(message -> {
                List<Map<String, Object>> events = message.getPayload();
                String routingKey = (String) message.getHeaders().get("routingKey");

                logger.info("Recieved source message with routingKey: {}", routingKey);

                return Flux.fromIterable(transformer.transform(events))
                               .collectList()
                               .map(list -> MessageBuilder.<List<EngineEvent>> createMessage(list,
                                                                                             message.getHeaders()));
            })
                    .doOnNext(processorSink::next)
                    .doOnError(error -> logger.error("Error handling message ", error))
                    .retry()
                    .subscribe();
        };
    }*/

    /*@StreamListener
    public void receive(@Input(EngineEventsConsumerChannels.SOURCE)
                            Flux<Message<List<Map<String,Object>>>> input) {

        // Let's process and transform message from input stream
        input.flatMapSequential(message -> {
            List<Map<String, Object>> events = message.getPayload();
            String routingKey = (String) message.getHeaders().get("routingKey");

            logger.info("Recieved source message with routingKey: {}", routingKey);

            return Flux.fromIterable(transformer.transform(events))
                       .collectList()
                       .map(list -> MessageBuilder.<List<EngineEvent>> createMessage(list,
                                                                                     message.getHeaders()));
        })
        .doOnNext(processorSink::next)
        .doOnError(error -> logger.error("Error handling message ", error))
        .retry()
        .subscribe();
    }*/
}
