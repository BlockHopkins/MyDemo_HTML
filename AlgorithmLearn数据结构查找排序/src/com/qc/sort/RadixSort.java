package com.qc.sort;

import java.util.Arrays;

import com.qc.utils.IOUtils;

/**
 * 基数排序(Radix Sort)
 * 属于"分配式排序"
 * @author Administrator
 *
 */
public class RadixSort {
	public static void main(String[] args) {
		int[] array = IOUtils.arrayInput();
		int[] resultLSD = radixSortLSD(array);
		IOUtils.println("低位优先LSD排序结果：",Arrays.toString(resultLSD));
		int[] resultMSD = radixSortMSD(array);
		IOUtils.println("高位优先MSD排序结果：",Arrays.toString(resultMSD));
	}
	
	/**
	 * 基数排序(低位优先LSD: Least Significant Digit first)
	 * 对一组数组，
	 * 1.首先根据个位数的数值，在走访数值时将它们分配至编号0到9的桶子中，
	 * 2.接下来将这些桶子中的数值重新串接起来，成为一串新数组
	 * 3.接着再进行一次分配，这次是根据十位数来分配，持续进行以上的动作直至最高位数为止。
	 * 
	 * LSD的基数排序适用于位数小的数列，如果位数多的话，使用MSD的效率会比较好。
	 * @param array
	 * @return
	 */
	public static int[] radixSortLSD(int[] array){
		int n = getLength(array);//获取元素的最高位数
//		if(n==0){
//			return array;
//		}
		
		int[][] bin = new int[10][array.length];//二维数组，第一维表示基数，第二维存储分配到该基数的各个数字
		int[] indexs = new int[10];//bin数组各行的当前下标  
		
		for(int i=1; i<=n; i++){//从低位开始，对各个元素该位上的数字进行分配
			int pow = pow(10, i-1);
			//对每个元素进行分配
			for(int j=0; j<array.length; j++){
				int radix = (array[j]/pow)%10;//得到元素当前这一位的数字
				bin[radix][indexs[radix]++] = array[j];
			}
			//按照分配后的顺序整理到原数组中。
			for(int j=0, x=0; j<10; j++){
				for(int k=0; k<indexs[j]; k++){
					array[x++] = bin[j][k];
				}
				indexs[j]=0;//清空下一轮的bin数组下标
			}
		}
		
		return array;
	}
	
	/**
	 * 基数排序(高位优先MSD: Most Significant Digit first)
	 * 
	 * MSD的方式与LSD相反，是由高位数为基底开始进行分配，
	 * 但在分配之后并不马上合并回一个数组中，而是在每个“桶子”中建立“子桶”，将每个桶子中的数值按照下一数位的值分配到“子桶”中。
	 * 在进行完最低位数的分配后再合并回单一的数组中。
	 * @param array
	 * @return
	 */
	public static int[] radixSortMSD(int[] array){
		int n = getLength(array);//获取元素的最高位数
		return msd(array, n, array.length);
	}
	
	/**
	 * msd 递归方法
	 * @param array 数组
	 * @param bit	当前位数
	 * @param len	数组有效长度
	 * @return
	 */
	private static int[] msd(int[] array, int bit, int len){
		int [][] bin = new int[10][len];//二维数组，第一维表示基数，第二维存储分配到该基数的各个数字
		int[] indexs = new int[10];//bin数组各行的当前下标  
		int pow = pow(10, bit-1);
		//对每个元素进行分配
		for(int j=0; j<len; j++){
			int radix = (array[j]/pow)%10;//得到元素当前这一位的数字
			bin[radix][indexs[radix]++] = array[j];//分配
		}
		//如果没到末位，递归进行低一位的分配
		if(bit>1){
			for(int j=0;j<10;j++){
				if(indexs[j]>0){
					bin[j] = msd(bin[j], bit-1, indexs[j]);
				}
			}
		}
		//重新整理回原数组中
		for(int j=0, x=0; j<10; j++){
			for(int k=0; k<indexs[j]; k++){
				array[x++] = bin[j][k];
			}
		}
		IOUtils.println(Arrays.toString(array));
		return array;
	}
	
	/**
	 * 获取数组元素的最大位数
	 * @param array
	 * @return
	 */
	private static int getLength(int[] array){
		int n = 0;
		int pow = 1;
		for(int i=0;i<array.length;i++){
			if(array[i]>=pow){
				n++;
				pow = pow*10;
			}
		}
		return n;
	}
	
	/**
	 * 求a的b次方
	 * @param a
	 * @param b
	 * @return
	 */
	private static int pow(int a, int b){
		int result = 1;
		for(int i=0;i<b;i++){
			result *= a; 
		}
		return result;
	}
}
