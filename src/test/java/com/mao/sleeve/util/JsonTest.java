package com.mao.sleeve.util;


import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;

/**
 * @ClassName: JsonTest
 * @Description: TODO
 * @Author 毛毛
 * @CreateDate 2021/11/11/周四 18:05
 * @Version: v1.0
 */
//@SpringBootTest(classes = SleeveApplication.class)
public class JsonTest {
    @Test
    public void test() {
        Optional<Object> optional = Optional.ofNullable("1");
        //System.out.println(optional);
        optional.map(String::valueOf).ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //String next = scanner.next();
        System.out.println(Math.round(5.0/7 * 100));
        HashMap hashMap = new HashMap<String, String>();
        //hashMap.get()
        //scanner.close();
    }

    public int longestCommonSubsequence(String text1, String text2) {
        // if(text1 == null || text2 == null) return 0;
        // if(text1.length == 0 || text2.length == 0) return 0;
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        char[] rows = chars1;
        char[] cols = chars2;
        if (chars1.length < chars2.length) {
            rows = chars2;
            cols = chars1;
        }
        int[] dp = new int[cols.length+1];


        for (int row = 1; row <= rows.length; row++) {
            int curr = 0;
            for (int col = 1; col <= cols.length; col++) {
                int leftTop = curr;
                curr = dp[col];
                if (rows[row - 1] == cols[col - 1])
                    dp[col] = leftTop + 1;
                else {
                    dp[col] = Math.max(dp[col - 1], dp[col]);
                }
            }
        }
        return dp[cols.length];
    }
}
