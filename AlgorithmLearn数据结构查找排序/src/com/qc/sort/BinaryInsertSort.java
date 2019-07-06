package com.qc.sort;

import java.util.Arrays;

import com.qc.utils.IOUtils;

/**
 * 二分（折半）插入排序
 * @param array
 * @return
 */
public class BinaryInsertSort {
	
	public static void main(String[] args) {
		int[] array = binaryInsertSort(IOUtils.arrayInput());
		IOUtils.println("排序结果：", Arrays.toString(array));
	}
	
	/**
	 * 二分（折半）插入排序
	 * 插入排序，确定元素位置时采用二分法查找，提高查找速度
	 * @param array
	 * @return
	 */
	public static int[] binaryInsertSort(int[] array){
		int i=1,j=0;
		int temp = 0;
		int head,tail,mid = 0;
		while(i<array.length){
			if(array[i] < array[i-1]){
				temp = array[i];//暂存当前元素
				head = 0;
				tail = i-1;//折半查找的有序序列为前i-1个元素
				//开始折半查找
				while(head<=tail){
					mid = (head+tail)/2;
					if(temp<array[mid]){
						tail = mid-1;//前半部分
					}else{
						head = mid+1;//后半部分
					}
				}
				//找到插入位置，开始后移比较大的元素
				for(j=i-1;j>=head;j--){
					array[j+1] = array[j];
				}
				array[head]=temp;//元素归位
			}
			IOUtils.println("第"+i+"趟：", Arrays.toString(array));
			i++;
		}
		return array;
	}
}
