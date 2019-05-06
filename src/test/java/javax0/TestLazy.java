package javax0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("PointlessBooleanExpression")
class TestLazy {
    @Test
    @DisplayName("Demonstrate shortcut without lazy support")
    void test1() {
        var ts = new TestSupport();
        if (false && ts.callMe()) {
            Assertions.fail();
        }
        Assertions.assertEquals(0, ts.count);
    }

    @Test
    @DisplayName("Demonstrate missing shortcut without lazy")
    void test2() {
        var ts = new TestSupport();
        var z = ts.callMe();
        if (false && z) {
            Assertions.fail();
        }
        Assertions.assertEquals(1, ts.count);
    }

    @Test
    @DisplayName("Demonstrate Lazy to be invoked only after shortcut and only once")
    void test3() {
        final var ts = new TestSupport();
        var z = Lazy.let(ts::callMe);
        if (false && z.get()) {
            Assertions.fail();
        }
        Assertions.assertEquals(0, ts.count);
        z.get();
        Assertions.assertEquals(1, ts.count);
        z.get();
        Assertions.assertEquals(1, ts.count);
    }

    @Test
    @DisplayName("Demonstrate Lazy sync to be invoked only after shortcut and only once")
    void test4() {
        final var ts = new TestSupport();
        var z = Lazy.sync(ts::callMe);
        if (false && z.get()) {
            Assertions.fail();
        }
        Assertions.assertEquals(0, ts.count);
        z.get();
        Assertions.assertEquals(1, ts.count);
        z.get();
        Assertions.assertEquals(1, ts.count);
    }

    private static class TestSupport {
        int count = 0;

        boolean callMe() {
            count++;
            return true;
        }
    }
}
