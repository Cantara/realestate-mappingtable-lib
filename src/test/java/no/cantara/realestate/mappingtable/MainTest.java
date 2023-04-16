package no.cantara.realestate.mappingtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void loadPlugins() {
        Main main = new Main();
        assertTrue(main.loadPlugins());
    }
}