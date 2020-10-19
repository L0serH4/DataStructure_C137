
#include <iostream>
using namespace std;

// 链表节点
typedef struct RingNode
{
    int pos;  // 位置
    struct RingNode *next;
}RingNode, *RingNodePtr;


// 创建约瑟夫环，pHead:链表头指针，count:链表元素个数
void CreateRing(RingNodePtr pHead, int count)
{
    RingNodePtr pCurr = NULL, pPrev = NULL;
    int i = 1;
    pPrev = pHead;
    while(--count > 0)
    {
        pCurr = (RingNodePtr)malloc(sizeof(RingNode));
        i++;
        pCurr->pos = i;
        pPrev->next = pCurr;
        pPrev = pCurr;
    }
    pCurr->next = pHead;  // 构成环状链表
}

void PrintRing(RingNodePtr pHead)
{
    RingNodePtr pCurr;
    cout<<pHead->pos;
    pCurr = pHead->next;
    while(pCurr != NULL)
    {
        if(pCurr->pos == 1){
            break;
        }
        cout<<pCurr->pos;
        pCurr = pCurr->next;
    }
}

void KickFromRing(RingNodePtr pHead, int m){
    while(pHead==NULL){
        return;
    }
    RingNodePtr pCurr, pPrev;
    int i = 1;    // 计数
    pCurr = pPrev = pHead;
    while(pCurr != NULL)
    {
        if (i == m)
        {
            // 踢出环
            cout<<pCurr->pos<<" ";    // 显示出圈循序
            pPrev->next = pCurr->next;
            free(pCurr);
            pCurr = pPrev->next;
            i = 1;
        }
        pPrev = pCurr;
        pCurr = pCurr->next;
        if (pPrev == pCurr)
        {
            // 最后一个
            cout<< pCurr->pos;    // 显示出圈循序
            free(pCurr);
            break;
        }
        i++;
    }
}

int main()
{
    int d = 0, n = 0,s=0;
    RingNodePtr pHead = NULL;
    cout<<"---------------Josephus Ring---------------\n";
    cout<<"n(总共人数) = \n";
    cin>>n;
    cout<<"d(出去人的编号) = \n";
    cin>>d;
    cout<<"s(第一次开始计数的位置) = ";
    cin>>s;
    if(n <= 0 || d <= 0|| s<=0)
    {
        cout<<"Input Error\n";
        system("pause");
        return 0;
    }

    // 建立链表
    pHead = (RingNodePtr)malloc(sizeof(RingNode));
    pHead->pos = 1;
    pHead->next = NULL;
    CreateRing(pHead, n);
    PrintRing(pHead);
    // 开始出圈
    cout<<"死亡顺序: ";
    //更改下表头开始计数的位置
    while(s--!=1){
        pHead=pHead->next;
    }
    KickFromRing(pHead,d);
    cout<<"\n";
    system("pause");
    return 0;
}
