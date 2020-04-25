package br.com.thiago.test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import br.com.thiago.servicos.Servicos;
import br.com.thiago.test.entidate.PessoaRequest;
import br.com.thiago.test.entidate.PessoaResponse;
import br.com.thiago.test.entidate.PessoaUpdate;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class ReqResExample {

	@Before
	public void configuraApi() {
		baseURI = 	"https://reqres.in/";
	}
	@Test
	public void methodGet() {
	;//PessoaResponse as =
				given()
		.when()
			.get(Servicos.getUsers_ID.getValor(), 2)
		.then().contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/thiagoExemple.json"))
			.log().all()
			.and().extract().response().as(PessoaResponse.class);
		
		
	}
	@Test
	public void methodPost() {
		PessoaRequest pessoaRequest = new PessoaRequest("thiago","QA");
		
		basePath= "/api/users";		
		PessoaResponse as = given()
			.contentType("application/json")
		.body(pessoaRequest)	
		.when()
			.post("/")
		.then()
			.statusCode(HttpStatus.SC_CREATED)
			.extract().response().as(PessoaResponse.class);
		
		Assert.assertNotNull(as);
		Assert.assertNotNull(as.getId());
		Assert.assertEquals(pessoaRequest.getNome(), as.getNome());
		Assert.assertEquals(pessoaRequest.getJob(), as.getJob());
	}
	@Test
	public void methodPost2() {
		PessoaRequest pessoaRequest = new PessoaRequest("thiago","QA");
		
		basePath= "/api/users";		
		String as =
				given()
			.contentType("application/json")
		.body(pessoaRequest)
		.when()
			.post("/")
		.then()
			.statusCode(HttpStatus.SC_CREATED).log().all()
			.and().extract().response().path("nome");
		
		System.out.println(as);
		Assert.assertNotNull(as);
	}
	
	
	@Test
public void TestGet() {
		
		String pathSchema = "Schemas/userList.json";
		basePath = Servicos.getUsers_PAGE.getValor();
		
		given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.param("page", "2")
			.param("total", "12")
			.log().all()
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(pathSchema))
			.body("page", equalTo(2))
		.and()
			.body("total", equalTo(12))
		.and()
			.body("data", Matchers.hasSize(6));
		
	}
	@Test
	public void TestUpdate() {
		
		int id = 2;
		basePath = Servicos.getUsers_ID.getValor();
		
		PessoaRequest request = new PessoaRequest("Vincius", "Desenvolvedor");
		PessoaResponse response = new  PessoaResponse();
		
		String pathSchema = "Schemas/userUpdate.json";
		
		PessoaUpdate as = given()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("id", id)
			.log().all()
		.when()
			.body(request)
			.patch()
		.then()
			.statusCode(HttpStatus.SC_OK)
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(pathSchema))
			.extract()
			.response()
			.as(PessoaUpdate.class);
		
		
		Assert.assertEquals(request.getNome(), response.getNome());
		Assert.assertEquals(request.getJob(), response.getJob());
		Assert.assertEquals(request.getJob(), response.getJob());
		Assert.assertNotNull(response.getCreatedAt());
		
		
	}
	
		
	
	
@Test
	public void Testdelete() {
		
		basePath = Servicos.getUsers_ID.getValor();
		
		given()
			.pathParam("id", 1)
			.log().all()
		.when()
			.delete()
		.then()
			.statusCode(HttpStatus.SC_NO_CONTENT);
			
	}
	



	
	
}
