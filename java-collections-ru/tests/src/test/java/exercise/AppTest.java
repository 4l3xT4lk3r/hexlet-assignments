package exercise;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    List<Integer> list;

    @BeforeEach
    void initList() {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    void take_ListSizeGreaterThanCount() {
        // BEGIN
        Assertions.assertEquals(App.take(list, 2).size(), 2);
        // END
    }

    @Test
    void take_ListSizeEqualsCount() {
        // BEGIN
        Assertions.assertEquals(App.take(list, 4).size(), 4);
        // END
    }

    @Test
    void take_CountEqualsZero() {
        // BEGIN
        Assertions.assertEquals(App.take(list, 0).size(), 0);
        // END
    }

    @Test
    void take_ListSizeLessThanCount() {
        // BEGIN
        Assertions.assertEquals(App.take(list, 8).size(), 4);
        // END
    }
}
