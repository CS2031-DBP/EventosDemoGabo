package dbp.week5.asesoriaeventos.User.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dbp.week5.asesoriaeventos.Meeting.Meeting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;
}
