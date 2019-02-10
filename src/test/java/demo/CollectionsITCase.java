package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;

public class CollectionsITCase {
  public static Long id = 999L;
  public Comparator<String> c = new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
      return s1.compareTo(s2);
      // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
      // return lhs.customInt > rhs.customInt ? -1 : (lhs.customInt < rhs.customInt) ? 1 : 0;
    }
  };

  @Test
  public void testArrayList() {
    ArrayList<String> a = new ArrayList<String>();
    a.add(" ");
    a.add("test");
    a.add("hgkjasfdgh");
    a.add("rwetrtew");
    a.add("dsgfsaa");
    a.add("rwetgscx");
    a.add("test2");

    printArray(a);
    a.sort(c);
    printArray(a);
    Collections.sort(a);
    printArray(a);
  }

  private void printArray(ArrayList<?> arr) {
    arr.forEach(System.out::println);
  }

  @Test
  public void testHashMap() {
    ConcurrentHashMap<Integer, String> aMap = new ConcurrentHashMap<Integer, String>();
    aMap.put(1, "ena");
    aMap.put(2, "dyo");
    aMap.put(3, "tria");

    aMap.forEach((k, v) -> {
      System.out.println("HMap key: " + k + " value: " + v);
    });
  }

  @Test
  public void testHashSet() {
    HashSet<String> aSet = new HashSet<String>();
    aSet.add("dyo");
    aSet.add("ena");
    aSet.add("tria");

    aSet.forEach(v -> {
      System.out.println("HSet value: " + v);
    });
  }

  @Test
  public void testLinkedList() {
    LinkedList<String> aList = new LinkedList<String>();
    aList.add("ena");
    aList.add("dyo");
    aList.add("tria");

    aList.forEach(v -> {
      System.out.println("aList value: " + v);
    });
  }


  public void checkScheduledMessages() {
    for (String i : Arrays.asList("1", "2")) {
      System.out.println("value: " + i);
    }
  }

}
