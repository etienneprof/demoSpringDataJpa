package fr.ovalt.demo_spring_data.bizarre;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationATesterTest {

    @Test
    void additionner_5et2_retourne7() {
        ApplicationATester app = new ApplicationATester();
        int result = app.additionner(5, 2);
        assertEquals(7, result);
    }
}