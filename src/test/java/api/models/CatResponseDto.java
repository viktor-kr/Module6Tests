package api.models;

import lombok.Getter;

import java.util.List;



@Getter
public class CatResponseDto {
    private StatusDto status;
    private CatDto data;
}
