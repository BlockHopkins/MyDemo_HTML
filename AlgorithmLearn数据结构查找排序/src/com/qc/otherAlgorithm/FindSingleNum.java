package com.qc.otherAlgorithm;

import com.qc.utils.IOUtils;

/**
 * 假定有2K+1个数，其中有2k个相同的数，需要找出单独一个的那个数，
 * 比如：2、3、4、4、3、5、6、6、5。
 * 我们巧用异或运算符的规则，得出一个数和0异或还是自己，一个数和自己异或是0的原理，可以做到。
 * @author Qc
 *
 */
public class FindSingleNum {
	
	public static void main(String[] args) {
		int[] array = {2,3,4,4,3,5,6,6,5};
		IOUtils.println("单独的数字为：",findSingleNum(array));
	}
	
	public static int findSingleNum(int[] array){
		int result = 0;
		for(int element : array){
			result ^= element;
		}
		return result;
	}
}
