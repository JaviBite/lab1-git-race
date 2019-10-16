/**
 * Unit test for Thanos
 * Created by:
 * Date:
 * Last modified by: Daniel Revillo Rey, on 21\9\2019
 * Comments:
 */

package es.unizar.webeng.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/** Spring allows using @Autowired beans in the test instance */
@RunWith(SpringRunner.class)
@WebMvcTest(Thanos.class)
public class ThanosUnitTest {

    @Autowired
    private Thanos controller;


    // Test for thanos app (form)
    @Test
    public void testThanos() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.play(null, map);
	/** Checks if "thanos" view is being used by the controller */
        assertThat(view, is("thanos"));
    }

    // Test for thanos app (response alive)
    @Test
    public void testThanos_res_alive() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.play("true", map);
        /** Checks if the "thanos_res" view is being used by the controller */
        assertThat(view, is("thanos_res"));
        /** Checks if msg2 exists */
        assertThat(map.containsKey("msg2"), is(true));
        /** Finally checks if msg2 matches the string "Congratulations!!" */
        assertThat(map.get("msg2"), is("Congratulations!!"));
    }

    // Test for thanos app (response dead)
    @Test
    public void testThanos_res_dead() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String view = controller.play("false", map);
        /** Checks if the "thanos_res" view is being used by the controller */
        assertThat(view, is("thanos_res"));
        /** Checks if msg2 exists */
        assertThat(map.containsKey("msg2"), is(true));
        /** Finally checks if msg2 matches the string "You would dead :(" */
        assertThat(map.get("msg2"), is("You would dead :("));
    }
}
