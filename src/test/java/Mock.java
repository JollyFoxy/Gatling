import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Before;
import org.junit.BeforeClass;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Mock {
    private final WireMockServer wireMockServer =new WireMockServer(options().port(8080));

    @BeforeClass
    public static void before(){

    }
}
