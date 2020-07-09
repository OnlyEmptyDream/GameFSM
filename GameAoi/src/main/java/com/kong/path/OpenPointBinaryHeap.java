package com.kong.path;

import com.kong.aoi.obj.Vector2f;

import java.awt.*;
import java.util.Arrays;


public class OpenPointBinaryHeap {
    private int capacity;//最大容量 maxSize
    private int size = 0;
    private Vector2f[]array;

    public OpenPointBinaryHeap(int capacity) {
        this.capacity = capacity;
        this.array = new Vector2f[this.capacity];
    }

    /**
     * 简单描述下 入堆进行的操作
     * 就是把它放在数组的末尾。然后和它在当前位置/2 处的父节点比较，直到父节点为空或者大于父节点
     * @param e
     */

    public void push(Vector2f e){
        if(size == capacity){
            return;
        }
        array[size] = e;
        size++;
        int fatherIndex = (size >> 1) -1; //size已经+1了 因此子节点/2 正好为父节点  原先：左孩子：2i+1 右孩子：2i+2

        int currentIndex = size -1;
        siftUp(fatherIndex, currentIndex);
    }

    private void siftUp(int fatherIndex, int currentIndex){

        // fatherIndex 不是代变真正的父节点的改变  只是用其判断 当前节点是否还有父节点
        while(fatherIndex >= 0){
            if(array[fatherIndex].getF() >= array[currentIndex].getF()){
                Vector2f temp = array[fatherIndex];
                array[fatherIndex] = array[currentIndex];
                array[currentIndex] = temp;

                currentIndex = fatherIndex;
                fatherIndex = ((fatherIndex + 1) >> 1) -1;
            }else{
                //已经找到了合适的位置
                break;
            }
        }
    }

    public Vector2f pop(){
        if(size <= 0){
            return null;
        }
        // 将首位堆 出栈，然后将最后一位放到第一位，然后依次与自己的子节点比大小，直到找到合适的位置
        Vector2f ret = array[0];
        array[0] = array[size -1];
        array[size -1] = null;
        size--;

        if(size == 0){
            return ret;
        }

        int currentIndex = 0;
        int childIndex = (currentIndex << 1) + 1;

        while (childIndex < size){
            //因为左右是不分大小的  因此每次拿当前节点与子节点中 最小的做比较
            if(childIndex + 1 < size && array[childIndex].getF() > array[childIndex + 1].getF()){
                childIndex++;
            }

            //比最小的还小可以停止了
            if (array[currentIndex].getF() <= array[childIndex].getF()) {
                break;
            }

            //比最小的还大  交换位置 继续寻找
            Vector2f temp = array[currentIndex];
            array[currentIndex] = array[childIndex];
            array[childIndex] = temp;

            currentIndex = childIndex;
            childIndex = (currentIndex << + 1) + 1;
        }
        return ret;
    }

    //更新节点信息  可能在某种情况下  当前点距初始点的距离会发生改变
    //改变原因 比如去右上角 有两种方式 1：右上角斜线走 2：先右后上  这两种走法造成的目标点到原点的距离发生改变
    public void update(Vector2f point){
        int currentIndex = -1;
        for (int i = 0; i < size; i++){
            Vector2f vector2f = array[i];
            if(point == vector2f){
                currentIndex = i;
            }
        }
        if(currentIndex == -1){
            return;
        }


        // f值只会变小，不会变大，所以只要和父节点比较就可以了。
        int fatherIndex = ((currentIndex + 1) >> 1) - 1;
        siftUp(fatherIndex, currentIndex);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    public static void main(String[] args) {
        OpenPointBinaryHeap heap = new OpenPointBinaryHeap(20);
        Vector2f Point1 = new Vector2f(1, 2);
        Point1.setF(90);
        heap.push(Point1);
        System.out.println(Arrays.toString(heap.array));
        Vector2f Point2 = new Vector2f(4, 5);
        Point2.setF(30);
        heap.push(Point2);
        System.out.println(Arrays.toString(heap.array));
        Vector2f Point3 = new Vector2f(1, 3);
        Point3.setF(100);
        heap.push(Point3);
        System.out.println(Arrays.toString(heap.array));
        Vector2f Point4 = new Vector2f(4, 2);
        Point4.setF(60);
        heap.push(Point4);
        System.out.println(Arrays.toString(heap.array));



//        Point3.setF(5);
//        heap.update(Point3);
//        Vector2f point5 = new Vector2f(5, 3);
//        point5.setF(70);
//        heap.push(point5);
//        System.out.println(Arrays.toString(heap.array));
//        heap.pop();
//        System.out.println(Arrays.toString(heap.array));
    }
}
