package com.qc.sort;

import java.util.Arrays;

import com.qc.utils.IOUtils;

/**
 * 希尔排序Shell's Sort
 * 又称“缩小增量排序”（Diminishing Increment Sort）
 * 直接插入排序的改进，不稳定
 * 
 * 基本思想
 * 先取一个小于n的整数d1作为第一个增量，把文件的全部记录分组。
 * 所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；
 * 然后，取第二个增量d2<d1重复上述的分组和排序，
 * 直至所取的增量  =1(  <  …<d2<d1)，即所有记录放在同一组中进行直接插入排序为止。
 * 该方法实质上是一种分组插入方法
 * 
 * 希尔排序是按照不同步长对元素进行插入排序，
 * 当刚开始元素很无序的时候，步长最大，所以插入排序的元素个数很少，速度很快；
 * 当元素基本有序了，步长很小，插入排序对于有序的序列效率很高。
 * 所以，希尔排序的时间复杂度会比o(n^2)好一些。
 * 
 * @author Administrator
 *
 */
public class ShellSort {
	
	public static void main(String[] args) {
		int[] array = shellSort(IOUtils.arrayInput());
		IOUtils.println("排序结果", Arrays.toString(array));
	}
	
	/**
	 * 希尔排序
	 * @param array
	 * @return
	 */
	public static int[] shellSort(int[] array){
		int gap = array.length;
		int j = 0;
		int temp = 0;
		while(true){
			gap = gap/2;
			for(int x=0;x<gap;x++){
				//对于每一组数据进行直接插入排序
				for(int i=x+gap;i<array.length;i=i+gap){//从第二个开始
					if(array[i]<array[i-gap]){
						temp = array[i];
						for(j=i-gap; j>=0 && temp<array[j]; j = j-gap){
							array[j+gap] = array[j];
						}
						array[j+gap] = temp;
					}
				}
			}
			IOUtils.println("gap="+gap,Arrays.toString(array));
			if(gap==1){
				break;
			}
		}
		return array;
	}
}
