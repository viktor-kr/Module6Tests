package api.models;

import lombok.Getter;

import java.util.List;



@Getter
public class CatListResponseDto {
    private StatusDto status;
    private List<CatDto> data;
}
