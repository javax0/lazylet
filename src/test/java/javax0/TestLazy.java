package javax0;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;


@SuppressWarnings("PointlessBooleanExpression")
public class TestLazy {
    @Test
    void test1() {
        var ts = new TestSupport();
        if (false && ts.callMe()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        System.out.println("invoked " + ts.count + " times");
    }

    @Test
    void test2() {
        var ts = new TestSupport();
        var z = ts.callMe();
        if (false && z) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        System.out.println("invoked " + ts.count + " times");
    }

    @Test
    void test3() {
        final var ts = new TestSupport();
        Supplier<Boolean> z = Lazy.let(() -> ts.callMe());
        if (false && z.get()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        System.out.println("invoked " + ts.count + " times");
        z.get();
        System.out.println("invoked " + ts.count + " times");
        z.get();
        System.out.println("invoked " + ts.count + " times");
    }

    private static class TestSupport {
        int count = 0;

        boolean callMe() {
            count++;
            return true;
        }
    }
}
