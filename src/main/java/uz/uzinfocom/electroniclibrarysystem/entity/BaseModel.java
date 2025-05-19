package uz.uzinfocom.electroniclibrarysystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
     LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
     LocalDateTime updatedAt;

    @Column(name = "deleted_at", nullable = true)
     LocalDateTime deletedAt;

     Boolean isDeleted = false;


}