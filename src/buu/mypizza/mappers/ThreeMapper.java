package buu.mypizza.mappers;

/**
 *
 * @author nazar
 */
public interface ThreeMapper<F1, F2, F3, T> {
    
    T map(F1 from1, F2 from2, F3 from3);
    
}
