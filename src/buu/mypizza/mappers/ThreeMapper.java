package buu.mypizza.mappers;

import java.util.List;

/**
 *
 * @author nazar
 */
public interface ThreeMapper<F1, F2, F3, T> {
    
    T map(F1 from1, F2 from2, F3 from3);
    
    List<T> mapList(List<F1> from1, F2 from2, F3 from3);
    
}
