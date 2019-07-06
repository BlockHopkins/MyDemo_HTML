package com.qc.sort;

import java.util.Arrays;

import com.qc.utils.IOUtils;

/**
 * 归并排序
 * (Merge Sort)
 * 速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列
 * @author Administrator
 *
 */
public class MergeSort {
	
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
		int[] temp = new int[array.length];//先创建一个用于排序的空间，递归里不需重复创建。
		return mergeSort(array, 0, array.length-1,temp);
	}
	
	private static int[] mergeSort(int[] array, int start, int end, int[] temp){
		if(start<end){
			int mid = (start+end)/2;//分成两半
			mergeSort(array, start, mid, temp);//递归对第一部分排序
			mergeSort(array, mid+1, end, temp);//递归对第二部分排序
			merge(array, start, mid, end, temp);//将两部分合并
		}
		return array;
	}
	
	private static int[] merge(int[] array, int start, int mid, int end, int[] temp){
		int i=start;//第一个子序列下标区间为[start,mid]
		int j=mid+1;//第二个子序列下标区间为[mid+1,end]
		int k=start;//排序后的序列的当前位置
		
		//从头开始比较两个子序列的元素，取小的放到有序的临时数组里。
		while(i<=mid && j<=end){
			if(array[i]<=array[j]){
				temp[k++] = array[i++];
			}else{
				temp[k++] = array[j++];
			}
		}
		//将未排完的子序列复制到temp尾部
		while(i<=mid){
			temp[k++]=array[i++];
		}
		while(j<=end){
			temp[k++]=array[j++];
		}
		//将排完序的临时空间里的子序列复制到原数组中
		for(i=start;i<=end;i++){
			array[i] = temp[i];
		}
		IOUtils.println("start:"+start+",end:"+end, Arrays.toString(array));
		return array;
	}
}
