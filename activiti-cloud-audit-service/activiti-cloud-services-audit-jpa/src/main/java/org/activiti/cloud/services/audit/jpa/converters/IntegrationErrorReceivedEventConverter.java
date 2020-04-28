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

package org.activiti.cloud.services.audit.jpa.converters;


import org.activiti.api.process.model.events.IntegrationEvent;
import org.activiti.cloud.api.model.shared.events.CloudRuntimeEvent;
import org.activiti.cloud.api.model.shared.impl.events.CloudRuntimeEventImpl;
import org.activiti.cloud.api.process.model.events.CloudIntegrationErrorReceivedEvent;
import org.activiti.cloud.api.process.model.impl.events.CloudIntegrationErrorReceivedEventImpl;
import org.activiti.cloud.services.audit.jpa.events.AuditEventEntity;
import org.activiti.cloud.services.audit.jpa.events.IntegrationErrorReceivedEventEntity;

public class IntegrationErrorReceivedEventConverter extends BaseEventToEntityConverter {

    public IntegrationErrorReceivedEventConverter(EventContextInfoAppender eventContextInfoAppender) {
        super(eventContextInfoAppender);
    }

    @Override
    public String getSupportedEvent() {
        return IntegrationEvent.IntegrationEvents.INTEGRATION_ERROR_RECEIVED.toString();
    }

    @Override
    protected IntegrationErrorReceivedEventEntity createEventEntity(CloudRuntimeEvent cloudRuntimeEvent) {
        return new IntegrationErrorReceivedEventEntity(CloudIntegrationErrorReceivedEvent.class.cast(cloudRuntimeEvent));
    }

    @Override
    protected CloudRuntimeEventImpl<?, ?> createAPIEvent(AuditEventEntity auditEventEntity) {
        IntegrationErrorReceivedEventEntity entity = IntegrationErrorReceivedEventEntity.class.cast(auditEventEntity);

        return new CloudIntegrationErrorReceivedEventImpl(entity.getIntegrationContext(),
                                                          entity.getErrorMessage(),
                                                          entity.getErrorClassName(),
                                                          entity.getStackTraceElements());
    }
}
