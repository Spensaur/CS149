# include <iostream>
# include <iomanip>
using namespace std;

void seats( double [] , int);
void mapSeats();
char movieMenu(char);

int main()
{
    const int rowNum = (15.0);
    double rowValue[rowNum]; //array to hold row pices
    char selection;
    int row2, col2;
    const char TAKEN = '#';//seats taken
    const char EMPTY = '*';//seats free
    const int row = 10;//number of rows
    const int col = 10;//number of col
    char map[row][col];//array to hold seat chart 


    
    for(int i= 0;i<row;i++)//initiating array 
    {
        for (int j=0;j<col;j++)
        {
            map[i][j]=EMPTY;
        }
    }

    mapSeats();

    seats(rowValue, rowNum);//ask user to enter price of each row
    cout << endl;
    
    do
        {
            cout << "MOVIE THEATER MENU" << endl;
            cout << "------------------" << endl;
            cout << "1) Sell a ticket" << endl;
            cout << "Q) Quit program" << endl;
            cout << "Please make a selection: ";
            cin >> selection;
            
            if(selection =='1')
            {
                cout << "Please enter a row number and a seat number for the ticket: " ;
                cout << "Row # :" ;
                cin >> row2;
                cout << endl;
                cout << "Seat # :" ;
                cin >> col2;
                cout << endl;
// Check if seat is free
if(map[row2][col2] == TAKEN)
{
    cout << "This seat is taken! Try another one. \n";
    continue; // start the loop again
}
else // and if it is - sell the ticket
    map[row2][col2]=TAKEN;
// Add the next loop to immediately see the effects:
for (int i = 0; i < row; i++){
    for(int j = 0; j < col; j++){
        cout << map[i][j];
    }
    cout << endl;
}


            }
            else if(selection =='q'||selection=='Q')
            {
                cout << "Thank you for using the program." << endl;
            }
            else if(selection != '1' || selection !='q' || selection !='Q')
            {
                cout << "Invalid selection." << endl;
            }
        }while(selection != '1' || selection !='q' || selection !='Q');
    

    system("PAUSE");
    return 0;
}


void seats(double rowPrice[], int row)
{

    cout << "Please enter a ticket price for each row." << endl;
    
    for(int i = 0 ; i < row; i++)
    {
        cout << "Row # " << i+1 << ": " ;
        cin >> rowPrice[i]; 
    }
}

void mapSeats()
{
    const char TAKEN = '#';//seats taken
    const char EMPTY = '*';//seats free
    const int rw=20;
    const int cl=15;

    cout << "Seats " ;
    for(int k = 0 ; k <20;k++) //loop to display nums 0 to 19
    {
        cout << fixed<< setw(2) << " " << k ;
    }

    for(int i=0;i<rw;i++)//making array display what's in it
    {
        cout << endl<< "Row " << i;
        for(int j=0;j<cl;j++)
        {
            cout << fixed<< setw(2) << "" << EMPTY;
        }
    }
    cout << endl;
}