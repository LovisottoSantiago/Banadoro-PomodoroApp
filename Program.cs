using System;
using System.Security.Cryptography.X509Certificates;
using LibVLCSharp.Shared;

class Program {
    static void Main(string[] args) {

        Core.Initialize();
        Pomodoro pomo;

        Console.Write("Ingresa los minutos: ");
        int min = Convert.ToInt32(Console.ReadLine());

        pomo = new Pomodoro(min);

        pomo.StartWork();          

        Console.ReadKey();

    }
}
