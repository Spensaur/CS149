#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <signal.h>
#include <sys/time.h>
#include <string.h>

#define ROW_COUNT 10;
#define COLUMN_COUNT 10;
#define SELLING_DURATION 60; 
char* seats[10][10];
int seating[10][10];
int attendeeCount[10];
int attendeeName[10];
int attendeeName2[10];
pthread_mutex_t seatsMutex;  // mutex protects seats
pthread_mutex_t printMutex;  // mutex protects printing
pthread_mutex_t queueMutex[10]; //mutex protecting its respective queue
sem_t emptyqueue[10];   //ticketbooth waits on its respective semaphore

struct itimerval boothTimer;  // ticketbooth hour timer
time_t startTime;
int timesUp = 0;  // 1 = ticket selling ohour over

void print(char* event)
{
    time_t now;
    time(&now);
    double elapsed = difftime(now, startTime);
    int min = 0;
    int sec = (int) elapsed;

    if (sec >= 60) {
        min++;
        sec -= 60;
    }
    pthread_mutex_lock(&printMutex);
    pthread_mutex_lock(&seatsMutex);
    printf("TIME: %1d:%02d EVENT: ", min, sec);
    printf("%s\n", event);
    int i;
    int j;

    printf("--SEATING CHART--\n");
    for (i = 0; i < 10; i++)
    {
        for (j = 0; j < 10; j++)
        {
            if(seats[i][j] == NULL)
            {
                printf("  -  ");
            }
            else
            {
                printf("%s ", seats[i][j]);   
            }
        }
        printf("\n");
    }
    printf("\n");
    pthread_mutex_unlock(&seatsMutex);
    pthread_mutex_unlock(&printMutex);
}


char computeLine(int queue)
{
    if(queue == 0)
    {
        return 'H';
    }
    else if(queue > 0 && queue < 4)
    {
        return 'M';
    }
    else
    {
        return 'L';
    }
}

void getInLine(int queue) //method is called when an attendee gets in line.
{
    char event[80];
    pthread_mutex_lock(&queueMutex[queue]);
    attendeeCount[queue]++;
    attendeeName[queue]++;
    pthread_mutex_lock(&printMutex);
    printf("Attendee %c%d%02d arrives and waits\n", computeLine(queue), queue, attendeeName[queue]);
    pthread_mutex_unlock(&queueMutex[queue]);
    pthread_mutex_unlock(&printMutex);
    sem_post(&emptyqueue[queue]);
}

void *attendee(void* param)
{
    int queue = *((int *) param); 
    sleep(rand()%60);
    getInLine(queue);
    return NULL;
}

void sellTickets(int boothNum)
{
    //printf("selltickits - boothNum: %d\n", boothNum);
    char event[80];
    char name[5];
    sem_wait(&emptyqueue[boothNum]);
    pthread_mutex_lock(&queueMutex[boothNum]);
    attendeeCount[boothNum]--;
    int num = attendeeName2[boothNum];
    attendeeName2[boothNum]++;
    if(boothNum == 0)
    {
        sleep((rand()%1) + 1);
    }
    else if(boothNum > 0 && boothNum < 4)
    {
        sleep((rand()%2) + 2);
    }
    else
    {
        sleep((rand()%3) + 4);
    }
    pthread_mutex_unlock(&queueMutex[boothNum]);
    pthread_mutex_lock(&seatsMutex);
    int i;
    int j;
    int k = 0;
    if(boothNum == 0) //cheap
    {
        i = 0;
        j = 0;
        while (seating[i][j] == 1 && (i <= 9 && j <= 9))
        {
            if(j == 9)
            {
                j = 0;
                i++;
            }
            else
            {
                j++;
            }
            if(i > 9)
            {
                k = 1;
            }
        }
    }
    else if(boothNum > 0 && boothNum < 4) //med
    {
        i = 4;
        j = 0;
        int flip = 0;
        int count = 1;
        while(seating[i][j] == 1 && count < 11)
        {
            if(j == 9)
            {
                j = 0;
                if(flip == 0)
                {
                    i += count;
                    count++;
                    flip = 1;
                }
                else
                {
                     i -= count;
                    count++;
                    flip = 0;
                }
            }
            else
            {
                j++;
            }
            if(count == 11)
            {
                k = 1;
            }
        }
    }
    else //expensive
    {
        i = 9;
        j = 9;
        while (seating[i][j] == 1 && (i >= 0 && j >= 0))
        {
            if(j == 0)
            {
                j = 9;
                i--;
            }
            else
            {
                j--;
            }
            if(i < 0)
            {
                k =1;
            }
        }
    }
    if(k == 1)
    {
        pthread_mutex_unlock(&seatsMutex);
        sprintf(event, "Ran out of seats for attendee:  %c%d%02d\n", computeLine(boothNum), boothNum, attendeeName[boothNum]);
        print(event);
    }
    else
    {
        seating[i][j] = 1;
        sprintf(name, "%c%d%02d", computeLine(boothNum), boothNum, num);
        seats[i][j] = (char*)malloc(sizeof(name));
        strcpy(seats[i][j], name);
        pthread_mutex_unlock(&seatsMutex);
        sprintf(event, "%c%d%02d seated at seat: [%d][%d]\n", computeLine(boothNum), boothNum, num, i, j);
        print(event);
    }
}

void *ticketbooth(void* param)
{
    //printf("reach ticketbooth\n");
    int boothNum = *((int *) param);
    //printf("ticketbooth - boothNum: %d\n", boothNum);
    do {
        sellTickets(boothNum);
    } while (!timesUp);

    print("Done selling tickets");
    return NULL;
}

// Timer signal handler.
void timerHandler(int signal)
{
    timesUp = 1;  // office hour is over
}


int main(int argc, char *argv[])
{
    if (argc < 2)
    {
        printf("Put in one argument");
        return -1;
    }

    srand(time(0));

    pthread_mutex_init(&seatsMutex, NULL);
    pthread_mutex_init(&printMutex, NULL);

    int linenumber[10];
    int n = atoi(argv[1]);
    int i;
    int j;
    // Set the timer signal handler.
    signal(SIGALRM, timerHandler);

    for (i = 0; i < 10; i++) //generate the ticketseller threads and semaphores
    {
        linenumber[i] = i;
        sem_init(&emptyqueue[i], 0, 0);
        pthread_mutex_init(&queueMutex[i], NULL);
        attendeeCount[i] = 0;
        attendeeName[i] = 0;
        attendeeName2[i] = 1;
        pthread_t boothThreadId;
        pthread_attr_t boothAttr;
        pthread_attr_init(&boothAttr);
        pthread_create(&boothThreadId, &boothAttr, ticketbooth, &linenumber[i]);
        for(j = 0; j < n;j++) //generate "n" attendees for each queue.
        {
            pthread_t attendeeThreadId;
            pthread_attr_t attendeeAttr;
            pthread_attr_init(&attendeeAttr);
            pthread_create(&attendeeThreadId, &attendeeAttr, attendee, &linenumber[i]);
            if(j == n-1 && i == 9)
            {
                time(&startTime);
                // Set the timer for ticketselling duration.
                boothTimer.it_value.tv_sec = 60;
                setitimer(ITIMER_REAL, &boothTimer, NULL);
                pthread_join(boothThreadId, NULL);
            }
        }
    }

    return 0;
}
