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

package org.activiti.cloud.organization.core.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Group model entity
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
@Table(name = "GROUPS")
public class Group {

    @Id
    private String id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Group> subgroups;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> projects;

    public Group() {    // for JPA
    }

    public Group(String id) {
        this.id = id;
    }

    public List<Group> getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(List<Group> subgroups) {
        this.subgroups = subgroups;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
