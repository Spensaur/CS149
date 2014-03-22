#include <stdio.h>
#include <stdlib.h>


typedef struct node
{
    int x;
    struct node *next;
}Node;

Node listadd(Node *last)
{
    Node *newNode;
    newNode = malloc(sizeof(Node));
    *last = *newNode;
    return *last;
}

void listremove(Node *first)
{
    Node temp = *first;
    first = temp.next;
    free(&temp);
}

/*
typedef struct linkedlist LLIST;

struct linkedlist
{
    struct node *first;
    struct node *last;
};

void listadd(LLIST l, int arrivaltime)
{
    struct node *customer;
    customer = (struct node*)malloc(sizeof(struct node));
    customer->next = arrivaltime;
    l->last->next = *customer;
    l->last = customer;
}

void listremove(LLIST l)
{
    struct node temp = l.first;
    printf("removing: %d", temp->next);
    l->first = l->first->next;
    free(temp);
}
*/


int main()
{
    Node *head;
    Node *tail;
    head = (Node*)malloc(sizeof(head));
    head->x = 4;
    Node *second;
    second = (Node*)malloc(sizeof(head));
    second->x = 5;
    *tail = listadd(head);
    *tail = listadd(head);
    printf("tail: ", tail->x);
/*
    LLIST test = malloc(sizeof(LLIST));
    listadd(test, 3);
    listadd(test, 4);
    listadd(test, 5);
    listremove(test);
    listremove(test);
*/

    return 0;
}
