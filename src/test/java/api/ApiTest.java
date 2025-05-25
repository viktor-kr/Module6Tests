package api;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api.models.CatDto;
import api.models.StatusDto;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;



public class ApiTest {

    private static final String EXPECTED_CAT = "Cat 26";
    private static final Random RANDOM = new Random();

    private static final String NEW_NAME_TEMPLATE = "Name_%s";
    private static final String NEW_COLOR_TEMPLATE = "Color_%s";
    private static final String CHANGE_TEMPLATE = "Change_%s";

    private final CatConnector catConnector = new CatConnector();

    private CatDto catEntity;

    @BeforeClass
    private void precondition() {
        catEntity = CatDto.builder()
                .name(String.format(NEW_NAME_TEMPLATE, System.currentTimeMillis()))
                .age(RANDOM.nextInt(10, 100))
                .color(String.format(NEW_COLOR_TEMPLATE, RANDOM.nextInt(200)))
                .build();
    }

    @Test(priority = 1)
    public void apiCreateCatTest() {
        CatDto actualCat = catConnector.createNewCat(catEntity);

        SoftAssertions.assertSoftly(
                softAssertions -> {
                    softAssertions.assertThat(actualCat.getName())
                            .as("Check create Cat Name")
                            .isEqualTo(catEntity.getName());
                    softAssertions.assertThat(actualCat.getAge())
                            .as("Check create Cat Age")
                            .isEqualTo(catEntity.getAge());
                    softAssertions.assertThat(actualCat.getColor())
                            .as("Check create Cat Color")
                            .isEqualTo(catEntity.getColor());
                }
        );
        catEntity.setId(actualCat.getId());
    }

    @Test(priority = 2)
    public void apiAvailableCatInListTest() {
        List<CatDto> catList = catConnector.getCatList();

        List<String> catNameList = catList.stream()
                .map(CatDto::getName)
                .toList();

        assertThat(catNameList)
                .as("Check avaliable expected cat")
                .contains(catEntity.getName());
    }

    @Test(priority = 3)
    public void apiAvailableCatTest() {
        CatDto actualCat = catConnector.getCatById(catEntity.getId());
        assertThat(actualCat)
                .as("Full Check Create Cat")
                .usingRecursiveComparison()
                .isEqualTo(catEntity);
    }

    @Test(priority = 4)
    public void apiEditCatTest() {
        catEntity.setName(String.format(CHANGE_TEMPLATE, catEntity.getName()));
        catEntity.setColor(String.format(CHANGE_TEMPLATE, catEntity.getColor()));
        catEntity.setAge(RANDOM.nextInt(200));

        CatDto actualUpdateCat = catConnector.updateCatById(catEntity);

        assertThat(actualUpdateCat)
                .as("Full Check Edit Cat")
                .usingRecursiveComparison()
                .isEqualTo(catEntity);
    }

    @Test(priority = 5)
    public void apiDeleteCatTest() {
        StatusDto statusDeleteCat = catConnector.deleteCatById(catEntity);

        assertThat(statusDeleteCat.getStatus())
                .as("Check Delete Status")
                .isEqualTo(1);
    }

    @Test(priority = 6)
    public void apiNotExistDeleteCatTest() {
        List<CatDto> catList = catConnector.getCatList();

        List<Integer> catIds = catList.stream().map(CatDto::getId).toList();

        assertThat(catIds)
                .as("Check Not Available Delete Cat")
                .doesNotContain(catEntity.getId());
    }
}