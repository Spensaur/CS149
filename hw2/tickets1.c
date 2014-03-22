#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <signal.h>
#include <sys/time.h>

#define ROW_COUNT 10;
#define COLUMN_COUNT 10;  
//char seats[ROW_COUNT][COLUMN_COUNT];
int currenttime;
int customerCount[10];

struct node
{
    int time; 
    struct node *next; 
};

struct linkedlist 
{
    struct node *first;
    struct node *last; 
};

typedef struct linkedlist list;

list* queue[10];

void addCustomer(list* l, int time)
{
    struct node *newNode= (struct node*)malloc(sizeof(struct node));
    newNode->time= time; 
    newNode->next= NULL;
    if (l->last!=l->first)
    {   
        l->last->next= newNode;
        l->last= newNode;
    }
    else 
    {
        l->last=newNode;
        l->first->next=l->last;
    }
}

void removeCustomer(list* l)
{
    l->first=l->first->next; 
}

void main(int argc, char *argv[])
{
    if (argc == 0)
    {
        printf("Put in one argument");
        return -1;
    }
    
    int n = *argv[1] - '0';    
    int i;      
    for (i=0; i<10; i++)
    {
        /*queue[i]= (list*)malloc(sizeof(list));
        queue[i]->first=(struct node*)malloc(sizeof(struct node)); 
        queue[i]->first->time=-1;
        queue[i]->first->next=NULL; 
        queue[i]->last=queue[i]->first;*/
    customerCount[i] = n;
    }
    currenttime= 1;
    int random;
    while (currenttime<60)
    {
        printf("currenttime: %d, ", currenttime);
        for (i=0; i<10; i++)
    {
        random=rand()%(61 - currenttime);
    	printf("rando: %d, ", random);
        if (random<=customerCount[i])
            {
        if(queue[i] == NULL)
        {
            queue[i]= (list*)malloc(sizeof(list));
                queue[i]->first=(struct node*)malloc(sizeof(struct node)); 
                queue[i]->first->time= currenttime;
                queue[i]->first->next=NULL; 
                queue[i]->last=queue[i]->first;
        }
        else
        {   
                    addCustomer(queue[i], currenttime); 
                    printf("reached random and passed addcustomer, ");  
        }    
         customerCount[i]--; 
            }
       /* if (queue[i]->first->time ==-1)
            {
                queue[i]->first=queue[i]->first->next;
                printf("reached the first time through\n");  
            } */  
         
    }   
         currenttime++;
    }
struct node* temp=(struct node*)malloc(sizeof(struct node));
for (i=0; i<10; i++)
{
temp=queue[i]->first; 
while (temp->next!=NULL)
{
    printf("%d, ", temp->time);
    temp=temp->next;
}
}
return 0;
}
