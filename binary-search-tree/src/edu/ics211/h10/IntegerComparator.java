package edu.ics211.h10;

import java.util.Comparator;

/** A class to compare integers. 
 * @author IoaneOmerod
 *
 */
public class IntegerComparator implements Comparator<Integer> {

  /** The IntegerComparator Constructor. 
   * 
   */
  public IntegerComparator() {
    
  }


  /* (non-Javadoc)
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(Integer o1, Integer o2) {
    if (o1 < o2) {
      return -1; 
    } else if (o1 > o2) {
      return 1; 
    } else {
      return 0;
    }
  }

}
