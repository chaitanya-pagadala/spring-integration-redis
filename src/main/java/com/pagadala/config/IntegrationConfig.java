package com.pagadala.config;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileReadingMessageSource.WatchEventType;
import org.springframework.integration.file.filters.AcceptAllFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {

    public String INPUT_DIR = "/apps/test/input";
    public String OUTPUT_DIR = "/apps/test/output";

    @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "fileInputChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setUseWatchService(true);
        source.setDirectory(new File(INPUT_DIR));
        source.setFilter(new AcceptAllFileListFilter<>());
        source.setWatchEvents(WatchEventType.CREATE);
        return source;
    }

    @Bean
    @InboundChannelAdapter(value = "fileInputChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> deleteFileMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setUseWatchService(true);
        source.setDirectory(new File(INPUT_DIR));
        source.setFilter(new AcceptAllFileListFilter<>());
        source.setWatchEvents(WatchEventType.DELETE, WatchEventType.MODIFY);
        return source;
    }

    @Bean
    @Transformer(inputChannel = "fileInputChannel", outputChannel = "processFileChannel")
    public FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }
}