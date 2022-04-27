package demo3.demo3.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNews {

    private Long newsId;
    private String newsTitle;
    private String newsReporter;
    private String newsMedia;
    private String newsUrl;
    private String mediaUrl;
    private String keyword1;
    private String keyword2;
    private String keyword3;

}
