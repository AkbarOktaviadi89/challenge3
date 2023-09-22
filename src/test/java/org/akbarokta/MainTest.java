package org.akbarokta;

import org.akbarokta.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void getInputIntTest_Positive() {
        //assume that the input is 1
        Integer actual = Main.getInputInt();
        assertEquals(1, actual);
    }

    @Test
    public void getInputIntTest_Negative() {
        //assume that the input is String
        Integer actual = Main.getInputInt();
        assertEquals(-1, actual);
    }

    @Test
    public void HandleInputError_Positif() {
        //assume that the input is "Y"
        Integer actual = Main.handleInputError();
        assertEquals(-1, actual);
    }

    @Test
    public void HandleInputError_Negatif() {
        //assume that the input is "N"
        Integer actual = Main.getInputInt();
        assertEquals(0, actual);
    }
}