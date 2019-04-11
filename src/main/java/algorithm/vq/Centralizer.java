package algorithm.vq;

import java.util.Collection;

@FunctionalInterface
public interface Centralizer<Point> {
    
    Point centre(Collection<Point> points);
}
