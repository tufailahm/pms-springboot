package com.revature.pms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user100", schema = "projectone")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class User {
        @Id
        private int userId;
        private String username;
        private String password;

}
