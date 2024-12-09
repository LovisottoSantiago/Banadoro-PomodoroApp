using System;
using System.IO;
using System.Timers;
using System.Threading.Tasks;
using LibVLCSharp.Shared;

public class Pomodoro {

        private static System.Timers.Timer? workTimer;
        private static int timeLeft;
        private static string soundFilePath = "cdt-el-bananero.mp3"; 
        
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

        private static void OnTimedEvent(object source, ElapsedEventArgs e) {
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

        private static void ShowAlert() {            
            
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

        private static void PlaySound() {
            Core.Initialize();

            using (var libVLC = new LibVLC())
            using (var mediaPlayer = new MediaPlayer(libVLC)) {
                try {
                    var media = new Media(libVLC, new Uri(Path.GetFullPath(soundFilePath)));
                    mediaPlayer.Play(media);
                    Console.WriteLine("Reproduciendo sonido...");

                    while (mediaPlayer.IsPlaying) {
                        System.Threading.Thread.Sleep(100);
                    }

                    Console.WriteLine("Reproducción finalizada.");
                }
                catch (Exception ex) {
                    Console.WriteLine($"Error al reproducir el sonido: {ex.Message}");
                }
            }
        }

        
        
    }

