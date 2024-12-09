using System;
using System.IO;
using System.Timers;
using System.Threading.Tasks;
using LibVLCSharp.Shared;
using System.Media;
using System.Diagnostics;

public class Pomodoro {

        private System.Timers.Timer? workTimer;
        private int timeLeft;
        
        public Pomodoro(int min){
            timeLeft = min * 60;

            workTimer = new System.Timers.Timer(1000);
            
            #pragma warning disable CS8622 
            workTimer.Elapsed += OnTimedEvent;
            #pragma warning restore CS8622 

        }

        public void StartWork() {
            Console.WriteLine("Iniciando");
            workTimer?.Start();  
        }

        private void OnTimedEvent(object source, ElapsedEventArgs e) {
            if (timeLeft > 0) {
                int minutes = timeLeft / 60;
                int seconds = timeLeft % 60;
                
                Console.WriteLine($"{minutes:D2}:{seconds:D2}s left");
                timeLeft--;
            } else {
                Console.WriteLine("¡El tiempo de trabajo ha terminado!");
                workTimer?.Stop();
                Task.Run(() => PlaySound());
                ShowAlert();
            }
        }

        private void ShowAlert() {            
            
            Gtk.Application.Init();
            Gtk.Window myWin = new Gtk.Window("Alerta Pomodoro");
            myWin.SetDefaultSize(600, 600);
            myWin.SetPosition(Gtk.WindowPosition.Center);
            myWin.BorderWidth = 10;

            Gtk.Label myLabel = new Gtk.Label("¡Tiempo terminado!");
            myWin.Add(myLabel);

            myWin.KeepAbove = true;

            myWin.DeleteEvent += delegate { Gtk.Application.Quit(); };
            myWin.ShowAll();

            Gtk.Application.Run();            
        }

        public void PlaySound() {
            string archivoWav = "cdt-el-bananero.wav"; 
            
            if (Environment.OSVersion.Platform == PlatformID.Unix) {            
                Process.Start("aplay", archivoWav);
            }
            else if (Environment.OSVersion.Platform == PlatformID.Win32NT) {                
                Process.Start("wmplayer", archivoWav);
            }
            else {
                Console.WriteLine("Sistema no soportado para reproducción de audio.");
            }

            Console.WriteLine("Reproducción iniciada...");
            Console.ReadLine(); 
        }


        
        
    }

