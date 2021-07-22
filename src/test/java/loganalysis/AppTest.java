package loganalysis;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AppTest 
{

    InMemoryStore logStore;

    @BeforeMethod
    public void setUp() {
        HashMap<String, String> logStore = new HashMap<>();
    }

    @Test
    public void testTest(){
        String ip = "10.10.10.10";



        Assert.assertEquals(1,1);
    }


}
