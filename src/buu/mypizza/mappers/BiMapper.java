package buu.mypizza.mappers;

import java.util.List;

/**
 *
 * @author nazar
 */
public interface BiMapper<F1, F2, T> {

    T map(F1 from1, F2 form2);
    
    List<T> mapList(List<F1> fromList1, List<F2> fromList2);
    
}
