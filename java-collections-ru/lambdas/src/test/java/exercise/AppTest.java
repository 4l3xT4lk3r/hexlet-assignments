package exercise;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {
    @Test
    void testImageIsNull() {
        String[][] image = null;
        String[][] result = App.enlargeArrayImage(image);
        String[][] expected = new String[0][];
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testImageIsEmpty() {
        String[][] image = {};
        String[][] result = App.enlargeArrayImage(image);
        String[][] expected = {};
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testImageIsEmpty2() {
        String[][] image = {{}, {}, {}, {}};
        String[][] result = App.enlargeArrayImage(image);
        String[][] expected = {};
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testImageIsOneElement() {
        String[][] image = {{"*"}};
        String[][] result = App.enlargeArrayImage(image);
        String[][] expected = {{"*", "*"}, {"*", "*"}};
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testImageIsTwoElements() {
        String[][] image = {{"*"}, {"*"}};
        String[][] result = App.enlargeArrayImage(image);
        String[][] expected = {
                {"*", "*"},
                {"*", "*"},
                {"*", "*"},
                {"*", "*"},
        };
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testImageExample1() {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };
        String[][] result = App.enlargeArrayImage(image);
        String[][] expected = {
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", " ", " ", " ", " ", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*"},
        };
        assertThat(result).isEqualTo(expected);
    }
}

// END
