package demo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Test;

public class ExerciseSimpleITCase {
  public static Long id = 99999L;

  public static int limit = 100000;
  public static int[] bigArrayOfInt = new int[limit];
  static {
    for (int i = 0; i < limit; i++) {
      bigArrayOfInt[i] = i;
    }
  }


  @Test
  public void ivTest() {
    int result = 0, i = 1000000;
    while (i >= 1) {
      i /= 2;
      result++;
    }
    System.out.println("** result " + result);
  }

  @Test
  public void countDivisibleTest() {
    assertEquals(3, countDivisible(6, 11, 2));
    assertEquals(20, countDivisible(11, 345, 17));
    assertEquals(1, countDivisible(0, 0, 11));
    assertEquals(0, countDivisible(1, 1, 11));
    assertEquals(1, countDivisible(10, 10, 5));
    assertEquals(0, countDivisible(10, 10, 7));
    assertEquals(0, countDivisible(10, 10, 20));
    assertEquals(1, countDivisible(10, Integer.MAX_VALUE, Integer.MAX_VALUE));
    assertEquals(12345, countDivisible(101, 123456789, 10000));

  }

  public int countDivisible(int A, int B, int K) {
    return (B / K) - (A / K) + (A % K == 0 ? 1 : 0);
  }

  @Test
  public void passingCarsTest() {
    assertEquals(5, passingCars(new int[] {0, 1, 0, 1, 1}));
  }

  public int passingCars(int[] A) {
    int result = 0, eastbound = 0;
    for (int i : A) {
      if (i == 0) {
        eastbound++;
      }
      if (eastbound > 0) {
        if (i == 1) {
          result += eastbound;
          if (result > 1000000000) {
            return -1;
          }
        }
      }
    }

    return result;
  }

  @Test
  public void maxCountersTest() {
    assertTrue("test failure",
        Arrays.equals(new int[] {3, 2, 2, 4, 2}, maxCounters(5, new int[] {3, 4, 4, 6, 1, 4, 4})));
  }

  /*
   * You are given N counters, initially set to 0, and you have two possible operations on them:
   * 
   * increase(X) − counter X is increased by 1, max counter − all counters are set to the maximum
   * value of any counter.
   * 
   * A non-empty array A of M integers is given. This array represents consecutive operations:
   * 
   * if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X), if A[K] = N + 1 then
   * operation K is max counter.
   */
  public int[] maxCounters(int N, int[] A) {
    int[] result = new int[N];
    int min = 0, max = 0;
    for (int k : A) {
      if (k > N) {
        min = max;
      } else {
        int current = result[k - 1];
        if (current < min)
          current = min;
        result[k - 1] = ++current;
        if (current > max) {
          max = current;
        }
      }
    }
    for (int i = 0; i < N; i++) {
      if (result[i] < min)
        result[i] = min;
    }

    return result;
  }

  @Test
  public void frogRiverOneTest() {
    assertEquals(0, frogRiverOne(1, new int[] {1}));
    assertEquals(-1, frogRiverOne(2, new int[] {1}));
    assertEquals(6, frogRiverOne(5, new int[] {1, 3, 1, 4, 2, 3, 5, 4}));

    assertEquals(limit - 1, frogRiverOne(limit - 1, bigArrayOfInt));
  }

  /*
   * A small frog wants to get to the other side of a river. The frog is initially located on one
   * bank of the river (position 0) and wants to get to the opposite bank (position X+1). Leaves
   * fall from a tree onto the surface of the river.
   * 
   * You are given an array A consisting of N integers representing the falling leaves. A[K]
   * represents the position where one leaf falls at time K, measured in seconds.
   * 
   * The goal is to find the earliest time when the frog can jump to the other side of the river.
   * The frog can cross only when leaves appear at every position across the river from 1 to X (that
   * is, we want to find the earliest moment when all the positions from 1 to X are covered by
   * leaves). You may assume that the speed of the current in the river is negligibly small, i.e.
   * the leaves do not change their positions once they fall in the river.
   */
  public int frogRiverOne(int X, int[] A) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    for (int i = 0; i < A.length; i++) {
      map.putIfAbsent(A[i], i);
    }
    int result = -1;
    for (int i = 1; i <= X; i++) {
      Integer leaf = map.get(i);
      if (leaf == null) {
        return -1;
      }
      if (leaf.intValue() > result) {
        result = leaf;
      }
    }

    return result;
  }

  @Test
  public void tapeEquilibriumTest() {
    assertEquals(200, tapeEquilibrium(new int[] {100, -100}));
    assertEquals(6, tapeEquilibrium(new int[] {3, -3}));
    assertEquals(3, tapeEquilibrium(new int[] {3, -1, 2, -4, -3}));

    assertEquals(1, tapeEquilibrium(new int[] {3, 1, 2, 4, 3}));
    assertEquals(552, tapeEquilibrium(bigArrayOfInt));
  }

  /*
   * A non-empty array A consisting of N integers is given. Array A represents numbers on a tape.
   * 
   * Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ...,
   * A[P − 1] and A[P], A[P + 1], ..., A[N − 1].
   * 
   * The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] +
   * A[P + 1] + ... + A[N − 1])|
   * 
   * In other words, it is the absolute difference between the sum of the first part and the sum of
   * the second part.
   */
  public int tapeEquilibrium(int[] A) {
    int[] cumulative = new int[A.length];
    int total = 0;
    int result = Integer.MAX_VALUE;
    for (int i = 0; i < A.length; i++) {
      total += A[i];
      cumulative[i] = total;
    }
    for (int n = 0; n < cumulative.length - 1; n++) {
      int diff = Math.abs(cumulative[n] - (total - cumulative[n]));
      if (diff < result) {
        result = diff;
      }
    }

    return result;
  }

  @Test
  public void permMissingElemTest() {
    bigArrayOfInt[0] = limit + 1;

    assertEquals(limit, permMissingElem(bigArrayOfInt));
    assertEquals(2, permMissingElem(new int[] {1}));
    assertEquals(1, permMissingElem(new int[] {}));
    assertEquals(3, permMissingElem(new int[] {4, 1, 2}));

  }
  /*
   * Find the missing element in a given permutation.
   */
  public int permMissingElem(int[] A) {
    HashSet<Integer> set = new HashSet<Integer>();
    for (int n : A) {
      set.add(n);
    }
    // System.err.println(set.size());
    int n = 1;
    while (set.contains(n)) {
      n++;
    }
    return n;
  }

  /*
   * A small frog wants to get to the other side of the road. The frog is currently located at
   * position X and wants to get to a position greater than or equal to Y. The small frog always
   * jumps a fixed distance, D.
   * 
   * Count the minimal number of jumps that the small frog must perform to reach its target.
   */
  public int frogJump(int X, int Y, int D) {
    return (int) Math.ceil((double) (Y - X) / D);
  }


  @Test
  public void oddOccurrencesInArrayTest() {
    assertEquals(7, oddOccurrencesInArray(new int[] {9, 3, 9, 3, 9, 7, 9}));

  }
  /*
   * The array contains an odd number of elements, and each element of the array can be paired with
   * another element that has the same value, except one.
   * 
   * Return the one element that is left unpaired.
   */
  public int oddOccurrencesInArray(int[] A) {
    int result = 0;
    Arrays.sort(A);
    for (int i = 0; i < A.length; i++) {
      if (i % 2 == 0) {
        result = A[i];
      } else if (result != A[i]) {
        return result;
      }
    }
    return result;
  }

  /*
   * move each element of array A, K positions to the right cycling to start of array if position is
   * greater than array length
   */
  public int[] arrayCyclicRotation(int[] A, int K) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    int len = A.length;
    for (int i = 0; i < len; i++) {
      map.put(i, A[i]);
    }
    int[] result = new int[len];
    map.forEach((idx, val) -> {
      result[(idx + K) % len] = val;
    });

    return result;
  }

  @Test
  public void cardGameTest() {
    int[] A = {1, 2, 4, 3};
    int[] B = {1, 3, 2, 3};
    assertEquals(2, cardGame(A, B));

    int[] c = {3, 2, 1, 6, 5};
    int[] d = {4, 2, 1, 3, 3};
    assertEquals(3, cardGame(c, d));

    System.err.println("limit=" + limit + ",  result=" + cardGame(bigArrayOfInt, bigArrayOfInt));
    assertEquals(limit, cardGame(bigArrayOfInt, bigArrayOfInt));
  }

  public int cardGame(int[] A, int[] B) {
    int result = 1;

    HashSet<Integer> set = new HashSet<Integer>();
    for (int i = 0; i < A.length; i++) {
      if (A[i] == B[i])
        set.add(A[i]);
    }

    while (set.contains(result)) {
      result++;
    }
    return result;
  }


  @Test
  public void bettingTest() {
    assertEquals(2147483000, betting(2147483001, 0));
    assertEquals((2147483001 + 1) / 2, betting(2147483001, 1));
    assertEquals(Integer.MAX_VALUE - 1, betting(Integer.MAX_VALUE, 0));
  }
  public int betting(int N, int K) {
    int result = 0;
    while (N > 1) {
      if (K == 0) {
        return result + N - 1;
      }
      else if (N % 2 == 1) {
        N--;
      } else {
        N = N / 2;
        K--;
      }
      result++;
    }

    return result;
  }

  @Test
  public void binaryGapTest() {

    assertEquals(29, BinaryGap(Integer.parseInt("1000000000000000000000000000001", 2)));
    assertEquals(0, BinaryGap(Integer.parseInt("1000000000000000000000000000000", 2)));
    assertEquals(0, BinaryGap(Integer.MAX_VALUE));
    assertEquals(1, BinaryGap(1000));
    assertEquals(2, BinaryGap(1001));
    assertEquals(1, BinaryGap(1002));
    assertEquals(1, BinaryGap(1003));

  }

  public int BinaryGap(int N) {
    int result = 0;
    int last = 0;
    char[] bits = Integer.toBinaryString(N).toCharArray();
    // System.err.println(N + " Bin=" + Integer.toBinaryString(N) + " len=" + bits.length);

    for (int i = 1; i < bits.length; i++) {
      if (bits[i] == '0')
        continue;

      if (i - last - 1 > result) {
        result = i - last - 1;
      }
      last = i;
    }
    return result;
  }



  public int missingInteger(int[] A) {
    HashSet<Integer> set = new HashSet<Integer>();
    for (int n : A) {
      if (n > 0)
        set.add(n);
    }
    System.err.println(set.size());
    int n = 1;
    while (set.contains(n)) {
      n++;
    }
    return n;
  }

}
