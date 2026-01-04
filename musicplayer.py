import os
import tkinter as tk
from tkinter import font
import pygame
from PIL import Image, ImageTk

window = tk.Tk()
window.title("Sigma Soundpad")
window.geometry("600x750")

img = Image.open("bg1.jpg")
img = img.resize((600, 750))
bg = ImageTk.PhotoImage(img)
bgLabel = tk.Label(window, image=bg)
bgLabel.place(x=0, y=0, relwidth=1, relheight=1)

pygame.mixer.init()

label = tk.Label(window,
                 text="‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ MP3 Soundpad‎ ‎ ‎ ‎ ‎‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎ ‎  ",
                 font=("Comic Sans MS", 25, "bold"), bg="#0a58bf", fg="black", bd=0, highlightthickness=0)
label.pack(side="top", pady=10)

def play():
    selection = list.curselection()
    filename = list.get(selection[0])
    path = os.path.join(folder_path, filename)
    pygame.mixer.music.load(path)
    pygame.mixer.music.play()
    playing = True

def stop():
    pygame.mixer.music.stop()
    playing = False

button = tk.Button(window, text="Play ▶", bg="#00ff1a", font=("Calibri", 12, "bold"), command=play)
button.place(x=10, y=15, width=75, height=35)

button_1 = tk.Button(window, text="Stop ||", bg="#eb4034", font=("Calibri", 12, "bold"), command=stop)
button_1.place(x=90, y=15, width=75, height=35)

list = tk.Listbox(window, width=25, height=60, font=("Calibri", 15, "bold"), bg="#0a58bf", fg="black")
list.pack(side="left", padx=10, pady=10)
folder_path = r"C:\Users\MaksimPC\Desktop\Music"
for filename in os.listdir(folder_path):
    list.insert(tk.END, filename)

label = tk.Label(window, text=(filename), font="Calibri 14", fg="black", bg="#9bebb8")


window.mainloop()
