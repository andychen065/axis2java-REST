package cn.edu.xidian;

public class Salary {
  public int getSalary(String name) {
    if ( name.equals("zhangsan") ) {
      return 3000;
    }
    else if ( name.equals("lisi") ) {
      return 4000;
    }
    else 
      return 5000;
  }
}