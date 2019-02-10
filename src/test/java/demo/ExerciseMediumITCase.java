package demo;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.Test;

public class ExerciseMediumITCase {
  public static Long id = 99999L;
  public Comparator<String> c = new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
      return s1.compareTo(s2);
      // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
      // return lhs.customInt > rhs.customInt ? -1 : (lhs.customInt < rhs.customInt) ? 1 : 0;
    }
  };
  public static int limit = 100000;
  public static int[] bigArrayOfInt = new int[limit];
  static {
    for (int i = 0; i < limit; i++) {
      bigArrayOfInt[i] = i;
    }
  }

  @Test
  public void genomicRangeQueryTest() {
    assertTrue(Arrays.equals(new int[] {2, 4, 1},
        genomicRangeQuery("CAGCCTA", new int[] {2, 5, 0}, new int[] {4, 5, 6})));
  }

  /*
   * A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which
   * correspond to the types of successive nucleotides in the sequence. Each nucleotide has an
   * impact factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of
   * 1, 2, 3 and 4, respectively. You are going to answer several queries of the form: What is the
   * minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?
   * 
   * The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N
   * characters. There are M queries, which are given in non-empty arrays P and Q, each consisting
   * of M integers. The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of
   * nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).
   */

  public int[] genomicRangeQuery(String S, int[] P, int[] Q) {
    int[][] prefixSums = new int[3][S.length()+1];
    int i = 0;
    // A==65, C==67, G==71, T==84
    for (byte b: S.getBytes()) {
      int a = 0, c = 0, g = 0;
      if (b == 65) {
        a = 1;
      } else if (b == 67) {
        c = 1;
      } else if (b == 71) {
        g = 1;
      }

      // calculate prefix sums. Look explanation
      // https://codility.com/media/train/3-PrefixSums.pdf
      prefixSums[0][i + 1] = prefixSums[0][i] + a;
      prefixSums[1][i + 1] = prefixSums[1][i] + c;
      prefixSums[2][i + 1] = prefixSums[2][i] + g;
      i++;
    }

    int M = P.length;
    int[] result = new int[M];

    for (int x = 0; x < M; x++) {
      int fromIndex = P[x];
      // we need to add 1 to Q[x],
      // because our prefixSums[0][0], prefixSums[1][0] and prefixSums[2][0]
      // have 0 values by default, look above prefixSums[0][i+1] = prefixSums[0][i] + a;
      int toIndex = Q[x] + 1;
      if (prefixSums[0][toIndex] - prefixSums[0][fromIndex] > 0) {
        result[x] = 1;
      } else if (prefixSums[1][toIndex] - prefixSums[1][fromIndex] > 0) {
        result[x] = 2;
      } else if (prefixSums[2][toIndex] - prefixSums[2][fromIndex] > 0) {
        result[x] = 3;
      } else {
        result[x] = 4;
      }

    }
    return result;
  }

}
