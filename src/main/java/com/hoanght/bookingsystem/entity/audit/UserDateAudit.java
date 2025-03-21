package com.hoanght.bookingsystem.entity.audit;

import com.hoanght.bookingsystem.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Getter
@Setter
public class UserDateAudit extends DateAudit {
    @CreatedBy
    private User createdBy;

    @LastModifiedBy
    private User updatedBy;
}