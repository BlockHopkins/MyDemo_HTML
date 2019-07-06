package com.qc.sort;

import java.util.Arrays;

import com.qc.utils.IOUtils;

/**
 * 选择排序
 * 每次从数组无序区中取最大或最小的数据，交换放到无序区的最高位或最低位
 * 非稳定排序算法，如[5,5,3]，从小到大排序，第一趟就将第一个5放到3后面，最终结果第一个5在第二个5后面。
 * @author Qc
 *
 */
public class SelectSort {

	public static void main(String[] args) {
		int[] result = selectSort(IOUtils.arrayInput());
		IOUtils.println("排序结果：", Arrays.toString(result));
	}
	
	/**
	 * 选择排序，从小到大
	 * 每一轮从无序区中取最大的一个，交换放到无序区最高位
	 * @param array
	 * @return
	 */
	public static int[] selectSort(int[] array){
		int temp = 0;
		
		int maxIndex = array.length-1;//无序区最高位下标
		int selectIndex = 0;//选择的最高位
		
		for(int i = 0; i<array.length-1; i++,maxIndex--){
			selectIndex = maxIndex;//每一轮选择的数据初始化为无序区最高位的数据
			
			//选出无序区最大的数据
			for(int j=0;j<maxIndex;j++){
				if(array[selectIndex] < array[j]){
					selectIndex = j;
				}
			}
			
			//如果选择的数据不是最高位的数据，则交换
			if(selectIndex != maxIndex){
				temp = array[selectIndex];
				array[selectIndex] = array[maxIndex];
				array[maxIndex] = temp;
			}
			
			IOUtils.println("第"+i+"趟：",Arrays.toString(array));
		}
		return array;
	}
}
