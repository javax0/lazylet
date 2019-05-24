package javax0;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {

    final private Supplier<T> supplier;
    private boolean supplied = false;
    private T value;

    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> let(Supplier<T> supplier) {
        return new Lazy(supplier);
    }

    public static <T> Lazy<T>.Sync<T> sync(Supplier<T> supplier) {
        return new Lazy<>(supplier).new Sync<T>();
    }

    @Override
    public T get() {
        if (supplied) {
            return value;
        }
        supplied = true;
        return value = supplier.get();
    }

    public class Sync<T> implements Supplier<T> {

        private volatile boolean supplied = false;
        private volatile boolean preSupplied = false;
        private volatile T value;

        /**
         * Gets a result.
         *
         * @return a result
         */
        @Override
        public T get() {
            if (supplied) {
                return value;
            }
            synchronized (this) {
                if (preSupplied) {
                    return value;
                }
                preSupplied = true;
                value = (T) supplier.get();
                supplied = true;
                return value;
            }
        }
    }
}
