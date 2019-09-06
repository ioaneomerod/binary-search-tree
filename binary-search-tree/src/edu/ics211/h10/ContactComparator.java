package edu.ics211.h10;

import java.util.Comparator;

/** A class that compares contacts. 
 * @author IoaneOmerod
 *
 */
public class ContactComparator implements Comparator<Contact> {

  /** Constructor of ContactComparator. 
   * 
   */
  public ContactComparator() {
    
  }


  /* (non-Javadoc)
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(Contact o1, Contact o2) {
    int comp = o1.getLastName().compareTo(o2.getLastName()); 
    if (comp == 0) {
      return o1.getFirstName().compareTo(o2.getFirstName());
    } else {
      return comp; 
    }
  }

}
