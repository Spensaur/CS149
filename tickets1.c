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

list queues[10];

void addCustomer(linkedlist l)
{
	list *newNode= (list*)malloc(sizeof(list));
	newNode->time= currenttime; 
	newNode->next= NULL;
	l->next= newNode;
}

void removeNode()
{
}

void main(int argc, char *argv[])
{
	int n=argv[1];
	int i; 	
	for (int i=0; i<10; i++)
	{
		queue[i]= (list*)malloc(sizeof(list));
		queue[i]->time=rand()%60;
		
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
		}
	currenttime++;		
	}

}
