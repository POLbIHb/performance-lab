package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Long res = Long.MAX_VALUE;
        int curSum = 0;
        int sum = 0;

        List<Integer> nums = readNumbersFromFile(args[0]);
        int n = nums.size();
        Collections.sort(nums);

        for (Integer num : nums) {
            sum += num;
        }

        for(int i = 0; i<n;++i){
            int k = nums.get(i);
            curSum+=k;
            res = Math.min(res, 2*k*(i+1)-n*k+sum-2*curSum);
        }
        System.out.println(res);
    }
    private static List<Integer> readNumbersFromFile(String fileName) {
        List<Integer> nums = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                int num = Integer.parseInt(scanner.nextLine());
                nums.add(num);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return nums;
    }
}