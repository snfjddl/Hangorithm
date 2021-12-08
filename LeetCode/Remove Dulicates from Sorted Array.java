import java.util.*;
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = -101, cnt = 0;
        for(int num : nums) {
            if(num > i) {
                i = num;
                nums[cnt] = i;
                cnt++;
            }
        }
            
        return cnt;
    }
}