public class Program {

    public static void Main(string[] args){

        Pomodoro pomo;

        System.Console.WriteLine("Ingresa los minutos: ");
        int min = Convert.ToInt32(Console.ReadLine());
          

        if (min > 0) {
                pomo = new Pomodoro(min);
                pomo.StartWork();
        }



        Console.ReadKey();
    }

}