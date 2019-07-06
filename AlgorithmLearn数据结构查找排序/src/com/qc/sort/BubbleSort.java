package com.qc.sort;

import java.util.Arrays;

import com.qc.utils.IOUtils;

/**
 * 冒泡排序
 * 相邻元素间比较，相等不交换，稳定排序算法
 * @author Administrator
 *
 */
public class BubbleSort {
	
	public static void main(String[] args) {
		int[] result = bubbleSort(IOUtils.arrayInput());
		IOUtils.println("排序结果：", Arrays.toString(result));
	}
	
	/**
	 * 冒泡排序，从小到大
	 * @param array
	 * @return
	 */
	public static int[] bubbleSort(int[] array){
		int temp = 0;
		for(int i=array.length-1; i>0; i--){
			for(int j=0; j<i; j++){
				if(array[j]>array[j+1]){
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
			IOUtils.println("第"+(array.length-i)+"趟：", Arrays.toString(array));
		}
		return array;
	}
}
