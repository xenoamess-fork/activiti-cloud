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

package org.activiti.starters.test;

public class VariableUpdatedEventBuilder extends VariableEventBuilder<MockVariableUpdatedEvent, VariableUpdatedEventBuilder > {

    private final MockVariableUpdatedEvent event;

    private VariableUpdatedEventBuilder(long timestamp) {
        event = new MockVariableUpdatedEvent(timestamp);
    }

    public static VariableUpdatedEventBuilder aVariableUpdatedEvent(long timestamp) {
        return new VariableUpdatedEventBuilder(timestamp);
    }


    @Override
    protected MockVariableUpdatedEvent getEvent() {
        return event;
    }
}
