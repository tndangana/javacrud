package zw.co.test.covid.model;

import lombok.Data;

import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public abstract class BaseId {
    @Id
    private String id;
    private Date createdAt;
    private Date updatedAt;




}
