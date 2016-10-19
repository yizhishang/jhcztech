import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloController extends MockControllerTest
{
	@Test
	public void testQueryArticlePageByCatalogId()
	{
		try {
			mockMvc.perform(
					get("/queryArticlePageByCatalogId.action").param(
							"catalogId", "2914"
					).param("curPage", "1")
					.param("numPerPage", "1")
			).andExpect(
					status().isOk()
			).andDo(
					print()
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
