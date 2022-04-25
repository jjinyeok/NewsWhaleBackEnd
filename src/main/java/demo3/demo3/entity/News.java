package demo3.demo3.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(name = "news_title")
    private String newsTitle;

    @Column(name = "news_reporter")
    private String newsReporter;

    @Column(name = "news_media")
    private String newsMedia;

    @Column(name = "news_url")
    private String newsUrl;

    @Column(name = "media_url")
    private String mediaUrl;

    @OneToMany(mappedBy = "news")
    List<NewsKeyword> newsKeywords = new ArrayList<NewsKeyword>();

}
