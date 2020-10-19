package com.datastructure.linkedlist;

/**
 * @author Sakura
 */
public class Josepfu {
    public static void main(String[] args) {
        //测试环形列表的构建和遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //加入5个小孩节点
        circleSingleLinkedList.addBoy(8);
        circleSingleLinkedList.showBoy();

        //测试小孩离开环形链表
        //2 4 1 5 3
        circleSingleLinkedList.countBoy(1,2,8);
    }
}

/**
 * 创建一个Boy类,表示一个节点
 * 创建一个first节点,当前没有编号
 * 添加curBoy节点,构建成一个环形的链表
 * nums为总节点数,简单的数据校验
 */
class CircleSingleLinkedList{

    private Boy first = null;
    public void addBoy(int nums){
        if (nums <  1){
            System.out.println("nums有误");
            return;
        }
        //辅助变量,帮助构建环形链表
        Boy curBoy = null;
        //创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i ==1){
                first = boy;
                //形成环状
                first.setNext(first);
                //让curBoy指向第一个小孩 first不能动,只能通过curBoy来操作
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     *
     */
    public void showBoy(){
       //判断链表,是否为空
       if (first == null){
           System.out.println("链表为空");
           return;
       }
       //因为first不能操作,通过辅助变量完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号%d \n",curBoy.getNo());
            if (curBoy.getNext() == first){
                //遍历完成
                break;
            }
            //curBoy后移
            curBoy = curBoy.getNext();
       }
    }

    /**
     * 根据用户输入,计算出小孩出圈的顺序
     * int startBoy, 表示从第几个小孩开始数数
     * int countNums, 表示数第几下
     * int nums 表示最初有多少小孩再环形链表内
     */
    public void countBoy(int startNo,int countNums,int nums){
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误,重新输入");
            return;
        }
        //创建辅助指针,帮助小孩离开环形链表
        Boy helper = first;
        //辅助变量指向环形链表的最后的节点
        while (true){
            //helper指向最后的节点
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //K节点不为1时, 小孩报数前,先让first和helper移动 k-1次
        for (int j = 0;j < startNo -1; j++){
            first = first.getNext();
            helper = helper.getNext();
            break;
        }
        //小孩报数,让first和helper 指针同时移动 m -1次 然后出圈,直到环形链表中只有一个节点
        while(true){
            //说明环形链表中只有一个节点
            if (helper == first){
                break;
            }
            //让first和helper 指针同时移动 countNum -1次
            for (int j = 0; j < countNums -1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这是first指向的节点,就是要离开环形链表的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点离开环形链表
            first = first.getNext();
           // 不能使用 helper.getNext() = first; next被private修饰
            helper.setNext(first);
        }
        System.out.printf("最后一个留在环形链表的的编号是%d \n",first.getNo());
    }


}

/**
 * 创建一个Boy类,表示一个节点
 * Boy next 指向下一个节点,默认为空
 */
class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}