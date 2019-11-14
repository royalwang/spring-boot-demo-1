package demo.springboot;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class HelloControllerTest {
    private static MockMvc mvc;

    @BeforeAll
    static void setUp() {
        HelloController controller = new HelloController();
        HelloBean bean = new HelloBean();
        bean.setName("bambrow");
        controller.setHelloBean(bean);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void helloTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("Hello bambrow from Spring Boot!")));
    }

}
