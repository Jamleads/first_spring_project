package com.rungroup.web.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
public class ClubDto {
    private Long id;
    @NotEmpty(message = "Club title should npt be empty")
    private String title;
    @NotEmpty(message =  "Photo link is required")
    private String photoUrl;
    @NotEmpty(message = "Content should not be empty")
    private  String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
