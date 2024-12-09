using System;
using LibVLCSharp.Shared;
using System.Media;

class Program {
    static void Main(string[] args) {


        Pomodoro pomo = new Pomodoro(1);        
        pomo.StartWork();

        Console.ReadKey();
    }
}
