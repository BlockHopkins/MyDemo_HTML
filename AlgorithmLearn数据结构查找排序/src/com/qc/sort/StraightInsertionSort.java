package com.qc.sort;

import java.util.Arrays;

import com.qc.utils.IOUtils;

/**
 * 直接插入排序
 * 对数组中n个元素逐一进行插入排序，
 * 对于第n个元素，认为前n-1个元素为已排序的有序数组；将这第n个元素插到前n-1个有序数组的正确位置。
 * 
 * 基本思想：
 * 	每次从无序表中取出第一个元素，把它插入到有序表的合适位置，使有序表仍然有序。
 * 	第一趟比较前两个数，然后把第二个数按大小插入到有序表中；
 * 	第二趟把第三个数据与前两个数从后向前扫描，把第三个数按大小插入到有序表中；
 * 	依次进行下去，进行了(n-1)趟扫描以后就完成了整个排序过程。
 * @author Administrator
 *
 */
public class StraightInsertionSort {
	
	public static void main(String[] args) {
		int[] array = straightInsertionSort(IOUtils.arrayInput());
		IOUtils.println("排序结果", Arrays.toString(array));
	}
	
	/**
	 * 直接插入排序
	 * @param array
	 * @return
	 */
	public static int[] straightInsertionSort(int[] array){
		int temp = 0;
		int j = 0;
		for(int i=1;i<array.length;i++){//从第二个开始遍历每一个元素，认为第一个元素为一个“有序数组”
			if(array[i-1]>array[i]){//如果当前比前一个小，则应该插到前面(有序部分)的正确位置
				temp = array[i]; // temp表示当前元素
				for(j=i-1;j>=0 && temp<array[j];j--){
					//如果temp小于array[j]，则将array[j]后移，即temp前移
					array[j+1] = array[j];
				}
				//将temp前移到正确位置
				array[j+1] = temp;
			}
			IOUtils.println("第"+i+"个元素", Arrays.toString(array));
		}
		return array;
	}
}
