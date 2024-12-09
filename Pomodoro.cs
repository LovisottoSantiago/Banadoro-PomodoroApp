using System.Runtime.CompilerServices;
using System.Timers;

    public class Pomodoro {

        private static System.Timers.Timer? workTimer;
        private static int timeLeft;
        
        public Pomodoro(int min){
            timeLeft = min * 60;

            workTimer = new System.Timers.Timer(1000);
            
            #pragma warning disable CS8622 
            workTimer.Elapsed += OnTimedEvent;
            #pragma warning restore CS8622 

        }

        public void StartWork() {
            Console.WriteLine("El temporizador de trabajo ha comenzado.");
            workTimer?.Start();  
        }

        private static void OnTimedEvent(object source, ElapsedEventArgs e) {
            if (timeLeft > 0) {
                int minutes = timeLeft / 60;
                int seconds = timeLeft % 60;
                
                Console.WriteLine($"{minutes:D2}:{seconds:D2}s left");
                timeLeft--;
            } else {
                Console.WriteLine("¡El tiempo de trabajo ha terminado!");
                workTimer?.Stop();
            }
        }


        
    }

