package hw2;

/**
 * Created with IntelliJ IDEA.
 * User: rohitkrishnan
 * Date: 2/22/14
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;     //import para sa ScannerClass
import javax.swing.*;   //import para sa JOptionPane class

public class FCFS
{

    static Scanner js = new Scanner(System.in); //input reader

    static Integer[] jobbt = {2,4,6,8,10};        //BurstTime
    static Integer[] jobat = {1,2,3,4,5};        //ArrivalTIme
    static Integer[] jmark = {1,2,3,4,5};   //marker

    public static void main(String[] args)
    {

        System.out.println("CPU Scheduling Algorithm - First Come First Serve\n\n");

        int a = 0;//initialize ng counter



        System.out.println("\n\nSummary of Inputs:\n");

        for(int b = 0; b<5; b++)
        {
            System.out.println("JOB" + (b+1) + ":\tBurst Time: " + jobbt[b] + "\t\t" + "Arrival Time: " + jobat[b]);
        }

//BUBBLE SORTING

        bubble_srt(jobat, jobbt, jmark, 5);


        System.out.println("\n\nSORTED ACCORDING TO ARRIVAL TIME:\n");

        for(int z = 0; z<5; z++)
        {
            System.out.println("JOB" + jmark[z] + ":\tBurst Time: " + jobbt[z] + "\t\t" + "Arrival Time: " + jobat[z]);
        }

        System.out.println("\n\n");

        fcfs(jobbt, jmark, 5);

        System.exit(0);

    }//main

    public static void bubble_srt(Integer a[], Integer b[], Integer c[], int n)
    {
        int i, j,h=0,f = 0, g=0;
        for(i = 0; i < n; i++)
        {
            for(j = 1; j < (n-i); j++)
            {
                if(a[j-1] > a[j])
                {
                    h = a[j-1];
                    a[j-1]=a[j];
                    a[j]=h;

                    f = b[j-1];
                    b[j-1]=b[j];
                    b[j]=f;

                    g = c[j-1];
                    c[j-1]=c[j];
                    c[j]=g;
                }
            }
        }


    }//BUBBLE SORTER



    public static void fcfs(Integer b[], Integer a[], int n)
    {
        Double wt[] = new Double[5];
        Double[] tat = new Double[5];
        Double[] art = new Double[5];
        Double twt = 0.0;
        Double sum = 0.0;
        Double awt = 0.0;


        wt[0]=0.0;//Waiting Time always starts with 0 ...
        System.out.println("Waiting time for JOB" + (a[0]) + ": " + wt[0]);

        for(int i=1;i<5;i++)//compute ng waiting time
        {
            wt[i]=b[i-1]+wt[i-1];
            System.out.println("Waiting time for JOB" + (a[i]) + ": " + wt[i]);
        }

        System.out.println("\n");

        for(int q=0;q<n;q++)//Turn Around Time = WT + BT
        {
            twt=twt+wt[q];
            tat[q]= b[q] + wt[q];
            System.out.println("Turn-Around Time for JOB" + (a[q]) + ": " + tat[q]);
            sum+=tat[q];
        }

        System.out.println("\n");

        for(int r = 0; r<n; r++ ) //Arrival Time = TAT - BT
        {
            art[r] = tat[r] - b[r];
            System.out.println("Arrival Time for JOB" + (a[r]) + ": " + art[r]);
        }

        awt=twt/n;
        sum=sum/n;

        System.out.println("\n\nTotal Waiting Time:" + twt);
        System.out.println("Average Waiting Time:" + awt);
        System.out.println("Total Turn-Around Time:" + sum);
        System.out.println("Average Turn-round Time:" + (sum/5));

        System.out.println("\n");
    }


}//class - FCFS
