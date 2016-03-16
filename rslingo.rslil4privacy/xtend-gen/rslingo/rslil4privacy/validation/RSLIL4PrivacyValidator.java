/**
 * generated by Xtext
 */
package rslingo.rslil4privacy.validation;

import java.util.Calendar;
import org.eclipse.xtext.validation.Check;
import rslingo.rslil4privacy.rSLIL4Privacy.Date;
import rslingo.rslil4privacy.rSLIL4Privacy.Month;
import rslingo.rslil4privacy.rSLIL4Privacy.RSLIL4PrivacyPackage;
import rslingo.rslil4privacy.validation.AbstractRSLIL4PrivacyValidator;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class RSLIL4PrivacyValidator extends AbstractRSLIL4PrivacyValidator {
  @Check
  public void checkDate(final Date date) {
    boolean _or = false;
    int _day = date.getDay();
    boolean _lessThan = (_day < 1);
    if (_lessThan) {
      _or = true;
    } else {
      int _day_1 = date.getDay();
      boolean _greaterThan = (_day_1 > 31);
      _or = _greaterThan;
    }
    if (_or) {
      this.error("Day should be between 1 and 31", 
        RSLIL4PrivacyPackage.Literals.DATE__DAY);
    } else {
      Month _month = date.getMonth();
      String _name = _month.getName();
      boolean _equals = _name.equals("Feb");
      if (_equals) {
        boolean _and = false;
        int _year = date.getYear();
        boolean _isLeapYear = this.isLeapYear(_year);
        if (!_isLeapYear) {
          _and = false;
        } else {
          int _day_2 = date.getDay();
          boolean _greaterThan_1 = (_day_2 > 29);
          _and = _greaterThan_1;
        }
        if (_and) {
          int _year_1 = date.getYear();
          String _plus = ("Feb " + Integer.valueOf(_year_1));
          String _plus_1 = (_plus + " only has 29 days");
          this.error(_plus_1, RSLIL4PrivacyPackage.Literals.DATE__DAY);
        } else {
          boolean _and_1 = false;
          int _year_2 = date.getYear();
          boolean _isLeapYear_1 = this.isLeapYear(_year_2);
          boolean _not = (!_isLeapYear_1);
          if (!_not) {
            _and_1 = false;
          } else {
            int _day_3 = date.getDay();
            boolean _greaterThan_2 = (_day_3 > 28);
            _and_1 = _greaterThan_2;
          }
          if (_and_1) {
            int _year_3 = date.getYear();
            String _plus_2 = ("Feb " + Integer.valueOf(_year_3));
            String _plus_3 = (_plus_2 + " only has 28 days");
            this.error(_plus_3, RSLIL4PrivacyPackage.Literals.DATE__DAY);
          }
        }
      } else {
        boolean _or_1 = false;
        boolean _or_2 = false;
        boolean _or_3 = false;
        Month _month_1 = date.getMonth();
        String _name_1 = _month_1.getName();
        boolean _equals_1 = _name_1.equals("Apr");
        if (_equals_1) {
          _or_3 = true;
        } else {
          Month _month_2 = date.getMonth();
          String _name_2 = _month_2.getName();
          boolean _equals_2 = _name_2.equals("Jun");
          _or_3 = _equals_2;
        }
        if (_or_3) {
          _or_2 = true;
        } else {
          Month _month_3 = date.getMonth();
          String _name_3 = _month_3.getName();
          boolean _equals_3 = _name_3.equals("Sep");
          _or_2 = _equals_3;
        }
        if (_or_2) {
          _or_1 = true;
        } else {
          Month _month_4 = date.getMonth();
          String _name_4 = _month_4.getName();
          boolean _equals_4 = _name_4.equals("Nov");
          _or_1 = _equals_4;
        }
        if (_or_1) {
          Month _month_5 = date.getMonth();
          String _name_5 = _month_5.getName();
          String _plus_4 = (_name_5 + " ");
          int _year_4 = date.getYear();
          String _plus_5 = (_plus_4 + Integer.valueOf(_year_4));
          String _plus_6 = (_plus_5 + "only has 30 days");
          this.error(_plus_6, RSLIL4PrivacyPackage.Literals.DATE__DAY);
        }
      }
    }
  }
  
  public boolean isLeapYear(final int year) {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, year);
    int _actualMaximum = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    return (_actualMaximum > 365);
  }
}
