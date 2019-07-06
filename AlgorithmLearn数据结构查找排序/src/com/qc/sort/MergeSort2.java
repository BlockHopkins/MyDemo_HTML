package com.qc.sort;

import java.util.Arrays;

import com.qc.utils.IOUtils;

/**
 * 归并排序的第二种实现，不在原序列上排，用拆分到子数组的形式。
 * @author Administrator
 *
 */
public class MergeSort2 {
	
	public static void main(String[] args) {
		int[] array = beginMergeSort(IOUtils.arrayInput());
		IOUtils.println("排序结果：", Arrays.toString(array));
	}
	/**
	 * 归并排序，
	 * 使用递归，将序列切分成两半直到每个元素为一个子序列，再两两合并排序，直到合成一个完整的序列。
	 * @param array
	 * @return
	 */
	public static int[] beginMergeSort(int[] array){
		return mergeSort(array);
	}
	
	private static int[] mergeSort(int[] array){
		if(array.length>1){
			int mid = array.length/2;//分成两半
			int[] array1 = mergeSort(Arrays.copyOfRange(array, 0, mid));//递归对第一部分排序
			int[] array2 = mergeSort(Arrays.copyOfRange(array, mid, array.length));//递归对第二部分排序
			return merge(array1,array2);//将两部分合并
		}
		return array;
	}
	
	private static int[] merge(int[] array1, int[] array2){
		int i=0;//第一个子序列当前位置
		int j=0;//第二个子序列当前位置
		int k=0;//排序后的序列的当前位置
		int[] temp = new int[array1.length + array2.length];//合并后的序列
		
		//从头开始比较两个子序列的元素，取小的放到有序的临时数组里。
		while(i<array1.length && j<array2.length){
			if(array1[i]<=array2[j]){
				temp[k++] = array1[i++];
			}else{
				temp[k++] = array2[j++];
			}
		}
		//将未排完的子序列复制到temp尾部
		while(i<array1.length){
			temp[k++]=array1[i++];
		}
		while(j<array2.length){
			temp[k++]=array2[j++];
		}
		IOUtils.println(Arrays.toString(array1)+" + "+Arrays.toString(array2)+" => "+Arrays.toString(temp));
		return temp;
	}
}
