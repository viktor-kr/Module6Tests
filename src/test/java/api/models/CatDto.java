package api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    private String name;
    private Integer age;
    private String color;
}