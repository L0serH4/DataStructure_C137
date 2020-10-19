
#include <iostream>
using namespace std;

// ����ڵ�
typedef struct RingNode
{
    int pos;  // λ��
    struct RingNode *next;
}RingNode, *RingNodePtr;


// ����Լɪ�򻷣�pHead:����ͷָ�룬count:����Ԫ�ظ���
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
    pCurr->next = pHead;  // ���ɻ�״����
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
    int i = 1;    // ����
    pCurr = pPrev = pHead;
    while(pCurr != NULL)
    {
        if (i == m)
        {
            // �߳���
            cout<<pCurr->pos<<" ";    // ��ʾ��Ȧѭ��
            pPrev->next = pCurr->next;
            free(pCurr);
            pCurr = pPrev->next;
            i = 1;
        }
        pPrev = pCurr;
        pCurr = pCurr->next;
        if (pPrev == pCurr)
        {
            // ���һ��
            cout<< pCurr->pos;    // ��ʾ��Ȧѭ��
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
    cout<<"n(�ܹ�����) = \n";
    cin>>n;
    cout<<"d(��ȥ�˵ı��) = \n";
    cin>>d;
    cout<<"s(��һ�ο�ʼ������λ��) = ";
    cin>>s;
    if(n <= 0 || d <= 0|| s<=0)
    {
        cout<<"Input Error\n";
        system("pause");
        return 0;
    }

    // ��������
    pHead = (RingNodePtr)malloc(sizeof(RingNode));
    pHead->pos = 1;
    pHead->next = NULL;
    CreateRing(pHead, n);
    PrintRing(pHead);
    // ��ʼ��Ȧ
    cout<<"����˳��: ";
    //�����±�ͷ��ʼ������λ��
    while(s--!=1){
        pHead=pHead->next;
    }
    KickFromRing(pHead,d);
    cout<<"\n";
    system("pause");
    return 0;
}
