package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }
        T max = (T) this.get(0);
        for (int i = 1; i < this.size(); i++) {
            if (comparator.compare((T) this.get(i), max) > 0) {
                max = (T) this.get(i);
            } else {
                continue;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }
        T max = (T) this.get(0);
        for (int i = 1; i < this.size(); i++) {
            if (c.compare((T) this.get(i), max) > 0) {
                max = (T) this.get(i);
            } else {
                continue;
            }
        }
        return max;
    }
}
