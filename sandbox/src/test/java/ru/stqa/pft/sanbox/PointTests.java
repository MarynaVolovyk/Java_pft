package ru.stqa.pft.sanbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;
import ru.stqa.pft.sandbox.Square;

public class PointTests {

  @Test
  public void testPoint(){
    Point p1 = new Point(4, 7);
    Point p2 = new Point(2, 8);
    Assert.assertEquals(p1.distance(p2), 2.23606797749979);
  }

}




