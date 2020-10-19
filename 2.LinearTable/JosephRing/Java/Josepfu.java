package com.datastructure.linkedlist;

/**
 * @author Sakura
 */
public class Josepfu {
    public static void main(String[] args) {
        //���Ի����б�Ĺ����ͱ���
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //����5��С���ڵ�
        circleSingleLinkedList.addBoy(8);
        circleSingleLinkedList.showBoy();

        //����С���뿪��������
        //2 4 1 5 3
        circleSingleLinkedList.countBoy(1,2,8);
    }
}

/**
 * ����һ��Boy��,��ʾһ���ڵ�
 * ����һ��first�ڵ�,��ǰû�б��
 * ���curBoy�ڵ�,������һ�����ε�����
 * numsΪ�ܽڵ���,�򵥵�����У��
 */
class CircleSingleLinkedList{

    private Boy first = null;
    public void addBoy(int nums){
        if (nums <  1){
            System.out.println("nums����");
            return;
        }
        //��������,����������������
        Boy curBoy = null;
        //������������
        for (int i = 1; i <= nums; i++) {
            //���ݱ�Ŵ���С���ڵ�
            Boy boy = new Boy(i);
            //����ǵ�һ��С��
            if(i ==1){
                first = boy;
                //�γɻ�״
                first.setNext(first);
                //��curBoyָ���һ��С�� first���ܶ�,ֻ��ͨ��curBoy������
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
       //�ж�����,�Ƿ�Ϊ��
       if (first == null){
           System.out.println("����Ϊ��");
           return;
       }
       //��Ϊfirst���ܲ���,ͨ������������ɱ���
        Boy curBoy = first;
        while (true){
            System.out.printf("С���ı��%d \n",curBoy.getNo());
            if (curBoy.getNext() == first){
                //�������
                break;
            }
            //curBoy����
            curBoy = curBoy.getNext();
       }
    }

    /**
     * �����û�����,�����С����Ȧ��˳��
     * int startBoy, ��ʾ�ӵڼ���С����ʼ����
     * int countNums, ��ʾ���ڼ���
     * int nums ��ʾ����ж���С���ٻ���������
     */
    public void countBoy(int startNo,int countNums,int nums){
        //�ȶ����ݽ���У��
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("������������,��������");
            return;
        }
        //��������ָ��,����С���뿪��������
        Boy helper = first;
        //��������ָ������������Ľڵ�
        while (true){
            //helperָ�����Ľڵ�
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //K�ڵ㲻Ϊ1ʱ, С������ǰ,����first��helper�ƶ� k-1��
        for (int j = 0;j < startNo -1; j++){
            first = first.getNext();
            helper = helper.getNext();
            break;
        }
        //С������,��first��helper ָ��ͬʱ�ƶ� m -1�� Ȼ���Ȧ,ֱ������������ֻ��һ���ڵ�
        while(true){
            //˵������������ֻ��һ���ڵ�
            if (helper == first){
                break;
            }
            //��first��helper ָ��ͬʱ�ƶ� countNum -1��
            for (int j = 0; j < countNums -1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //����firstָ��Ľڵ�,����Ҫ�뿪���������С���ڵ�
            System.out.printf("С��%d��Ȧ\n",first.getNo());
            //��ʱ��firstָ���С���ڵ��뿪��������
            first = first.getNext();
           // ����ʹ�� helper.getNext() = first; next��private����
            helper.setNext(first);
        }
        System.out.printf("���һ�����ڻ�������ĵı����%d \n",first.getNo());
    }


}

/**
 * ����һ��Boy��,��ʾһ���ڵ�
 * Boy next ָ����һ���ڵ�,Ĭ��Ϊ��
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