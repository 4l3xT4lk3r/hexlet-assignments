package exercise.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

// BEGIN
@Entity
@Table(name = "tasks")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    long id;

    String title;

    String description;

    @LastModifiedDate
    private Date updatedAt;

    @CreatedDate
    private Date createdAt;

}
// END
