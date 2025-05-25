package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import api.models.*;

import java.util.List;


public class CatConnector {

    public List<CatDto> getCatList() {
        return RestAssured.given()
                .baseUri("http://localhost:8080")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/api/v1/cats/list")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(CatListResponseDto.class, ObjectMapperType.GSON)
                .getData();
    }

    public CatDto getCatById(int catId) {
        return RestAssured.given()
                .baseUri("http://localhost:8080")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get("/api/v1/cats/get/{catId}", catId)
                .then()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(CatResponseDto.class, ObjectMapperType.GSON)
                .getData();
    }

    public CatDto createNewCat(CatDto body) {
        return RestAssured.given()
                .baseUri("http://localhost:8080")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body, ObjectMapperType.GSON)
                .post("/api/v1/cats/create")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(CatResponseDto.class, ObjectMapperType.GSON)
                .getData();
    }

    public CatDto updateCatById(int catId, CatDto body) {
        return RestAssured.given()
                .baseUri("http://localhost:8080")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body, ObjectMapperType.GSON)
                .put("/api/v1/cats/update/{catId}", catId)
                .then()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(CatResponseDto.class, ObjectMapperType.GSON)
                .getData();
    }

    public CatDto updateCatById(CatDto body) {
        return updateCatById(body.getId(), body);
    }

    public StatusDto deleteCatById(int catId) {
        return RestAssured.given()
                .baseUri("http://localhost:8080")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .delete("/api/v1/cats/delete/{catId}", catId)
                .then()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(DeleteCatDto.class, ObjectMapperType.GSON)
                .getStatus();
    }

    public StatusDto deleteCatById(CatDto cat) {
        return deleteCatById(cat.getId());
    }
}