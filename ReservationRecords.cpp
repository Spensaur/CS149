#include <iostream>
#include <vector>
#include <string>
using namespace std;

class ReservationRecords
{
private:
	struct Flight
	{
		string FlightNo;
		int seats;
	};

	struct Record
	{
		string Name;
		string PhoneNumber;
		string FlightNum;
		string DateOfTravel;
	};

	vector<Flight> FList;
	vector<Record> PList;

public:

	// add all info into flightlist
	// function {}

	void NewFlight(string FN, int seats)
	{
		for(int i =0; i<FList.size(); i++)
		{
			if(FN.compare(FList[i].FlightNo))
			{
				cout << "Flight already exists" << endl;
				return;
			}
		}
		
		Flight Addition;
		Addition.FlightNo = FN;
		Addition.seats = seats;
		FList.push_back(Addition);
	};

	void add(string Name, string PhoneNumber, string FlightNum, string Date)
	{
		// go through passenger list to check name and make sure reservation does not exist

		// go through flight and when find flight list check if seats are available. If nothing found, print "no flight exists"

		// otherwise, add object to passenger list
	};
};

int main()
{
	// call build flight list
	
}