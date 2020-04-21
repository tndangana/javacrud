package zw.co.test.covid.model;

import lombok.Data;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public abstract class BaseId {
    @Id
    private String id;
    private LocalDate createdAt;
    private LocalDate updatedAt;




}
