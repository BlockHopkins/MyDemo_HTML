package com.qc.sort;

import java.util.Arrays;

import com.qc.utils.IOUtils;

/**
 * 快速排序（Quicksort）是对冒泡排序的一种改进。
 * 基本思想：
 * 	通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 	然后再按此方法对这两部分数据分别进行快速排序，
 * 	个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * @author Administrator
 *
 */
public class QuickSort {
	public static void main(String[] args) {
		int[] array = IOUtils.arrayInput();
		quickSort(array,0,array.length-1);
		IOUtils.println("排序结果：", Arrays.toString(array));
	}
	
	/**
	 * 快速排序
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void quickSort(int[] array, int start, int end){
		if(start>end){
			return;
		}
		int low = start;
		int high = end;
		int pivot = array[low];
		
		while(low<high){
			while(low<high && array[high]>=pivot){
				high--;
			}
			if(low<high){
				array[low]=array[high];
			}
			while(low<high && array[low]<=pivot){
				low++;
			}
			if(low<high){
				array[high]=array[low];
			}
		}
		array[low]=pivot;
		IOUtils.println(start+"到"+end, Arrays.toString(array));
		quickSort(array, start,low-1);
		quickSort(array, high+1, end);
	}
}
