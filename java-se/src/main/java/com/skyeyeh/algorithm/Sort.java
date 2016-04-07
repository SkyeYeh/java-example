package com.skyeyeh.algorithm;

/**
 * Created by TV6015 on 2016/4/7.
 */
public class Sort {

    /**
     * 交換排序法。
     *
     * @param datas 資料
     */
    public void exchange(int[] datas) {
        int length = datas.length;
        int temp;

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (datas[i] < datas[j]) {
                    temp = datas[i];
                    datas[i] = datas[j];
                    datas[j] = temp;
                }
            }
        }
    }

    /**
     * 選擇排序法。
     *
     * @param datas 資料
     */
    public void selection(int[] datas) {
        int length = datas.length;
        int temp;
        int max;

        for (int i = 0; i < length - 1; i++) {
            max = datas[i];

            for (int j = i + 1; j < length; j++) {
                if (max < datas[j]) {
                    temp = datas[i];
                    datas[i] = datas[j];
                    datas[j] = temp;

                    max = datas[i];
                }
            }
        }
    }

    /**
     * 插入排序法。
     *
     * @param datas 資料
     */
    public void insertion(int[] datas) {
        int length = datas.length;
        int[] clone = datas.clone();

        for (int i = 1; i < length; i++) {

            int j;
            for (j = i - 1; (j >= 0) && (clone[j] < clone[i]); j--) {
                clone[j + 1] = datas[j];
            }

            clone[j] = clone[i];

        }
    }

    public static void insertion_sort(int[] ori_arr) { //插入排序（Insertion sort）
        int len = ori_arr.length; //取得陣列長度

        for (int i = 1; i < len; i++) {
            int k = i - 1;
            while (ori_arr[i] < ori_arr[k]) { //遞增遞減判斷
                ori_arr[k + 1] = ori_arr[k];
                k--;
                if (k < 0) {
                    break;
                }
            }
            ori_arr[k + 1] = ori_arr[i];
        }
    }

    public static void main(String[] args) {
        int[] datas = {1, 7, 2, 3, 5, 8};

        Sort sort = new Sort();

        sort.insertion(datas);

        for (int data : datas) {
            System.out.print(data);
        }
        System.out.println(" ");
    }
}
