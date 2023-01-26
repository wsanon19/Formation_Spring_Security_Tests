package com.security.forma_security.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "authorities")
public class Role {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long Id ;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;


    public Role(ERole name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

}
