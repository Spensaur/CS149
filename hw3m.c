#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <signal.h>
#include <sys/time.h>

#define ROW_COUNT 10;
#define COLUMN_COUNT 10;
#define SELLING_DURATION 60; 
char seats[ROW_COUNT][COLUMN_COUNT];
int customerCount[10];
int cheapCol, cheapRow = 0;
int mediumRow = 0;
int mediumCol = 4;
int expensiveRow, expensiveCol = 9;
pthread_mutex_t seatsMutex;  // mutex protects seats
pthread_mutex_t printMutex;  // mutex protects printing
sem_t emptyqueue;   //ticketbooth waits on this semaphore


void printSeats()
{
    int i;
    int j;
    pthread_mutex_lock(&printMutex);
    pthread_mutex_lock(&seatsMutex);
    printf("--SEATING CHART--\n");
    for (i = 0; i < COLUMN_COUNT; i++)
    {
        for (j = 0; i < ROW_COUNT; j++)
        {
            printf("%c ", seats[ROW_COUNT][COLUMN_COUNT]);   
        }
        printf("\n");
    }
    pthread_mutex_lock(&seatsMutex);
    pthread_mutex_lock(&printMutex);
}

void computeWaitTime(int type) //method to compute the wait time
{

}

void buyTicket(int id) //method is called when an attendee buys a ticket.
{

}

void *attendee(void* param1, void* param2)
{
    int id = *((int *) param1); //for the name
    int type = *((int *) param2); //for how long to wait
    computeWaitTime(type);
    buyTicket(id)
}

void *sellTickets()
{
    
}

void *ticketbooth(void* param)
{
    do {
        sellTickets();
    } while (!timesUp);

    print("Professor closes her door");
    return NULL;
}



void ticketbooth()
{
    int i;
    int queuePriority;
    for(i = 0; i < 10; i++)
    {
        if (queue[i]->first != NULL)
        {
            int temp = queue[i]->first->time;
            if(queuePriority > temp)
            {
                queuePriority = temp;
            }
        }
    }
}

int main(int argc, char *argv[])
{
    if (argc < 2)
    {
        printf("Put in one argument");
        return -1;
    }
    
    int n = atoi(argv[1]);    
}
