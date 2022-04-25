package demo3.demo3.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long keywordID;

    @Column(name = "keyword_name")
    private String keywordName;

    @OneToMany(mappedBy = "keyword")
    List<UserKeyword> userKeywords = new ArrayList<UserKeyword>();

    @OneToMany(mappedBy = "keyword")
    List<NewsKeyword> newsKeywords = new ArrayList<NewsKeyword>();

}
