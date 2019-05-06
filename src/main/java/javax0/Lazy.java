package javax0;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {

    final Supplier<T> supplier;
    private boolean supplied = false;
    private T value;

    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy let(Supplier<T> supplier) {
        return new Lazy(supplier);
    }

    @Override
    public T get() {
        if (supplied) {
            return value;
        }
        supplied = true;
        return value = supplier.get();
    }

    public static Lazy.Sync let() {
        return let(null).new Sync();
    }

    public class Sync implements Supplier<T> {

        private volatile boolean synSupplied = false;
        private volatile T synValue;

        public Sync sync(Supplier<T> supplier) {
            return new Lazy(supplier).new Sync();
        }


        /**
         * Gets a result.
         *
         * @return a result
         */
        @Override
        public T get() {
            if (synSupplied) {
                return synValue;
            }
            synValue = supplier.get();
            synSupplied = true;
            return synValue;
        }
    }
}
