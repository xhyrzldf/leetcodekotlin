package com.matrix.array

import java.util.*

/**
 * leetcodeKotlin.
 * Description : Given an array S of n integers,
 * are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * 翻译 : 给定一个由N个数字组成的数组S，里面存在这样的ABC三个数使得A+B+C=0吗？
 * 找到所有的解(不重复，例如1,3,5与5,1,3视作重复的)
 * <p>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * @Date : 2018/3/20 22:51
 * @author : Matrix [xhyrzldf@foxmail.com]
 */

/**
 * 解决思路
 * 1.将数组排序
 * 2.接着对每一个数进行一次循环 每一个循环中的问题就变成了 一个有序数组的two sum问题，可以参考TwoSumII_167
 * 3.最终时间复杂度为O(n^2)
 * 优化思路
 * 1.跳过相同的数字，因为题目中说明不需要给出重复的答案
 * 2.当遍历的nums[i]大于0的时候，很明显后面的数字已经不会有结果了，直接break
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    Arrays.sort(nums)
    val res = LinkedList<List<Int>>()
    for (i in 0 until nums.size - 2) {
        //优化点2. 跳过nums[i]>0的数字
        if (nums[i] > 0) break
        //优化点1. 跳过相同的数字,第二个条件的 i>0 可以省略
        if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
            //问题简化为twoSum2 从nums[i+1]到末尾 寻找和为 0 - nums[i]的两个数
            var lo = i + 1
            var hi = nums.size - 1
            val sum = 0 - nums[i]
            while (lo < hi) {
                when {
                    nums[lo] + nums[hi] == sum -> {
                        //如果寻找到则 将答案存入集合中，跳过相同的数字，寻找下一组数字
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]))
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--
                        lo++
                        hi--
                        //否则就移动下标继续寻找
                    }
                    nums[lo] + nums[hi] < sum -> lo++
                    else -> hi--
                }
            }
        }
    }
    return res
}

fun main(args: Array<String>) {
    //  -5,0,1,2,3,4,5,7,9
    // expect answer [-5, 0, 5] [-5, 1, 4] [-5, 2, 3]
    threeSum(intArrayOf(0, 3, 1, 9, 2, -5, 4, 7, 5)).forEach { println(it) }
}
