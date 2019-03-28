package buu.mypizza.mappers;

import java.util.List;

/**
 *
 * @author nazar
 */
public interface Mapper<F, T>{
    
    T map(F from);
    
    List<T> mapList(List<F> fromList);
    
}
