package com.project.young.twittertokafkaservice.init.impl;

import com.project.young.config.KafkaConfigData;
import com.project.young.kafka.admin.client.KafkaAdminClient;
import com.project.young.twittertokafkaservice.init.StreamInitializer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaStreamInitializer implements StreamInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaStreamInitializer.class);
    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        kafkaAdminClient.checkSchemaRegistry();
        LOG.info("Topics with name {} is ready for operations!",
                kafkaConfigData.getTopicNamesToCreate().toArray());
    }
}
