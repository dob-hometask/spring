package dev.ione.dob.models;

import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member extends EntityWithUUID {
    private String username;
    private String dateOfBirth;
}
