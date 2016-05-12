package com.ddd.poc.domain.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date();

    @Column(nullable = false, updatable = false)
    private String eventClass;

    @Column(nullable = false, updatable = false)
    private String eventData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commandId")
    private CommandEntity command;

    EventEntity() {

    }

    public EventEntity(CommandEntity command, String eventData, String eventClass) {
        this.command = command;
        this.eventClass = eventClass;
        this.eventData = eventData;
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getEventClass() {
        return eventClass;
    }

    public String getEventData() {
        return eventData;
    }

    public CommandEntity getCommand() {
        return command;
    }
}
