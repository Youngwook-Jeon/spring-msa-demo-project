package com.project.young.twittertokafkaservice.listener;

import com.project.young.config.KafkaConfigData;
import com.project.young.kafka.avro.model.TwitterAvroModel;
import com.project.young.kafka.producer.config.service.KafkaProducer;
import com.project.young.twittertokafkaservice.transformer.TwitterStatusToAvroTransformer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
@RequiredArgsConstructor
public class TwitterKafkaStatusListener extends StatusAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStatusListener.class);
    private final KafkaConfigData kafkaConfigData;
    private final KafkaProducer<Long, TwitterAvroModel> kafkaProducer;
    private final TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;

    @Override
    public void onStatus(Status status) {
        LOG.info("Twitter status with text {}", status.getText());
        TwitterAvroModel twitterAvroModel =
                twitterStatusToAvroTransformer.getTwitterAvroModelFromStatus(status);
        kafkaProducer.send(kafkaConfigData.getTopicName(), twitterAvroModel.getUserId(), twitterAvroModel);
    }
}
