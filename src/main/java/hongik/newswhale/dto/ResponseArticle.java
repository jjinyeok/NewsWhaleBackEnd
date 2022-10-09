package hongik.newswhale.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseArticle {

    private Long articleId;
    private String articleTitle;
    private String articleReporter;
    private String articleUrl;
    private String articleMediaName;
    private String articleMediaUrl;
    private String articleMediaImageSrc;
    private Date articleLastModifiedTime;
    private String keyword1;
    private String keyword2;
    private String keyword3;

}
