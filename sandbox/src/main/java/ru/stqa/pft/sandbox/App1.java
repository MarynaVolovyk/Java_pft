package ru.stqa.pft.sandbox;

public class App1 {
  public static void main(String[] args) {

    Point p1 = new Point(4, 7);
    Point p2 = new Point(2, 8);

    System.out.println("Расстояние между точками равно " + p1.distance(p2));

  }


}
