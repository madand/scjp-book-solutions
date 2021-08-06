import java.util.Objects;

final class Pair {
    final private Object a;
    final private Object b;

    public Pair(Object a, Object b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return a + " " + b;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Pair) {
            var otherPair = (Pair) obj;
            // Objects.equals helps us cleanly handle possible nulls.
            return Objects.equals(a, otherPair.a) && Objects.equals(b, otherPair.b);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
