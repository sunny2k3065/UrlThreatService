package cli;

import org.junit.Test;

public class MainTest {
    @Test
    public void main() throws Exception {
        System.setProperty("servicePort", "8990");
        Main.main(new String[]{""});
    }

}
