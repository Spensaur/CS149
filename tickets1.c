#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <signal.h>
#include <sys/time.h>

#define ROW_COUNT 10;
#define COLUMN_COUNT 10; 
int currenttime=0; 
char seats[ROW_COUNT][COLUMN_COUNT];

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

list queue[10];

void addCustomer(list l)
{
	list *newNode= (list*)malloc(sizeof(list));
	newNode->time= currenttime; 
	newNode->next= NULL;
	l->last->next= newNode;
	l->last= newNode;
}

void removeCustomer(list l)
{
	l->first=l->first->next; 
// set the ->next to null, free(no star)
}

void main(int argc, char *argv[])
{
	int n=argv[1];
	int i; 	
	for (int i=0; i<10; i++)
	{
		queue[i]= (list*)malloc(sizeof(list));
		queue[i]->first=(struct node*)malloc(sizeof(struct node*)); 
		queue[i]->first->time=-1;
		queue[i]->first->next=NULL; 
		queue[i]->last=queue[i]->first;
	}
	while (currenttime<60)
	{
		for (i=0; i<10; i++)
		{
		int random=rand%currenttime;
		if (random<=n)
			{
			addCustomer(queue[i]);			
			}
		if (queue[i]->first->time ==-1)
			{
				queue[i]->first=queue[i]->first->next;
			}	
		}
	currenttime++;		
	}

}
