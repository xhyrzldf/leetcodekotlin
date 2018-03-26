package com.matrix.sort

/**
 * leetcode-kotlin.
 * Description : 快速排序
 * @Date : 2018/3/27 0:29
 * @author : Matrix [xhyrzldf@foxmail.com]
 */

/**
 * 核心思想 list(<x) + list(==x) + list(>X)
 */
fun quickSort(list: List<Int>): List<Int> = when (list.isEmpty()) {
    true -> emptyList()
    else -> quickSort(list.filter { it < list.first() }) +
            list.filter { it == list.first() } +
            quickSort(list.filter { it > list.first() })
}

fun main(args: Array<String>) {
    val list = listOf(5, 8, 9, 1, 10, 4, 19, 20, 3, 3, 2)
    println("new list : ${quickSort(list)}")
    println("old list : $list")
}