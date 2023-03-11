package com.experiment.reactive.entity;

import com.experiment.reactive.common.Device;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;



@Table(name = "image")
@Data
@Builder
public class Image implements Persistable<Long> {
    @Id
    private Long id;
    private String name;
    private Double width;
    private Double height;
    @Column(value = "number_of_pixels")
    private Integer numberOfPixels;
    private String format;
    private String url;
    @Column(value = "created_date")
    private Date createdDate;
    @Column(value = "last_modified_date")
    private Date lastModifiedDate;
    private Integer size;
    @Column(value = "captured_by")
    private String capturedBy;
    private Device device;

    @Transient
    private boolean newImage;

    @Override
    @Transient
    public boolean isNew() {
        return this.newImage || id == null;
    }

    public Image setAsNew(){
        this.newImage = true;
        return this;
    }
}
