package hongik.newswhale.infrastructure.persistance.jpa.entity;

import hongik.newswhale.application.domain.Authority;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "authority")
public class AuthorityEntity {

    @Id
    @Column(name = "authority_name")
    private String authorityName;

    public AuthorityEntity(Authority authority) {
        this.authorityName = authority.getAuthorityName();
    }

    public Authority toAuthority() {
        return Authority.builder()
                .authorityName(this.authorityName)
                .build();
    }
}
