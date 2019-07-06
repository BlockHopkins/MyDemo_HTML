package com.qc.otherAlgorithm;

import java.util.LinkedList;
import java.util.List;
import com.qc.utils.IOUtils;

/**
 * 求n以内全部素数
 * 使用筛选法
 * @author Administrator
 *
 */
public class GetPrime {
	public static void main(String[] args) {
		IOUtils.println("输入一个整数n，求n以内的所有素数。");
		int n = IOUtils.intInput();
		List<Integer> primeList = getPrime(n); 
		IOUtils.println(n+"以内全部素数：", primeList);
	}
	
	public static List<Integer> getPrime(int range){
		LinkedList<Integer> primeList = new LinkedList<Integer>();//用来存素数的list
		//创建一个range大小的数组，java中默认初始化每个元素为0，所以我们这里可以省去初始化的操作。
		int[] temp = new int[range+1];
//		for(int i=0;i<temp.length;i++){
//			temp[i] = 0;
//		}
		//筛选法，从2开始，将两数相乘结果标记为合数(用1表示)，并将素数放到list中。
		for(int i=2; i<temp.length; i++){
			if(temp[i]==0){
				primeList.add(i);//素数
			}
			for(int j=2;i*j<=range;j++){
				temp[i*j] = 1;//标记为合数
			}
		}
		
		return primeList;
	}
}
