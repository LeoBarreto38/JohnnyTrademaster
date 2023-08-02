import POJO.AutorizationPOST;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TokenTest extends Base {
    String token;
    String baseUrl = configuracao.getBaseUrl();

    public TokenTest() throws FileNotFoundException {
    }

    @Test
    public void TesteDeToken(){
        System.out.println(getToken());

    }

    @Test
    public void autorizationPOST(){
        token = getToken();
        String endpoint = "/authorization";

        Gson gson = new Gson();

        AutorizationPOST body = new AutorizationPOST();
        body.setBuyerDocument("57585651000108");

        AutorizationPOST.Authorization autorizacao1 = new AutorizationPOST.Authorization();
        autorizacao1.setSellerDocument("81386198000195");
        autorizacao1.setAmount(300.12);
        autorizacao1.setSplit(true);

        AutorizationPOST.Authorization autorizacao2 = new AutorizationPOST.Authorization();
        autorizacao2.setSellerDocument("61708562000189");
        autorizacao2.setAmount(200.11);
        autorizacao2.setSplit(false);

        List<AutorizationPOST.Authorization> listaAutorizacao = new ArrayList<>();

        listaAutorizacao.add(autorizacao1);
        listaAutorizacao.add(autorizacao2);

        body.setAuthorizations(listaAutorizacao);

        String jsonBody = gson.toJson(body);

        System.out.println(jsonBody);

        given()
                .baseUri(baseUrl)
                .header("Authorization","Bearer " + token)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(endpoint+ "?pageSize="+10+"&pageNumber="+1+"&updatedAt="2021-08-01+"&status="+"AA"+"&buyerDocument="+37954442000187)

                .then()
                .statusCode(200)
                .body("description", equalTo("Bad Request"))
                .body("serviceName", equalTo("tm-int-ms-create-authorize-v2"))
                .log().all()
                ;
    }
}
