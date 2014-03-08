#include <string>
#include <iostream>
#include <stdlib.h>


using namespace std;
int main()
{
	int c, s[10];
	int i, j;
	string name;



	cout << "Welcome to Airline Reservations System!\n";
	for (j=0; j<10; j++)
	{
		cout << "Please enter your name: ";
		getline(cin,name);

		cout << "Choose a class: ";
		cin >> c;

		switch(c)
		{
		case 1:
			cout << "First class" << endl;
			cout << "Seats available are 1,2,3,4,5.\n";
			do {
	    cout << "Pick a seat: ";
	    cin >> s[j];
	    for (i=0; i<j; i++) if (s[j]==s[i]) {cout << "Seat taken. ";
		break;}
	  } while (i!=j);
  	  if(s[j] <= 5)
		{

			cout << "\n";
			cout << "--------------------------\n";
		cout << "Name: " << name << endl;
		cout << "Class: " << "First class" << endl;
		cout << "Seat no.: " << s[j] << endl;
			cout << "--------------------------\n";

		}
		else
			cout << "Wrong number!  No seat for you!\n";
		break;
	case 2:
		cout << "Economic class\n";
		cout << "Seats available are 6,7,8,9,10.\n";
		do {
		cout << "Pick a seat number: ";
		cin >> s[j];
		for (i=0; i<j; i++) if (s[j]==s[i]) {cout << "Seat taken. ";
	break;}
  } while (i!=j);
		if(s[j] >= 6)
		{
			cout << "\n";
			cout << "--------------------------\n";
		cout << "Name: " << name << endl;
		cout << "Class: " << "Economic class" << endl;
		cout << "Seat no.: " << s[j] << endl;
			cout << "--------------------------\n";
		}
		else
			cout << "Wrong number!  No seat for you!\n";
		break;
		default:
				break;
	}
}


	system("pause");
	return 0;
}