/*
 * Copyright 2017 Alfresco, Inc. and/or its affiliates.
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

package org.activiti.cloud.examples.connectors;

import org.activiti.cloud.api.process.model.IntegrationRequest;
import org.activiti.cloud.connectors.starter.channels.IntegrationResultSender;
import org.activiti.cloud.connectors.starter.configuration.ConnectorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(TestErrorConnector.TestErrorConnectorChannels.class)
public class TestErrorConnector {

    public interface TestErrorConnectorChannels {

        String CHANNEL = "testErrorConnector";

        @Input(CHANNEL)
        SubscribableChannel input();
    }

    private final IntegrationResultSender integrationResultSender;
    private final ConnectorProperties connectorProperties;

    @Autowired
    public TestErrorConnector(IntegrationResultSender integrationResultSender,
                              ConnectorProperties connectorProperties) {
        this.integrationResultSender = integrationResultSender;
        this.connectorProperties = connectorProperties;
    }

    @StreamListener(value = TestErrorConnectorChannels.CHANNEL)
    public void handle(IntegrationRequest integrationRequest) {
        throw new Error("TestErrorConnector");
    }
}
