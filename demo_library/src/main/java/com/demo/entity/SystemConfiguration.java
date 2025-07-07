package com.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "system_configuration")
public class SystemConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "no_activity_for_reserved")
    private Integer noActivityForReserved;

    @Column(name = "after_fixed_duration")
    private Integer afterFixedDuration;

    @Column(name = "fixed_duration")
    private Integer fixedDuration;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNoActivityForReserved() { return noActivityForReserved; }
    public void setNoActivityForReserved(Integer val) { this.noActivityForReserved = val; }

    public Integer getAfterFixedDuration() { return afterFixedDuration; }
    public void setAfterFixedDuration(Integer val) { this.afterFixedDuration = val; }

    public Integer getFixedDuration() { return fixedDuration; }
    public void setFixedDuration(Integer val) { this.fixedDuration = val; }
}
